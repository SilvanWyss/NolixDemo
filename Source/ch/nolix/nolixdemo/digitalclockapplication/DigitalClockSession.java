package ch.nolix.nolixdemo.digitalclockapplication;

import ch.nolix.core.commontype.commontypehelper.GlobalStringHelper;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.system.application.webapplication.BackendWebClientSession;
import ch.nolix.system.graphic.image.Image;
import ch.nolix.system.time.moment.Time;
import ch.nolix.system.webgui.control.Label;
import ch.nolix.system.webgui.linearcontainer.VerticalStack;
import ch.nolix.system.webgui.main.Layer;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;
import ch.nolix.systemapi.webguiapi.containerapi.ContainerRole;

final class DigitalClockSession extends BackendWebClientSession<Object> {
	
	private static final int TIME_UPDATE_INTERVAL_IN_MILLISECONDS = 500;
	
	private static final IImage BACKGROUND_IMAGE =
	Image.fromResource("ch/nolix/nolixdemo/digitalclockapplication/resource/sonnenberg.jpg");
	
	private static final StyleCreator STYLE_CREATOR = StyleCreator.INSTANCE;
	
	private final Label timeLabel = new Label().setId(ControlIdCatalogue.TIME_LABEL_ID).setText(getCurrentTimeText());
	
	private final Label dateLabel = new Label().setId(ControlIdCatalogue.DATE_LABEL_ID).setText(getCurrentDateText());
	
	private final VerticalStack mainContentVerticalStack =
	new VerticalStack()
	.setRole(ContainerRole.MAIN_CONTENT_CONTAINER)
	.addControl(
		timeLabel,
		dateLabel
	);
	
	@Override
	protected void initialize() {
		
		final var style = STYLE_CREATOR.createStyle();
		
		getOriGUI()
		.pushLayer(
			new Layer()
			.setId(ControlIdCatalogue.TIME_LAYER_ID)
			.setRootControl(mainContentVerticalStack)
		)
		.setStyle(style);
		
		GlobalSequencer.runInBackground(
			() -> {
				
				//We must wait until the initialization will be finished.
				GlobalSequencer.waitForMilliseconds(100);
				
				getOriGUI().setBackgroundImage(BACKGROUND_IMAGE);
				updateCounterpart();
			}
		);
		
		GlobalSequencer
		.asLongAs(this::isOpen)
		.afterEveryMilliseconds(TIME_UPDATE_INTERVAL_IN_MILLISECONDS)
		.runInBackground(this::updateDateAndTime);
	}
	
	private void updateDateAndTime() {
		
		timeLabel.setText(getCurrentTimeText());
		dateLabel.setText(getCurrentDateText());
		
		restrictNextCounterpartUpdateToControl(mainContentVerticalStack);
		
		updateCounterpart();
	}
	
	private String getCurrentTimeText() {
		return getTimeAsText(Time.ofNow());
	}
	
	private String getTimeAsText(final Time time) {
		return
		String.format(
			"%02d:%02d:%02d",
			time.getHourOfDay(),
			time.getMinuteOfHour(),
			time.getSecondOfMinute()
		);
	}
	
	private String getCurrentDateText() {
		return getDateAsText(Time.ofNow());
	}
	
	private String getDateAsText(final Time time) {
		return
		String.format(
			"%s %02d.%02d.%04d",
			GlobalStringHelper.toPascalCase(time.getWeekday().toString()),
			time.getDayOfMonth(),
			time.getMonthOfYearAsInt(),
			time.getYearAsInt()
		);
	}
}
