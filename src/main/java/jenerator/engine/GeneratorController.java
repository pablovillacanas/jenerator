package jenerator.engine;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jenerator.annotations.GenerationConstraints;
import jenerator.annotations.constraints.Constraints;
import jenerator.annotations.constraints.DecimalNumberConstraints;
import jenerator.annotations.constraints.NaturalNumberConstraints;
import jenerator.annotations.constraints.StringConstraints;
import jenerator.engine.exceptions.CoverageExceededException;
import jenerator.engine.generators.DecimalNumberGenerator;
import jenerator.engine.generators.ElementFromDocumentGenerator;
import jenerator.engine.generators.NaturalNumberGenerator;
import jenerator.engine.generators.StringGenerator;
import jenerator.engine.generators.ValueGenerator;
import jenerator.engine.generators.exceptions.NoSuitableElementsOnSource;
import jenerator.engine.parser.document.PlainDocument;
import jenerator.engine.parser.document.PlainDocumentReader;
import jenerator.filters.exceptions.NotAnnotationEncountered;
import jenerator.utils.ClassUtils;
import jenerator.utils.FieldUtils;

/**
 * <p>
 * This class populates a list of instance.
 * </p>
 * 
 * @author pablo
 *
 */
public class GeneratorController {

	private List<? extends Object> instances;
	private Class<?> actualClass;
	private Map<Method, ValueGenerator<? extends Object>> relation;

	public GeneratorController(Class<?> class1, List<? extends Object> instances) {
		this.instances = instances;
		this.actualClass = class1;
		relation = new HashMap<Method, ValueGenerator<? extends Object>>();

	}

	public void process()
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException,
			SecurityException, NotAnnotationEncountered, CoverageExceededException, FileNotFoundException, IOException, NoSuitableElementsOnSource {
		List<Field> generableFields = ClassUtils.getGenerableFields(actualClass);
		int quantity = instances.size();
		for (Field field : generableFields) {
			Method getter = FieldUtils.getterOf(field);
			ValueGenerator<? extends Object> generatedValues = createValueGenerator(quantity, field.getType(),
					FieldUtils.getConstraints(field));
			generatedValues.generate();
			relation.put(getter, generatedValues);
		}
		for (Object t : instances) {
			relation.forEach((m, vg) -> {
				try {
					// TODO simplificar buscando el tipo del parámetro del metodo
					Class<?> type = FieldUtils.fieldOf(m).getType();
					m.invoke(t, type.cast(vg.getValue()));
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
			});
		}
	}

	@SuppressWarnings({ "unchecked" })
	private <E extends Object> ValueGenerator<? extends E> createValueGenerator(long quantity, Class<E> fieldType,
			Constraints constraints) throws CoverageExceededException, FileNotFoundException, IOException {
		ValueGenerator<? extends Object> valueGenerator = null;
		if (Number.class.isAssignableFrom(fieldType)) {
			if (Long.class.isAssignableFrom(fieldType) || Integer.class.isAssignableFrom(fieldType)
					|| Short.class.isAssignableFrom(fieldType) || Byte.class.isAssignableFrom(fieldType)) {
				if (constraints.getSource().equals(GenerationConstraints.NONSOURCE)) {
					valueGenerator = new NaturalNumberGenerator<Number>(fieldType, quantity,
							(NaturalNumberConstraints) constraints);
				} else {
					// FromSource
					valueGenerator = new ElementFromDocumentGenerator<Number>((Class<Number>) fieldType, quantity,
							(NaturalNumberConstraints) constraints);
				}
			} else {
				if (constraints.getSource().equals(GenerationConstraints.NONSOURCE)) {
					valueGenerator = new DecimalNumberGenerator<Number>(fieldType, quantity,
							(DecimalNumberConstraints) constraints);
				} else {
					// FromSource
					valueGenerator = new ElementFromDocumentGenerator<Number>((Class<Number>) fieldType, quantity,
							(DecimalNumberConstraints) constraints);
				}
			}
		} else if (String.class.isAssignableFrom(fieldType)) {
			if (constraints.getSource().equals(GenerationConstraints.NONSOURCE)) {
				valueGenerator = new StringGenerator(quantity, (StringConstraints) constraints);
			} else {
				valueGenerator = new ElementFromDocumentGenerator<String>((Class<String>) fieldType, quantity,
						(StringConstraints) constraints);
			}
		}
		return (ValueGenerator<E>) valueGenerator;
	}
}
