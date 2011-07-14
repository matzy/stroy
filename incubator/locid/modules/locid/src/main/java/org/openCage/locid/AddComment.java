package org.openCage.locid;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

import java.io.File;
import java.io.IOException;

/**
 * Created by jmidgard.
 */
public class AddComment {

    public static void main(String[] args) {

        if ( args.length == 0 ) {
            throw new IllegalArgumentException("comment expected");
        }

        run( new File("."), args[0]);

    }


    public static void run(File file, String comment) {

        if ( file.isDirectory() ) {
            for ( File child : file.listFiles() ) {
                run( child, comment );
            }
            return;
        }

        addComment( file, comment );

    }


    public static void addComment(File song, String comment) {
        AudioFile f = null;
        try {

            System.out.println( song.getAbsolutePath());

            f = AudioFileIO.read( song );
            Tag tag = f.getTag();
            AudioHeader ah = f.getAudioHeader();

//            System.out.println(ah);
//
//            System.out.println(tag.getFirst(FieldKey.COMMENT));

            tag.setField( FieldKey.COMMENT, tag.getFirst(FieldKey.COMMENT) + " " + comment );
            f.commit();

        } catch (CannotReadException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (TagException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ReadOnlyFileException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InvalidAudioFrameException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (CannotWriteException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }
}