package ch.nolix.nolixdemo.serverdashboardapplication;

import ch.nolix.business.serverdashboardapplication.main.ServerDashboardApplication;
import ch.nolix.core.environment.localcomputer.ShellProvider;
import ch.nolix.nolixdemo.digitalclockapplication.DigitalClockApplication;
import ch.nolix.nolixdemo.fractalgeneratorapplication.FractalGeneratorApplication;
import ch.nolix.nolixdemo.helloworldapplication.HelloWorldApplication;
import ch.nolix.planningpoker.webapplication.main.PlanningPokerApplication;
import ch.nolix.system.application.main.Server;

final class Launcher {
	
	public static void main(String[] args) {
		
		final var server = Server.forHttpPort();
		
		final var serverDashboardApplication = ServerDashboardApplication.forServer(server);
		server.addDefaultApplication(serverDashboardApplication);
		
		server.addApplication(new HelloWorldApplication());
		server.addApplication(new DigitalClockApplication());
		server.addApplication(new FractalGeneratorApplication());
		server.addApplication(PlanningPokerApplication.withInMemoryNodeDatabase());
		
		ShellProvider.startDefaultWebBrowserOpeningLoopBackAddress();
	}
	
	private Launcher() {}
}
