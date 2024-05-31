package ch.nolix.nolixdemo.digitalclockapplication;

import ch.nolix.system.element.style.DeepSelectingStyle;
import ch.nolix.system.element.style.Style;
import ch.nolix.systemapi.elementapi.styleapi.ISelectingStyleWithSelectors;
import ch.nolix.systemapi.elementapi.styleapi.IStyle;
import ch.nolix.systemapi.webguiapi.basecontainerapi.ContainerRole;

final class StyleCreator {

  public IStyle createStyle() {
    return new Style()
      .withSubStyle(
        createTimeLayerStyle(),
        createMainContainerStyle(),
        createTimeLabelStyle(),
        createDateLabelStyle());
  }

  private ISelectingStyleWithSelectors createTimeLayerStyle() {
    return new DeepSelectingStyle()
      .withSelectorId(ControlIdCatalogue.TIME_LAYER_ID)
      .withAttachingAttribute(
        "Opacity(90%)",
        "ContentAlignment(BOTTOM)");
  }

  private ISelectingStyleWithSelectors createMainContainerStyle() {
    return new DeepSelectingStyle()
      .withSelectorRole(ContainerRole.MAIN_CONTENT_CONTAINER)
      .withAttachingAttribute(
        "BaseWidth(100%)",
        "BaseBackground(Color(Black))",
        "BasePadding(50)",
        "BaseTextSize(200)",
        "BaseTextColor(White)");
  }

  private ISelectingStyleWithSelectors createTimeLabelStyle() {
    return new DeepSelectingStyle()
      .withSelectorId(ControlIdCatalogue.TIME_LABEL_ID)
      .withAttachingAttribute("BaseTextSize(100)");
  }

  private ISelectingStyleWithSelectors createDateLabelStyle() {
    return new DeepSelectingStyle()
      .withSelectorId(ControlIdCatalogue.DATE_LABEL_ID)
      .withAttachingAttribute("BaseTextSize(50)");
  }
}
