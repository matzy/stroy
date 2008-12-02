package org.openCage.stroy.file;

import org.openCage.util.io.FileUtils;
import org.openCage.util.prefs.PreferenceBase;
import org.openCage.util.prefs.PreferenceItem;
import org.openCage.util.prefs.Preferences;
import org.openCage.util.external.ExternalProgs;
import org.openCage.stroy.ui.prefs.StandardProgUI;

import java.util.*;

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

public class FileTypes extends PreferenceBase<Map<String, Action>> {


    private static final String key = "stroy.filetypes";

    public static FileTypes create() {
        PreferenceItem item = Preferences.getItem( key );

        if ( item == null ) {

            final FileTypes newItem = new FileTypes( getInitialMap());
            Preferences.add( key, newItem );

            return newItem;
        }

        if ( item instanceof FileTypes ) {
            final FileTypes newItem = (FileTypes)item;
            newItem.setInitial( getInitialMap());
            return newItem;
        }

        throw new IllegalArgumentException( "type mismatch" );
    }


    private FileTypes(Map<String, Action> val) {
        super(val);
    }

    private Action getOrCreateAction( String fileName ) {
        String ext = FileUtils.getExtension( fileName );

        if ( get().containsKey( ext ) ) {
            return get().get( ext );
        }

        get().put( ext, new Action( false, "unknown type",SimilarityAlgorithm.none, "---", "---" ));
        setDirty();
        return get().get( ext );
    }

    public SimilarityAlgorithm getSimilarityAlgorithm( String fileName ) {
        return getOrCreateAction( fileName ).algo;
    }



    private static Map<String, Action> getInitialMap() {
        final Map<String, Action> map = new HashMap<String, Action>();

        // java
        map.put( "java", new Action(
                false,
                "java source",
                SimilarityAlgorithm.java,
                StandardProgUI.STANDARD_DIFF_TEXT,
                ExternalProgs.open ));


        // c
        final String [] cc = {
                "cpp", "C++ source",
                "cc",  "C++ source",
                "h",   "C/C++ header",
                "c",   "C source" };
        add( map, SimilarityAlgorithm.c, StandardProgUI.STANDARD_DIFF_TEXT, ExternalProgs.open, cc );

        // text
        final String[] text = {
                "text", "text",
                "txt", "text",
                "tex", "TeX document source",
                "log", "log file",
                "from", "",
                "jdl", "java data language",
                "jsp", "java server page source",
                "sh", "UNIX shell script",
                "bash", "UNIX shell script, bash dialect",
                "bashrc", "bash script run one opening a shell ",
                "php", "php source",
                "ruby", "ruby source",
                "doc", "word document",
                "properties", "java properties",
                "vcproj", "MS visual studio project description (XML)",
                "mf", "java Manifest",
                "bat", "MSDOS batch script",
                "py", "python source",
                "ini", "MSDOS properties, key=val", // key=val list
                "sln",  "MS Visual Studio solution (XML?)",
                "policy", "java plugin ??",   // java plugin ??
                "css", "Cascading Style Sheet",

                "m", "Objective c",
                "pch", "objective c || precompiled header ??"
        };
        add( map, SimilarityAlgorithm.text, StandardProgUI.STANDARD_DIFF_TEXT, ExternalProgs.open, text );

        final String[] xml = {
                "xml", "xml",
                "html", "hypertext meta language",
                "htm", "hypertext meta language",
                "ism", "",
                "dtd", "XML scheme definition",
                "jspx", "java server page in xml",
                "plist", "",
                "xls", "MS Excel data",
                "iml", "IntelliJ Idea module description",
                "ipr", "IntelliJ Idea project description",
                "iws", "" };
        add( map, SimilarityAlgorithm.xml, StandardProgUI.STANDARD_DIFF_TEXT, ExternalProgs.open, xml );

        final String[] binary = {
                "jnilib", "odx version of java jni",
                "dvi", "compiled tex document",
                "dylib", "",
                "so", "UNIX library",
                "exe", "MS Windows executable",
                "dll", "MS dynamic link library",
                "lib", "",
                "vsd", "",
                "jar", "java library file",
                "war", "java library for web",
                "tgz", "archive, tarred and gzipped",
                "tar", "archive, tar format",
                "gz", "gzipped file",
                "zip", "archive, zip format",
                "jdb", "",
                "pbproj", "", // ?? osx

                "jpg", "picture jpeg format",
                "jpeg", "picture jpeg format",
                "gif", "picture, gif format",
                "png", "picture, png format",
                "bmp", "picture, bitmap format",
                "raw", "picture, raw format (different per camera)",
                "icns", "picture, osx icon format",

                "mp3", "audio, mpeg3 format",
                "wav", "audio, wav format",

                "jude", "jude (UML tool) file",
                "pdf", "Portable Document Format (Adobe)",
                "dat", "", // ???
                "ncb", "",
                "suo", "MS Visual Studio something",   // for ms visual studio
                "pbxtree", "",
                "header", "",
                "pbxsymbols", "",
                "xcodeproj", "OSX xcode project",
                "xcode", "",
                "pbxproj", "",
        };
        add( map, SimilarityAlgorithm.none, ExternalProgs.unknown, ExternalProgs.open, binary );




        map.put( "svn", new Action( true, "subversion local information" ));
        map.put( "DS_Store", new Action( true, "OSX finder files" ));
        map.put( "class", new Action(
                true,
                "java compiled class file",
                SimilarityAlgorithm.none,
                ExternalProgs.unknown,
                ExternalProgs.unknown));
        map.put( "obj", new Action(
                true,
                "MS compile c file",
                SimilarityAlgorithm.none,
                ExternalProgs.unknown,
                ExternalProgs.unknown));
        map.put( "o", new Action(
                true,
                "UNIX compiled",
                SimilarityAlgorithm.none,
                ExternalProgs.unknown,
                ExternalProgs.unknown));
        map.put( "copyarea.db", new Action(
                true,
                "ClearCase file",
                SimilarityAlgorithm.none,
                ExternalProgs.unknown,
                ExternalProgs.unknown));
        map.put( "copyarea.dat", new Action(
                true,
                "ClearCase helper file",
                SimilarityAlgorithm.none,
                ExternalProgs.unknown,
                ExternalProgs.unknown));
        map.put( "get_date.dat", new Action(
                true,
                "Alienbrain local info file",
                SimilarityAlgorithm.none,
                ExternalProgs.unknown,
                ExternalProgs.unknown));



        return map;
    }
    private static void add( final Map<String, Action> map, final SimilarityAlgorithm algo, String diff, String open, String[] exts ) {
        for ( int idx = 0; idx < exts.length; idx += 2 ) {
            map.put(exts[idx], new Action(false, exts[idx + 1], algo, diff, open));
        }
    }

    public Set<String> getTypeList() {
        return get().keySet();
    }

    public String getDescription(String s) {
        return get().get(s).description;
    }

    public void setDescription(String s, String text) {
        get().get(s).description = text;
        setDirty();
    }

    public void setDiffProg(String ext, String s) {
        get().get(ext).diff = s;
        setDirty();
    }

    public SimilarityAlgorithm getAlgo(String s) {
        return get().get(s).algo;
    }

    public void resetAlgo(String ext) {
        get().get(ext).algo = getResetVal().get(ext).algo;
        setDirty();
    }

    public void setAlgo(String ext, String val) {
        get().get(ext).algo = SimilarityAlgorithm.valueOf(val);
        setDirty();
    }

    public String getDiffType(String ext) {
        return get().get(ext).diff;
    }


    public void resetDiffProg(String ext) {
        if ( ! getResetVal().containsKey( ext )) {
            return;
        }

        get().get(ext).diff = getResetVal().get( ext ).diff;
        setDirty();
    }

    public String getOpen(String extension) {
        return get().get( extension ).open;
    }


    public void setOpenProg(String ext, String text) {
        get().get( ext ).open = text;
        setDirty();
    }

    public void resetOpenProg(String ext) {
        get().get(ext).open = getResetVal().get(ext).open;
        setDirty();
    }

}

