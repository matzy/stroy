//package other.org.openCage.ui;
//
//import com.google.inject.Provider;
//import org.junit.Ignore;
//import org.openCage.lang.artifact.Artifact;
//import org.openCage.lang.artifact.Project;
//
///**
// * Created by IntelliJ IDEA.
// * User: stephan
// * Date: Apr 23, 2010
// * Time: 10:20:09 PM
// * To change this template use File | Settings | File Templates.
// */
//@Ignore
//public class TestArtiProvider implements Provider<Artifact> {
//    @Override
//    public Artifact get() {
//        Project proj = Project.get( "testing" );
//
//        System.out.println( TestArtiProvider.class.getResource( ".").toString());
//
//        return proj.module( getClass(), "openCage", "ui-testing" ).
//                version("0.7.3").
//                author( proj.author( "Jeng Duh" )).
//                descriptionShort("all about testing").
//                contributer( proj.author( "i wish smith" )).
//                iconResourcePath( "bogen1.jpg" );
//    }
//}
