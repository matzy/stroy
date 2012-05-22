package org.openCage.osashosa;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Created with IntelliJ IDEA.
 * User: stephan
 * Date: 5/9/12
 * Time: 11:56 AM
 * To change this template use File | Settings | File Templates.
 */

@Target({java.lang.annotation.ElementType.PARAMETER})
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface Direct {

//    boolean optional() default false;
}
