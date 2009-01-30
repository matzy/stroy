package org.openCage.crypt;

import org.openCage.utils.io.with.*;

import java.io.*;
import java.net.URL;
import java.net.MalformedURLException;

public class Cryptb {
    private String padfile;
    private String textfile;
    final byte[] pad = new byte[1000000];
    final byte[] text = new byte[1000000];
    final byte[] textout = new byte[1000000];
    private int textLen;
    private int padLen;
    private String outfile;

    public Cryptb( String padfile, String textfile, String outfile ) {
        this.padfile = padfile;
        this.textfile = textfile;
        this.outfile = outfile;
    }

    public void read() {

        try {
            URL padurl = new URL( padfile );
            padLen =
                    WithIO.withIS( padurl, new InputStreamFunctor<Integer>() {
                        public Integer c(InputStream is) throws IOException{
                            return is.read( pad );
                        }
                    });
        } catch (MalformedURLException e) {
            padLen =
                    WithIO.withIS( new File( padfile), new InputStreamFunctor<Integer>() {
                        public Integer c(InputStream is) throws IOException{
                            return is.read( pad );
                        }
                    });

        }


        try {
            URL texturl = new URL( textfile );
            textLen =
                    WithIO.withIS( texturl, new InputStreamFunctor<Integer>() {
                        public Integer c(InputStream reader) throws IOException{
                            return reader.read( text );
                        }
                    });
        } catch (MalformedURLException e) {
            textLen =
                    WithIO.withIS( new File(textfile), new InputStreamFunctor<Integer>() {
                        public Integer c(InputStream reader) throws IOException{
                            return reader.read( text );
                        }
                    });
        }
    }


    public static void main(String[] args) {

        if ( args.length != 3 ) {
            System.out.println("Usage: pad padFile originalFile encryptedFile");
            System.exit( 1 );
        }

        Cryptb crypt = new Cryptb( args[0], args[1], args[2] );

        crypt.read();
        crypt.encrypt();
        crypt.write();
    }

    private void write() {
        WithIO.withOS( new File(outfile), new OutputStreamFunctor<Void>() {
            public Void c( OutputStream writer) throws IOException {
                writer.write(textout, 0, textLen );
                return null;
            }
        });
    }

    private void encrypt() {
        int pdx = 1001;
        if ( pdx >= padLen ) {
            pdx = 0;
        }
        for( int idx = 0; idx < textLen; ++idx ) {

            while( pad[pdx] == 0 ) {
                pdx++;
                if ( pdx >= padLen ) {
                    pdx = 0;
                }
            }

            textout[idx] = xor( text[idx], pad[pdx ]);

            //char check = xor(textout[idx], pad[pdx]);

            pdx++;
            if ( pdx >= padLen ) {
                pdx = 0;
            }
        }
    }

//    private void show() {
//        for( int idx = 0; idx < textLen; ++idx ) {
//            System.out.println( (int)text[idx] + " -> " + (int)textout[idx]);
//        }
//    }

    private byte xor( byte a, byte b ) {
//        b &= 0x007f;
        char res = (char)(a ^ b);

//        if ( res > 127 ) {
//            return a;
//        }
//
        return (byte)res;
    }
}
