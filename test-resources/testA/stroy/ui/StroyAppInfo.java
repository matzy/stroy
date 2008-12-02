package org.openCage.stroy.ui;

import com.google.inject.Inject;
import org.openCage.util.app.AppInfo;
import org.openCage.util.app.Reference;
import org.openCage.util.app.Version;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/***** BEGIN LICENSE BLOCK *****
* Version: MPL 1.1/GPL 2.0
*
* The contents of this file are subject to the Mozilla Public License Version
* 1.1 (the "License"); you may not use this file except in compliance with
* the License. You may obtain a copy of the License at
* http://www.mozilla.org/MPL/
*
* Software distributed under the License is distributed on an "AS IS" basis,
* WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
* for the specific language governing rights and limitations under the
* License.
*
* The Original Code is stroy code.
*
* The Initial Developer of the Original Code is Stephan Pfab <openCage@gmail.com>.
* Portions created by Stephan Pfab are Copyright (C) 2006, 2007, 2008.
* All Rights Reserved.
*
* Contributor(s):
*
* Alternatively, the contents of this file may be used under the terms of
* either the GNU General Public License Version 2 or later (the "GPL"),
* in which case the provisions of the GPL are applicable instead
* of those above. If you wish to allow use of your version of this file only
* under the terms of either the GPL, and not to allow others to
* use your version of this file under the terms of the MPL, indicate your
* decision by deleting the provisions above and replace them with the notice
* and other provisions required by the GPL. If you do not delete
* the provisions above, a recipient may use your version of this file under
* the terms of any one of the MPL, the GPL.
*
***** END LICENSE BLOCK *****/

public class StroyAppInfo implements AppInfo {

    private final Version version;
    private final List<Reference> refs = new ArrayList<Reference>();
    private final List<Reference> buildRefs = new ArrayList<Reference>();


    @Inject
    public StroyAppInfo( Version version ) {
        this.version = version;

        refs.add( new Reference("MD5" ).
                    description( "Fast implementation of RSA's MD5 hash generator" ).
                    address( "mailto:sjpaavol@cc.helsinki.fi").
                    lgpl());
        refs.add( new Reference("guice" ).
                    description( "google dependency injection lib").
                    address( "http://code.google.com/p/google-guice/").
                    apache2());
        refs.add( new Reference("RandomGUID" ).
                    description( "GUID generator (Marc A. Mnich)").
                    address( "http://www.javaexchange.com/").
                    licence( "http://www.javaexchange.com/", "Open"  ));
        refs.add( new Reference("Sys" ).
                    description( "tool to help make osx behaving java apps" ).
                    address( "http://www.muchsoft.com/").
                    licence( "http://www.muchsoft.com/java/docs/index.html", "Open but" ));
        refs.add( new Reference("commons-cli" ).
                    description( "apache command line api" ).
                    address( "http://commons.apache.org/cli/").
                    apache2());
        refs.add( new Reference("mydoggy" ).
                    description( "java ui docking framework" ).
                    address( "http://mydoggy.sourceforge.net/").
                    lgpl());
        refs.add( new Reference("tablelayout" ).
                    description( "table loayout manager (used by mydoggy)" ).
                    address( "https://tablelayout.dev.java.net/").
                    licence( "https://tablelayout.dev.java.net/files/documents/3495/59349/License.txt", "Clearthought" ));
        refs.add( new Reference("DesigngridLayout 0.2" ).
                    description( "Layout with user design oriented strategies" ).
                    address( "https://designgridlayout.dev.java.net/").
                    lgpl());
        refs.add( new Reference("swing-layout" ).
                    description( "Extensions to Swing to create professional cross platform layout (used by designgridlayout)").
                    address( "https://swing-layout.dev.java.net/").
                    lgpl());
        refs.add( new Reference("xstream" ).
                    description( "serialization library" ).
                    address( "http://xstream.codehaus.org/").
                bsd());
        // TODO
        refs.add( new Reference("SwingWorker for Java 5" ).
                    description( "backported swingworker" ).
                    address( "dffgdfg").
                    lgpl());
//        refs.add( new Reference("quaqua", "java osx laf and tools", "http://www.randelshofer.ch/quaqua/download.html"));
        refs.add( new Reference("jarbundler" ).
                    description( "osx app ant task to create osx bundles" ).
                    address( "http://informagen.com/JarBundler/").
                    gpl2());

        buildRefs.add( new Reference("java 5" ).
                        description( "the language not the isle").
                    address( "http://java.sun.com/"));
        buildRefs.add( new Reference("intellij Idea 6" ).
                    description( "nice java ide" ).
                    address( "http://www.jetbrains.com/idea/"));
        buildRefs.add( new Reference("junit 3.8" ).
                    description( "unit testing framework").
                    address( "http://www.junit.org/index.htm"));
        buildRefs.add( new Reference("ant" ).
                    description( "build system" ).
                    address( "http://ant.apache.org/"));
        buildRefs.add( new Reference("osx" ).
                    description( "os" ).
                    address( "http://www.apple.com"));

    }


    public String getProgName() {
        return "Stroy";
    }

    public Collection<String> getAuthors() {
        return Collections.singleton( "Stephan Pfab");
    }

    public String getContactEmail() {
        return "openCage@gmail.com";
    }

    public String getWebpage() {
        return null;
    }

    public Collection<Reference> getReferences() {
        return refs;
    }

    public Collection<Reference> getBuildRefs() {
        return buildRefs;
    }

    public Version getVersion() {
        return version;
    }

    public String getCopyright() {
        return "Stephan Pfab, all rights reserved";
    }

    public String getDescription() {
        return "Dirdiff done right: Matches files even if content changed, renamed and moved";
    }

    public String getChangeLog() {

        // next 175
        // (F) feature
        // (r) refactor
        // (d) detail

        String ret = "";

        ret += "TODO:\n";
        ret += "      ( 71) - generate mf in ant (jar list just once)\n";
        ret += "      ( 64) + measure balance between content and location\n";
        ret += "      ( 60) + test database\n";
        ret += "      ( 58) + external diff for win\n";
        ret += "      ( 51) + std logfile\n";
        ret += "      ( 39) + total diff for 3 way\n";
        ret += "      (  7) + keyboard navigation\n";
        ret += "      ( 17) + generate fuzzyhash file for bugreport\n";
        ret += "      ( 18) + create gui for bugreport\n";
        ret += "      ( 20) + improve ruby match\n";
        ret += "      ( 33) - make button in refs work iff mailto: or http\n";
        ret += "      ( 46) + buttons for go to next/prev change\n";
        ret += "      ( 53) - fix external 3 way diff\n";
        ret += "      ( 56) + (Werner) error dialog\n";
        ret += "      ( 76) + improve progress reports, numbers\n";
        ret += "      ( 85) + finish filter by path regex\n";
        ret += "      ( 86) + preset for external diff prog (i.e. make it configurable)\n";
        ret += "      ( 90) - improve menues (e.g. dialog are not modal and don't show menu)\n";
        ret += "      ( 93) + file mode for dirs selection\n";
        ret += "      ( 94) - finish filter for paths (also when reading in)\n";
        ret += "      ( 97) + logwindow: logfile, limit text history\n";
        ret += "      (101) + add methods to break match buttons\n";
        ret += "      (102) + add methods to merging button\n";
        ret += "      (104) + special handling for more extensions\n";
        ret += "      (106) + make summery dynamic\n";
        ret += "      (107) + regex search for dir/file\n";
        ret += "      (109) + improve details\n";
        ret += "      (112) + about: add image\n";
        ret += "      (113) + more icon work\n";
        ret += "      (114) + more view concept\n";
        ret += "      (115) + robust: unreadable files dirs: test plus dirs\n";
        ret += "      (120) F store computes hashes per path\n";
        ret += "      (139) (F) (Mike) show contents before match (good for big dirs)\n";
        ret += "      (140) (-) handle default diff for all platforms\n";
        ret += "      (145) (F) skyview for dir fold\n";
        ret += "      (147) (-) skyview click does not always jump to the right file\n";
        ret += "      (153) (F) tooltip with detailed info\n";
        ret += "      (155) (F) handle osx forcs, folders as files\n";
        ret += "      (157) (F) color prefs\n";
        ret += "      (160) (d) large text file fuzzyHash needs work\n";
        ret += "      (163) (d) help lib is not univeral\n";
        ret += "      (166) (F) add standard editors text, pic ...\n";
        ret += "      (171) (F) diff modes: e.g. paths only to find moves and onlys\n";
        ret += "      (172) (F) add change value (don't missuse quality)\n";
        ret += "      (174) (F) add open with ... (needs 166)\n";

        ret += "0.8.191 -------------\n";
        ret += "      (124) (F) adapt to win\n";
        ret += "      (173) (d) generate exe (jsmooth) (>124)\n";
        ret += "      (144) (d) (>124) win help\n";


        ret += "0.8.190 -------------\n";
        ret += "      (170) (d) introduced Lazy class and refactored some Once usages\n";
        ret += "      ( 44) + create osx help (duplicate >57)\n";
        ret += "      ( 57) (F) osx help\n";
        ret += "      (143) (d) (>57) generate reference section for help\n";

        ret += "0.8.189 -------------\n";
        ret += "      (162) (d) osx help does not always work (build.xml did not clean enough/ no univeral lib)\n";
        ret += "      (164) (d) .css added to filetypes\n";
        ret += "      (165) (d) standard progs pane needs description of std open\n";
        ret += "      (168) (d) filetypes need set to standard button\n";
        ret += "      (167) (d) prefs alogrithm buttons are not wired\n";
        ret += "      (168) (d) select an treenode on startup\n";
        ret += "      (169) (i) refactoring to get rid of the idea of a source, now it's just one two ...\n";


        ret += "0.8.188 -------------\n";
        ret += "      (161) (d) (>160) prevent out mem error in FuzzyHashGenText\n";
        ret += "      (162) (d) prevent out of mem handler in loghandlerPane\n";

        ret += "0.8.187 -------------\n";
        ret += "      (159) (d) (>158) new logo in help\n";

        ret += "0.8.186 -------------\n";
        ret += "      (158) (F) my own (i.e. copyright free) icon caterpillar -> butterfly\n";

        ret += "0.8.185 -------------\n";
        ret += "      (156) (d) change background selection color to work on leopard\n";

        ret += "0.8.184 -------------\n";
        ret += "      (154) (d) (> 153) tooltip for dirs\n";


        ret += "0.8.183 -------------\n";
        ret += "      (152) (d) (> 145) skyview for dir fold start\n";

        ret += "0.8.182 -------------\n";
        ret += "      (149) (-) dir selector accepts illegal strings\n";
        ret += "      (150) (F) (Mike) dir selector should use the dir from the other field as base\n";
        ret += "      (151) (d) (>148) get_date works by default now (missing .)\n";
        ret += "      (148) (-) popup filter sometimes does not work (e.g. alienbrain get_date...)\n";

        ret += "0.8.181 -------------\n";
        ret += "      (146) (F) show current filepath\n";
        ret += "      (141) (-) handle newer (version) or corrupted prefs (duplicate >81)\n";
        ret += "      ( 81) + robust: unreadable preset\n";

        ret += "0.8.180 -------------\n";
        ret += "      (142) (d) (>57) first osx style help\n";

        ret += "0.8.179 -------------\n";
        ret += "      (138) (-) log pref works again\n";

        ret += "0.8.178 -------------\n";
        ret += "      (128) (F) configurable handling per file type\n";
        ret += "      (129) (d) (Werner) (>128) configurable diff tool\n";

        ret += "0.8.177 -------------\n";
        ret += "      (136) (d) (>117) filter removes nodes from skyview immediate \n";
        ret += "      (137) (d) (>117) filter removes nodes from tasks immediate \n";
        ret += "      (117) (F) (Werner) Filter should filter the results on the fly\n";

        ret += "0.7.176 -------------\n";
        ret += "      (135) (d) (>117) filter removes nodes from tree immediate \n";

        ret += "0.7.175 -------------\n";
        ret += "      (127) (d) (>124) win: use java dialog to get directories (explorer can't)\n";
        ret += "      (130) (d) (>128) configurable similarity algoritm\n";
        ret += "      (131) (d) (>128) configurable open setting\n";
        ret += "      (132) (d) (>128) open for OSX\n";
        ret += "      (133) (d) (>128) open for win32 (thanks Mike)\n";
        ret += "      (121) (r) prefs to prefs in openCage.utils, actually preferences)\n";
        ret += "      (119) - (Werner) default preset for filters not working (again?) (> 96) (> 121)\n";

        ret += "0.7.174 -------------\n";
        ret += "      (116) (F) (Werner) Synchronized scrolling\n";
        ret += "      ( 47) (d) show selection in window midth (> 116)\n";

        ret += "0.6.173 -------------\n";
        ret += "      (126) (d) synchronized selection (>116)\n";

        ret += "0.6.172 -------------\n";
        ret += "      (  8) - adapt to win (started) (> 124) \n";
        ret += "      (123) (d) skyview adopted to windows\n";
        ret += "      (125) (d) filepath parsing adopted to windows\n";

        ret += "0.6.171 -------------\n";
        ret += "      (122) (d) prefs to prefs in openCage.utils (> 121)\n";

        ret += "0.6.170 -------------\n";
        ret += "      (118) + (Werner) .get_date.dat to standard filter\n";

        ret += "0.6.169 -------------\n";
        ret += "      (111) + more icon work (> 113)\n";
        ret += "      (110) - summery of 3\n";
        ret += "      (108) + view concept started (> 114)\n";

        ret += "0.6.168 -------------\n";
        ret += "      ( 19) + improve C++ match\n";
        ret += "      ( 21) + improve xml match\n";
        ret += "      ( 41) + better icon, e.g. transparent background Cocoflop (> 111)\n";
        ret += "      ( 80) + robust: unreadable files dirs (> 115)\n";

        ret += "0.6.167 -------------\n";
        ret += "      ( 98) + set icon for all windows (worked already when deployed)\n";
        ret += "      (103) + added more known extensions\n";
        ret += "      (105) - improved summery (> 106)\n";

        ret += "0.6.166 -------------\n";
        ret += "      ( 96) - ignore preset not active (> 94)\n";
        ret += "      ( 78) + improve log window (> 97)\n";
        ret += "      ( 99) - menu in main window too\n";
        ret += "      ( 42) + break match/ do match methods started (> 101)\n";
        ret += "      (  2) + merge, only button (> 102)\n";

        ret += "0.6.165 -------------\n";
        ret += "      ( 91) - dir dialog: go button not enables when values are preset\n";
        ret += "      ( 92) - ignore cannot deal with unparsable patterns\n";
        ret += "      ( 95) - log not set to start preset\n";

        ret += "0.6.164 -------------\n";
        ret += "      ( 89) / upgrade to mydoggy 1.3.1 (no issue)\n";
        ret += "      ( 65) - menues work only for main frame (> 90)\n";
        ret += "      ( 79) + finish prefs (bugs left)\n";

        ret += "0.5.163 -------------\n";
        ret += "      ( 84) + presetpane buttons to open windows (used)\n";
        ret += "      ( 87) + shutdownhook to save prefs\n";
        ret += "      ( 82) + test area in filter pane\n";
        ret += "      ( 88) - log view shows not last\n";
        ret += "      (100) - handle unknown extensions: binary + warning\n";


        ret += "0.5.162 -------------\n";
        ret += "      ( 31) + prefs: prefs started (> 79)\n";
        ret += "      (  3) + filter by path regex started (> 85)\n";
        ret += "      ( 83) + preset = prefs (OSX)\n";

        ret += "0.5.161 -------------\n";
        ret += "      ( 74) + (Werner) better progress report for longrunnig diffs (>76)\n";
        ret += "      ( 77) + start general log window (> 78)\n";
        ret += "      ( 50) + duplicate (77)\n";

        ret += "0.5.160 -------------\n";
        ret += "      ( 73) + bugreport shows progname and version\n";
        ret += "      ( 75) + send feature request\n";
        ret += "      ( 72) + mailto string works with subject and body\n";
        ret += "0.5.159 -------------\n";
        ret += "      ( 68) - fix mydoggy popup layout (thanxs Jason for designgrid 0.2)\n";
        ret += "      ( 69) - redesign jar dir layout and ant\n";
        ret += "      ( 59) - douplicate (68)\n";
        ret += "0.5.158 -------------\n";
        ret += "      (  6) + show selection in skybar\n";
        ret += "0.5.157 -------------\n";
        ret += "      ( 66) - refactor colors\n";
        ret += "0.5.156 -------------\n";
        ret += "      ( 62) - (64) too much weight on content see FileDistance.java in 04\n";
        ret += "      ( 63) - oops parent never mattered\n";
// refactor
        ret += "0.5.155 -------------\n";
// refactor matching graph -> task (to many graphs )
        ret += "      ( 61) - hierarchical dir match: do match\n";
        ret += "      (  5) - match renamed java dirs\n";
        ret += "0.5.154 -------------\n";
        ret += "      ( 55) - (Werner) 2 way screen\n";
        ret += "      ( 54) - content fuzzy equal == 1 != identical\n";
        ret += "0.5.152 -------------\n";
        ret += "      ( 49) + spawn external diff/merge tool plus button\n";
        ret += "0.5.151 -------------\n";
// refactor ui again
        ret += "      (  1) + show selection diff in extra panel\n";
        ret += "      ( 52) + use docking ui\n";
        ret += "0.4.143 -------------\n";
        ret += "      ( 48) - clear the selection when showing match of only\n";
        ret += "0.4.142 -------------\n";
        ret += "      ( 43) + make show help view readonly\n";
        ret += "0.4.141 -------------\n";
        ret += "      ( 22) + split using refrences into extra panel\n";
        ret += "      ( 32) + help screen\n";
        ret += "0.4.140 -------------\n";
        ret += "      ( 13) + changelog in gui\n";
        ret += "0.4.138 -------------\n";
        ret += "      (  4) + build osx app (ant)\n";
        ret += "      ( 10) + contact address in gui\n";
        ret += "      ( 11) + copyright in gui\n";
        ret += "      ( 35) + track versions\n";
        ret += "      ( 36) + scrollpanes middled\n";
        ret += "      ( 37) + left scrollbar on left\n";
        ret += "      ( 40) + osx icon\n";
        ret += "0.3.110 -------------\n";
        ret += "      (  9) + 3 way diff\n";
        ret += "      ( 27) + skyviewbar\n";
        ret += "      ( 29) + match selection to parent of only file\n";
        ret += "      ( 45) + use color as info for changes\n";
        ret += "0.2.100 -------------\n";
        ret += "      ( 12) + gui\n";
        ret += "      ( 28) + match selection\n";
        ret += "      ( 38) + designgridlayout\n";
        ret += "0.1.070 -------------\n";
        ret += "      ( 14) + cli\n";
        ret += "      ( 15) + deploy through ant\n";
        ret += "      ( 16) + guice\n";
        ret += "      ( 23) + match duplicates\n";
        ret += "      ( 24) + match unchanged dirs\n";
        ret += "      ( 25) + match changed dirs\n";
        ret += "      ( 26) + improve match for small files\n";
        ret += "      ( 30) + ant\n";
        ret += "      ( 34) + match unchanged files with md5 (fast md5)\n";
        ret += "      ( 67) + ignore white noise in java lines\n";

        return ret;
    }


    public String toString() {
        return getChangeLog();
    }
}
