package ch.nolix.nolixdemo.digitalclockapplication;

import ch.nolix.core.commontypetool.GlobalStringTool;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.system.application.webapplication.WebClientSession;
import ch.nolix.system.graphic.image.Image;
import ch.nolix.system.time.moment.Time;
import ch.nolix.system.webgui.atomiccontrol.Label;
import ch.nolix.system.webgui.linearcontainer.VerticalStack;
import ch.nolix.system.webgui.main.Layer;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.ILabel;
import ch.nolix.systemapi.webguiapi.basecontainerapi.ContainerRole;
import ch.nolix.systemapi.webguiapi.linearcontainerapi.IVerticalStack;

final class DigitalClockSession extends WebClientSession<Object> {

  private static final int UPDATE_START_DELAY_IN_MILLISECONDS = 200;

  private static final int TIME_UPDATE_INTERVAL_IN_MILLISECONDS = 200;

  private static final IImage BACKGROUND_IMAGE = Image.fromResource("image/sonnenberg.jpg");

  private static final StyleCreator STYLE_CREATOR = new StyleCreator();

  private final ILabel timeLabel = new Label().setId(ControlIdCatalogue.TIME_LABEL_ID).setText(getCurrentTimeText());

  private final ILabel dateLabel = new Label().setId(ControlIdCatalogue.DATE_LABEL_ID).setText(getCurrentDateText());

  private final IVerticalStack mainContentVerticalStack = new VerticalStack()
    .setRole(ContainerRole.MAIN_CONTENT_CONTAINER)
    .addControl(
      timeLabel,
      dateLabel);

  @Override
  protected void initialize() {

    final var style = STYLE_CREATOR.createStyle();

    getStoredGui()
      .pushLayer(
        new Layer()
          .setId(ControlIdCatalogue.TIME_LAYER_ID)
          .setRootControl(mainContentVerticalStack))
      .setBackgroundImage(BACKGROUND_IMAGE)
      .setStyle(style);

    GlobalSequencer.runInBackground(
      () -> {

        //We must wait until the initialization will be finished.
        GlobalSequencer.waitForMilliseconds(UPDATE_START_DELAY_IN_MILLISECONDS);

        GlobalSequencer
          .asLongAs(this::isAlive)
          .afterEveryMilliseconds(TIME_UPDATE_INTERVAL_IN_MILLISECONDS)
          .runInBackground(this::updateDateAndTime);
      });
  }

  private void updateDateAndTime() {

    timeLabel.setText(getCurrentTimeText());
    dateLabel.setText(getCurrentDateText());

    updateControlOnCounterpart(mainContentVerticalStack, false);
  }

  private String getCurrentTimeText() {
    return getTimeAsText(Time.ofNow());
  }

  private String getTimeAsText(final Time time) {
    return String.format(
      "%02d:%02d:%02d",
      time.getHourOfDay(),
      time.getMinuteOfHour(),
      time.getSecondOfMinute());
  }

  private String getCurrentDateText() {
    return getDateAsText(Time.ofNow());
  }

  private String getDateAsText(final Time time) {
    return String.format(
      "%s %02d.%02d.%04d",
      GlobalStringTool.toPascalCase(time.getWeekday().toString()),
      time.getDayOfMonth(),
      time.getMonthOfYearAsInt(),
      time.getYearAsInt());
  }
}
