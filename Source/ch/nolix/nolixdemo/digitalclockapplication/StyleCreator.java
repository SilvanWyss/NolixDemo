package ch.nolix.nolixdemo.digitalclockapplication;

import ch.nolix.system.element.style.DeepSelectingStyle;
import ch.nolix.system.element.style.Style;
import ch.nolix.system.element.stylebuilder.DeepSelectingStyleBuilder;
import ch.nolix.system.element.stylebuilder.StyleBuilder;
import ch.nolix.systemapi.webguiapi.basecontainerapi.ContainerRole;

final class StyleCreator {

  public static final StyleCreator INSTANCE = new StyleCreator();

  private StyleCreator() {
  }

  public Style createStyle() {
    return new StyleBuilder()
      .addSubStyle(
        createTimeLayerStyle(),
        createMainContainerStyle(),
        createTimeLabelStyle(),
        createDateLabelStyle())
      .build();
  }

  private DeepSelectingStyle createTimeLayerStyle() {
    return new DeepSelectingStyleBuilder()
      .setSelectorId(ControlIdCatalogue.TIME_LAYER_ID)
      .addAttachingAttribute(
        "Opacity(90%)",
        "ContentAlignment(BOTTOM)")
      .build();
  }

  private DeepSelectingStyle createMainContainerStyle() {
    return new DeepSelectingStyleBuilder()
      .addSelectorRole(ContainerRole.MAIN_CONTENT_CONTAINER)
      .addAttachingAttribute(
        "BaseWidth(100%)",
        "BaseBackground(Color(Black))",
        "BasePadding(50)",
        "BaseTextSize(200)",
        "BaseTextColor(White)")
      .build();
  }

  private DeepSelectingStyle createTimeLabelStyle() {
    return new DeepSelectingStyleBuilder()
      .setSelectorId(ControlIdCatalogue.TIME_LABEL_ID)
      .addAttachingAttribute("BaseTextSize(100)")
      .build();
  }

  private DeepSelectingStyle createDateLabelStyle() {
    return new DeepSelectingStyleBuilder()
      .setSelectorId(ControlIdCatalogue.DATE_LABEL_ID)
      .addAttachingAttribute("BaseTextSize(50)")
      .build();
  }
}
