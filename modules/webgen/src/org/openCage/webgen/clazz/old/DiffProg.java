//package org.openCage.wikidot.diffs;
//
//import org.openCage.wikidot.WikidotGen;
//
//import java.util.Comparator;
//
//public class DiffProg {
//
//    private final String name;
//    private String url = "";
////    private final String nic;
//    private String version = "";
//    private String licence = "?";
//    private String comment = "";
//    private boolean osx = false;
//    private boolean linux = false;
//    private boolean windows = false;
//    private boolean os9 = false;
//    private boolean os2 = false;
//    private String modified = "";
//    private boolean openstep = false;
//    private boolean vista = false;
//    private boolean javapf = false;
//    private boolean gui = false;
//    private boolean has = false;
//    private boolean worksas = false;
//    private boolean textDiff = false;
//    private boolean textMerge = false;
//    private boolean picDiff = false;
//    private boolean clt = false;
//    private boolean folderDiff = false;
//    private boolean may = false;
//    private String type = "";
//    private String special = "";
//
//    public DiffProg( String name ) {
//        this.name = name;
////        nic = name.replace( " ", "" );
//    }
//
//    public DiffProg url( String url ) {
//        this.url = url;
//        return this;
//    }
//
//    public DiffProg version( String version ) {
//        this.version = version;
//        return this;
//    }
//
//    public DiffProg licence( String licence ) {
//        this.licence = licence;
//        return this;
//    }
//
//    public void printSummery() {
//        System.out.println( WikidotGen.row( WikidotGen.link( name ) + " " + WikidotGen.externalLink( url, "->"),
//                                            licence,
//                                            platformPictures(),
//                                            getType(),
//                                            ui()));
//    }
//
//    private String getType() {
//        return type;
//    }
//
//    public DiffProg comment( String comment ) {
//        this.comment = comment;
//        return this;
//    }
//
//    public DiffProg osx() {
//        osx = true;
//        return this;
//    }
//
//    public DiffProg windows() {
//        windows = true;
//        return this;
//    }
//
//    public DiffProg linux() {
//        linux = true;
//        return this;
//    }
//
//    public DiffProg modified( String time ) {
//        this.modified = time;
//        return this;
//    }
//
//    public static Comparator<DiffProg> alphaComparator() {
//        return new Comparator<DiffProg>() {
//            public int compare( DiffProg o1, DiffProg o2 ) {
//                return  o1.name.compareToIgnoreCase( o2.name );
//            }
//        };
//    }
//
//    public DiffProg openstep() {
//        openstep = true;
//        return this;
//    }
//
//    public String platformPictures() {
//        String ret = "";
//        if ( osx ) {
//            ret += "[[image http://www.heise.de/ct/icons/shareware/macosx.gif]]";
//        }
//
//        if ( vista ) {
//            ret += "[[image http://www.heise.de/ct/icons/shareware/vista.gif]]";
//        }
//
//        if ( linux ) {
//            ret += "[[image http://www.heise.de/ct/icons/shareware/linux.gif]]";
//        }
//
//        if ( windows ) {
//            ret += "[[image http://www.heise.de/ct/icons/shareware/win.gif]]";
//        }
//
//        if ( javapf ) {
//            ret += "[[image http://www.heise.de/ct/icons/shareware/java.gif]]";
//        }
//
//        if ( os9 ) {
//            ret += "[[image http://www.heise.de/ct/icons/shareware/macos.gif]]";
//        }
//
//        if ( openstep ) {
//            ret += "OpenStep";
//        }
//
//        if ( os2 ) {
//            ret += "OS/2";
//        }
//
//        return ret;
//    }
//
//    public DiffProg vista() {
//        vista = true;
//        return this;
//    }
//
//    public DiffProg gui() {
//        gui = true;
//        return this;
//    }
//
//    public DiffProg has() {
//        has = true;
//        return this;
//    }
//
//    private String ui() {
//        if ( gui && clt ) {
//            return "[[image http://stroy.wdfiles.com/local--files/diff-central/view_left_right.png]][[image http://stroy.wdfiles.com/local--files/diff-central/terminal.png]]";
//        }
//
//        if ( gui ) {
//            return "[[image http://stroy.wdfiles.com/local--files/diff-central/view_left_right.png]]";
//        }
//
//        if ( clt ) {
//            return "[[image http://stroy.wdfiles.com/local--files/diff-central/terminal.png]]";
//        }
//
//        return "";
//    }
//
//    private String features() {
//        String ret = "";
//
//        if ( has ) {
//            ret += "<";
//        } else if ( worksas ) {
//            ret += "{";
//        }
//
//        if ( textDiff ) {
//            ret += "D ";
//        }
//
//        if ( textMerge ) {
//            ret += "M ";
//        }
//
//        if ( picDiff ) {
//            ret += "PD ";
//        }
//
//        if ( folderDiff ) {
//            ret += "f";
//        }
//
//        if ( has ) {
//            ret += ">";
//        } else if ( worksas ) {
//            ret += "}";
//        }
//        return ret;
//    }
//
//    public DiffProg textDiff() {
//        textDiff = true;
//        return this;
//    }
//
//    public DiffProg textMerge() {
//        textMerge = true;
//        return this;
//    }
//
//    public DiffProg worksas() {
//        worksas = true;
//        return this;
//    }
//
//    public DiffProg picDiff() {
//        picDiff = true;
//        return this;
//    }
//
//    public DiffProg clt() {
//        clt = true;
//        return this;
//    }
//
//    public DiffProg os9() {
//        os9 = true;
//        return this;
//    }
//
//    public DiffProg folderDiff() {
//        folderDiff = true;
//        return this;
//    }
//
//    public DiffProg javapf() {
//        javapf = true;
//        return this;
//    }
//
//    public DiffProg may() {
//        may = true;
//        return this;
//    }
//
//    public DiffProg scm() {
//        type = "SCM";
//        return this;
//    }
//
//
//    public DiffProg merge() {
//        type = "Merge";
//        return this;
//    }
//
//    public DiffProg textedit() {
//        type = "TextEditor";
//        return this;
//    }
//
//    public DiffProg diff() {
//        type = "Diff";
//        return this;
//    }
//
//    public DiffProg gpl() {
//        licence = "GPL2";
//        return this;
//    }
//
//    public DiffProg lgpl() {
//        licence = "LGPL";
//        return this;
//    }
//
//    public DiffProg scmAndMerge() {
//        type = "SCM and Merge";
//        return this;
//    }
//
//    public DiffProg scmHasMerge() {
//        type = "SCM has Merge";
//        return this;
//    }
//
//    public DiffProg mergeSync() {
//        type = "MergeSync";
//        return this;
//    }
//
//    public DiffProg free() {
//        licence = "free";
//        return this;
//    }
//
//    public DiffProg mpl() {
//        licence = "MPL1.1";
//        return this;
//    }
//
//    public DiffProg special(String s) {
//        this.special = s;
//        return this;
//    }
//
//    public DiffProg sync() {
//        type = "Sync";
//        return this;
//    }
//}
