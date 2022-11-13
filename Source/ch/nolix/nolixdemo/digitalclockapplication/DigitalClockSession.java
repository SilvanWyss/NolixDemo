package ch.nolix.nolixdemo.digitalclockapplication;

import ch.nolix.core.commontype.commontypehelper.GlobalStringHelper;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.system.application.main.VoidApplicationContext;
import ch.nolix.system.application.webapplication.BackendWebClientSession;
import ch.nolix.system.graphic.image.Image;
import ch.nolix.system.time.moment.Time;
import ch.nolix.system.webgui.control.Text;
import ch.nolix.system.webgui.linearcontainer.VerticalStack;
import ch.nolix.system.webgui.main.Layer;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;
import ch.nolix.systemapi.webguiapi.containerapi.ContainerRole;

final class DigitalClockSession extends BackendWebClientSession<VoidApplicationContext> {
	
	private static final int TIME_UPDATE_INTERVAL_IN_MILLISECONDS = 200;
	
	private static final IImage BACKGROUND_IMAGE =
	Image.fromResource("ch/nolix/nolixdemo/digitalclockapplication/resource/Sonnenberg.jpg");
	
	private final Text timeLabel = new Text().setId(ControlIdCatalogue.TIME_LABEL_ID);
	
	private final Text dateLabel = new Text().setId(ControlIdCatalogue.DATE_LABEL_ID);
	
	@Override
	protected void initialize() {
		
		getRefGUI()
		.setStyle(LookCreator.INSTANCE.createLook())
		.pushLayer(
			new Layer()
			.setId(ControlIdCatalogue.TIME_LAYER_ID)
			.setRootControl(
				new VerticalStack()
				.setRole(ContainerRole.MAIN_CONTENT_CONTAINER)
				.addControl(
					timeLabel,
					dateLabel
				)
			)
		)
		.setBackgroundImage(BACKGROUND_IMAGE);
		
		GlobalSequencer
		.asLongAs(this::isOpen)
		.afterAllMilliseconds(TIME_UPDATE_INTERVAL_IN_MILLISECONDS)
		.runInBackground(this::updateDateAndTime);
	}
	
	private void updateDateAndTime() {
		
		timeLabel.setText(getCurrentTimeText());
		dateLabel.setText(getCurrentDateText());
		
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
