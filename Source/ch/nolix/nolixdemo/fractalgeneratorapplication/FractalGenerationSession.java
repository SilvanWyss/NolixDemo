package ch.nolix.nolixdemo.fractalgeneratorapplication;

import ch.nolix.system.application.guiapplication.BackendGUIClientSession;
import ch.nolix.system.application.main.VoidApplicationContext;
import ch.nolix.system.gui.containerwidget.ContainerRole;
import ch.nolix.system.gui.containerwidget.Grid;
import ch.nolix.system.gui.containerwidget.VerticalStack;
import ch.nolix.system.gui.dialog.WaitDialogCreator;
import ch.nolix.system.gui.widget.Button;
import ch.nolix.system.gui.widget.DropdownMenu;
import ch.nolix.system.gui.widget.ImageWidget;
import ch.nolix.system.gui.widget.Label;
import ch.nolix.system.gui.widget.LabelRole;
import ch.nolix.system.gui.widget.Widget;
import ch.nolix.systemapi.guiapi.imageapi.IImage;
import ch.nolix.systemapi.guiapi.widgetguiapi.IWidget;
import ch.nolix.template.guilook.AnthrazitGUILookCreator;

final class FractalGenerationSession extends BackendGUIClientSession<VoidApplicationContext> {
	
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
	
	private final ImageWidget fractalImageWidget = new ImageWidget().setImage(DEFAULT_FRACTAL_IMAGE);
		
	@Override
	protected void initialize() {
		getRefGUI()
		.setConfiguration(AnthrazitGUILookCreator.INSTANCE.createGUILook())
		.pushLayerWithRootWidget(createMainWidget());
	}
	
	private IWidget<?, ?> createMainWidget() {
		return
		new VerticalStack()
		.setRole(ContainerRole.OVERALL_CONTAINTER)
		.addWidget(createTitleWidget(), createControlWidget(), fractalImageWidget);
	}
		
	private Widget<?, ?> createTitleWidget() {
		return new Label().setRole(LabelRole.TITLE).setText(getApplicationName());
	}
	
	private Widget<?, ?> createControlWidget() {
		return new VerticalStack().addWidget(createConfigurationWidet(), generateFractalImageButton);
	}
	
	private Widget<?, ?> createConfigurationWidet() {
		return
		new Grid()
		.setWidget(1, 1, "Function")
		.setWidget(
			1,
			2,
			new DropdownMenu()
			.addItem("p->p^2+z", fractalBuilder::setMandelbrotFunction)
			.addItem("p->p^2-0.8+0.15i", fractalBuilder::setFunctionSumOfPredecessorPower2AndFixZ)
			.addItem("p->p^4+z", fractalBuilder::setFunctionSumOfPredecessarPower4AndZ)
			.selectFirstItem()
		)
		.setWidget(1, 3, "Maximal iteration count")
		.setWidget(
			1,
			4,
			createMaxIterationCountDropdownMenu()
		)
		.setWidget(2, 1, "Size")
		.setWidget(
			2,
			2,
			new DropdownMenu()
			.addItem("Small", fractalBuilder::setSmallSize)
			.addItem("Medium", fractalBuilder::setMediumSize)
			.addItem("Large", fractalBuilder::setLargeSize)
			.addItem("Very large", fractalBuilder::setVeryLargeSize)
			.selectFirstItem()
		)
		.setWidget(2, 3, "Coloring")
		.setWidget(
			2,
			4,
			new DropdownMenu()
			.addItem("Unique beige", fractalBuilder::setUniqueBeigeColoring)
			.addItem("Blue", fractalBuilder::setBlueColoring)
			.addItem("Green", fractalBuilder::setGreenColoring)
			.addItem("Red", fractalBuilder::setRedColoring)
			.addItem("Yellow", fractalBuilder::setYellowColoring)
			.selectFirstItem()
		);
	}
	
	private Widget<?, ?> createMaxIterationCountDropdownMenu() {
		
		final var maxIterationCountDropdownMenu = new DropdownMenu();
		
		for (var i = 10; i <= 100; i += 10) {
			
			final var j = i;
			
			maxIterationCountDropdownMenu.addItem(String.valueOf(j), () -> fractalBuilder.setMaxIterationCount(j));
		}
		
		maxIterationCountDropdownMenu.selectItem("30");
		
		return maxIterationCountDropdownMenu;
	}
	
	private void startRegenerateFractalImage() {
		getRefGUI().pushLayer(
			WaitDialogCreator.INSTANCE.createWaitDialogForJobAndTerminalAction(
				this::regenerateFractalImage,
				this::updateCounterpart
			)
		);
	}
	
	private void regenerateFractalImage() {
		fractalImageWidget.setImage(fractalBuilder.createFractalImage());
	}
}
