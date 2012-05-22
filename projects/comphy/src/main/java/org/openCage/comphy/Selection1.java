//package org.openCage.comphy;
//
//import com.google.inject.name.Named;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
///**
// * Created with IntelliJ IDEA.
// * User: stephan
// * Date: 5/21/12
// * Time: 6:54 PM
// * To change this template use File | Settings | File Templates.
// */
//public class Selection1 extends Property {
//
//    private List<String> list = new ArrayList<String>();
//
//    private String       selection;
//
//    public String getSelection() {
//        return selection;
//    }
//
//    /**
//     * set the selected element of the list
//     * @param selection A valid element of the list
//     */
//    public void setSelection( String selection ) {
//        if ( !list.contains( selection ) ) {
//            //Log.warning( "selection outside list: ignored" );
//            throw new IllegalArgumentException( "selection outside list"  );
//        }
//
//        this.selection = selection;
//    }
//
//
//    public List<String> getList() {
//        return Collections.unmodifiableList(list);
//    }
//
//    @Override
//    public void addObserver(Observer ob) {
//        //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    @Override
//    public Readable toReadable() {
//        return null;  //To change body of implemented methods use File | Settings | File Templates.
//    }
//}
