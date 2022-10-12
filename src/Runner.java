public class Runner {

    public static void main(String[] args) {
        Board board = new Board();
        ConnectFour game = new ConnectFour(board);
        game.setPlayer1(new HumanPlayer('X', board, "Alice"));
        // game.setPlayer1(new AIPlayer('X', board, "Alice"));
        // game.setPlayer2(new HumanPlayer('O', board, "Bob"));
        game.setPlayer2(new AIPlayer('O', board, "Bob"));
        game.playGame();
    }
}
