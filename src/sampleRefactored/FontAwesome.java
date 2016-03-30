package sampleRefactored;

import javafx.scene.text.Font;

public class FontAwesome {

    public static final Font FONT = Font.loadFont(FontAwesome.class.getResourceAsStream("/res/fontawesome-webfont.ttf"), 12);

    public static final String ICON_PLAY = "\uf04b";
    public static final String ICON_PAUSE = "\uf04c";
    public static final String ICON_INCREASE_RATE = "\uf04e";
    public static final String ICON_DECREASE_RATE = "\uf04a";
    public static final String ICON_RELOAD = "\uf021";

    private FontAwesome() {
    }
}
