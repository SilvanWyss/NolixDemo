package ch.nolix.nolixdemo.fractalgeneratorapplication;

import ch.nolix.system.application.main.Application;
import ch.nolix.system.application.main.VoidApplicationContext;
import ch.nolix.system.application.webapplication.BackendWebClient;

public final class FractalGeneratorApplication extends Application<BackendWebClient<Object>, Object> {
	
	public static final String NAME = "Fractal Generator";
	
	public FractalGeneratorApplication() {
		super(VoidApplicationContext.INSTANCE);
	}
	
	@Override
	public String getApplicationName() {
		return NAME;
	}
	
	@Override
	protected Class<?> getInitialSessionClass() {
		return FractalGenerationSession.class;
	}
}
