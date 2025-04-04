package ch.nolix.nolixdemo.fractalgeneratorapplication;

import ch.nolix.system.graphic.color.Color;
import ch.nolix.system.graphic.color.X11ColorCatalog;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;
import ch.nolix.tech.math.bigdecimalmath.ComplexNumber;
import ch.nolix.tech.math.bigdecimalmath.ComplexSequenceDefinedBy1Predecessor;
import ch.nolix.template.math.sequence.SequenceFactory;

final class FractalBuilder {

  private final ch.nolix.tech.math.fractal.FractalBuilder internalFractalBuilder = //
  new ch.nolix.tech.math.fractal.FractalBuilder();

  public IImage createFractalImage() {
    return internalFractalBuilder.build().toImage();
  }

  public FractalBuilder setUniqueBeigeColoring() {

    internalFractalBuilder.setColorFunction(i -> X11ColorCatalog.BEIGE);

    return this;
  }

  public FractalBuilder setBlueColoring() {

    internalFractalBuilder.setColorFunction(
      i -> Color.withRedValueAndGreenValueAndBlueValue(
        0,
        0,
        Color.MAX_COLOR_COMPONENT * i / internalFractalBuilder.getMaxIterationCount()));

    return this;
  }

  public FractalBuilder setFunctionSumOfPredecessorPower2AndFixZ() {

    internalFractalBuilder
      .setRealComponentInterval(-1.5, 1.5)
      .setImaginaryComponentInterval(-1.5, 1.5)
      .setSequenceCreator(
        z -> new ComplexSequenceDefinedBy1Predecessor(z, p -> p.getPower2().getSum(new ComplexNumber(-0.8, 0.15))));

    return this;
  }

  public FractalBuilder setFunctionSumOfPredecessarPower4AndZ() {

    internalFractalBuilder
      .setRealComponentInterval(-1.5, 1.5)
      .setImaginaryComponentInterval(-1.5, 1.5)
      .setSequenceCreator(
        z -> new ComplexSequenceDefinedBy1Predecessor(new ComplexNumber(0.0, 0.0), p -> p.getPower4().getSum(z)));

    return this;
  }

  public FractalBuilder setGreenColoring() {

    internalFractalBuilder.setColorFunction(
      i -> Color.withRedValueAndGreenValueAndBlueValue(
        0,
        Color.MAX_COLOR_COMPONENT * i / internalFractalBuilder.getMaxIterationCount(),
        0));

    return this;
  }

  public FractalBuilder setLargeSize() {

    internalFractalBuilder.setWidthInPixel(600).setHeightInPixel(600);

    return this;
  }

  public FractalBuilder setMandelbrotFunction() {

    internalFractalBuilder
      .setRealComponentInterval(-2.0, 1.0)
      .setImaginaryComponentInterval(-1.5, 1.5)
      .setSequenceCreator(SequenceFactory::createMandelbrotSequenceForIncrement);

    return this;
  }

  public FractalBuilder setMaxIterationCount(final int maxIterationCount) {

    internalFractalBuilder.setMaxIterationCount(maxIterationCount);

    return this;
  }

  public FractalBuilder setMediumSize() {

    internalFractalBuilder.setWidthInPixel(400).setHeightInPixel(400);

    return this;
  }

  public FractalBuilder setRedColoring() {

    internalFractalBuilder.setColorFunction(
      i -> Color.withRedValueAndGreenValueAndBlueValue(
        Color.MAX_COLOR_COMPONENT * i / internalFractalBuilder.getMaxIterationCount(),
        0,
        0));

    return this;
  }

  public FractalBuilder setSmallSize() {

    internalFractalBuilder.setWidthInPixel(200).setHeightInPixel(200);

    return this;
  }

  public FractalBuilder setVeryLargeSize() {

    internalFractalBuilder.setWidthInPixel(800).setHeightInPixel(800);

    return this;
  }

  public FractalBuilder setYellowColoring() {

    internalFractalBuilder.setColorFunction(
      (int i) -> {

        final var colorComponent = Color.MAX_COLOR_COMPONENT * i / internalFractalBuilder.getMaxIterationCount();

        return Color.withRedValueAndGreenValueAndBlueValue(colorComponent, colorComponent, 0);
      });

    return this;
  }
}
