package ch.nolix.nolixdemo.digitalclockapplication;

import ch.nolix.system.application.main.Application;
import ch.nolix.system.application.main.VoidApplicationContext;
import ch.nolix.system.application.webapplication.BackendWebClient;

public final class DigitalClockApplication
extends Application<BackendWebClient<VoidApplicationContext>, VoidApplicationContext> {
	
	public static final String NAME = "Digital Clock";
	
	public DigitalClockApplication() {
		super(NAME, DigitalClockSession.class, VoidApplicationContext.INSTANCE);
	}
}
