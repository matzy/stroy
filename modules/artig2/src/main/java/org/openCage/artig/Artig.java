package org.openCage.artig;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.openCage.artig.stjx.*;
import org.openCage.io.fspath.FSPath;
import org.openCage.io.fspath.FSPathBuilder;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Artig {
    private FSPath base;
    private Project project;
    private Map<String,Module> modules = new HashMap<String, Module>();
    private Project deflt;

    public static void main(String[] args) {

        AllBaseOptions cmds = new AllBaseOptions();
        CmdLineParser baseParser = new CmdLineParser(cmds);

//        if (  cmds.getArgs().size() > 0 ) {
//            String cmd = cmds.getArgs().get(0);
//
//            if ( cmd.equals( "help" )) {
//
//            } else if ( cmd.equals() )
//        }

        CmdOptions bean = new CmdOptions();
        CmdLineParser parser = new CmdLineParser(bean);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar myprogram.jar [options...] arguments...");
            parser.printUsage(System.err);
            return;
        }

        if ( bean.getArgs().isEmpty() || bean.getArgs().get(0).equals("help")) {
            System.err.println("artig: generate poms and build files from Artifact descriptions");
            parser.printUsage(System.err);
//            System.err.println("       help: this text");
//            System.err.println("       pom:  generate poms");
            System.exit(1);
        }

        FSPath path = FSPathBuilder.getPath( new File( bean.getDir() ));

        System.out.println( path );
        Artig artig = new Artig( path );


        if ( bean.getArgs().get(0).equals("maven")) {
            artig.readModules();
            artig.validate();
            artig.useDropins();
            artig.validate();

            new MavenGen( artig ).generate();

            return;          
        }


        if ( bean.getArgs().get(0).equals("ant")) {
            artig.readModules();
            artig.validate();
            artig.useDropins();
            artig.validate();

            new ElephantsGen( artig ).generate();

            return;
        }

        if ( bean.getArgs().get(0).equals("all")) {
            artig.readModules();
            artig.validate();
            artig.useDropins();
            artig.validate();

            new MavenGen( artig ).generate();
            new ElephantsGen( artig ).generate();
            new CalcDeployed(artig).generate();

            return;
        }

        if ( bean.getArgs().get(0).equals("test")) {
            artig.readModules();
            artig.validate();
            artig.useDropins();
            artig.validate();

            try {
                Process process = new ProcessBuilder( "mvn", "install").start();


                InputStream is = process.getInputStream();

                int ch;
                while ( (ch = is.read()) > -1 ) {
                    System.out.write( ch );
                }

            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            return;
        }

        if ( bean.getArgs().get(0).equals("check-for-updates")) {
            artig.readModules();
            artig.validate();
            artig.useDropins();
            artig.validate();

            try {
                Process process = new ProcessBuilder( "mvn", "versions:display-dependency-updates").start();


                InputStream is = process.getInputStream();

                int ch;
                while ( (ch = is.read()) > -1 ) {
                    System.out.write( ch );
                }

            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            return;
        }



        if ( bean.getArgs().get(0).equals("libs")) {
            artig.readModules();
            artig.validate();
            artig.useDropins();
            artig.validate();

            new Libs( artig ).copyToRepo();

        }



        if ( bean.getArgs().get(0).equals("artig")) {
            artig.readModules();
            artig.validate();
            artig.useDropins();
            artig.validate();

            CalcDeployed calc = new CalcDeployed(artig);
            calc.generate();
//            ModuleRef moduleRef = new ModuleRef();
//            moduleRef.setName( "fausterize" );
//
//            Deployed dp = calc.calc( moduleRef );
//
//            System.out.println( ToXML.toStringDeployed( "", dp ));

            return;
        }


    }

    private void useDropins() {
        for ( ArtifactRef dropinRef : project.getDropIns() ) {
            Artifact dropin = find( dropinRef );
            ArtifactRef orig = dropin.getDropInFor().getArtifactRef();

            for ( Module mod : modules.values() ) {
                replace( mod.getArtifact(), orig, dropinRef );
            }

            for ( Artifact ext : project.getExternals() ) {
                replace( ext, orig, dropinRef );
            }
            
        }
    }

    private void replace(Artifact artifact, ArtifactRef orig, ArtifactRef dropin) {
        ArrayList<ArtifactRef> newDeps = new ArrayList<ArtifactRef>();

        for ( ArtifactRef ref : artifact.getDepends() ) {
            if ( is( ref, orig )) {
                newDeps.add( dropin );
            } else {
                newDeps.add( ref );
            }
        }

        artifact.getDepends().clear();
        artifact.getDepends().addAll( newDeps );

    }

    private boolean is(ArtifactRef aa, ArtifactRef bb) {
        return /*aa.getGroupId().equals( bb.getGroupId() )  && */ aa.getName().equals( bb.getName()); // TODO optional groupId
    }

    private void validate() {

        List<String> artiNames = new ArrayList<String>();

        for ( Artifact ext : project.getExternals() ) {
            if ( artiNames.contains( ext.getName())) {
                throw new IllegalArgumentException( "name collision " + ext.getName() + " used twice in external Artifacts" );  // TODO use extra groupId
            }
            isValid( ext );
        }

        for ( Module mod : modules.values() ) {
            isValid( mod.getArtifact() );
        }
    }


    public Artig( FSPath base ) {
        this.base = base;

        System.out.println( ">>> " + Artig.class.getResource(".") ); // bad idea, does not work for jars
        System.out.println( ">>> " + Artig.class.getResource("default.artig") );

        deflt = (Project)(read( Artig.class.getResourceAsStream("default.artig")).getKind());
        for ( String name : base.toFile().list() ) {
            if ( name.endsWith( ".artig" )) {
                project = (Project)(read( base.add( name )).getKind());
                return;
            }
        }

        throw new IllegalArgumentException( "no file.artig found" );

    }

    public void readModules() {
        for (ModuleRef mod : project.getModules() ) {

            System.out.println("Reading Module Description " + mod.getName() );

            Module module = (Module)read( base.add( "modules", mod.getName(), mod.getName() + ".artig")).getKind();
            modules.put( mod.getName(), module );
            System.out.println( module );
        }
    }

    public void isValid( Artifact arti ) {
        // check artirefs

        Licence licence = findLicence( arti.getLicence());

        if ( licence  == null ) {
            throw new IllegalArgumentException( "Licence " + arti.getLicence() + " is not defined in the project" );
        }

        for ( ArtifactRef ref : arti.getDepends() ) {
            Artifact refA = find( ref);
            if ( refA == null ) {
                throw new IllegalArgumentException( "Reference " + ref + " is not defined in the project" );
            }

            Licence refLicence = findLicence( refA.getLicence() );

            if ( refLicence == null ) {
                throw new IllegalArgumentException( "Licence " + arti.getLicence() + " is not defined in the project" );
            }

            if ( !refLicence.getNegatives().isEmpty() ) {
                for (LicenceRef lref : refLicence.getNegatives() ) {
                    if ( lref.getName().equals( licence.getName() )) {
                        throw new IllegalArgumentException( "Licence " + arti.getLicence() + " can not use " + ref  );
                    }
                }
            } else if (!refLicence.getPositives().isEmpty()) {
                boolean no = true;
                for (LicenceRef lref : refLicence.getPositives() ) {
                    if ( lref.getName().equals( licence.getName() )) {
                        no = false;
                        break;
                    }
                }

                if ( no ) {
                    throw new IllegalArgumentException( "Licence " + arti.getLicence() + " can not use " + ref  );
                }
            }
            
        }

    }

    public Licence findLicence(String licence) {
        for ( Licence lic : project.getLicences()) {
            if ( lic.getName().equals( licence )) {
                return lic;
            }
        }

        for ( Licence lic : deflt.getLicences()) {
            if ( lic.getName().equals( licence )) {
                return lic;
            }
        }
        return null;
    }

    public Artifact find( ArtifactRef ref ) {
        for ( Artifact ext : project.getExternals() ) {
            if ( is( ext, ref )) {
                return ext;
            }
        }

        for ( Module mod : modules.values()) {
            if ( is( mod.getArtifact(), ref )) {
                return mod.getArtifact();
            }
        }

        return null;
    }

    public boolean isModule( ArtifactRef ref ) {
        for ( Module mod : modules.values()) {
            if ( is( mod.getArtifact(), ref )) {
                return true;
            }
        }

        return false;
    }


    public String getModuleName( ArtifactRef ref ) {
        for ( String mod : modules.keySet()) {
            if ( is( modules.get(mod).getArtifact(), ref )) {
                return mod;
            }
        }

        throw new IllegalArgumentException( "not a module " + ref );
    }

    public String getModuleName( Module ref ) {
        for ( String mod : modules.keySet()) {
            if ( modules.get(mod) == ref ) {
                return mod;
            }
        }

        throw new IllegalArgumentException( "not a module " + ref );
    }

    public static boolean is( Artifact arti, ArtifactRef ref ) {
        return arti.getName().equals( ref.getName() ) && (ref.getGroupId() != null ? arti.getGroupId().equals( ref.getGroupId()) : true );
    }

    public ArtifactDescription read( InputStream is ) {
        ArtifactDescriptionFromXML from = new ArtifactDescriptionFromXML();

        // create factory
        SAXParserFactory factory = SAXParserFactory.newInstance();

        // set it so that it is validating and namespace aware
        factory.setValidating( true );
        factory.setNamespaceAware( true );

        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse( is, from );
        }
        catch( IOException ioe ) {
//            System.err.println("Problem parsing " + path );
            ioe.printStackTrace();
        }
        catch( SAXException saxe ) {
//            System.err.println("Problem parsing " + path );
            saxe.printStackTrace();
        }
        catch( ParserConfigurationException pce ) {
            pce.printStackTrace();
        } catch ( IllegalArgumentException exp ) {
//            System.err.println("Problem parsing " + path );
            exp.printStackTrace();
        }

        return from.getGoal();

    }


    public ArtifactDescription read( FSPath path ) {
        ArtifactDescriptionFromXML from = new ArtifactDescriptionFromXML();

        // create factory
        SAXParserFactory factory = SAXParserFactory.newInstance();

        // set it so that it is validating and namespace aware
        factory.setValidating( true );
        factory.setNamespaceAware( true );

        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse( path.toFile(), from );
        }
        catch( IOException ioe ) {
            System.err.println("Problem parsing " + path );
            ioe.printStackTrace();
        }
        catch( SAXException saxe ) {
            System.err.println("Problem parsing " + path );
            saxe.printStackTrace();
        }
        catch( ParserConfigurationException pce ) {
            pce.printStackTrace();
        } catch ( IllegalArgumentException exp ) {
            System.err.println("Problem parsing " + path );
            exp.printStackTrace();
        }

        return (ArtifactDescription)from.getGoal();

    }

    public Collection<Module> getModules() {
        return modules.values();
    }

    public Project getProject() {
        return project;
    }

    public Module get(ModuleRef mod) {
        return modules.get( mod.getName() );
    }

    public FSPath getRootPath() {
        return base;
    }
}
