package darts;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class HomepageController {

    /**
     * Will start a new game of classic '01 darts
     *
     * @param actionEvent
     */
    public void startClassicGame(ActionEvent actionEvent) throws Exception {
        // Get the root stage
        Scene scene = Helper.getCurrentScene(actionEvent);
        Parent classic = FXMLLoader.load(getClass().getResource("classic.fxml"));
        scene.setRoot(classic);

    }

    /**
     * Will start a new game of around the world.
     *
     * @param actionEvent
     */
    public void startAroundTheWorld(ActionEvent actionEvent) throws Exception {
        Scene scene = Helper.getCurrentScene(actionEvent);
        Parent aroundTheWorld = FXMLLoader.load(getClass().getResource("aroundTheWorld.fxml"));
        scene.setRoot(aroundTheWorld);
    }

    /**
     * Will start a new game of cricket.
     *
     * @param actionEvent
     */
    public void startCricketGame(ActionEvent actionEvent) {
        // TODO: Implement cricket mode
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Game Mode Unavailable");
        alert.setHeaderText(null);
        alert.setContentText("Cricket not implemented yet. Please use one of the other modes.");
        alert.showAndWait();
    }
}
