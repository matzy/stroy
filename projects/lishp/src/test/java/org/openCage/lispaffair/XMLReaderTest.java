package org.openCage.lispaffair;

import org.junit.Test;
import org.openCage.lishp.Symbol;

import java.text.ParseException;

import static junit.framework.Assert.assertNotNull;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 11/30/11
 * Time: 9:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class XMLReaderTest {

    @Test
    public void testStd() throws ParseException {
        Object obj = XMLReader.readTags();

        assertNotNull( obj );

        Lispaffair la = new Lispaffair();
        Environment env = la.getEnv();

        env.bind(Symbol.get("tags"), obj);

//        LispFormat frmt = new LispFormat();
//
//        Object ret = frmt.parseObject( "tags" );
//
//        assertEquals(1, Eval.eval( ret, env ));

        new Repl(env).repl();

    }

    public static void main(String[] args) throws ParseException {
        Object obj = XMLReader.readTags();

        assertNotNull( obj );

        Lispaffair la = new Lispaffair();
        Environment env = la.getEnv();

        env.bind(Symbol.get("tags"), obj);
        Repl repl = new Repl( env );

        repl.eval( "(set posts (tail (tail (tail (head (head tags))))))" );
        repl.eval( "(set posts2 (let (: (: x y z pp) nix) tags in pp))" );

        repl.eval( "(set pp (head (tail posts)))" );
        repl.eval( "(set tags (fct (x) (tags-rec-3 (tail x))))");
        repl.eval( "(set tags-rec (fct (x) " +
                   "            ((fct (key val tl)" +
                   "               (if (= key 'tag) " +
                   "                 (print ((. string split) val  \" \"))" +
                   "                 (tags-rec tl))) " +
                   "             (head (head x)) (head (tail (head x))) (tail x))))" );
        repl.eval( "(set has (fct (x y) (if (isNil x) () )" );

        repl.eval( "(set tags-rec-2 (fct (x) " +
                "      (let  (: (key val) tl)   x  " +
                "         in" +
                "               (if (= key 'tag) " +
                "                 (print ((. string split) val  \" \"))" +
                "                 (tags-rec-2 tl))) " +
                "         )))" );

        repl.eval( "(set tags-rec-3 (fct (x) " +
                "         (find x " +
                "           (fct (y) (let (key val) y in (= key 'tag)))" +
                "           (fct (y) (let (key val) y in (print ((. string split) val  \" \" ))))" +
                "))))" );


        repl.eval( "((. list for-each) posts tags)" );
//        LispFormat frmt = new LispFormat();
//
//        Object ret = frmt.parseObject( "tags" );
//
//        assertEquals(1, Eval.eval( ret, env ));

        repl.repl();


    }
}
