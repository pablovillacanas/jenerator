package jenerator.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * <p>
 * Putting this annotation over a field avoids the framework to set values on
 * it. It is virtually equals to set the property of nullable of
 * CommonConstraints to one.
 * </p>
 * 
 * @author Pablo Villacanas
 *
 */
@Retention(RUNTIME)
@Target(FIELD)
public @interface NoGenerable {

}
