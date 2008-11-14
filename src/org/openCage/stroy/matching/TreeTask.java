package org.openCage.stroy.matching;

import org.openCage.stroy.tree.Noed;

public interface TreeTask extends Task<Noed>{

    public Noed getLeftRoot();
    public Noed getRightRoot();


}
