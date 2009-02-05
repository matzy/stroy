package org.openCage.crypt;

import org.openCage.utils.io.with.WithIO;
import org.openCage.utils.io.with.ReaderFunctor;
import org.openCage.utils.io.with.WriterFunctor;

import java.io.Reader;
import java.io.IOException;
import java.io.Writer;

public class Crypt {
    private String padfile;
    private String textfile;
    final char[] pad = new char[1000000];
    final char[] text = new char[1000000];
    final char[] textout = new char[1000000];
    private int textLen;
    private int padLen;
    private String outfile;

    public Crypt( String padfile, String textfile, String outfile ) {
        this.padfile = padfile;
        this.textfile = textfile;
        this.outfile = outfile;
    }

    public void read() {

        padLen =
                WithIO.withReader( padfile, new ReaderFunctor<Integer>() {
                    public Integer c(Reader reader) throws IOException{
                        return reader.read( pad );
                    }
                });


        textLen =
                WithIO.withReader( textfile, new ReaderFunctor<Integer>() {
                    public Integer c(Reader reader) throws IOException{
                        return reader.read( text );
                    }
                });
    }


    public static void main(String[] args) {

        if ( args.length != 3 ) {
            System.out.println("Usage: pad padFile originalFile encryptedFile");
            System.exit( 1 );
        }

        Crypt crypt = new Crypt( args[0], args[1], args[2] );

        crypt.read();
        crypt.encrypt();
        crypt.write();
    }

    private void write() {
        WithIO.withWriter( outfile, new WriterFunctor() {
            public void c(Writer writer) throws IOException {
                writer.write(textout, 0, textLen );
            }
        });
    }

    private void encrypt() {
        int pdx = 0;
        for( int idx = 0; idx < textLen; ++idx ) {

            while( pad[pdx] == 0 ) {
                pdx++;
                if ( pdx >= padLen ) {
                    pdx = 0;
                }
            }

            textout[idx] = xor( text[idx], pad[pdx ]);

            char check = xor(textout[idx], pad[pdx]);

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

    private char xor( char a, char b ) {
        b &= 0x007f;
        char res = (char)(a ^ b);

        if ( res > 127 ) {
            return a;
        }

        return res;
    }
}
