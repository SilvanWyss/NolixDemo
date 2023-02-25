package ch.nolix.nolixdemo.serverdashboardapplication;

import ch.nolix.business.serverdashboardapplication.ServerDashboardApplication;
import ch.nolix.nolixdemo.digitalclockapplication.DigitalClockApplication;
import ch.nolix.nolixdemo.fractalgeneratorapplication.FractalGenerator;
import ch.nolix.nolixdemo.helloworldapplication.HelloWorldApplicationDemo;
import ch.nolix.system.application.main.Server;

public final class Launcher {
	
	public static void main(String[] args) {
		
		final var server = Server.forDefaultPort();
		
		server.addDefaultApplication(ServerDashboardApplication.forServer(server));
		
		server.addApplication(new HelloWorldApplicationDemo.HelloWorldApplication());
		server.addApplication(new DigitalClockApplication());
		server.addApplication(new FractalGenerator());
	}
	
	private Launcher() {}
}
