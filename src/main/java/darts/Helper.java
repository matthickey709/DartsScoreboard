package darts;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Helper {

    /**
     * Will return the primary stage for the application.
     *
     * @param event
     * @return primary stage of the application
     */
    static Stage getPrimaryStage(Event event) {
        return (Stage) ((Node)event.getSource()).getScene().getWindow();
    }

    static Scene getCurrentScene(Event event) {
        return ((Node)event.getSource()).getScene();
    }
}
