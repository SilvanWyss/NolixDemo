package ch.nolix.nolixdemo.digitalclockapplication;

import ch.nolix.system.configuration.Configuration;
import ch.nolix.system.configuration.DeepConfiguration;

final class LookCreator {
	
	public static final LookCreator INSTANCE = new LookCreator();
	
	private LookCreator() {}
	
	public Configuration createLook() {
		return
		new Configuration()
		.addAttachingAttribute("BackgroundColor(SteelBlue)")
		.addConfiguration(
			createTimeLayerLook(),
			createTimeLabelLook()
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
	
	private DeepConfiguration createTimeLabelLook() {
		return
		new DeepConfiguration()
		.setSelectorId(WidgetIdCatalogue.TIME_LABEL_ID)
		.addAttachingAttribute(
			"MinWidth(100%)",
			"BaseBackground(Color(Black))",
			"BasePadding(50)",
			"BaseTextSize(200)",
			"BaseTextColor(White)"
		);
	}
}
