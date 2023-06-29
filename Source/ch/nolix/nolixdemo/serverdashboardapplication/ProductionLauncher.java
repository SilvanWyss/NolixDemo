package ch.nolix.nolixdemo.serverdashboardapplication;

import ch.nolix.business.serverdashboardapplication.main.ServerDashboardApplication;
import ch.nolix.nolixdemo.digitalclockapplication.DigitalClockApplication;
import ch.nolix.nolixdemo.fractalgeneratorapplication.FractalGeneratorApplication;
import ch.nolix.nolixdemo.helloworldapplication.HelloWorldApplication;
import ch.nolix.planningpoker.webapplication.main.PlanningPokerApplication;
import ch.nolix.system.application.main.SecureServer;

final class ProductionLauncher {
	
	public static void main(String[] args) {
		
		final var secureServer = SecureServer.forHttpsPortAndDomainAndSSLCertificateFromNolixConfiguration();
		
		secureServer.addDefaultApplication(ServerDashboardApplication.forServer(secureServer));
		
		secureServer.addApplication(new HelloWorldApplication());
		secureServer.addApplication(new DigitalClockApplication());
		secureServer.addApplication(new FractalGeneratorApplication());
		secureServer.addApplication(PlanningPokerApplication.withInMemoryNodeDatabase());
	}
	
	private ProductionLauncher() {}
}
