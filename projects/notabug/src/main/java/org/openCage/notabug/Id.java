package org.openCage.notabug;

import java.util.UUID;

public class Id {

    private String id;

    public Id(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id;
    }

    public static Id next() {
        return new Id(UUID.randomUUID().toString());
    }
}
