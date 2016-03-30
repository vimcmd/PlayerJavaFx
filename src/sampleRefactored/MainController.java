package sampleRefactored;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

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
    @FXML
    private Button playBtn;
    @FXML
    private Button increaseRateBtn;
    @FXML
    private Button decreaseRateBtn;
    @FXML
    private Button reloadBtn;

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

        initButtons();

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

    private void initButtons() {
        playBtn.setFont(FontAwesome.FONT);
        if (mediaPlayer.autoPlayProperty().getValue()) {
            playBtn.setText(FontAwesome.ICON_PAUSE);
        } else {
            playBtn.setText(FontAwesome.ICON_PLAY);
        }

        increaseRateBtn.setFont(FontAwesome.FONT);
        increaseRateBtn.setText(FontAwesome.ICON_INCREASE_RATE);
        decreaseRateBtn.setFont(FontAwesome.FONT);
        decreaseRateBtn.setText(FontAwesome.ICON_DECREASE_RATE);
        reloadBtn.setFont(FontAwesome.FONT);
        reloadBtn.setText(FontAwesome.ICON_RELOAD);
    }

    private String getCurrentTimeFormatted() {
        return getTimeFormatted(mediaPlayer.getCurrentTime());
    }

    private String getTotalDurationFormatted() {
        return getTimeFormatted(mediaPlayer.getTotalDuration());
    }

    private String getTimeFormatted(Duration duration) {
        String time;
        int hours = (int) duration.toHours();
        int minutes = (int) duration.toMinutes() - hours * 60;
        int seconds = (int) duration.toSeconds() - hours * 60 * 60 - minutes * 60;

        if (hours > 0 & minutes > 0 & seconds > 0) {
            time = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        } else {
            time = String.format("%02d:%02d", minutes, seconds);
        }
        return time;
    }

    public void playPause(ActionEvent event) {
        Status status = mediaPlayer.getStatus();
        mediaPlayer.setRate(1);
        if (status == Status.PLAYING) {
            mediaPlayer.pause();
            playBtn.setText(FontAwesome.ICON_PLAY);
        }

        if (status == Status.PAUSED || status == Status.HALTED || status == Status.DISPOSED) {
            mediaPlayer.play();
            playBtn.setText(FontAwesome.ICON_PAUSE);
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