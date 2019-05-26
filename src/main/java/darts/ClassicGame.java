package darts;

import java.io.IOException;

public class ClassicGame extends Game {

    private final Integer startingScore;
    private Integer player1Score, player2Score;
    private Integer player1Sets, player2Sets;
    private Integer player1Legs, player2Legs;

    public ClassicGame(Player player1, Player player2, Integer startingScore) {
        super(player1, player2, GameType.CLASSIC);
        this.player1Sets = 0;
        this.player2Sets = 0;
        this.player1Legs = 0;
        this.player2Legs = 0;
        this.startingScore = startingScore;
        this.player1Score = startingScore;
        this.player2Score = startingScore;
    }

    public void updatePlayerScore(Integer recordedScore, Boolean isPlayer1Turn) throws IOException {

        Integer playerScore;

        if (isPlayer1Turn) {
            if (player1Score >= recordedScore) {
                // update player 1 score
                this.setPlayer1Score(player1Score - recordedScore);
                playerScore = this.getPlayer1Score();
            } else {
                throw new IOException("Entered score is greater than current score!");
            }
        } else {
            if (player2Score >= recordedScore) {
                // update player 1 score
                setPlayer2Score(player2Score - recordedScore);
                playerScore = this.getPlayer2Score();
            } else {
                throw new IOException("Entered score is greater than current score!");
            }
        }

        // If a player has won, increase their legs/sets accordingly
        if (playerScore == 0) {
            updateLegsAndSets(isPlayer1Turn);
            setPlayer1Score(this.startingScore);
            setPlayer2Score(this.startingScore);
        }
    }

    private void updateLegsAndSets(Boolean isPlayer1Turn) {
        if (isPlayer1Turn) {
            if (this.getPlayer1Legs() == 2) {
                // If player has 2 legs won, increase their sets. Controller will handle a win.
                this.setPlayer1Sets(this.getPlayer1Sets() + 1);
                this.setPlayer1Legs(0);
            } else {
                // Increase player's leg count
                this.setPlayer1Legs(this.getPlayer1Legs() + 1);
                assert this.getPlayer1Legs() < 3;
            }
        } else {
            if (this.getPlayer2Legs() == 2) {
                // If player has 2 legs won, increase their sets. Controller will handle a win.
                this.setPlayer2Sets(this.getPlayer2Sets() + 1);
                this.setPlayer2Legs(0);
            } else {
                // Increase player's leg count
                this.setPlayer2Legs(this.getPlayer2Legs() + 1);
                assert this.getPlayer2Legs() < 3;
            }
        }
    }

    public Integer getStartingScore() {
        return startingScore;
    }

    public Integer getPlayer1Score() {
        return player1Score;
    }

    public void setPlayer1Score(Integer player1Score) {
        this.player1Score = player1Score;
        assert player1Score <= startingScore && player1Score >= 0;
    }

    public Integer getPlayer2Score() {
        return player2Score;
    }

    public void setPlayer2Score(Integer player2Score) {
        this.player2Score = player2Score;
    }

    public Integer getPlayer1Sets() {
        return player1Sets;
    }

    public void setPlayer1Sets(Integer player1Sets) {
        this.player1Sets = player1Sets;
    }

    public Integer getPlayer2Sets() {
        return player2Sets;
    }

    public void setPlayer2Sets(Integer player2Sets) {
        this.player2Sets = player2Sets;
    }

    public Integer getPlayer1Legs() {
        return player1Legs;
    }

    public void setPlayer1Legs(Integer player1Legs) {
        this.player1Legs = player1Legs;
    }

    public Integer getPlayer2Legs() {
        return player2Legs;
    }

    public void setPlayer2Legs(Integer player2Legs) {
        this.player2Legs = player2Legs;
    }
}
