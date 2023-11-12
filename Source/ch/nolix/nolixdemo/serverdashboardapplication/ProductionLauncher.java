package ch.nolix.nolixdemo.serverdashboardapplication;

import ch.nolix.nolixdemo.digitalclockapplication.DigitalClockApplication;
import ch.nolix.nolixdemo.fractalgeneratorapplication.FractalGeneratorApplication;
import ch.nolix.planningpoker.webapplication.main.PlanningPokerApplication;
import ch.nolix.system.application.main.SecureServer;
import ch.nolix.tech.serverdashboardapplication.main.ServerDashboardApplication;

final class ProductionLauncher {

  private ProductionLauncher() {
  }

  public static void main(String[] args) {

    final var secureServer = SecureServer.forHttpsPortAndDomainAndSSLCertificateFromNolixConfiguration();

    secureServer.addDefaultApplication(ServerDashboardApplication.forServer(secureServer));

    secureServer.addApplication(new DigitalClockApplication());
    secureServer.addApplication(new FractalGeneratorApplication());
    secureServer.addApplication(PlanningPokerApplication.withFileNodeDatabase("planning_poker_database.spec"));
  }
}
