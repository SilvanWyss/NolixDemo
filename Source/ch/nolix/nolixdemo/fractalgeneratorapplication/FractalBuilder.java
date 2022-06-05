package ch.nolix.nolixdemo.fractalgeneratorapplication;

import ch.nolix.business.bigdecimalmath.ComplexNumber;
import ch.nolix.business.bigdecimalmath.ComplexSequenceDefinedBy1Predecessor;
import ch.nolix.system.gui.color.Color;
import ch.nolix.systemapi.guiapi.imageapi.IImage;
import ch.nolix.template.bigdecimalmath.GlobalSequenceCreator;

public final class FractalBuilder {
	
	private final ch.nolix.business.bigdecimalmath.FractalBuilder internalFractalBuilder =
	new ch.nolix.business.bigdecimalmath.FractalBuilder();
	
	public IImage createFractalImage() {
		return internalFractalBuilder.build().toImage();
	}
	
	public FractalBuilder setUniqueBeigeColoring() {
		
		internalFractalBuilder.setColorFunction(i -> Color.BEIGE);
		
		return this;
	}
	
	public FractalBuilder setBlueColoring() {
		
		internalFractalBuilder.setColorFunction(
			i ->
			Color.withRedValueAndGreenValueAndBlueValue(
				0,
				0,
				Color.MAX_COLOR_COMPONENT * i / internalFractalBuilder.getMaxIterationCount()
			)
		);
		
		return this;
	}
	
	public FractalBuilder setFunctionSumOfPredecessorPower2AndFixZ() {
		
		internalFractalBuilder
		.setRealComponentInterval(-1.5, 1.5)
		.setImaginaryComponentInterval(-1.5, 1.5)
		.setSequenceCreator(
			z ->
			new ComplexSequenceDefinedBy1Predecessor(z,	p -> p.getPower2().getSum(new ComplexNumber(-0.8, 0.15)))
		);
		
		return this;
	}
	
	public FractalBuilder setFunctionSumOfPredecessarPower4AndZ() {
		
		internalFractalBuilder
		.setRealComponentInterval(-1.5, 1.5)
		.setImaginaryComponentInterval(-1.5, 1.5)
		.setSequenceCreator(
			z -> new ComplexSequenceDefinedBy1Predecessor(new ComplexNumber(0.0, 0.0), p -> p.getPower4().getSum(z))
		);
		
		return this;
	}
	
	public FractalBuilder setGreenColoring() {
		
		internalFractalBuilder.setColorFunction(
			i ->
			Color.withRedValueAndGreenValueAndBlueValue(
				0,
				Color.MAX_COLOR_COMPONENT * i / internalFractalBuilder.getMaxIterationCount(),
				0
			)
		);
		
		return this;
	}
	
	public FractalBuilder setLargeSize() {
		
		internalFractalBuilder.setWidthInPixel(900).setHeightInPixel(900);
		
		return this;
	}
	
	public FractalBuilder setMandelbrotFunction() {
		
		internalFractalBuilder
		.setRealComponentInterval(-2.0, 1.0)
		.setImaginaryComponentInterval(-1.5, 1.5)
		.setSequenceCreator(GlobalSequenceCreator::createMandelbrotSequenceForIncrement);
	
		return this;
	}
	
	public FractalBuilder setMaxIterationCount(final int maxIterationCount) {
		
		internalFractalBuilder.setMaxIterationCount(maxIterationCount);
		
		return this;
	}
	
	public FractalBuilder setMediumSize() {
		
		internalFractalBuilder.setWidthInPixel(600).setHeightInPixel(600);
		
		return this;
	}
	
	public FractalBuilder setRedColoring() {
		
		internalFractalBuilder.setColorFunction(
			i ->
			Color.withRedValueAndGreenValueAndBlueValue(
				Color.MAX_COLOR_COMPONENT * i / internalFractalBuilder.getMaxIterationCount(),
				0,
				0
			)
		);
		
		return this;
	}
	
	public FractalBuilder setSmallSize() {
		
		internalFractalBuilder.setWidthInPixel(300).setHeightInPixel(300);
		
		return this;
	}
	
	public FractalBuilder setVeryLargeSize() {
		
		internalFractalBuilder.setWidthInPixel(1200).setHeightInPixel(1200);
		
		return this;
	}
	
	public FractalBuilder setYellowColoring() {
		
		internalFractalBuilder.setColorFunction(
			i -> {
				
				final var colorComponent =
				Color.MAX_COLOR_COMPONENT * i / internalFractalBuilder.getMaxIterationCount();
				
				return Color.withRedValueAndGreenValueAndBlueValue(colorComponent, colorComponent, 0);
			}
		);
		
		return this;
	}
}
