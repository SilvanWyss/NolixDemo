package ch.nolix.nolixdemo.digitalclockapplication;

import ch.nolix.system.element.style.DeepStyle;
import ch.nolix.system.element.style.Style;
import ch.nolix.systemapi.guiapi.containercontrolproperty.ContainerRole;

final class LookCreator {
	
	public static final LookCreator INSTANCE = new LookCreator();
	
	private LookCreator() {}
	
	public Style createLook() {
		return
		new Style()
		.addConfiguration(
			createTimeLayerLook(),
			createMainContainerLook(),
			createTimeLabelLook(),
			createDateLabelLook()
		);
	}
	
	private DeepStyle createTimeLayerLook() {
		return
		new DeepStyle()
		.setSelectorId(WidgetIdCatalogue.TIME_LAYER_ID)
		.addAttachingAttribute(
			"Opacity(90%)",
			"ContentPosition(BOTTOM)"
		);
	}
	
	private DeepStyle createMainContainerLook() {
		return
		new DeepStyle()
		.addSelectorRole(ContainerRole.MAIN_CONTENT_CONTAINER)
		.addAttachingAttribute(
			//TODO: "MinWidth(100%)"
			"BaseBackground(Color(Black))",
			"BasePadding(50)",
			"BaseTextSize(200)",
			"BaseTextColor(White)"
		);
	}
	
	private DeepStyle createTimeLabelLook() {
		return
		new DeepStyle()
		.setSelectorId(WidgetIdCatalogue.TIME_LABEL_ID)
		.addAttachingAttribute("BaseTextSize(100)");
	}
	
	private DeepStyle createDateLabelLook() {
		return
		new DeepStyle()
		.setSelectorId(WidgetIdCatalogue.DATE_LABEL_ID)
		.addAttachingAttribute("BaseTextSize(50)");
	}
}
