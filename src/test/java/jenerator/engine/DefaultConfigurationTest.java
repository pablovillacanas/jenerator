package jenerator.engine;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import jenerator.Jenerator;
import jenerator.JeneratorException;
import jenerator.NoAnnotationFoo;
import jenerator.configuration.JeneratorConfiguration;
import jenerator.configuration.constraints.ConstraintsConfiguration;

public class DefaultConfigurationTest {

	JeneratorConfiguration jeneratorConfiguration = JeneratorConfiguration.getInstance();

	@Test
	public void test() throws NoSuchFieldException, SecurityException, JeneratorException {
		ConstraintsConfiguration configuration = new ConstraintsConfiguration().setMaxNaturalValue(1000)
				.setMinNaturalValue(10).setMaxDecimalValue(20).setMinDecimalValue(0).setDecimalPrecision((short) 3);
		jeneratorConfiguration.setConstraintsConfiguration(configuration);
		List<NoAnnotationFoo> nafoo = Jenerator.generate(NoAnnotationFoo.class, 10);
		Iterator<NoAnnotationFoo> iterator = nafoo.iterator();
		while (iterator.hasNext()) {
			NoAnnotationFoo next = iterator.next();
			System.out.println(next);
		}
	}
}
