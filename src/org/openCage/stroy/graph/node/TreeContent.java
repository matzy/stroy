package org.openCage.stroy.graph.node;

import org.openCage.stroy.fuzzyHash.FuzzyHash;

import java.io.File;

public interface TreeContent {

    public boolean        isLeaf();
    public void           setParent( TreeContent parent );
    public TreeContent    getParent();

    public String         getName();
    public String         getChecksum();   // ???
    public FuzzyHash      getFuzzyHash();
    public String         getType(); // ???


    // lets refactor
    public String getLocation(); // ???

    // TODO whats that here
    /**
     * Get the File if there is one
     * @return the underlaying file if there is one
     */
    public File getFile(); // ???

}
