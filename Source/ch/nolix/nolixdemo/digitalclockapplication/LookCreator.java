package ch.nolix.nolixdemo.digitalclockapplication;

import ch.nolix.system.configuration.Configuration;
import ch.nolix.system.configuration.DeepConfiguration;
import ch.nolix.system.gui.containerwidget.ContainerRole;

final class LookCreator {
	
	public static final LookCreator INSTANCE = new LookCreator();
	
	private LookCreator() {}
	
	public Configuration createLook() {
		return
		new Configuration()
		.addAttachingAttribute("BackgroundColor(SteelBlue)")
		.addConfiguration(
			createTimeLayerLook(),
			createMainContainerLook(),
			createTimeLabelLook(),
			createDateLabelLook()
		);
	}
	
	private DeepConfiguration createTimeLayerLook() {
		return
		new DeepConfiguration()
		.setSelectorId(WidgetIdCatalogue.TIME_LAYER_ID)
		.addAttachingAttribute(
			"Opacity(90%)",
			"ContentPosition(Bottom)"
		);
	}
	
	private DeepConfiguration createMainContainerLook() {
		return
		new DeepConfiguration()
		.addSelectorRole(ContainerRole.MAIN_CONTENT_CONTAINER)
		.addAttachingAttribute(
			"MinWidth(100%)",
			"BaseBackground(Color(Black))",
			"BasePadding(50)",
			"BaseTextSize(200)",
			"BaseTextColor(White)"
		);
	}
	
	private DeepConfiguration createTimeLabelLook() {
		return
		new DeepConfiguration()
		.setSelectorId(WidgetIdCatalogue.TIME_LABEL_ID)
		.addAttachingAttribute("BaseTextSize(100)");
	}
	
	private DeepConfiguration createDateLabelLook() {
		return
		new DeepConfiguration()
		.setSelectorId(WidgetIdCatalogue.DATE_LABEL_ID)
		.addAttachingAttribute("BaseTextSize(50)");
	}
}
