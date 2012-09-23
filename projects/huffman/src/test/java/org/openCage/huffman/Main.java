//package org.openCage.huffman;
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
//public class Main {
//
//    public static void main(String[] args) {
//        byte[] in = new byte[10];
//
//        in[0] = 'a';
//        in[1] = 'a';
//        in[2] = 'a';
//        in[3] = 'a';
//        in[4] = 'a';
//        in[5] = 'a';
//        in[6] = 'a';
//        in[7] = 'b';
//        in[8] = 'b';
//        in[9] = 'b';
//
//
//        new Huffman().encode( in );
//
//
//    }
//
//    public static boolean combine( PQue pque ) {
//
//        HNode left = pque.pop();
//        HNode right = pque.pop();
//
//        pque.add( new HNode( 1001, left, right, left.weight + right.weight ));
//
//        return false;
//
//    }
//
//    public static void printBinary( HNode node, String prefix ) {
//        if ( node.left == null && node.right == null ) {
//            System.out.println( "" + node + " -> "  + prefix );
//            return;
//        }
//
//        printBinary( node.left, prefix + "0");
//        printBinary( node.right, prefix + "1");
//    }
//}
