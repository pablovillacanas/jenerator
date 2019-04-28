package jenerator.engine;

/**
 * <p>
 * This class must provide a minimal configuration, like if the developer is
 * going to use our annotations or Hibernate ones.
 * </p>
 * 
 * @author pablo
 *
 */
public class EngineConfiguration {

	private static EngineConfiguration engineConfiguration;

	private EngineConfiguration() {
	}

	public static EngineConfiguration getInstance() {
		if (engineConfiguration == null) {
			engineConfiguration = new EngineConfiguration();
		}
		return engineConfiguration;
	}

}
