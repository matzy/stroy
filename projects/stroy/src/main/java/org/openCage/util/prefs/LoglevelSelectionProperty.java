//package org.openCage.util.prefs;
//
//import org.openCage.comphy.*;
//import org.openCage.util.logging.Log;
//
//import java.util.logging.Level;
//
//import static org.openCage.comphy.DereaderUtil.deread;
//
//public class LoglevelSelectionProperty extends ListSelectionProperty<LogLevelProperty> {
//
//    public LoglevelSelectionProperty() {
//        list = new ListProperty<LogLevelProperty>( Key.valueOf("loglevel"), new LogLevelProperty.Dereader());
//        list.add( new LogLevelProperty(Level.SEVERE));
//        list.add( new LogLevelProperty(Level.WARNING));
//        list.add( new LogLevelProperty(Level.INFO));
//        list.add( new LogLevelProperty(Level.FINE));
//        list.add( new LogLevelProperty(Level.FINER));
//        list.add( new LogLevelProperty(Level.FINEST));
//
//        selection = new LogLevelProperty(Level.INFO);
//    }
//
//    @Override
//    public void setSelection(LogLevelProperty selection) {
//        super.setSelection(selection);
//        Log.setLevel( selection.get());
//    }
//
//    public static class Dereader extends DereadalizerMap<LoglevelSelectionProperty> {
//
//        @Override
//        public LoglevelSelectionProperty fromMap(RMap map) {
//            LoglevelSelectionProperty ret = new LoglevelSelectionProperty();
//            ret.list = deread( new ListPropertyDereader<LogLevelProperty>( new LogLevelProperty.Dereader()), map.get("list"));
//            ret.setSelection( deread( new LogLevelProperty.Dereader(), map.get("selection")));
//
//            return ret;
//        }
//    }
//
//}
