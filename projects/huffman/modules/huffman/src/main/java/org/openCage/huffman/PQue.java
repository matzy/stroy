//package org.openCage.huffman;
//
//import java.util.ArrayList;
//
///***** BEGIN LICENSE BLOCK *****
//* Version: MPL 1.1
//*
//* The contents of this file are subject to the Mozilla Public License Version
//* 1.1 (the "License"); you may not use this file except in compliance with
//* the License. You may obtain a copy of the License at
//* http://www.mozilla.org/MPL/
//*
//* Software distributed under the License is distributed on an "AS IS" basis,
//* WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
//* for the specific language governing rights and limitations under the
//* License.
//*
//* The Original Code is stroy code.
//*
//* The Initial Developer of the Original Code is Stephan Pfab <openCage@gmail.com>.
//* Portions created by Stephan Pfab are Copyright (C) 2006 - 2010.
//* All Rights Reserved.
//*
//* Contributor(s):
//***** END LICENSE BLOCK *****/
//public class PQue {
//
//    public ArrayList<HNode> que = new ArrayList<HNode>();
//
//    public int size() {
//        return que.size();
//    }
//
//    public void add(HNode node) {
//        int idx  = 0;
//        for ( HNode elem: que ) {
//            if ( elem.weight > node.weight ) {
//                que.add( idx, node );
//                return;
//            }
//            idx++;
//        }
//
//        que.add( node );
//    }
//
//    @Override
//    public String toString() {
//        String ret = "(";
//        for ( HNode node : que  ) {
//            ret += node.toString() + " ";
//        }
//        return ret + ")";
//    }
//
//    public HNode pop() {
//        HNode top = que.get(0);
//        que.remove(0);
//        return top;
//    }
//
//    public HNode peek() {
//        return que.get(0);
//    }
//}
