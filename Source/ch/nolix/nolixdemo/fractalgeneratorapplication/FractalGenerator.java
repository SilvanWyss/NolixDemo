package ch.nolix.nolixdemo.fractalgeneratorapplication;

import ch.nolix.system.application.main.Application;
import ch.nolix.system.application.main.VoidApplicationContext;
import ch.nolix.system.application.webapplication.BackendWebClient;

public final class FractalGenerator
extends Application<BackendWebClient<Object>, Object> {
	
	public static final String NAME = "Fractal Generator";
	
	public FractalGenerator() {
		super(NAME, FractalGenerationSession.class, VoidApplicationContext.INSTANCE);
	}
}
