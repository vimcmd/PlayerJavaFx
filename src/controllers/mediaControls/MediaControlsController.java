package controllers.mediaControls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import utils.FontAwesome;
import controllers.MainController;

import java.io.File;
import java.net.MalformedURLException;

/**
 * fx:id="mediaControls"
 */
public class MediaControlsController {

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
    private HBox hBox;
    @FXML
    private Button fastPlayback;
    @FXML
    private Slider seekSlider;
    @FXML
    private Label totalTimeLabel;
    @FXML
    private Button playPauseBtn;

    // initial volume value = 100, after uses for remember value on mute/unmute action
    private double volumeValue = 100;
    //private MediaPlayer mediaPlayer;
    //private Media media;
    private double rate = 1;
    private double rateStep = 0.2;

    public void init(MainController mainController) {
        this.mainController = mainController;
        initControls();
        addListeners();

        // TODO: 01.04.2016 get file uri from another class
        // TODO: 01.04.2016 get mediaPlayer from another class or inject it in method argument
        //String file = new File("D:/video.mp4").getAbsolutePath();
        //try {
        //    media = new Media(new File(file).toURI().toURL().toExternalForm());
        //} catch (MalformedURLException e) {
        //    e.printStackTrace();
        //}
        //mediaPlayer = new MediaPlayer(media);
        //mediaView.setMediaPlayer(mediaPlayer);
        //mediaPlayer.setAutoPlay(true);
    }


    public void initControls() {
        slowPlayback.setFont(FontAwesome.FONT);
        slowPlayback.setText(FontAwesome.ICON_BACKWARD2);
        playPauseBtn.setFont(FontAwesome.FONT);

        //if (mediaPlayer.autoPlayProperty().getValue()) {
        //    playPauseBtn.setText(FontAwesome.ICON_PAUSE2);
        //} else {
        //    playPauseBtn.setText(FontAwesome.ICON_PLAY3);
        //}
        playPauseBtn.setText(FontAwesome.ICON_PLAY3);

        fastPlayback.setFont(FontAwesome.FONT);
        fastPlayback.setText(FontAwesome.ICON_FORWARD3);

        volumeLabel.setFont(FontAwesome.FONT);
        // TODO: 31.03.2016 set volumeLabel based on media
        volumeLabel.setText(FontAwesome.ICON_VOLUME_HIGH);
        volumeLabel.setPrefWidth(FontAwesome.FONT.getSize() + 2); // prevent bouncing when icon changes level (mute-low-medium-high)

        currentTimeLabel.setText("00:00");
        // TODO: 31.03.2016 set totalTime based on media duration
        totalTimeLabel.setText("00:00");

        final int START_PLAYBACK_POSITION = 0;
        seekSlider.setValue(START_PLAYBACK_POSITION);

        volumeSlider.setValue(100);

    }

    private void addListeners() {
        volumeSlider.valueProperty().addListener(observable -> {
            //mediaPlayer.setVolume(volumeSlider.getValue() / 100);
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
        });

        //seekSlider.valueProperty().addListener(observable -> {
        //    if (seekSlider.isPressed()) {
        //        mediaPlayer.seek(mediaPlayer.getMedia().getDuration().multiply(seekSlider.getValue() / 100));
        //    }
        //});
    }

    public void muteClick(MouseEvent event) {
        if (volumeSlider.getValue() > 0) {
            this.volumeValue = volumeSlider.getValue();
            volumeSlider.setValue(0);
        } else {
            volumeSlider.setValue(this.volumeValue);
        }
    }

    // TODO: 01.04.2016 move to util class?
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

    //private String getCurrentTimeFormatted() {
    //    return getTimeFormatted(mediaPlayer.getCurrentTime());
    //}
    //
    //private String getTotalDurationFormatted() {
    //    return getTimeFormatted(mediaPlayer.getTotalDuration());
    //}

    //public void playPause(ActionEvent event) {
    //    MediaPlayer.Status status = mediaPlayer.getStatus();
    //    mediaPlayer.setRate(1);
    //    if (status == MediaPlayer.Status.PLAYING) {
    //        mediaPlayer.pause();
    //        playPauseBtn.setText(FontAwesome.ICON_PLAY3);
    //    }
    //
    //    if (status == MediaPlayer.Status.PAUSED || status == MediaPlayer.Status.HALTED || status == MediaPlayer.Status.DISPOSED) {
    //        mediaPlayer.play();
    //        playPauseBtn.setText(FontAwesome.ICON_PAUSE2);
    //    }
    //}

    //public void increasePlaybackSpeed(ActionEvent event) {
    //    this.rate += rateStep;
    //    mediaPlayer.setRate(rate);
    //}

    //public void decreasePlaybackSpeed(ActionEvent event) {
    //    if (this.rate - this.rateStep > this.rateStep) {
    //        this.rate -= 0.2;
    //    }
    //    mediaPlayer.setRate(rate);
    //}

    //public void reload(ActionEvent event) {
    //    this.rate = 1;
    //    mediaPlayer.setRate(rate);
    //    mediaPlayer.seek(mediaPlayer.getStartTime());
    //    mediaPlayer.play();
    //}


}
