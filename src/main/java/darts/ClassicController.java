package darts;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ClassicController implements Initializable {

    @FXML Text p1Name, p2Name;
    private ClassicGame game;

    @FXML Text p1Sets, p2Sets;
    @FXML Text p1Legs, p2Legs;
    @FXML Text p1Score, p2Score;

    @FXML Pane p1SetsPane, p2SetsPane;
    @FXML Pane p1LegsPane, p2LegsPane;
    @FXML Pane p1ScorePane, p2ScorePane;

    private static final Float H_MULTIPLIER = 3.5F;
    private static final Integer V_MULTIPLIER = 2;

    public void editPlayer1Score(MouseEvent mouseEvent) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("classicScoreInput.fxml"));
        Parent root = loader.load();
        ClassicScoreInputController inputController = loader.getController();
        inputController.setGame(this.game);
        inputController.setIsPlayer1Turn(true);
        Scene scene = Helper.getCurrentScene(mouseEvent);
        scene.setRoot(root);

    }

//    public void editPlayer1Score(TouchEvent touchEvent) throws Exception {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("classicScoreInput.fxml"));
//        Parent root = loader.load();
//        ClassicScoreInputController inputController = loader.getController();
//        inputController.setGame(this.game);
//        inputController.setIsPlayer1Turn(true);
//        Stage stage = Helper.getPrimaryStage(touchEvent);
//        stage.setScene(new Scene(root));
//        stage.show();
//    }

    public void editPlayer2Score(MouseEvent mouseEvent) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("classicScoreInput.fxml"));
        Parent root = loader.load();
        ClassicScoreInputController inputController = loader.getController();
        inputController.setGame(this.game);
        inputController.setIsPlayer1Turn(false);
        Scene scene = Helper.getCurrentScene(mouseEvent);
        scene.setRoot(root);
    }

//    public void editPlayer2Score(TouchEvent touchEvent) throws Exception {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("classicScoreInput.fxml"));
//        Parent root = loader.load();
//        ClassicScoreInputController inputController = loader.getController();
//        inputController.setGame(this.game);
//        inputController.setIsPlayer1Turn(false);
//        Stage stage = Helper.getPrimaryStage(touchEvent);
//        stage.setScene(new Scene(root));
//        stage.show();
//    }

    public void returnToHomepage(ActionEvent actionEvent) throws Exception {
        Parent homepage = FXMLLoader.load(getClass().getResource("homepage.fxml"));
        Scene scene = Helper.getCurrentScene(actionEvent);
        scene.setRoot(homepage);
    }

    public void setGame(ClassicGame game) {
        this.game = game;
        this.updateScoreboard();
    }

    void updateScoreboard() {
        // Refresh the sets and legs
        this.p1Sets.setText(game.getPlayer1Sets().toString());
        this.p2Sets.setText(game.getPlayer2Sets().toString());
        this.p1Legs.setText(game.getPlayer1Legs().toString());
        this.p2Legs.setText(game.getPlayer2Legs().toString());

        // Refresh the scores
        this.p1Score.setText(game.getPlayer1Score().toString());
        this.p2Score.setText(game.getPlayer2Score().toString());

        // Set up dialog in case a player wins

        if (game.getPlayer1Sets() == 3 || game.getPlayer2Sets() == 3) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("GAME OVER");
            // If a player has won, show a dialog and reset the game
            if (game.getPlayer1Sets() == 3) {
                // Player 1 wins
                alert.setHeaderText("Player 1 wins!");

            } else if (game.getPlayer2Sets() == 3) {
                // Player 2 wins
                alert.setHeaderText("Player 2 wins!");
            }

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
                Scene scene = this.p1Score.getScene();
                Parent homepage = null;
                try {
                    homepage = FXMLLoader.load(getClass().getResource("homepage.fxml"));
                } catch (Exception e) {

                }

                scene.setRoot(homepage);
            }
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Initialize the game object
        this.game = new ClassicGame(new Player(p1Name.getText()), new Player(p2Name.getText()), 501);

        // Initially set sets and legs to zero for both players and do their centering
        this.p1Sets.setText(game.getPlayer1Sets().toString());
        this.p1Sets.layoutXProperty().bind(this.p1SetsPane.widthProperty().subtract(p1Sets.wrappingWidthProperty()).divide(H_MULTIPLIER));
        this.p1Sets.layoutYProperty().bind(this.p1SetsPane.heightProperty().subtract(p1Sets.wrappingWidthProperty()).divide(V_MULTIPLIER));

        this.p2Sets.setText(game.getPlayer2Sets().toString());
        this.p2Sets.layoutXProperty().bind(this.p2SetsPane.widthProperty().subtract(p2Sets.wrappingWidthProperty()).divide(H_MULTIPLIER));
        this.p2Sets.layoutYProperty().bind(this.p2SetsPane.heightProperty().subtract(p2Sets.wrappingWidthProperty()).divide(V_MULTIPLIER));

        this.p1Legs.setText(game.getPlayer1Legs().toString());
        this.p1Legs.layoutXProperty().bind(this.p1LegsPane.widthProperty().subtract(p1Legs.wrappingWidthProperty()).divide(H_MULTIPLIER));
        this.p1Legs.layoutYProperty().bind(this.p1LegsPane.heightProperty().subtract(p1Legs.wrappingWidthProperty()).divide(V_MULTIPLIER));

        this.p2Legs.setText(game.getPlayer2Legs().toString());
        this.p2Legs.layoutXProperty().bind(this.p1LegsPane.widthProperty().subtract(p2Legs.wrappingWidthProperty()).divide(H_MULTIPLIER));
        this.p2Legs.layoutYProperty().bind(this.p1LegsPane.heightProperty().subtract(p2Legs.wrappingWidthProperty()).divide(V_MULTIPLIER));

        // Initially set remaining score to 501 for both players and do their centering
        // TODO: have a previous screen that players can choose between 301 and 501 starting scores
        this.p1Score.setText(game.getStartingScore().toString());
        this.p1Score.layoutXProperty().bind(this.p1ScorePane.widthProperty().subtract(p1Score.wrappingWidthProperty()).divide(H_MULTIPLIER));
        this.p1Score.layoutYProperty().bind(this.p1ScorePane.heightProperty().subtract(p1Score.wrappingWidthProperty()).divide(V_MULTIPLIER));

        this.p2Score.setText(game.getStartingScore().toString());
        this.p2Score.layoutXProperty().bind(this.p2ScorePane.widthProperty().subtract(p2Score.wrappingWidthProperty()).divide(H_MULTIPLIER));
        this.p2Score.layoutYProperty().bind(this.p2ScorePane.heightProperty().subtract(p2Score.wrappingWidthProperty()).divide(V_MULTIPLIER));



    }
}
