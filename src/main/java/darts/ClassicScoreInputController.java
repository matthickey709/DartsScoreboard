package darts;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClassicScoreInputController implements Initializable {

    private ClassicGame game;
    private Boolean isPlayer1Turn;


    // Must be set to the player's score at the beginning of the editing process.
    @FXML Text scoreValue;

    public void registerScore(ActionEvent actionEvent) throws Exception {

        Integer inputScore = Integer.parseInt(this.scoreValue.getText());

        // If score is greater than 180, have a popup window say to fix score.
        if (inputScore > 180) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Score");
            alert.setHeaderText(null);
            alert.setContentText("Score must be between 0 and 180 inclusive.");

            alert.showAndWait();
            return;
        }

        // TODO: whoever's turn it is, update their score and return the game back to ClassicController
        try{
            game.updatePlayerScore(inputScore, this.isPlayer1Turn);
        } catch (IOException e) {
            // Exception thrown if input score is greater than player's current score.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Score");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());

            alert.showAndWait();
        }

        returnToGame(actionEvent);

    }

    public void cancelInput(ActionEvent actionEvent) throws Exception {

        returnToGame(actionEvent);

    }

    public void inputButtonNumber(ActionEvent actionEvent) {

        // Take the current input button pressed at append it to the end of the score string.
        Button pressedButton = (Button)actionEvent.getSource();
        String buttonVal = pressedButton.getText();

        // If appending will not let score exceed 3 digits, allow appending.
        if (this.scoreValue.getText().length() < 3) {
            assert this.scoreValue.getText().length() < 3;
            appendButtonPressToScore(buttonVal);
            assert this.scoreValue.getText().length() <= 3;
        }

    }

    private void appendButtonPressToScore(String buttonVal) {
        // If score is just zero, replace zero with input number, otherwise append
        if (this.scoreValue.getText().equals("0")) {
            this.scoreValue.setText(buttonVal);
        } else {
            this.scoreValue.setText(this.scoreValue.getText() + buttonVal);
        }

    }

    public void backspaceScoreValue(ActionEvent actionEvent) {

        // No need to backspace if there is no score entered.
        if (this.scoreValue.getText().equals("0")) {
            return;
        } else if (this.scoreValue != null && this.scoreValue.getText().length() > 1) {
            // If string is not null and has a length and is not just zero, remove the character
            this.scoreValue.setText(this.scoreValue.getText().substring(0, this.scoreValue.getText().length() - 1));
        } else {
            this.scoreValue.setText("0");
        }

    }

    public void returnToGame(ActionEvent actionEvent) throws Exception {
        Stage primaryStage = Helper.getPrimaryStage(actionEvent);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("classic.fxml"));
        Parent classic = loader.load();
        ClassicController classicController = loader.getController();
        classicController.setGame(this.game);
        classicController.updateScoreboard();
        Scene scene = scoreValue.getScene();
        scene.setRoot(classic);
    }

    public void setGame(ClassicGame game) {
        this.game = game;
    }

    public void setIsPlayer1Turn(Boolean isPlayer1Turn) {
        this.isPlayer1Turn = isPlayer1Turn;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Initialize the score appearing to zero.
        this.scoreValue.setText("0");

    }
}
