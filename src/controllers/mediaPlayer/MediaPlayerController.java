package controllers.mediaPlayer;

import controllers.MainController;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.media.MediaView;

public class MediaPlayerController {

    private MainController mainController;

    @FXML
    private MediaView mediaView;

    public void init(MainController mainController) {
        this.mainController = mainController;
        mediaViewFitContent();
    }

    private void mediaViewFitContent() {
        DoubleProperty width = mediaView.fitWidthProperty();
        DoubleProperty height = mediaView.fitHeightProperty();
        width.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
        height.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));
    }


}
