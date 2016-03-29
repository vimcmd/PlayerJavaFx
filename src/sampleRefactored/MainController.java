package sampleRefactored;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private MediaView mediaView;
    @FXML
    private Slider volumeSlider;
    @FXML
    private Slider seekSlider;
    @FXML
    private Label currentTimeLabel;
    @FXML
    private Label totalTimeLabel;

    private MediaPlayer mediaPlayer;
    private Media media;
    private double rate = 1;
    private double rateStep = 0.2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String file = new File("D:/video.mp4").getAbsolutePath();
        try {
            media = new Media(new File(file).toURI().toURL().toExternalForm());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.setAutoPlay(true);

        // preserve video ratio and filled into scene
        DoubleProperty width = mediaView.fitWidthProperty();
        DoubleProperty height = mediaView.fitHeightProperty();
        width.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
        height.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));

        volumeSlider.setValue(mediaPlayer.getVolume() * 100);
        volumeSlider.valueProperty().addListener(observable -> {
            mediaPlayer.setVolume(volumeSlider.getValue() / 100);
        });

        seekSlider.setValue(0);
        seekSlider.valueProperty().addListener(observable -> {
            if (seekSlider.isPressed()) {
                mediaPlayer.seek(mediaPlayer.getMedia().getDuration().multiply(seekSlider.getValue() / 100));
            }
        });
        mediaPlayer.currentTimeProperty().addListener(e -> {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    seekSlider.setValue(mediaPlayer.getCurrentTime().toMillis() / mediaPlayer.getTotalDuration()
                                                                                             .toMillis() * 100);
                    currentTimeLabel.setText(getCurrentTimeFormatted());
                }
            });
        });

        mediaPlayer.setOnReady(new Runnable() {
            @Override
            public void run() {
                totalTimeLabel.setText(getTotalDurationFormatted());

            }
        });
    }

    private String getCurrentTimeFormatted() {
        // FIXME: 29.03.2016 don't like that
        int hours = (int) mediaPlayer.getCurrentTime().toHours();
        int minutes = (int) mediaPlayer.getCurrentTime().toMinutes() - hours * 60;
        int seconds = (int) mediaPlayer.getCurrentTime().toSeconds() - hours * 60 * 60 - minutes * 60;
        return getTimeFormatted(hours, minutes, seconds);
    }

    private String getTotalDurationFormatted() {
        // FIXME: 29.03.2016 don't like that
        final int hours = (int) mediaPlayer.getTotalDuration().toHours();
        final int minutes = (int) mediaPlayer.getTotalDuration().toMinutes() - hours * 60;
        final int seconds = (int) mediaPlayer.getTotalDuration().toSeconds() - hours * 60 * 60 - minutes * 60;
        return getTimeFormatted(hours, minutes, seconds);
    }

    private String getTimeFormatted(int hours, int minutes, int seconds) {
        String time;
        if (hours > 0 & minutes > 0 & seconds > 0) {
            time = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        } else {
            time = String.format("%02d:%02d", minutes, seconds);
        }
        return time;
    }

    public void playPause(ActionEvent event) {
        Status status = mediaPlayer.getStatus();
        if (status == Status.PLAYING) {
            mediaPlayer.pause();
        }

        if (status == Status.PAUSED || status == Status.HALTED || status == Status.DISPOSED) {
            mediaPlayer.play();
        }
    }

    public void increasePlaybackSpeed(ActionEvent event) {
        this.rate += rateStep;
        mediaPlayer.setRate(rate);
    }

    public void decreasePlaybackSpeed(ActionEvent event) {
        if (this.rate - this.rateStep > this.rateStep) {
            this.rate -= 0.2;
        }
        mediaPlayer.setRate(rate);
    }

    public void reload(ActionEvent event) {
        this.rate = 1;
        mediaPlayer.setRate(rate);
        mediaPlayer.seek(mediaPlayer.getStartTime());
        mediaPlayer.play();
    }

}