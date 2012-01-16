package org.openCage.lishp;

public abstract class Function implements Fexpr {

    public final boolean expectsEvaluatedArguments() {
        return true;
    }

}
