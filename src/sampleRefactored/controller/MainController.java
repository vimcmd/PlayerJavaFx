package sampleRefactored.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import sampleRefactored.controller.mediaControls.MediaControlsController;
import sampleRefactored.controller.mediaPlayer.MediaPlayerController;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController {

    @FXML
    MediaControlsController mediaControlsController;
    @FXML
    MediaPlayerController mediaPlayerController;
    @FXML
    private BorderPane borderPane;

    @FXML
    public void initialize() {
        mediaControlsController.init(this);
        //mediaPlayerController.init(this);

        borderPane.widthProperty().addListener(e -> {
            mediaControlsController.setHBoxWidth(borderPane.widthProperty().getValue());
        });
    }



}