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

    public void init(MainController mainController) {
        this.mainController = mainController;
        mediaViewFitContent();

        // TODO: 02.04.2016 set file via FileChooser from MainController
        String file = new File("D:/video.mp4").getAbsolutePath();
        try {
            media = new Media(new File(file).toURI().toURL().toExternalForm());
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
