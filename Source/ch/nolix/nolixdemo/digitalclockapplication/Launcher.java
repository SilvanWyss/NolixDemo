package ch.nolix.nolixdemo.digitalclockapplication;

import ch.nolix.core.environment.localcomputer.ShellProvider;
import ch.nolix.core.programcontrol.flowcontrol.GlobalFlowController;
import ch.nolix.system.application.main.Server;

final class Launcher {

  private Launcher() {
  }

  public static void main(String[] args) {

    final var server = Server.forHttpPort();

    server.addDefaultApplication(new DigitalClockApplication());

    ShellProvider.startDefaultWebBrowserOpeningLoopBackAddress();

    GlobalFlowController
      .waitForSeconds(2)
      .andThen()
      .asSoonAsNoMore(server::hasClientConnected)
      .runInBackground(server::close);
  }
}
