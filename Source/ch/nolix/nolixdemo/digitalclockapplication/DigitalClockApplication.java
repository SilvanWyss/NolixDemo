package ch.nolix.nolixdemo.digitalclockapplication;

import ch.nolix.system.application.guiapplication.BackendGUIClient;
import ch.nolix.system.application.main.Application;
import ch.nolix.system.application.main.VoidApplicationContext;

public final class DigitalClockApplication
extends Application<BackendGUIClient<VoidApplicationContext>, VoidApplicationContext> {
	
	public static final String NAME = "Digital Clock";
	
	public DigitalClockApplication() {
		super(NAME, DigitalClockSession.class, VoidApplicationContext.INSTANCE);
	}
}
