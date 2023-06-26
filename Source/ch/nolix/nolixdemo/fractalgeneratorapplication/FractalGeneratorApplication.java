package ch.nolix.nolixdemo.fractalgeneratorapplication;

import ch.nolix.system.application.main.Application;
import ch.nolix.system.application.webapplication.WebClient;

public final class FractalGeneratorApplication extends Application<WebClient<Object>, Object> {
	
	public static final String NAME = "Fractal Generator";
	
	public FractalGeneratorApplication() {
		super(new FractalBuilderContext());
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
