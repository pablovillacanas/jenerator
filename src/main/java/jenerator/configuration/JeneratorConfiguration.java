package jenerator.configuration;

import jenerator.configuration.filters.GenerableAnnotationsFilter;
import jenerator.configuration.filters.GenerableFieldsFilter;
import jenerator.configuration.filters.GenerableAnnotationsFilter.AnnotationFilterType;
import jenerator.configuration.filters.GenerableFieldsFilter.FieldFilterType;

/**
 * <p>
 * This class must provide a minimal configuration, like if the developer is
 * going to use our annotations or Hibernate ones.
 * </p>
 * 
 * @author Pablo Villacanas
 *
 */
public class JeneratorConfiguration {

	private static JeneratorConfiguration engineConfiguration;
	private GenerableFieldsFilter generableFieldsFilter = new GenerableFieldsFilter();
	private GenerableAnnotationsFilter generableAnnotationFilter = new GenerableAnnotationsFilter();

	private JeneratorConfiguration() {
	}

	public static JeneratorConfiguration getInstance() {
		if (engineConfiguration == null) {
			engineConfiguration = new JeneratorConfiguration();
			engineConfiguration.setGenerableFieldsFilterType(FieldFilterType.EXPLICITFILTER);
			engineConfiguration.setGenerableAnnotationFilterType(AnnotationFilterType.STANDARD);
		}
		return engineConfiguration;
	}

	public GenerableFieldsFilter getGenerableFieldsFilter() {
		return generableFieldsFilter;
	}

	public void setGenerableFieldsFilterType(FieldFilterType fieldFilterType) {
		generableFieldsFilter.setFilterType(fieldFilterType);
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

}
