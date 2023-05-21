package ch.nolix.nolixdemo.helloworldapplication;

import ch.nolix.system.application.main.Application;
import ch.nolix.system.application.main.VoidApplicationContext;
import ch.nolix.system.application.webapplication.BackendWebClient;

public final class HelloWorldApplication extends Application<BackendWebClient<Object>, Object> {
	
	public static final String NAME = "Hello World application";
	
	public HelloWorldApplication() {
		super(VoidApplicationContext.INSTANCE);
	}
	
	@Override
	public String getApplicationName() {
		return NAME;
	}
	
	@Override
	protected Class<?> getInitialSessionClass() {
		return HelloWorldSession.class;
	}
}
