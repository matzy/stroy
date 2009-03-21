package org.openCage.ipad;

import java.net.URL;
import java.net.MalformedURLException;
import java.io.InputStream;
import java.io.IOException;
import java.io.File;
import java.io.OutputStream;

import org.openCage.utils.io.With;
import org.openCage.lang.E1;

public class Main {
    private String padfile;
    private String textfile;
    final byte[] pad = new byte[1000000];
    final byte[] text = new byte[1000000];
    final byte[] textout = new byte[1000000];
    private int textLen;
    private int padLen;
    private String outfile;

    private BufferXor bxor = new BufferXor();

    public Main( String padfile, String textfile, String outfile ) {
        this.padfile = padfile;
        this.textfile = textfile;
        this.outfile = outfile;
    }

    public void read() {

        try {
            URL padurl = new URL( padfile );
            padLen =
                    With.withIS( padurl, new E1<Integer, InputStream>() {
                        public Integer c(InputStream is) throws IOException {
                            return is.read( pad );
                        }
                    });
        } catch (MalformedURLException e) {
            padLen =
                    With.withIS( new File( padfile), new E1<Integer, InputStream>() {
                        public Integer c(InputStream is) throws IOException{
                            return is.read( pad );
                        }
                    });

        }


        try {
            URL texturl = new URL( textfile );
            textLen =
                    With.withIS( texturl, new E1<Integer, InputStream>() {
                        public Integer c(InputStream reader) throws IOException{
                            return reader.read( text );
                        }
                    });
        } catch (MalformedURLException e) {
            textLen =
                    With.withIS( new File(textfile), new E1<Integer, InputStream>() {
                        public Integer c(InputStream reader) throws IOException{
                            return reader.read( text );
                        }
                    });
        }
    }


    public static void main(String[] args) {

        if ( args.length != 3 ) {
            System.out.println("Usage: ipad padFile originalFile encryptedFile");
            System.exit( 1 );
        }

        Main crypt = new Main( args[0], args[1], args[2] );

        crypt.read();
        crypt.encrypt();
        crypt.write();
    }

    private void write() {
        With.withOS( new File(outfile), new E1<Void, OutputStream>() {
            public Void c( OutputStream os) throws IOException {
                os.write(textout, 0, textLen );
                return null;
            }
        });
    }

    private void encrypt() {
        bxor.xor(  text, textout, textLen, pad, padLen, 1001 );
    }


}
