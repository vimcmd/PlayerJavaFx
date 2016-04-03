package controllers;

import controllers.mediaControls.MediaControlsController;
import controllers.mediaPlayer.MediaPlayerController;
import controllers.menuBar.MenuBarController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;

public class MainController {

    @FXML
    MediaControlsController mediaControlsController;
    @FXML
    MediaPlayerController mediaPlayerController;
    @FXML
    MenuBarController menuBarController;

    private Stage primaryStage;
    private File playbackFile;

    @FXML
    public void initialize() {
        mediaPlayerController.init(this);
        mediaControlsController.init(this);
        menuBarController.init(this);
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayerController.getMediaPlayer();
    }

    public File getPlaybackFile() throws FileNotFoundException {
        // TODO: 03.04.2016 start app with empty file, with spinner on mediaView
        if (playbackFile != null) {
            return playbackFile;
        } else {
            setPlayBackFile(getPlaybackFileViaOpenDialog());
            if (playbackFile != null) {
                return playbackFile;
            } else {
                Platform.exit();
                throw new FileNotFoundException("Playback file not initialized");
            }
        }
    }

    public File getPlaybackFileViaOpenDialog() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter mediaFiles = new FileChooser.ExtensionFilter("Media files", "*.mp4", "*.avi");
        fileChooser.getExtensionFilters().add(mediaFiles);
        return fileChooser.showOpenDialog(this.getPrimaryStage());
    }

    public void setPlayBackFile(File file) throws FileNotFoundException {
        if (file != null) {
            if (!file.equals(playbackFile)) {
                playbackFile = file;
                initialize();
            } else {
                System.err.println("File already playing");
            }
        }
    }

    public Stage getPrimaryStage() {
        return this.primaryStage;
    }

    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;
    }


}