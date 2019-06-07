package jenerator.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * <p>
 * This annotation is the entry point for all classes that are wanted to being
 * generated.
 * </p>
 * 
 * @author Pablo Villacanas
 *
 */
@Retention(RUNTIME)
@Target(TYPE)
public @interface Generable {

}
