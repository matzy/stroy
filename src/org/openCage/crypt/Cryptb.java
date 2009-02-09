package org.openCage.crypt;

import org.openCage.utils.io.with.*;

import java.io.*;
import java.net.URL;
import java.net.MalformedURLException;
import static org.openCage.utils.lang.Math.xor;

public class Cryptb {
    private String padfile;
    private String textfile;
    final byte[] pad = new byte[1000000];
    final byte[] text = new byte[1000000];
    final byte[] textout = new byte[1000000];
    private int textLen;
    private int padLen;
    private String outfile;

    private BufferXor bxor = new BufferXor();

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
        bxor.xor(  text, textout, textLen, pad, padLen, 1001 );
    }


}
