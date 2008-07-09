package org.openCage.stroy.file;

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


public class Action {
    public String description;
    public SimilarityAlgorithm algo;
    public String diff;
    public String open;
//    public boolean file;
//    public boolean ignored;


    public Action( boolean ignored, String description )  {
//        file = false;
//        this.ignored = ignored;
        this.algo = SimilarityAlgorithm.none;
        this.diff = "---";
        this.open = "---";
        this.description = description;
    }



    public Action( boolean ignored, String description, SimilarityAlgorithm algo, String diff, String open ) {
//        file = true;
//        this.ignored = ignored;
        this.algo = algo;
        this.diff = diff;
        this.open = open;
        this.description = description;
    }

//    public static PreferenceItem<List<String>> getAlgoList() {
//        return PreferenceStringList.getOrCreate(
//                "stroy.algos",
//                Arrays.asList( "---", "binary", "text", "xml", "java", "c"));
//    }
//
//    public static PreferenceItem<List<String>> getDiffList() {
//        return PreferenceStringList.getOrCreate(
//                "stroy.diff",
//                Arrays.asList( "---", "text", "picture"  ));
//    }
}
