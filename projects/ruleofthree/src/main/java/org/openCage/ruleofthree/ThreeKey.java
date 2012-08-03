package org.openCage.ruleofthree;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 7/31/12
 * Time: 9:37 AM
 * To change this template use File | Settings | File Templates.
 */
public class ThreeKey implements Comparable<ThreeKey> {

    private final String str;

    public ThreeKey(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return str;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ThreeKey threeKey = (ThreeKey) o;

        if (str != null ? !str.equals(threeKey.str) : threeKey.str != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return str != null ? str.hashCode() : 0;
    }

    @Override
    public int compareTo(ThreeKey threeKey) {
        return str.compareTo(threeKey.str);
    }

    public static ThreeKey valueOf(String str) {
        return new ThreeKey(str);
    }
}
