package ch.nolix.nolixdemo.helloworldapplication;

import ch.nolix.system.application.guiapplication.BackendGUIClient;
import ch.nolix.system.application.guiapplication.BackendGUIClientSession;
import ch.nolix.system.application.main.Application;
import ch.nolix.system.application.main.Server;
import ch.nolix.system.application.main.VoidApplicationContext;
import ch.nolix.system.gui.widget.Label;
import ch.nolix.system.gui.widget.WidgetLookState;

public final class HelloWorldApplicationDemo {
	
	public static void main(String[] args) {
		
		final var server = Server.forDefaultPort();
		
		server.addDefaultApplication(new HelloWorldApplication());
	}
	
	private static final class HelloWorldApplication
	extends Application<BackendGUIClient<VoidApplicationContext>, VoidApplicationContext> {
		
		public static final String NAME = "Hello World Demo";
		
		public HelloWorldApplication() {
			super(NAME, HelloWorldSession.class, VoidApplicationContext.INSTANCE);
		}
	}
	
	private static final class HelloWorldSession extends BackendGUIClientSession<VoidApplicationContext> {
		
		@Override
		protected void initialize() {
			getRefGUI()
			.pushLayerWithRootWidget(
				new Label()
				.setText("Hello World")
				.onLook(l -> l.setTextSizeForState(WidgetLookState.BASE, 50))
			);
		}
	}
	
	private HelloWorldApplicationDemo() {}
}
