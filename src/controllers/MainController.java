package controllers;

import controllers.mediaControls.MediaControlsController;
import controllers.mediaPlayer.MediaPlayerController;
import controllers.menuBar.MenuBarController;
import javafx.fxml.FXML;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

public class MainController {

    @FXML
    MediaControlsController mediaControlsController;
    @FXML
    MediaPlayerController mediaPlayerController;
    @FXML
    MenuBarController menuBarController;

    private Stage primaryStage;
    // TODO: 03.04.2016 start app with empty file, with spinner on mediaView
    private File playbackFile = new File("D:/video.mp4");

    @FXML
    public void initialize() {
        mediaPlayerController.init(this);
        mediaControlsController.init(this);
        menuBarController.init(this);

    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayerController.getMediaPlayer();
    }

    public File getPlayBackFile() {
        return playbackFile;
    }

    public void setPlayBackFile(File file) {
        playbackFile = file;
        initialize();
    }

    public Stage getPrimaryStage() {
        return this.primaryStage;
    }

    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;
    }


}