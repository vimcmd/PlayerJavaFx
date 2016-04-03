package controllers.menuBar;

import controllers.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;

import java.io.File;

public class MenuBarController {
    @FXML
    private MenuItem openFileMenuItem;

    private MainController mainController;


    public void init(MainController mainController) {
        this.mainController = mainController;
    }

    public void openFile() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter mediaFiles = new FileChooser.ExtensionFilter("Media files", "*.mp4", "*.avi");
        fileChooser.getExtensionFilters().add(mediaFiles);

        File file = fileChooser.showOpenDialog(mainController.getPrimaryStage());
        if (file != null) {
            this.mainController.setPlayBackFile(file);
        }
    }

}
