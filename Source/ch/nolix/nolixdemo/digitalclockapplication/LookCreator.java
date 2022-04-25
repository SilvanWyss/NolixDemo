package ch.nolix.nolixdemo.digitalclockapplication;

import ch.nolix.system.configuration.Configuration;
import ch.nolix.system.configuration.DeepConfiguration;
import ch.nolix.system.gui.base.Layer;

final class LookCreator {
	
	public static final LookCreator INSTANCE = new LookCreator();
	
	private LookCreator() {}
	
	public Configuration createLook() {
		return
		new Configuration()
		.addAttachingAttribute("BackgroundColor(SteelBlue)")
		.addConfiguration(
			createLayerLook(),
			createTimeLabelLook()
		);
	}
	
	private DeepConfiguration createLayerLook() {
		return
		new DeepConfiguration()
		.setSelectorType(Layer.class)
		.addAttachingAttribute("ContentPosition(Center)");
	}
	
	private DeepConfiguration createTimeLabelLook() {
		return
		new DeepConfiguration()
		.setSelectorId(WidgetIdCatalogue.TIME_LABEL_ID)
		.addAttachingAttribute(
			"BaseTextSize(200)",
			"BaseTextColor(White)"
		);
	}
}