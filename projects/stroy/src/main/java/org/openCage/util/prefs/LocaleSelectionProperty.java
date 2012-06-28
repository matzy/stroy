//package org.openCage.util.prefs;
//
//import org.openCage.comphy.*;
//import org.openCage.util.logging.Log;
//
//import java.util.Locale;
//
//import static org.openCage.comphy.DereaderUtil.deread;
//
///**
// * Created with IntelliJ IDEA.
// * User: stephan
// * Date: 6/4/12
// * Time: 7:54 AM
// * To change this template use File | Settings | File Templates.
// */
//public class LocaleSelectionProperty extends ListSelectionProperty<PropertyOfImutable<Locale>> {
//    public LocaleSelectionProperty() {
//        list = new ListProperty<PropertyOfImutable<Locale>>( Key.valueOf("locale"), new PropertyOfImutable.ToAndFro(Locale.class));
//        list.add( new PropertyOfImutable<Locale>( Locale.getDefault()));
//        list.add( new PropertyOfImutable<Locale>( Locale.ENGLISH));
//        list.add( new PropertyOfImutable<Locale>( Locale.GERMAN));
//        list.add( new PropertyOfImutable<Locale>( Locale.JAPANESE));
//        list.add( new PropertyOfImutable<Locale>( new Locale("ES")));
//        selection = new PropertyOfImutable<Locale>( Locale.getDefault());
//    }
//
//    @Override
//    public void setSelection(PropertyOfImutable<Locale> loc) {
//        super.setSelection(loc);
//        Log.setLevel(loc.get());
//    }
//
//    public static class ToAndFro extends DereadalizerMap<LocaleSelectionProperty> {
//
//        @Override
//        public LocaleSelectionProperty fromMap(RMap map) {
//            LocaleSelectionProperty ret = new LocaleSelectionProperty();
//            ret.list = deread( new ListPropertyDereader<PropertyOfImutable<Locale>>(
//                    new PropertyOfImutable.ToAndFro(Locale.class), map.get("list"));
//            ret.setSelection( deread( new PropertyOfImutable.ToAndFro(Locale.class), map.get("selection")));
//
//            return ret;
//        }
//    }
//
//}
