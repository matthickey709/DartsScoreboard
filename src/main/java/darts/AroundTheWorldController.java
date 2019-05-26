package darts;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AroundTheWorldController implements Initializable {

    private AroundTheWorldGame game;

    //! Elements in the view

    // Text fields
    @FXML private Text gameTitle;
    @FXML private Text p1Name;
    @FXML private Text p2Name;
    @FXML private Text p1Target;
    @FXML private Text p2Target;

    // Buttons
    @FXML private Button quitBtn;
    @FXML private Button p1IncreaseBtn;
    @FXML private Button p1DecreaseBtn;
    @FXML private Button p2IncreaseBtn;
    @FXML private Button p2DecreaseBtn;


    /**
     * Will increase the target for Player 1.
     * Increases number from 1-20, after player presses increase when on 20,
     * the next target will be BULL. When increase is pressed when player on BULL,
     * game ends and Player 1 will win.
     *
     * @param actionEvent
     */
    public void increasePlayer1(ActionEvent actionEvent) {

        this.p1Target.setText(this.game.determineNextTarget(this.p1Target.getText()));
        if (this.p1Target.getText().equals("WIN!")) {
            gameOver("Player 1");
        }
    }

    /**
     * Will decrease the target for Player 1.
     * Will not decrease below 1. Decreasing when on BULL will go to 20.
     *
     * @param actionEvent
     */
    public void decreasePlayer1(ActionEvent actionEvent) {
        this.p1Target.setText(this.game.determinePreviousTarget(this.p1Target.getText()));
    }

    /**
     * Will increase the target for Player 2.
     * Increases number from 1-20, after player presses increase when on 20,
     * the next target will be BULL. When increase is pressed when player on BULL,
     * game ends and Player 1 will win.
     *
     * @param actionEvent
     */
    public void increasePlayer2(ActionEvent actionEvent) {
        this.p2Target.setText(this.game.determineNextTarget(this.p2Target.getText()));
        if (this.p2Target.getText().equals("WIN!")) {
            gameOver("Player 2");
        }
    }

    /**
     * Will decrease the target for Player 2.
     * Will not decrease below 1. Decreasing when on BULL will go to 20.
     *
     * @param actionEvent
     */
    public void decreasePlayer2(ActionEvent actionEvent) {
        this.p2Target.setText(this.game.determinePreviousTarget(this.p2Target.getText()));
    }

    private void gameOver(String winner) {
        // Open an alert showing the winner of the game and option to quit or play again
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("GAME OVER");
        alert.setHeaderText(winner + " wins!");

        alert.setContentText("Choose your option.");

        ButtonType buttonTypePlay = new ButtonType("Play again");
        ButtonType buttonTypeQuit = new ButtonType("Return to Main Menu");

        alert.getButtonTypes().setAll(buttonTypePlay, buttonTypeQuit);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypePlay){
            // Start game over with initial conditions
            this.initialize(null, null);
        } else {
            // Return to main menu
            Scene scene = this.gameTitle.getScene();
            Parent homepage = null;
            try {
                homepage = FXMLLoader.load(getClass().getResource("homepage.fxml"));
            } catch (Exception e) {

            }

            scene.setRoot(homepage);
        }
    }


    /**
     * Discards progress for the current game and returns to
     * the main menu/homepage for next game selection.
     *
     * @param actionEvent
     */
    public void returnToHomepage(ActionEvent actionEvent) throws Exception {
        Parent homepage = FXMLLoader.load(getClass().getResource("homepage.fxml"));
        this.gameTitle.getScene().setRoot(homepage);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Set game title
        gameTitle.setText("Around the World");

        // Each player will aim for 1 first.
        p1Target.setText("1");
        p2Target.setText("1");

        // Set the Player names
        // TODO: pass in player names from profiles
        p1Name.setText("Player 1");
        p2Name.setText("Player 2");

        // Initialize the game object
        this.game = new AroundTheWorldGame(new Player(p1Name.getText()), new Player(p2Name.getText()));

    }
}
