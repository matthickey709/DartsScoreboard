package darts;

public abstract class Game {
    Player player1;
    Player player2;
    GameType gameType;

    public Game(Player player1, Player player2, GameType gameType) {
        this.player1 = player1;
        this.player2 = player2;
        this.gameType = gameType;
    }

    GameType getGameType() {
        return this.gameType;
    }


}
