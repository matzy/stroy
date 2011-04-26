package org.openCage.babelgen;

import org.openCage.generj.*;
import org.openCage.generj.Package;

import java.util.*;

import static org.openCage.generj.Call.CALL;
import static org.openCage.generj.Dot.DOT;
import static org.openCage.generj.NameExpr.NAME;
import static org.openCage.generj.NewExpr.NEW;
import static org.openCage.generj.Package.PACKAGE;
import static org.openCage.generj.Str.STR;
import static org.openCage.generj.Typ.TYP;
import static org.openCage.generj.Typ.STRING;

public class BabelGen {

    public static void main(String[] args) {

    }

    private ResourceBundle bundle;
    private String bundleLocation;


    public BabelGen( String bundleLocation ) {
        bundle = getSingleLocaleBundle( bundleLocation );
        this.bundleLocation = bundleLocation;
    }

    public BabelGen( String bundleLocation, PropertyResourceBundle prb ) {
        bundle = prb;
        this.bundleLocation = bundleLocation;
    }

    private static ResourceBundle getSingleLocaleBundle( String bundleLocation ) {
        ResourceBundle bundle = ResourceBundle.getBundle(bundleLocation, new ResourceBundle.Control() {
            @Override
            public List<Locale> getCandidateLocales(String s, Locale locale) {
                List<Locale> withRoot = new ArrayList<Locale>();
                withRoot.add( Locale.ENGLISH );
                withRoot.add( Locale.ROOT );
                return withRoot;
            }
        });

        return bundle;
    }

    public String generate( String package_, String name ) {
        Class_ newBB = new Class_( Package.valueOf(package_), TYP( name ) );
        Typ babelBundle   = newBB.import_( Package.valueOf( "org.openCage.babel"), TYP( "BabelBundle" ));
        Typ localePref    = newBB.import_( Package.valueOf( "org.openCage.babel"), TYP( "LocalePreference" ));
        Typ messageFormat = newBB.import_( Package.valueOf( "java.text"), TYP( "MessageFormat" ));

        newBB.privat_().field( babelBundle, NAME("bundle"));

        newBB.public_().cnstr().arg( localePref, NAME("lp")).body().
                assign(NAME("bundle"), NEW( babelBundle, STR(bundleLocation), NAME("lp")));

        // all keys of that locale are in the fallback
        Enumeration<String> keys = bundle.getKeys();
        while ( keys.hasMoreElements() ) {
            String key = keys.nextElement();

            String locVal = bundle.getString( key );

                // check args

            int argIdx = 0;

            while( locVal.contains("{" + argIdx + "}")) {
                ++argIdx;
            }


            if ( argIdx == 0 ) {
                newBB.public_().method( STRING, makeName( key ) ).body().
                    return_(CALL(DOT(NAME("bundle"), NAME("get")), STR( key )));
            } else {
                Method_ mes = newBB.public_().method( STRING, makeName( key ));

                for ( int idx = 0; idx < argIdx; ++idx ) {
                    mes.arg( STRING, NAME( "arg" + idx ));
                }

                Call formatting = CALL( DOT( NAME(messageFormat.toString()), NAME("format")));

                formatting.arg( CALL(DOT(NAME("bundle"), NAME("get")), STR( key )));

                for ( int idx = 0; idx < argIdx; ++idx ) {
                    formatting.arg( NAME("arg" + idx));
                }

                mes.body().return_( formatting );
            }

        }


        System.out.println(newBB.toString());

        return newBB.toString();
    }

    public static String makeName( String key ) {
        return key.replace(".", "_");
    }

}
