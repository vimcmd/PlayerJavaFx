package controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import controllers.mediaControls.MediaControlsController;
import controllers.mediaPlayer.MediaPlayerController;

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
    }



}