package ch.nolix.nolixdemo.fractalgeneratorapplication;

import ch.nolix.system.application.main.SslServer;

final class ProductionLauncher {

  private ProductionLauncher() {
  }

  public static void main(String[] args) {

    final var sslServer = SslServer.forHttpsPortAndDomainAndSSLCertificateFromNolixConfiguration();

    sslServer.addDefaultApplication(new FractalGeneratorApplication());
  }
}
