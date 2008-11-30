package org.openCage.stroy.algo.tree.filesystem;

import com.google.inject.BindingAnnotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

/**
 * Created by IntelliJ IDEA.
 * User: stephan
 * Date: 30.11.2008
 * Time: 21:55:41
 * To change this template use File | Settings | File Templates.
 */
@Retention( RetentionPolicy.RUNTIME)
@Target( {ElementType.FIELD, ElementType.PARAMETER})
@BindingAnnotation
public @interface FileSystem {}
