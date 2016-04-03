package controllers.mediaPlayer;

import controllers.MainController;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.net.MalformedURLException;

public class MediaPlayerController implements IMediaPlayer {

    private MainController mainController;

    @FXML
    private MediaView mediaView;

    private MediaPlayer mediaPlayer;
    private Media media;

    // FIXME: 03.04.2016 bug - on window resize mediaView hovers other views (controls and menuBar)

    public void init(MainController mainController) {
        this.mainController = mainController;
        mediaViewFitContent();

        if (mediaPlayer != null) {
            mediaPlayer.dispose();
        }

        try {
            File playBackFile = mainController.getPlayBackFile();
            media = new Media(playBackFile.toURI().toURL().toExternalForm());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.setAutoPlay(true);
    }

    private void mediaViewFitContent() {
        DoubleProperty width = mediaView.fitWidthProperty();
        DoubleProperty height = mediaView.fitHeightProperty();
        width.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
        height.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));
    }


    @Override
    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }
}
