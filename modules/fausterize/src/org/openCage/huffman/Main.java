package org.openCage.huffman;

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
* Portions created by Stephan Pfab are Copyright (C) 2006 - 2010.
* All Rights Reserved.
*
* Contributor(s):
***** END LICENSE BLOCK *****/
public class Main {

    public static void main(String[] args) {
//        int[] freq = new int[257];
//
//        String str = "Ihr naht euch wieder, schwankende Gestalten,\n" +
//                "Die früh sich einst dem trüben Blick gezeigt.\n" +
//                "Versuch ich wohl, euch diesmal festzuhalten?\n" +
//                "Fühl ich mein Herz noch jenem Wahn geneigt?\n" +
//                "Ihr drängt euch zu!  nun gut, so mögt ihr walten,\n" +
//                "Wie ihr aus Dunst und Nebel um mich steigt;\n" +
//                "Mein Busen fühlt sich jugendlich erschüttert\n" +
//                "Vom Zauberhauch, der euren Zug umwittert.\n" +
//                "\n" +
//                "Ihr bringt mit euch die Bilder froher Tage,\n" +
//                "Und manche liebe Schatten steigen auf;\n" +
//                "Gleich einer alten, halbverklungnen Sage\n" +
//                "Kommt erste Lieb und Freundschaft mit herauf;\n" +
//                "Der Schmerz wird neu, es wiederholt die Klage\n" +
//                "Des Lebens labyrinthisch irren Lauf,\n" +
//                "Und nennt die Guten, die, um schöne Stunden\n" +
//                "Vom Glück getäuscht, vor mir hinweggeschwunden.";
//
//        PQue pq = new PQue();
//
//        for ( int i = 0; i < str.length(); ++i ) {
//            if (str.charAt( i ) + 127 < 257  ) {
//                freq[ str.charAt( i ) + 127 ]++;
//            }
//        }
//
//        for ( int i = 0;  i < 257; i++ ) {
//              pq.push( new HNode( i-127, null, null, freq[i]));
//        }
//
////        for ( int i = -126; i < 126 ; ++i ) {
////            pq.push( new HNode( i, null, null, (int)(Math.random() * 10 )));
////        }
//
////        pq.push( new HNode( 'a', null, null, 10 ));
////        pq.push( new HNode( 'b', null, null, 20 ));
////        pq.push( new HNode( 'c', null, null, 30 ));
////
////        pq.push( new HNode( 'd', null, null, 15 ));
////        pq.push( new HNode( 'e', null, null, 30 ));
//
//
//        System.out.println( pq );
//
//        while ( pq.size() > 1 ) {
//            combine( pq );
//            System.out.println( pq );
//        }
//
//        printBinary( pq.pop(), "" );
//
//        BitArray ba = new BitArray( 17 );
//        ba.set( 2 );
//        ba.set( 16 );
//
//        System.out.println(ba);
//
//
//        DynamicBitArray dba = new DynamicBitArray();
//
//        dba.append( false );
//        dba.append( false );
//        dba.append( true );
//        dba.append( false );
//        dba.append( false );
//        dba.append( false );
//        dba.append( false );
//        dba.append( true );
//        dba.append( false );
//        dba.append( false );
//
//        System.out.println( dba );

        byte[] in = new byte[10];

        in[0] = 'a';
        in[1] = 'a';
        in[2] = 'a';
        in[3] = 'a';
        in[4] = 'a';
        in[5] = 'a';
        in[6] = 'a';
        in[7] = 'b';
        in[8] = 'b';
        in[9] = 'b';

        new Huffman().encode( in );

    }

    public static boolean combine( PQue pque ) {

        HNode left = pque.pop();
        HNode right = pque.pop();

        pque.push( new HNode( 1001, left, right, left.weight + right.weight ));

        return false;

    }

    public static void printBinary( HNode node, String prefix ) {
        if ( node.left == null && node.right == null ) {
            System.out.println( "" + node + " -> "  + prefix );
            return;
        }

        printBinary( node.left, prefix + "0");
        printBinary( node.right, prefix + "1");
    }
}
