package ch.nolix.nolixdemo.helloworldapplication;

import ch.nolix.system.application.webapplication.WebClientSession;
import ch.nolix.system.webgui.control.Label;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

final class HelloWorldSession extends WebClientSession<Object> {
	
	@Override
	protected void initialize() {
		getOriGui().pushLayerWithRootControl(
			new Label()
			.setText("Hello World!")
			.editStyle(s -> s.setTextSizeForState(ControlState.BASE, 50))
		);
	}
}
