package ch.nolix.nolixdemo.fractalgeneratorapplication;

import ch.nolix.system.application.main.Server;

final class Launcher {
	
	public static void main(String[] args) {
		
		final var server = Server.forDefaultPort(true);
		
		server.addDefaultApplication(new FractalGenerator());
	}
	
	private Launcher() {}
}
