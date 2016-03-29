package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.MalformedURLException;

public class Main extends Application {

    private Player player;
    private FileChooser fileChooser;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        //primaryStage.setTitle("Hello World");
        //primaryStage.setScene(new Scene(root, 300, 275));
        //primaryStage.show();

        MenuItem openMenu = new MenuItem("Open");
        Menu fileMenu = new Menu("File");
        MenuBar menuBar = new MenuBar();

        fileMenu.getItems().add(openMenu);
        menuBar.getMenus().add(fileMenu);

        fileChooser = new FileChooser();

        openMenu.setOnAction(e -> {
            player.player.pause();
            File file = fileChooser.showOpenDialog(primaryStage);

            if (file != null) {
                try {
                    player = new Player(file.toURI().toURL().toExternalForm());
                    Scene scene = new Scene(player, 640, 388, Color.BLACK);
                    primaryStage.setScene(scene);
                } catch (MalformedURLException e1) {
                    e1.printStackTrace();
                }
            }
        });


        player = new Player("file:///D:/video.mp4");
        player.setTop(menuBar);
        Scene scene = new Scene(player, 640, 388, Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}