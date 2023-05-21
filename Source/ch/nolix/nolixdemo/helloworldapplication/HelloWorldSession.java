package ch.nolix.nolixdemo.helloworldapplication;

import ch.nolix.system.application.webapplication.BackendWebClientSession;
import ch.nolix.system.webgui.control.Label;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

public final class HelloWorldSession extends BackendWebClientSession<Object> {
	
	@Override
	protected void initialize() {
		getOriGUI().pushLayerWithRootControl(
			new Label()
			.setText("Hello World!")
			.editStyle(s -> s.setTextSizeForState(ControlState.BASE, 50))
		);
	}
}
