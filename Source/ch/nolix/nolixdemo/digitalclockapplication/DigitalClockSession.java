package ch.nolix.nolixdemo.digitalclockapplication;

import ch.nolix.core.programcontrol.sequencer.Sequencer;
import ch.nolix.system.application.guiapplication.BackendGUIClientSession;
import ch.nolix.system.application.main.VoidApplicationContext;
import ch.nolix.system.gui.base.Layer;
import ch.nolix.system.gui.image.MutableImage;
import ch.nolix.system.gui.widget.Label;
import ch.nolix.system.time.base.Time;

final class DigitalClockSession extends BackendGUIClientSession<VoidApplicationContext> {
	
	private static final int TIME_UPDATE_INTERVAL_IN_MILLISECONDS = 200;
	
	private static final MutableImage BACKGROUND_IMAGE =
	MutableImage.fromResource("ch/nolix/nolixdemo/digitalclockapplication/resource/Sonnenberg.jpg");
	
	private final Label timeLabel = new Label().setId(WidgetIdCatalogue.TIME_LABEL_ID);
	
	@Override
	protected void initialize() {
		
		getRefGUI()
		.setConfiguration(LookCreator.INSTANCE.createLook())
		.pushLayer(
			new Layer()
			.setId(WidgetIdCatalogue.TIME_LAYER_ID)
			.setRootWidget(timeLabel)
		)
		.setBackgroundImage(BACKGROUND_IMAGE);
		
		Sequencer
		.asLongAs(this::isOpen)
		.afterAllMilliseconds(TIME_UPDATE_INTERVAL_IN_MILLISECONDS)
		.runInBackground(this::updateTime);
	}
	
	private String getCurrentTimeText() {
		
		final var currentTime = Time.fromCurrentTime();
		
		return
		String.format(
			"%02d:%02d:%02d",
			currentTime.getHourOfDay(),
			currentTime.getMinuteOfHour(),
			currentTime.getSecondOfMinute()
		);
	}
	
	private void updateTime() {
		
		timeLabel.setText(getCurrentTimeText());
		
		updateCounterpart();
	}
}
