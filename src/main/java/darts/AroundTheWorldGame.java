package darts;

public class AroundTheWorldGame extends Game {

    public AroundTheWorldGame(Player player1, Player player2) {
        super(player1, player2, GameType.AROUND_THE_WORLD);
    }

    String determineNextTarget(String currentTarget) {

        if (currentTarget.equals("BULL")) {
            // When player hits bull, game is over
            return "WIN!";
        } else if (currentTarget.equals("20")) {
            // When player hits 20, onto BULL
            return "BULL";
        } else if (currentTarget.equals("WIN!")) {
            // Cannot go past win
            return currentTarget;
        } else {
            // Cast the string to a number, increment it, then return the string
            return String.valueOf(Integer.parseInt(currentTarget) + 1);
        }
    }

    String determinePreviousTarget(String currentTarget) {

        if (currentTarget.equals("1")) {
            // Cannot go below 1
            return currentTarget;
        } else if (currentTarget.equals("BULL")) {
            // BULL reduces to 20
            return "20";
        } else if (currentTarget.equals("WIN!")) {
            // Once you win, game is over!
            return currentTarget;
        } else {
            // Cast the string to int, reduce it, then return the string
            return String.valueOf(Integer.parseInt(currentTarget) - 1);
        }
    }
}
