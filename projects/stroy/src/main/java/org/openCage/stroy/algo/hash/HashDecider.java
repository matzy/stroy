package org.openCage.stroy.algo.hash;

import org.openCage.kleinod.lambda.F1;
import org.openCage.lindwurm.content.Content;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 7/10/12
 * Time: 5:09 PM
 * To change this template use File | Settings | File Templates.
 */
public interface HashDecider {

    F1<Integer, String > get( Content content );
}
