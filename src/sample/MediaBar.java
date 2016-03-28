package sample;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;

public class MediaBar extends HBox {

    Slider time = new Slider();
    Slider volume = new Slider();
    Button playButton = new Button("||");
    Label volumeLabel = new Label("Volume: ");
    MediaPlayer player;

    public MediaBar(MediaPlayer player) {
        this.player = player;
        setAlignment(Pos.CENTER);
        setPadding(new Insets(5, 10, 5, 10));
        volume.setPrefWidth(70);
        volume.setMinWidth(30);
        volume.setValue(100);
        HBox.setHgrow(time, Priority.ALWAYS);
        playButton.setPrefWidth(30);

        getChildren().add(playButton);
        getChildren().add(time);
        getChildren().add(volumeLabel);
        getChildren().add(volume);

        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Status status = player.getStatus();

                if (status == Status.PLAYING) {
                    if (player.getCurrentTime().greaterThanOrEqualTo(player.getTotalDuration())) {
                        player.seek(player.getStartTime());
                        player.play();
                    } else {
                        player.pause();
                        playButton.setText(">");
                    }
                }

                if (status == Status.PAUSED || status == Status.HALTED || status == Status.DISPOSED) {
                    player.play();
                    playButton.setText("||");
                }
            }
        });

        player.currentTimeProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                updateTimeValue();
            }
        });

        time.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if (time.isPressed()) {
                    player.seek(player.getMedia().getDuration().multiply(time.getValue() / 100));
                }
            }
        });

        volume.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if (volume.isPressed()) {
                    player.setVolume(volume.getValue() / 100);
                }
            }
        });

    }

    protected void updateTimeValue() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                time.setValue(player.getCurrentTime().toMillis() / player.getTotalDuration().toMillis() * 100);
            }
        });
    }
}