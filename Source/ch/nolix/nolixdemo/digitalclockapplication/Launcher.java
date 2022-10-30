package ch.nolix.nolixdemo.digitalclockapplication;

import ch.nolix.system.application.main.Server;

final class Launcher {
	
	public static void main(String[] args) {
		
		final var server = Server.forDefaultPort();
		
		server.addDefaultApplication(new DigitalClockApplication());
	}
	
	private Launcher() {}
}
