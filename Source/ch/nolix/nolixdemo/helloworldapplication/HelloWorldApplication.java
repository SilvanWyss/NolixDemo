package ch.nolix.nolixdemo.helloworldapplication;

import ch.nolix.core.programatom.voidobject.VoidObject;
import ch.nolix.system.application.main.Application;
import ch.nolix.system.application.webapplication.WebClient;

public final class HelloWorldApplication extends Application<WebClient<Object>, Object> {

  public static final String NAME = "Hello World application";

  public HelloWorldApplication() {
    super(new VoidObject());
  }

  @Override
  public String getApplicationName() {
    return NAME;
  }

  @Override
  protected Class<?> getInitialSessionClass() {
    return HelloWorldSession.class;
  }
}
