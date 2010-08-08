package org.openCage.stjx;

/**
* Created by IntelliJ IDEA.
* User: stephan
* Date: Aug 8, 2010
* Time: 2:37:17 AM
* To change this template use File | Settings | File Templates.
*/
public class Ref {
 private String name;
 private boolean optional;

 public Ref( String name, boolean optional ) {
     this.name = name;
     this.optional = optional;
 }

 public static Ref optional( String name ) {
     return new Ref( name, true );
 }

 public static Ref required( String name ) {
     return new Ref( name, false );
 }

 public String getName() {
     return name;
 }

 public String toJava() {
     return Stjx.toJavaBeanAttribute( name, Stjx.toFirstLower( name ));
 }

    public boolean isOptional() {
        return optional;
    }
}
