package ch.nolix.nolixdemo.digitalclockapplication;

import ch.nolix.system.element.style.DeepStyle;
import ch.nolix.system.element.style.Style;
import ch.nolix.systemapi.webguiapi.containerapi.ContainerRole;

final class StyleCreator {
	
	public static final StyleCreator INSTANCE = new StyleCreator();
	
	private StyleCreator() {}
	
	public Style createStyle() {
		return
		new Style()
		.addConfiguration(
			createTimeLayerStyle(),
			createMainContainerStyle(),
			createTimeLabelStyle(),
			createDateLabelStyle()
		);
	}
	
	private DeepStyle createTimeLayerStyle() {
		return
		new DeepStyle()
		.setSelectorId(ControlIdCatalogue.TIME_LAYER_ID)
		.addAttachingAttribute(
			"Opacity(90%)",
			"ContentPosition(BOTTOM)"
		);
	}
	
	private DeepStyle createMainContainerStyle() {
		return
		new DeepStyle()
		.addSelectorRole(ContainerRole.MAIN_CONTENT_CONTAINER)
		.addAttachingAttribute(
			"BaseWidth(100%)",
			"BaseBackground(Color(Black))",
			"BasePadding(50)",
			"BaseTextSize(200)",
			"BaseTextColor(White)"
		);
	}
	
	private DeepStyle createTimeLabelStyle() {
		return
		new DeepStyle()
		.setSelectorId(ControlIdCatalogue.TIME_LABEL_ID)
		.addAttachingAttribute("BaseTextSize(100)");
	}
	
	private DeepStyle createDateLabelStyle() {
		return
		new DeepStyle()
		.setSelectorId(ControlIdCatalogue.DATE_LABEL_ID)
		.addAttachingAttribute("BaseTextSize(50)");
	}
}
