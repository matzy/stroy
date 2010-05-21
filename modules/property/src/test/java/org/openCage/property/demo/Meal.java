package org.openCage.property.demo;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: Mar 30, 2010
 * Time: 9:32:55 AM
 * To change this template use File | Settings | File Templates.
 */
public class Meal {

    private String starter;
    private String main;

    public Meal( String starter, String main ) {
        this.starter = starter;
        this.main = main;
    }

    public String getStarter() {
        return starter;
    }

    public void setStarter(String starter) {
        this.starter = starter;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "starter='" + starter + '\'' +
                ", main='" + main + '\'' +
                '}';
    }
}
