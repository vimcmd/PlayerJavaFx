package controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import controllers.mediaControls.MediaControlsController;
import controllers.mediaPlayer.MediaPlayerController;
import javafx.scene.media.MediaPlayer;

public class MainController {

    @FXML
    MediaControlsController mediaControlsController;
    @FXML
    MediaPlayerController mediaPlayerController;

    @FXML
    public void initialize() {
        mediaPlayerController.init(this);
        mediaControlsController.init(this);
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayerController.getMediaPlayer();
    }



}