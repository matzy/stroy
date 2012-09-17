package org.openCage.stroy.algo.hash.str;

import org.openCage.kleinod.lambda.F1;
import org.openCage.lindwurm.content.Content;
import org.openCage.stroy.algo.hash.HashDecider;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 7/10/12
 * Time: 6:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class HashDeciderImpl implements HashDecider {
    private F1<Integer, String> whitespace = new WhitespaceIgnoringHash();

    @Override
    public F1<Integer, String> get(Content content) {
        return whitespace;
    }
}
