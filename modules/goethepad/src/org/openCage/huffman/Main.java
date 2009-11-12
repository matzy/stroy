package org.openCage.huffman;

/**
 * Created by IntelliJ IDEA.
 * User: spfab
 * Date: Nov 10, 2009
 * Time: 11:40:21 AM
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    public static void main(String[] args) {
        int[] freq = new int[257];

        String str = "Ihr naht euch wieder, schwankende Gestalten,\n" +
                "Die früh sich einst dem trüben Blick gezeigt.\n" +
                "Versuch ich wohl, euch diesmal festzuhalten?\n" +
                "Fühl ich mein Herz noch jenem Wahn geneigt?\n" +
                "Ihr drängt euch zu!  nun gut, so mögt ihr walten,\n" +
                "Wie ihr aus Dunst und Nebel um mich steigt;\n" +
                "Mein Busen fühlt sich jugendlich erschüttert\n" +
                "Vom Zauberhauch, der euren Zug umwittert.\n" +
                "\n" +
                "Ihr bringt mit euch die Bilder froher Tage,\n" +
                "Und manche liebe Schatten steigen auf;\n" +
                "Gleich einer alten, halbverklungnen Sage\n" +
                "Kommt erste Lieb und Freundschaft mit herauf;\n" +
                "Der Schmerz wird neu, es wiederholt die Klage\n" +
                "Des Lebens labyrinthisch irren Lauf,\n" +
                "Und nennt die Guten, die, um schöne Stunden\n" +
                "Vom Glück getäuscht, vor mir hinweggeschwunden.";

        PQue pq = new PQue();

        for ( int i = 0; i < str.length(); ++i ) {
            if (str.charAt( i ) + 127 < 257  ) {
                freq[ str.charAt( i ) + 127 ]++;
            }
        }

        for ( int i = 0;  i < 257; i++ ) {
              pq.push( new HNode( i-127, null, null, freq[i]));            
        }

//        for ( int i = -126; i < 126 ; ++i ) {
//            pq.push( new HNode( i, null, null, (int)(Math.random() * 10 )));
//        }

//        pq.push( new HNode( 'a', null, null, 10 ));
//        pq.push( new HNode( 'b', null, null, 20 ));
//        pq.push( new HNode( 'c', null, null, 30 ));
//
//        pq.push( new HNode( 'd', null, null, 15 ));
//        pq.push( new HNode( 'e', null, null, 30 ));


        System.out.println( pq );

        while ( pq.size() > 1 ) {
            combine( pq );
            System.out.println( pq );
        }

        printBinary( pq.pop(), "" );
        



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
