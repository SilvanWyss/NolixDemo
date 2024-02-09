package ch.nolix.nolixdemo.serverdashboardapplication;

import ch.nolix.core.environment.localcomputer.ShellProvider;
import ch.nolix.nolixdemo.digitalclockapplication.DigitalClockApplication;
import ch.nolix.nolixdemo.fractalgeneratorapplication.FractalGeneratorApplication;
import ch.nolix.planningpoker.webapplication.main.PlanningPokerApplication;
import ch.nolix.system.application.main.Server;
import ch.nolix.tech.serverdashboardapplication.main.ServerDashboardApplication;

final class TestLauncher {

  private TestLauncher() {
  }

  public static void main(String[] args) {

    final var server = Server.forHttpPort();

    final var serverDashboardApplication = ServerDashboardApplication.forServer(server);
    server.addDefaultApplication(serverDashboardApplication);

    server.addApplication(new DigitalClockApplication());
    server.addApplication(new FractalGeneratorApplication());
    server.addApplication(PlanningPokerApplication.withFileNodeDatabase("planning_poker_database.spec"));

    ShellProvider.startDefaultWebBrowserOpeningLoopBackAddress();
  }
}
