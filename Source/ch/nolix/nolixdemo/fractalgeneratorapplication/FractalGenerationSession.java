package ch.nolix.nolixdemo.fractalgeneratorapplication;

import ch.nolix.system.application.webapplication.WebClientSession;
import ch.nolix.system.webgui.container.GridContainer;
import ch.nolix.system.webgui.control.Button;
import ch.nolix.system.webgui.control.ImageControl;
import ch.nolix.system.webgui.control.Label;
import ch.nolix.system.webgui.dialog.WaitDialogFactory;
import ch.nolix.system.webgui.itemmenu.DropdownMenu;
import ch.nolix.system.webgui.linearcontainer.VerticalStack;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;
import ch.nolix.systemapi.webguiapi.containerapi.ContainerRole;
import ch.nolix.systemapi.webguiapi.controlapi.LabelRole;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.template.webgui.style.DarkModeStyleCreator;

final class FractalGenerationSession extends WebClientSession<Object> {
	
	private static final IImage DEFAULT_FRACTAL_IMAGE =
	new FractalBuilder()
	.setMandelbrotFunction()
	.setMaxIterationCount(30)
	.setSmallSize()
	.setUniqueBeigeColoring()
	.createFractalImage();
	
	private static final DarkModeStyleCreator DARK_MODE_STYLE_CREATOR = new DarkModeStyleCreator();
	
	private final Button generateFractalImageButton =
	new Button().setText("Generate").setLeftMouseButtonPressAction(this::startRegenerateFractalImage);
	
	private final FractalBuilder fractalBuilder = new FractalBuilder();
	
	private final ImageControl fractalImageControl = new ImageControl().setImage(DEFAULT_FRACTAL_IMAGE);
	
	@Override
	protected void initialize() {
		getOriGUI()		
		.pushLayerWithRootControl(createMainControl())
		.setStyle(DARK_MODE_STYLE_CREATOR.createDarkModeStyle());
	}
	
	private IControl<?, ?> createMainControl() {
		return
		new VerticalStack()
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
		return
		new GridContainer()
		.insertTextAtRowAndColumn(1, 1, "Function")
		.insertControlAtRowAndColumn(
			1,
			2,
			new DropdownMenu()
			.addItemWithTextAndSelectAction("p->p^2+z", fractalBuilder::setMandelbrotFunction)
			.addItemWithTextAndSelectAction("p->p^2-0.8+0.15i", fractalBuilder::setFunctionSumOfPredecessorPower2AndFixZ)
			.addItemWithTextAndSelectAction("p->p^4+z", fractalBuilder::setFunctionSumOfPredecessarPower4AndZ)
			.selectFirstItem()
		)
		.insertTextAtRowAndColumn(1, 5, "Maximal iteration count")
		.insertControlAtRowAndColumn(
			1,
			6,
			createMaxIterationCountDropdownMenu()
		)
		.insertTextAtRowAndColumn(2, 1, "Size")
		.insertControlAtRowAndColumn(
			2,
			2,
			new DropdownMenu()
			.addItemWithTextAndSelectAction("Small", fractalBuilder::setSmallSize)
			.addItemWithTextAndSelectAction("Medium", fractalBuilder::setMediumSize)
			.addItemWithTextAndSelectAction("Large", fractalBuilder::setLargeSize)
			.addItemWithTextAndSelectAction("Very large", fractalBuilder::setVeryLargeSize)
			.selectFirstItem()
		)
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
			.selectFirstItem()
		);
	}
	
	private IControl<?, ?> createMaxIterationCountDropdownMenu() {
		
		final var maxIterationCountDropdownMenu = new DropdownMenu();
		
		for (var i = 10; i <= 100; i += 10) {
			
			final var j = i;
			
			maxIterationCountDropdownMenu.addItemWithTextAndSelectAction(
				String.valueOf(j),
				() -> fractalBuilder.setMaxIterationCount(j)
			);
		}
		
		maxIterationCountDropdownMenu.selectItemByText("30");
		
		return maxIterationCountDropdownMenu;
	}
	
	private void startRegenerateFractalImage() {
		getOriGUI().pushLayer(
			WaitDialogFactory.INSTANCE.createWaitDialogForJobAndTerminalAction(
				this::regenerateFractalImage,
				this::updateCounterpart
			)
		);
	}
	
	private void regenerateFractalImage() {
		fractalImageControl.setImage(fractalBuilder.createFractalImage());
	}
}
