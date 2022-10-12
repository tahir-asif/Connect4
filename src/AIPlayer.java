import java.util.Random;

public class AIPlayer extends Player{

    public AIPlayer(char symbol, Board board, String name) {
        super(symbol, board, name);
        // TODO Auto-generated constructor stub
    }


    @Override
    public void makeMove(Board board) {
        Random r = new Random();

        int AIWin = board.findWin(symbol, false);
        int otherWin = board.findWin('O', false);
        boolean validMove;
        int randMove;

        if (AIWin != -1) {
            board.dropPiece(symbol, AIWin);
        } else if (otherWin != -1) {
            board.dropPiece(symbol, otherWin);
        } else {
            randMove = r.nextInt(6) + 1;
            validMove = board.dropPiece(symbol, randMove);
            while (!validMove) {
                randMove = (randMove % 7) + 1;  // cycles through all possible moves starting at failed random selection 
                validMove = board.dropPiece(symbol, randMove);
            }
        }
    }
}
