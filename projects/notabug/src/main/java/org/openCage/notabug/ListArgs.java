package org.openCage.notabug;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;
import org.openCage.lang.functions.F1;

import java.util.Arrays;
import java.util.List;

import static org.openCage.lang.Forall.forall;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 8/8/12
 * Time: 8:25 AM
 * To change this template use File | Settings | File Templates.
 */
public class ListArgs {

    @Argument(required = true)
    private String cmd;

    @Option( required = false, name="-s")
    private String states;

    @Option( required = false, name="-id")
    private String id;

    public List<State> getStates() {
        return forall( Arrays.asList(states.split(",")) ).
                trans(new F1<State, String>() {
                    @Override
                    public State call(String o) {
                        return State.valueOf(o);
                    }
                })
                .toList();
    }

    public String getId() {
        return id;
    }

    public boolean matches( Task task ) {
        if ( states != null ) {
            if ( !getStates().contains( task.getState())) {
                return false;
            }
        }

        if ( id != null ) {
            if ( !task.getId().toString().startsWith( id )) {
                return false;
            }
        }

        return true;
    }
}
