package ch.nolix.nolixdemo.fractalgeneratorapplication;

import ch.nolix.system.application.webapplication.BackendWebClientSession;
import ch.nolix.system.webgui.container.GridContainer;
import ch.nolix.system.webgui.control.Button;
import ch.nolix.system.webgui.control.ImageControl;
import ch.nolix.system.webgui.control.Text;
import ch.nolix.system.webgui.dialog.WaitDialogFactory;
import ch.nolix.system.webgui.itemmenu.DropdownMenu;
import ch.nolix.system.webgui.linearcontainer.VerticalStack;
import ch.nolix.systemapi.guiapi.containercontrolproperty.ContainerRole;
import ch.nolix.systemapi.guiapi.imageapi.IImage;
import ch.nolix.systemapi.webguiapi.controlapi.TextRole;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

final class FractalGenerationSession extends BackendWebClientSession<Object> {
	
	private static final IImage DEFAULT_FRACTAL_IMAGE =
	new FractalBuilder()
	.setMandelbrotFunction()
	.setMaxIterationCount(30)
	.setSmallSize()
	.setUniqueBeigeColoring()
	.createFractalImage();
	
	private final Button generateFractalImageButton =
	new Button().setText("Generate").setLeftMouseButtonPressAction(this::startRegenerateFractalImage);
	
	private final FractalBuilder fractalBuilder = new FractalBuilder();
	
	private final ImageControl fractalImageWidget = new ImageControl().setImage(DEFAULT_FRACTAL_IMAGE);
		
	@Override
	protected void initialize() {
		getRefGUI().pushLayerWithRootControl(createMainWidget());
	}
	
	private IControl<?, ?> createMainWidget() {
		return
		new VerticalStack()
		.setRole(ContainerRole.OVERALL_CONTAINTER)
		.addControl(createTitleWidget(), createControlWidget(), fractalImageWidget);
	}
		
	private IControl<?, ?> createTitleWidget() {
		return new Text().setRole(TextRole.TITLE).setText(getApplicationName());
	}
	
	private IControl<?, ?> createControlWidget() {
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
		.insertTextAtRowAndColumn(1, 3, "Maximal iteration count")
		.insertControlAtRowAndColumn(
			1,
			4,
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
		.insertTextAtRowAndColumn(2, 3, "Coloring")
		.insertControlAtRowAndColumn(
			2,
			4,
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
		getRefGUI().pushLayer(
			WaitDialogFactory.INSTANCE.createWaitDialogForJobAndTerminalAction(
				this::regenerateFractalImage,
				this::updateCounterpart
			)
		);
	}
	
	private void regenerateFractalImage() {
		fractalImageWidget.setImage(fractalBuilder.createFractalImage());
	}
}
