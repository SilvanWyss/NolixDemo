package ch.nolix.nolixdemo.digitalclockapplication;

import ch.nolix.system.application.main.Application;
import ch.nolix.system.application.main.VoidApplicationContext;
import ch.nolix.system.application.webapplication.BackendWebClient;

public final class DigitalClockApplication extends Application<BackendWebClient<Object>, Object> {
	
	public static final String NAME = "Digital Clock";
	
	public DigitalClockApplication() {
		super(VoidApplicationContext.INSTANCE);
	}
	
	@Override
	public String getApplicationName() {
		return NAME;
	}
	
	@Override
	protected Class<?> getInitialSessionClass() {
		return DigitalClockSession.class;
	}
}
