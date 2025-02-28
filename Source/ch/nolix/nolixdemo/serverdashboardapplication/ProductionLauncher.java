package ch.nolix.nolixdemo.serverdashboardapplication;

import ch.nolix.application.serverdashboard.frontend.main.ServerDashboardApplication;
import ch.nolix.nolixdemo.digitalclockapplication.DigitalClockApplication;
import ch.nolix.nolixdemo.fractalgeneratorapplication.FractalGeneratorApplication;
import ch.nolix.planningpoker.frontend.main.PlanningPokerApplication;
import ch.nolix.system.application.main.SslServer;

final class ProductionLauncher {

  private ProductionLauncher() {
  }

  public static void main(String[] args) {

    final var sslServer = SslServer.forHttpsPortAndDomainAndSSLCertificateFromNolixConfiguration();

    sslServer.addDefaultApplication(ServerDashboardApplication.forServer(sslServer));

    sslServer.addApplication(new DigitalClockApplication());
    sslServer.addApplication(new FractalGeneratorApplication());
    sslServer.addApplication(PlanningPokerApplication.withFileNodeDatabase("planning_poker_database.spec"));
  }
}
