package org.openCage.stroy.tree.iter;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.openCage.stroy.tree.Noed;
import org.openCage.stroy.tree.NoedImpl;

import java.util.Iterator;

public class DepthFirstIterTest {

    @Test
    public void testSimple() {

        Noed root = NoedImpl.makeDirNoed( "root" );
        root.addChild( NoedImpl.makeDirNoed( "A" ));
        Noed B = NoedImpl.makeDirNoed( "B" );
        B.addChild( NoedImpl.makeDirNoed( "B.1" ));
        B.addChild( NoedImpl.makeDirNoed( "B.2" ));
        root.addChild( B );
        root.addChild( NoedImpl.makeDirNoed( "C" ));

        Iterator<Noed> iter = new DepthFirstIterator(root);

        assertEquals( "root", iter.next().getName() );
        assertEquals( "A", iter.next().getName() );
        assertEquals( "B", iter.next().getName() );
        assertEquals( "B.1", iter.next().getName() );
        assertEquals( "B.2", iter.next().getName() );
        assertEquals( "C", iter.next().getName() );

    }
}
