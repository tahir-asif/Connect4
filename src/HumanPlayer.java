import java.util.Scanner;

public class HumanPlayer extends Player {

    public HumanPlayer(char symbol, Board board, String name) {
        super(symbol, board, name);
        // TODO Auto-generated constructor stub
    }


    @Override
    public void makeMove(Board board) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter a column number: ");
        boolean validMove = board.dropPiece(symbol, scanner.nextInt());

        while (validMove == false) {
            System.out.println("That column is full. Please try another column: ");
            validMove = board.dropPiece(symbol, scanner.nextInt());
        }

        // scanner.close();
    }
}
