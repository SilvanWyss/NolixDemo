package ch.nolix.nolixdemo.helloworldapplication;

import ch.nolix.system.application.main.SecureServer;

final class ProductionLauncher {
	
	public static void main(String[] args) {
		
		final var secureServer = SecureServer.forHttpsPortAndDomainAndSSLCertificateFromNolixConfiguration();
		
		secureServer.addDefaultApplication(new HelloWorldApplication());
	}
	
	private ProductionLauncher() {}
}
