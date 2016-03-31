package sampleRefactored.controller.mediaControls;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import sampleRefactored.util.FontAwesome;
import sampleRefactored.controller.MainController;

/**
 * fx:id="mediaControls"
 */
public class MediaControlsController {

    private MainController mainController;

    @FXML
    private Button slowPlayback;
    @FXML
    private Label currentTimeLabel;
    @FXML
    private Label volumeLabel;
    @FXML
    private Slider volumeSlider;
    @FXML
    private HBox hBox;
    @FXML
    private Button fastPlayback;
    @FXML
    private Slider seekSlider;
    @FXML
    private Label totalTimeLabel;
    @FXML
    private Button playPauseBtn;

    //
    public void init(MainController mainController) {
        this.mainController = mainController;
        initControls();
    }


    public void initControls() {
        slowPlayback.setFont(FontAwesome.FONT);
        slowPlayback.setText(FontAwesome.ICON_BACKWARD2);
        playPauseBtn.setFont(FontAwesome.FONT);
        // TODO: 31.03.2016 playPauseBtn setText based on autoplay propery
        playPauseBtn.setText(FontAwesome.ICON_PLAY3);
        fastPlayback.setFont(FontAwesome.FONT);
        fastPlayback.setText(FontAwesome.ICON_FORWARD3);

        volumeLabel.setFont(FontAwesome.FONT);
        // TODO: 31.03.2016 set volumeLabel based on media
        volumeLabel.setText(FontAwesome.ICON_VOLUME_HIGH);

        currentTimeLabel.setText("00:00");
        // TODO: 31.03.2016 set totalTime based on media duration
        totalTimeLabel.setText("00:00");

        final int START_PLAYBACK_POSITION = 0;
        seekSlider.setValue(START_PLAYBACK_POSITION);

        volumeSlider.setValue(100);

    }


    public void setHBoxWidth(Double HBoxWidth) {
        this.hBox.setPrefWidth(HBoxWidth);
    }
}
