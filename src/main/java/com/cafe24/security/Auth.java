package com.cafe24.security;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.METHOD;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention( RUNTIME )
@Target( { TYPE, METHOD } )
public @interface Auth {
    public enum Role { ADMIN, USER };
    public Role role() default Role.USER;
}
