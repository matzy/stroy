package org.openCage.lindwurm;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 8/21/12
 * Time: 6:41 PM
 * To change this template use File | Settings | File Templates.
 */
public interface LindwurmBuilder {

    LindenNode build( Ignore ignore, File root );
}
