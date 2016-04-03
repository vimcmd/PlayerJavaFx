package controllers.mediaControls;

import controllers.MainController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import utils.DurationFormatter;
import utils.FontAwesome;

/**
 * fx:id="mediaControls"
 */
public class MediaControlsController implements IMediaControls {

    private MainController mainController;

    @FXML
    private Button slowPlayback;
    @FXML
    private Label currentTimeLabel;
    @FXML
    private Label volumeLabel;
    @FXML
    private Slider volumeSlider;
    @FXML
    private Button fastPlayback;
    @FXML
    private Slider seekSlider;
    @FXML
    private Label totalTimeLabel;
    @FXML
    private Button playPauseBtn;

    private MediaPlayer mediaPlayer;
    private double volumeValue = 100; // initial volume value = 100, after uses for remember value on mute/unmute action
    private double rate = 1;
    private double rateStep = 0.2;

    public void init(MainController mainController) {
        this.mainController = mainController;
        initControls();

        mediaPlayer.setOnReady(new Runnable() {
            @Override
            public void run() {
                totalTimeLabel.setText(getTotalDurationFormatted());
                addListeners();
                mediaPlayer.setVolume(volumeSlider.getValue() / 100);
            }
        });

    }

    public void initControls() {
        mediaPlayer = mainController.getMediaPlayer();

        slowPlayback.setFont(FontAwesome.FONT);
        slowPlayback.setText(FontAwesome.ICON_BACKWARD2);
        playPauseBtn.setFont(FontAwesome.FONT);

        if (mediaPlayer.autoPlayProperty().getValue()) {
            playPauseBtn.setText(FontAwesome.ICON_PAUSE2);
        } else {
            playPauseBtn.setText(FontAwesome.ICON_PLAY3);
        }

        fastPlayback.setFont(FontAwesome.FONT);
        fastPlayback.setText(FontAwesome.ICON_FORWARD3);

        volumeLabel.setFont(FontAwesome.FONT);
        volumeLabel.setText(FontAwesome.ICON_VOLUME_HIGH);
        volumeLabel.setPrefWidth(FontAwesome.FONT.getSize() + 2); // prevent bouncing when icon changes level (mute-low-medium-high)
        volumeLabelRefresh();

        currentTimeLabel.setText("00:00");
        totalTimeLabel.setText("00:00");

        final int START_PLAYBACK_POSITION = 0;
        seekSlider.setValue(START_PLAYBACK_POSITION);

        if (volumeSlider.getValue() > 0 && volumeSlider.getValue() < 100) {
            volumeValue = volumeSlider.getValue();
        }

    }

    private void addListeners() {
        volumeSlider.valueProperty().addListener(observable -> {
            mediaPlayer.setVolume(volumeSlider.getValue() / 100);
            volumeLabelRefresh();
        });

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
    }

    private void volumeLabelRefresh() {
        if (volumeSlider.getValue() >= 70) {
            volumeLabel.setText(FontAwesome.ICON_VOLUME_HIGH);
        }
        if (volumeSlider.getValue() >= 30 && volumeSlider.getValue() < 70) {
            volumeLabel.setText(FontAwesome.ICON_VOLUME_MEDIUM);
        }
        if (volumeSlider.getValue() > 0 && volumeSlider.getValue() < 30) {
            volumeLabel.setText(FontAwesome.ICON_VOLUME_LOW);
        }
        if (volumeSlider.getValue() == 0) {
            volumeLabel.setText(FontAwesome.ICON_VOLUME_MUTE2);
        }
    }

    public void muteClick(MouseEvent event) {
        if (volumeSlider.getValue() > 0) {
            this.volumeValue = volumeSlider.getValue();
            volumeSlider.setValue(0);
        } else {
            volumeSlider.setValue(this.volumeValue);
        }
    }

    private String getCurrentTimeFormatted() {
        return DurationFormatter.getTimeFormatted(mediaPlayer.getCurrentTime());
    }

    private String getTotalDurationFormatted() {
        return DurationFormatter.getTimeFormatted(mediaPlayer.getTotalDuration());
    }

    public void playPause(ActionEvent event) {
        MediaPlayer.Status status = mediaPlayer.getStatus();
        mediaPlayer.setRate(1);
        if (status == MediaPlayer.Status.PLAYING) {
            mediaPlayer.pause();
            playPauseBtn.setText(FontAwesome.ICON_PLAY3);
        }

        if (status == MediaPlayer.Status.PAUSED || status == MediaPlayer.Status.HALTED || status == MediaPlayer.Status.DISPOSED) {
            mediaPlayer.play();
            playPauseBtn.setText(FontAwesome.ICON_PAUSE2);
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
