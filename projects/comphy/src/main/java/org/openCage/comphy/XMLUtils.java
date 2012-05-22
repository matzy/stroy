package org.openCage.comphy;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 5/18/12
 * Time: 2:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class XMLUtils {

    /** Wandelt in XML-Fliesstext nicht erlaubte Zeichen in
     * XML-Entities um
     * @param body umzuwandelnder XML-String
     * @return umgewandelter XML-String
     */
    public static String escapeXmlEntities(String body) {
        if (body == null) body = "";

        StringBuffer buf = new StringBuffer(body);

        for (int i = 0; i < buf.length(); i++) {
            switch (buf.charAt(i)) {
                case '<':
                    buf.deleteCharAt(i);
                    buf.insert(i, "&lt;");
                    i += 3;

                    break;

                case '>':
                    buf.deleteCharAt(i);
                    buf.insert(i, "&gt;");
                    i += 3;

                    break;

                case '&':
                    buf.deleteCharAt(i);
                    buf.insert(i, "&amp;");
                    i += 4;

                    break;

                default:
                    break;
            }
        }

        return buf.toString();
    }


}
