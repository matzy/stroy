package org.openCage.stroy.file;

import org.openCage.util.io.FileUtils;
import org.openCage.util.prefs.PreferenceBase;
import org.openCage.util.prefs.PreferenceItem;
import org.openCage.util.prefs.Preferences;
import org.openCage.util.external.ExternalProgs;
import org.openCage.util.logging.Log;
import org.openCage.stroy.ui.prefs.StandardProgUI;

import java.util.*;

/***** BEGIN LICENSE BLOCK *****
* Version: MPL 1.1
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

        if ( fileName == null ) {
            Log.warning( "getOrCreateAction called with null" );
            throw new NullPointerException( "fileName" );
        }

        String ext = FileUtils.getExtension( fileName );

        if ( get().containsKey( ext ) ) {
            return get().get( ext );
        }

        Log.warning( "found new unknown file type: " + fileName );
        get().put( ext, new Action( false, "unknown type",SimilarityAlgorithm.none, ExternalProgs.unknown, ExternalProgs.unknown ));
        setDirty();
        return get().get( ext );
    }

    private Action getOrCreateActionForExt( String ext ) {

        if ( ext == null ) {
            Log.warning( "getOrCreateActionForExt called with null" );
            throw new NullPointerException( "ext" );
        }

        if ( get().containsKey( ext ) ) {
            return get().get( ext );
        }

        Log.warning( "found new unknown file type: " + ext );
        get().put( ext, new Action( false, "unknown type",SimilarityAlgorithm.none, ExternalProgs.unknown, ExternalProgs.unknown ));
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
                ExternalProgs.STANDARD_DIFF,
                ExternalProgs.open ));


        // c
        final String [] cc = {
                "cpp", "C++ source",
                "cc",  "C++ source",
                "h",   "C/C++ header",
                "c",   "C source" };
        add( map, SimilarityAlgorithm.c, ExternalProgs.STANDARD_DIFF, ExternalProgs.open, cc );

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
        add( map, SimilarityAlgorithm.text, ExternalProgs.STANDARD_DIFF, ExternalProgs.open, text );

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
        add( map, SimilarityAlgorithm.xml, ExternalProgs.STANDARD_DIFF, ExternalProgs.open, xml );

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

        final String[] bundles = {
                "pages", "pages document",
                "key", "keynote presentation",
                "numbers", "numbers spreadsheet",
                "app", "osx application"};
        add( map, SimilarityAlgorithm.none, ExternalProgs.unknown, ExternalProgs.open, bundles );



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
        return getOrCreateActionForExt(s).description;
    }

    public void setDescription(String s, String text) {
        getOrCreateActionForExt(s).description = text;
        setDirty();
    }

    public void setDiffProg(String ext, String s) {
        getOrCreateActionForExt(ext).diff = s;
        setDirty();
    }

    public SimilarityAlgorithm getAlgo(String s) {
        return getOrCreateActionForExt(s).algo;
    }

    public void resetAlgo(String ext) {
        if ( ! getResetVal().containsKey( ext )) {
            return;
        }

        get().get(ext).algo = getResetVal().get(ext).algo;
        setDirty();
    }

    public void setAlgo(String ext, String val) {
        getOrCreateActionForExt(ext).algo = SimilarityAlgorithm.valueOf(val);
        setDirty();
    }

    public String getDiffType(String ext) {
        return getOrCreateActionForExt(ext).diff;
    }


    public void resetDiffProg(String ext) {
        if ( ! getResetVal().containsKey( ext )) {
            return;
        }

        get().get(ext).diff = getResetVal().get( ext ).diff;
        setDirty();
    }

    public String getOpen(String extension) {
        return getOrCreateActionForExt( extension ).open;
    }


    public void setOpenProg(String ext, String text) {
        getOrCreateActionForExt( ext ).open = text;
        setDirty();
    }

    public void resetOpenProg(String ext) {
        if ( ! getResetVal().containsKey( ext )) {
            return;
        }

        get().get(ext).open = getResetVal().get(ext).open;
        setDirty();
    }

}

