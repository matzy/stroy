package org.openCage.gpad;

import com.sun.istack.internal.NotNull;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openCage.lang.protocol.BackgroundExecutor;
import org.openCage.lang.protocol.FE0;
import org.openCage.lang.protocol.FE1;
import org.openCage.localization.protocol.Localize;
import org.openCage.withResource.protocol.FileLineIterable;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.net.URI;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Jan 21, 2010
 * Time: 9:16:37 AM
 * To change this template use File | Settings | File Templates.
 */
public class UI2File {

    private final JTextArea               area;
    private final BackgroundExecutor      executor;
    private final TextEncoderIdx<String>  textEncoder  = new FaustString();
    private final Localize                localize;

    private File                     file;
    private URI                      pad;
    private boolean                  encoded;

    public UI2File( @NotNull JTextArea area,
                    @NotNull BackgroundExecutor executor,
                    @NotNull Localize localize,
                    @NotNull File file ) {
        this.area = area;
        this.executor = executor;
        this.localize = localize;
        this.file = file;

        executor.addPeriodicAndExitTask( new FE0<Void>() {
            @Override
            public Void call() throws Exception {

                write();
                return null;
            }
        });

        setInitialText();

    }

    private void setInitialText() {

        if ( file.exists() ) {
            try {
                area.append( FileUtils.readFileToString( file ));
                encoded = true;
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

            return;
        }

        area.append( localize.localize("org.openCage.fausterize.intro"));
        encoded = false;
    }


    public synchronized void codeToggle() {

        if ( pad == null ) {
            return;
        }

        if ( encoded ) {
            area.setText( textEncoder.decode( area.getText(), 0 ));
            encoded = false;
        } else {
            area.setText( textEncoder.encode( area.getText(), 0 ));
            encoded = true;
        }
    }

    private boolean canWrite() {
        return file != null & pad != null ;
    }

    private synchronized void write() {
        if ( canWrite() ) {
            if ( encoded ) {
                try {
                    FileUtils.writeStringToFile( file, area.getText() );
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            } else {
                try {
                    FileUtils.writeStringToFile( file, textEncoder.encode( area.getText(), 0 ));
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        }
    }


    public void setFile( @NotNull File file ) {
        this.file = file;
    }

    public void setPad( @NotNull URI pad ) {
        this.pad  = pad;
        textEncoder.setPad( pad);
    }

    public boolean isPadSet() {
        return pad != null;
    }


    public boolean isEncoded() {
        return encoded;
    }
}
