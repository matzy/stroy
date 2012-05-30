package org.openCage.comphy;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 5/30/12
 * Time: 6:39 AM
 * To change this template use File | Settings | File Templates.
 */
public class ReadableToXMLTest {

    @Test
    public void testAlpha() {
        RMap map = new RMap();
        map.put(Key.valueOf("333"), new RString("aaa"));
        map.put(Key.valueOf("111"), new RString("bbb"));
        map.put(Key.valueOf("222"), new RString("ccc"));

        assertEquals(
                "<comphy>\n" +
                "  <111>bbb</111>\n" +
                "  <222>ccc</222>\n" +
                "  <333>aaa</333>\n" +
                "</comphy>\n",
                new ReadableToXML().write("comphy", map).toString());

    }
}
