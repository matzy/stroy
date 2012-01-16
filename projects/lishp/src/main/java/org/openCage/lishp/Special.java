package org.openCage.lishp;

public abstract class Special implements Fexpr {
    public final boolean expectsEvaluatedArguments() {
        return false;
    }
}
