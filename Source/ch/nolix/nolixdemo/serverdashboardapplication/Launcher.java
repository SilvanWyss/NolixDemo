package ch.nolix.nolixdemo.serverdashboardapplication;

import ch.nolix.business.serverdashboard.ServerDashboard;
import ch.nolix.nolixdemo.digitalclockapplication.DigitalClockApplication;
import ch.nolix.nolixdemo.helloworldapplication.HelloWorldApplicationDemo;
import ch.nolix.system.application.main.Server;

public final class Launcher {
	
	public static void main(String[] args) {
		
		final var server = Server.forDefaultPort();
		
		server.addDefaultApplication(ServerDashboard.forServer(server));
		
		server.addApplication(
			new HelloWorldApplicationDemo.HelloWorldApplication(),
			new DigitalClockApplication()
		);
	}
	
	private Launcher() {}
}
