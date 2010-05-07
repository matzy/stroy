package org.openCage.lang.artifact;

public abstract class Version implements Comparable<Version>{

    public abstract String getShort();
    public abstract String getFull();

    public static Version valueOf( String str  ) {

        try {
            Version version = MMPVersion.parse( str );

            if ( version != null ) {
                return version;
            }
        } catch ( Exception exp ) {
            return new OtherVersion( str );
        }


        return new OtherVersion( str );
    }

    public abstract int getBuildnumber();
}
