package com.jayphone.processor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by JayPhone on 2020/5/14
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.FIELD)
public @interface CompileAnnotation {
    String value();
}
