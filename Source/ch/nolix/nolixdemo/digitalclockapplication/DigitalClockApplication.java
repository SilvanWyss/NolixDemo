package ch.nolix.nolixdemo.digitalclockapplication;

import ch.nolix.core.programatom.voidobject.VoidObject;
import ch.nolix.system.application.main.Application;
import ch.nolix.system.application.webapplication.WebClient;

public final class DigitalClockApplication extends Application<WebClient<Object>, Object> {

  public static final String NAME = "Digital Clock";

  public DigitalClockApplication() {
    super(new VoidObject());
  }

  @Override
  public String getApplicationName() {
    return NAME;
  }

  @Override
  protected Class<?> getInitialSessionClass() {
    return DigitalClockSession.class;
  }
}
