package net.leathermc.leather;

import java.lang.annotation.*;

@Retention(RetentionPolicy.CLASS)
@Target({ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.METHOD, ElementType.TYPE})
public @interface Environment {
	EnvType value();
}
