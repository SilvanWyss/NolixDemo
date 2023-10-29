package ch.nolix.nolixdemo.digitalclockapplication;

import ch.nolix.system.application.main.SecureServer;

final class ProductionLauncher {

  public static void main(String[] args) {

    final var secureServer = SecureServer.forHttpsPortAndDomainAndSSLCertificateFromNolixConfiguration();

    secureServer.addDefaultApplication(new DigitalClockApplication());
  }

  private ProductionLauncher() {
  }
}
