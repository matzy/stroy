package org.openCage.comphy;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 5/21/12
 * Time: 11:50 AM
 * To change this template use File | Settings | File Templates.
 */
public class ReadableTT {
    public static void main(String[] args) {

        new ReadableTT().run();

    }

    private void run() {
        PropertyStore5 ps = new PropertyStore5();

        ps.add( "B", new StringProperty( "foo"));
        ps.add( "AA", new StringProperty( "duh"));

        StringProperty sp  = ps.get("AA", new StringPropertyDereader());

        System.out.println(sp);

        ps.add( "FF", new FileType("jpg: pic ...", "image-diff"));

        ListProperty<StringProperty> tt = new ListProperty<StringProperty>("loglevels", new StringPropertyDereader());

        tt.add( new StringProperty("111"));
        tt.add( new StringProperty("222"));
        tt.add( new StringProperty("33"));

        System.out.println("---------");
        ps.add( "loglevel", tt);


        MapProperty<FileType> fileTypes = new MapProperty<FileType>();

        fileTypes.put("txt", new FileType("plain text", "standard"));
        fileTypes.put("java", new FileType("java source code", "standard"));
        fileTypes.put("class", new FileType("java compiled class", "hex diff"));

        ps.add("filetypes", fileTypes);


    }
}
