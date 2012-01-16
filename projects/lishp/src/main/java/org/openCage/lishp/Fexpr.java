package org.openCage.lishp;

import org.openCage.lispaffair.Environment;

import java.util.List;

public interface Fexpr {

    Object       apply(List<Object> lst, Environment env );
    int          argNum();
    StringBuffer format( StringBuffer toAppendTo );
    boolean      expectsEvaluatedArguments();

}
