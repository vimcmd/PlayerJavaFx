package sampleRefactored;

import javafx.scene.text.Font;

public class FontAwesome {

    public static final Font FONT = Font.loadFont(FontAwesome.class.getResourceAsStream("/res/fontello.ttf"), 12);

    public static final String ICON_VOLUME_OFF = "\uE800";
    public static final String ICON_VOLUME_DOWN = "\uE801";
    public static final String ICON_VOLUME_UP = "\uE802";
    public static final String ICON_PLAY = "\uE803";
    public static final String ICON_PAUSE = "\uE804";
    public static final String ICON_TO_END = "\uE805";
    public static final String ICON_TO_END_ALT = "\uE806";
    public static final String ICON_TO_START = "\uE807";
    public static final String ICON_TO_START_ALT = "\uE808";
    public static final String ICON_FAST_FW = "\uE809";
    public static final String ICON_SHUFFLE = "\uE80a";
    public static final String ICON_EXCHANGE = "\uE80b";
    public static final String ICON_COG = "\uE80c";
    public static final String ICON_CW = "\uE80d";
    public static final String ICON_ARROWS_CW = "\uE80e";
    public static final String ICON_CCW = "\uE80f";
    public static final String ICON_RESIZE_FULL = "\uE810";
    public static final String ICON_RESIZE_SMALL = "\uE811";
    public static final String ICON_STOP = "\uE812";

    private FontAwesome() {
    }
}
