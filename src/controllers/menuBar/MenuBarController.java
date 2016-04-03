package controllers.menuBar;

import controllers.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

import java.io.FileNotFoundException;

public class MenuBarController {
    @FXML
    private MenuItem openFileMenuItem;

    private MainController mainController;


    public void init(MainController mainController) {
        this.mainController = mainController;
    }

    public void openFile() throws FileNotFoundException {
        mainController.setPlayBackFile(mainController.getPlaybackFileViaOpenDialog());
    }

}
