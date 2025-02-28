package ch.nolix.nolixdemo.fractalgeneratorapplication;

import ch.nolix.system.application.webapplication.WebClientSession;
import ch.nolix.system.webgui.atomiccontrol.button.Button;
import ch.nolix.system.webgui.atomiccontrol.imagecontrol.ImageControl;
import ch.nolix.system.webgui.atomiccontrol.label.Label;
import ch.nolix.system.webgui.container.Grid;
import ch.nolix.system.webgui.itemmenu.dropdownmenu.DropdownMenu;
import ch.nolix.system.webgui.linearcontainer.VerticalStack;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.buttonapi.IButton;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.imagecontrolapi.IImageControl;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.labelapi.LabelRole;
import ch.nolix.systemapi.webguiapi.basecontainerapi.ContainerRole;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.template.webgui.dialog.WaitDialogBuilder;
import ch.nolix.template.webgui.style.StyleCatalog;

final class FractalGenerationSession extends WebClientSession<Object> {

  private static final IImage DEFAULT_FRACTAL_IMAGE = new FractalBuilder()
    .setMandelbrotFunction()
    .setMaxIterationCount(30)
    .setSmallSize()
    .setUniqueBeigeColoring()
    .createFractalImage();

  private final IButton generateFractalImageButton = new Button().setText("Generate")
    .setLeftMouseButtonPressAction(this::startRegenerateFractalImage);

  private final FractalBuilder fractalBuilder = new FractalBuilder();

  private final IImageControl fractalImageControl = new ImageControl().setImage(DEFAULT_FRACTAL_IMAGE);

  @Override
  protected void initialize() {
    getStoredGui()
      .pushLayerWithRootControl(createMainControl())
      .setStyle(StyleCatalog.DARK_STYLE);
  }

  private IControl<?, ?> createMainControl() {
    return new VerticalStack()
      .setRole(ContainerRole.OVERALL_CONTAINER)
      .addControl(createTitleControl(), createConfigurationControl(), fractalImageControl);
  }

  private IControl<?, ?> createTitleControl() {
    return new Label().setRole(LabelRole.TITLE).setText(getApplicationName());
  }

  private IControl<?, ?> createConfigurationControl() {
    return new VerticalStack().addControl(createConfigurationWidet(), generateFractalImageButton);
  }

  private IControl<?, ?> createConfigurationWidet() {
    return new Grid()
      .insertTextAtRowAndColumn(1, 1, "Function")
      .insertControlAtRowAndColumn(
        1,
        2,
        new DropdownMenu()
          .addItemWithTextAndSelectAction("p->p^2+z", fractalBuilder::setMandelbrotFunction)
          .addItemWithTextAndSelectAction("p->p^2-0.8+0.15i", fractalBuilder::setFunctionSumOfPredecessorPower2AndFixZ)
          .addItemWithTextAndSelectAction("p->p^4+z", fractalBuilder::setFunctionSumOfPredecessarPower4AndZ)
          .selectFirstItem())
      .insertTextAtRowAndColumn(1, 5, "Maximal iteration count")
      .insertControlAtRowAndColumn(
        1,
        6,
        createMaxIterationCountDropdownMenu())
      .insertTextAtRowAndColumn(2, 1, "Size")
      .insertControlAtRowAndColumn(
        2,
        2,
        new DropdownMenu()
          .addItemWithTextAndSelectAction("Small", fractalBuilder::setSmallSize)
          .addItemWithTextAndSelectAction("Medium", fractalBuilder::setMediumSize)
          .addItemWithTextAndSelectAction("Large", fractalBuilder::setLargeSize)
          .addItemWithTextAndSelectAction("Very large", fractalBuilder::setVeryLargeSize)
          .selectFirstItem())
      .insertTextAtRowAndColumn(2, 5, "Coloring")
      .insertControlAtRowAndColumn(
        2,
        6,
        new DropdownMenu()
          .addItemWithTextAndSelectAction("Unique beige", fractalBuilder::setUniqueBeigeColoring)
          .addItemWithTextAndSelectAction("Blue", fractalBuilder::setBlueColoring)
          .addItemWithTextAndSelectAction("Green", fractalBuilder::setGreenColoring)
          .addItemWithTextAndSelectAction("Red", fractalBuilder::setRedColoring)
          .addItemWithTextAndSelectAction("Yellow", fractalBuilder::setYellowColoring)
          .selectFirstItem());
  }

  private IControl<?, ?> createMaxIterationCountDropdownMenu() {

    final var maxIterationCountDropdownMenu = new DropdownMenu();

    for (var i = 10; i <= 100; i += 10) {

      final var j = i;

      maxIterationCountDropdownMenu.addItemWithTextAndSelectAction(
        String.valueOf(j),
        () -> fractalBuilder.setMaxIterationCount(j));
    }

    maxIterationCountDropdownMenu.selectItemByText("30");

    return maxIterationCountDropdownMenu;
  }

  private void startRegenerateFractalImage() {
    getStoredGui()
      .pushLayer(
        new WaitDialogBuilder()
          .setJob(this::regenerateFractalImage)
          .setTerminalAction(this::refresh)
          .build());
  }

  private void regenerateFractalImage() {
    fractalImageControl.setImage(fractalBuilder.createFractalImage());
  }
}
