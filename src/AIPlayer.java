import java.util.Random;
import java.util.ArrayList;

public class AIPlayer extends Player{

    private ArrayList<Integer> movesMade = new ArrayList<Integer>();

    public AIPlayer(char symbol, Board board, String name) {
        super(symbol, board, name);
        // TODO Auto-generated constructor stub
    }


    @Override
    public void makeMove(Board board) {
        Random r = new Random();

        int AIWin = board.findWin(symbol, false);
        int otherWin = board.findWin(symbol, true);
        boolean validMove;
        int randMove;

        if (AIWin != -1) {
            movesMade.add(AIWin);
            board.dropPiece(symbol, AIWin);
        } else if (otherWin != -1) {
            movesMade.add(otherWin);
            board.dropPiece(symbol, otherWin);
        } else {
            randMove = r.nextInt(6) + 1;
            validMove = board.dropPiece(symbol, randMove);
            while (!validMove) {
                randMove = (randMove % 7) + 1;  // cycles through all possible moves starting at failed random selection 
                validMove = board.dropPiece(symbol, randMove);
            }
            movesMade.add(randMove);
        }
        this.printMoves();
    }


    public void printMoves() {
        System.out.printf("%s ", name);
        for (int m : movesMade) { 		      
	        System.out.printf("%d ", m);
	    }
    }
}
