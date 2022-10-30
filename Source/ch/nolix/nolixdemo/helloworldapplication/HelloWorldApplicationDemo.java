package ch.nolix.nolixdemo.helloworldapplication;

import ch.nolix.system.application.main.Application;
import ch.nolix.system.application.main.Server;
import ch.nolix.system.application.main.VoidApplicationContext;
import ch.nolix.system.application.webapplication.BackendWebClient;
import ch.nolix.system.application.webapplication.BackendWebClientSession;
import ch.nolix.system.webgui.control.Text;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

public final class HelloWorldApplicationDemo {
	
	public static void main(String[] args) {
		
		final var server = Server.forDefaultPort();
		
		server.addDefaultApplication(new HelloWorldApplication());
	}
	
	public static final class HelloWorldApplication	extends Application<BackendWebClient<Object>, Object> {
		
		public static final String NAME = "Hello World Demo";
		
		public HelloWorldApplication() {
			super(NAME, HelloWorldSession.class, VoidApplicationContext.INSTANCE);
		}
	}
	
	public static final class HelloWorldSession extends BackendWebClientSession<Object> {
		
		@Override
		protected void initialize() {
			getRefGUI().pushLayerWithRootControl(
				new Text()
				.setText("Hello World")
				.editStyle(s -> s.setTextSizeForState(ControlState.BASE, 50))
			);
		}
	}
	
	private HelloWorldApplicationDemo() {}
}
