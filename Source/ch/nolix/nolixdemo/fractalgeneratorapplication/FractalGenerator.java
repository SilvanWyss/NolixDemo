package ch.nolix.nolixdemo.fractalgeneratorapplication;

import ch.nolix.system.application.guiapplication.BackendGUIClient;
import ch.nolix.system.application.main.Application;
import ch.nolix.system.application.main.VoidApplicationContext;

public final class FractalGenerator
extends Application<BackendGUIClient<VoidApplicationContext>, VoidApplicationContext> {
	
	public static final String NAME = "Fractal Generator";
	
	public FractalGenerator() {
		super(NAME, FractalGenerationSession.class, VoidApplicationContext.INSTANCE);
	}
}
