package jenerator.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * <p>
 * This annotation allows the generator engine to parse all its declared fields
 * and generate values for each one. If there is no annotation, object cannot be
 * generated.
 * </p>
 * <p>
 * This annotation triggers the generation for all the fields of the object but
 * annotated with {@link jenerator.annotations.NoGenereable NoGenerable}. It
 * also includes different kind of collections.
 * </p>
 * 
 * @author pablo
 *
 */
@Retention(RUNTIME)
@Target(TYPE)
public @interface Generable {

}
