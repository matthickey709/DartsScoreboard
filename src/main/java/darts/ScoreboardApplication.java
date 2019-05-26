package darts;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class ScoreboardApplication extends Application {

//    private Scene homepage, classic, classicInput, cricket, aroundTheWorld;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setTitle("Darts Scoreboard");
        Image applicationIcon = new Image(getClass().getResourceAsStream("icon.png"));
        primaryStage.getIcons().add(applicationIcon);

        // Load all info from FXML files
        Parent homepage = FXMLLoader.load(getClass().getResource("homepage.fxml"));
        // Set the initial scene and show.
        primaryStage.setScene(new Scene(homepage, bounds.getWidth(), bounds.getHeight()));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
