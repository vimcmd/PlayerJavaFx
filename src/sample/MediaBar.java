package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.media.MediaPlayer;

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
    }
}
