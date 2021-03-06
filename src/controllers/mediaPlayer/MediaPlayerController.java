package controllers.mediaPlayer;

import controllers.MainController;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.FileNotFoundException;
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

        try {
            media = new Media(mainController.getPlaybackFile().toURI().toURL().toExternalForm());
        } catch (MalformedURLException | FileNotFoundException e1) {
            e1.printStackTrace();
        }

        if (mediaPlayer != null) {
            mediaViewFitContent();
            mediaPlayer.dispose();
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
