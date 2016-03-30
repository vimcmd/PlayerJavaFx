package sampleRefactored;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;

public class PlayerControls extends HBox {
    @FXML
    private Slider volumeSlider;
    @FXML
    private Label volumeLabel;
    @FXML
    private Slider seekSlider;
    @FXML
    private Label currentTimeLabel;
    @FXML
    private Label totalTimeLabel;
    @FXML
    private Button playBtn;
    @FXML
    private Button increaseRateBtn;
    @FXML
    private Button decreaseRateBtn;
    @FXML
    private Button reloadBtn;


}
