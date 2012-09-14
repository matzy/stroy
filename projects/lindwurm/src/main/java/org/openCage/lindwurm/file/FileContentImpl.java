package org.openCage.lindwurm.file;

import org.openCage.kleinod.io.FileUtils;
import org.openCage.kleinod.type.Null;
import org.openCage.lindwurm.content.FileContent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 8/20/12
 * Time: 6:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class FileContentImpl implements FileContent {

    private static Logger LOG = Logger.getLogger( FileContentImpl.class.getName() );

    private final File file;

    public FileContentImpl(File file) {
        this.file = file;
    }

    @Override
    public File getFile() {
        return file;
    }

    @Override
    public String getName() {
        return file.getName();
    }

    @Override
    public String getType() {
        return FileUtils.getExtension(getName());
    }

    @Override
    public String getLocation() {
        throw new Error("what?");
    }

    @Override
    public InputStream getStream() {
        try {
            return new FileInputStream( file );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            LOG.warning(e.getMessage());
            return Null.of(InputStream.class);
        }
    }
}
