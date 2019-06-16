package jenerator.configuration;

import jenerator.configuration.constraints.ConstraintsConfiguration;
import jenerator.configuration.filters.GenerableAnnotationsFilter;
import jenerator.configuration.filters.GenerableAnnotationsFilter.AnnotationFilterType;

/**
 * <p>
 * This class provides a minimal configuration for diferent parameters of the
 * object generation.
 * </p>
 * 
 * @author Pablo Villacanas
 *
 */
public class JeneratorConfiguration {

	private static JeneratorConfiguration engineConfiguration;
	private GenerableAnnotationsFilter generableAnnotationFilter = new GenerableAnnotationsFilter();
	private ConstraintsConfiguration constraintsConfiguration = new ConstraintsConfiguration();

	private JeneratorConfiguration() {
	}

	public static JeneratorConfiguration getInstance() {
		if (engineConfiguration == null) {
			engineConfiguration = new JeneratorConfiguration();
			engineConfiguration.setGenerableAnnotationFilterType(AnnotationFilterType.STANDARD);
		}
		return engineConfiguration;
	}

	/**
	 * @return the generableAnnotationFilter
	 */
	public GenerableAnnotationsFilter getGenerableAnnotationFilter() {
		return generableAnnotationFilter;
	}

	/**
	 * @param filterType the generableAnnotationFilter to set
	 */
	public void setGenerableAnnotationFilterType(AnnotationFilterType filterType) {
		this.generableAnnotationFilter.setAnnotationFilterType(filterType);
	}

	/**
	 * @return the constraintsConfiguration
	 */
	public ConstraintsConfiguration getConstraintsConfiguration() {
		return constraintsConfiguration;
	}

	/**
	 * @param constraintsConfiguration the constraintsConfiguration to set
	 */
	public void setConstraintsConfiguration(ConstraintsConfiguration constraintsConfiguration) {
		this.constraintsConfiguration = constraintsConfiguration;
	}

}
