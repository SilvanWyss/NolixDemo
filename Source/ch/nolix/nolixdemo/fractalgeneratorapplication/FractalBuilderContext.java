package ch.nolix.nolixdemo.fractalgeneratorapplication;

import ch.nolix.systemapi.applicationapi.webapplicationapi.IWebApplicationService;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;

public final class FractalBuilderContext implements IWebApplicationService {

  public static final IImage APPLICATION_LOGO = new FractalBuilder()
    .setFunctionSumOfPredecessorPower2AndFixZ()
    .setMaxIterationCount(50)
    .setMediumSize()
    .setGreenColoring()
    .createFractalImage();

  @Override
  public IImage getApplicationLogo() {
    return APPLICATION_LOGO;
  }
}
