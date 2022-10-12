public class Board {

	private final int NUM_OF_COLUMNS = 7;
	private final int NUM_OF_ROWS = 6;
	private final int WIN_LENGTH = 4;  // pieces needed in a row to win; default = 4
	private final char NO_SYMBOL = '.';  // \u0000 kind of works

	char[][] board = new char[NUM_OF_ROWS][NUM_OF_COLUMNS];
	

	public Board() {  // reset does exactly this so change that
		for (int i = 0; i < NUM_OF_ROWS; i++)
			for (int j = 0; j < NUM_OF_COLUMNS; j++)
				board[i][j] = NO_SYMBOL;
	}


	public void printBoard() {
		System.out.print("\n");
		for (int i = 0; i < NUM_OF_COLUMNS; i++) {
			System.out.printf(" %d", i + 1);
		}
		System.out.print("\n");
		for (int i = NUM_OF_ROWS - 1; i >= 0; i--) {
			for (char col : board[i]) {
				System.out.printf(" %c", col);
			}
			System.out.print("\n");
		}
		for (int i = 0; i < NUM_OF_COLUMNS; i++) {
			System.out.print(" -");
		}
		System.out.print("\n");
	}

	
	public boolean dropPiece(char symbol, int column) {
		for (char[] row : board) {
			if (row[column - 1] == NO_SYMBOL) {
				row[column - 1] = symbol;
				return true;
			}
		}
		return false;
	}
	

	public boolean containsWin() {
		char currentSymbol;
		int diagWin; // positive slope graph i.e pointing up and right
		int horiWin;
		int vertWin;
		int negDiagWin; // negative slope graph i.e pointing down and right

		for (int row = 0; row < NUM_OF_ROWS; row++) {
			for (int col = 0; col < NUM_OF_COLUMNS; col++) {
				currentSymbol = board[row][col];

				if (currentSymbol == NO_SYMBOL) {continue;}

				diagWin = 1;
				horiWin = 1;
				vertWin = 1;
				negDiagWin = 1;

				for (int i = 1; i < WIN_LENGTH; i++) {
					try {
						if (board[row + i][col + i] == currentSymbol) {
							diagWin++;
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						break;
					}
				}
				for (int i = 1; i < WIN_LENGTH; i++) {
					try {
						if (board[row][col + i] == currentSymbol) {
							horiWin++;
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						break;
					}
				}
				for (int i = 1; i < WIN_LENGTH; i++) {
					try {
						if (board[row + i][col] == currentSymbol) {
							vertWin++;
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						break;
					}
				}
				for (int i = 1; i < WIN_LENGTH; i++) {
					try {
						if (board[row - i][col + i] == currentSymbol) {
							negDiagWin++;
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						break;
					}
				}

				if (diagWin == WIN_LENGTH || horiWin == WIN_LENGTH || vertWin == WIN_LENGTH || negDiagWin == WIN_LENGTH) {
					return true;
				}
			}
		}
		return false;
	}


	public int findWin(char symbol, boolean oppositeSymbol) {
		int diagWin;
		int horiWin;
		int vertWin;
		int negDiagWin;

		if (oppositeSymbol) {
			for (int row = 0; row < NUM_OF_ROWS; row++) {
				for (int col = 0; col < NUM_OF_COLUMNS; col++) {
					if (board[row][col] == NO_SYMBOL || board[row][col] == symbol) {
						continue;
					} else {
						symbol = board[row][col];
						break;
					}
				}
			}
		}

		for (int row = 0; row < NUM_OF_ROWS; row++) {
			for (int col = 0; col < NUM_OF_COLUMNS; col++) {
				if (board[row][col] != symbol) {continue;}

				// fuckkckck ki love trainsssss!!!!!!!!!!

				// counting how many symbols in a row appear
				diagWin = 1;
				horiWin = 1;
				vertWin = 1;
				negDiagWin = 1;

				// find diagonal win conditions
				for (int i = 1; i < WIN_LENGTH - 1; i++) {
					try {
						if (board[row + i][col + i] == symbol) {
							diagWin++;
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						break;
					}
				} 
				if (diagWin == WIN_LENGTH - 1) {
					try {
						// checks if next diagonal space is open to the right && there is a piece below it
						if (board[row + WIN_LENGTH - 1][col + WIN_LENGTH - 1] == NO_SYMBOL
							&& board[row + WIN_LENGTH - 2][col + WIN_LENGTH - 1] != NO_SYMBOL) {
							return col + WIN_LENGTH;
						}
						// checks if next diagonal space is open to the left && there is a piece below it
						if (row - 2 >= 0) {  // if on the 3rd row or higher
							if (board[row - 1][col - 1] == NO_SYMBOL
								&& board[row - 2][col - 1] != NO_SYMBOL) {
								return col;
							}
						} else if (row - 1 >= 0) {  // ~if on 2nd row
							if (board[row - 1][col - 1] == NO_SYMBOL) {
								return col;
							}
						}
					} catch (ArrayIndexOutOfBoundsException e) {}
				}

				// find inverse diagonal win conditions
				for (int i = 1; i < WIN_LENGTH - 1; i++) {
					try {
						if (board[row - i][col + i] == symbol) {
							negDiagWin++;
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						break;
					}
				} 
				if (negDiagWin == WIN_LENGTH - 1) {
					try {
						// checks if next diagonal space is open to the right && there is a piece below it
						if (board[row - WIN_LENGTH + 1][col + WIN_LENGTH - 1] == NO_SYMBOL
							&& board[row - WIN_LENGTH][col + WIN_LENGTH - 1] != NO_SYMBOL) {
							return col + WIN_LENGTH;
						}
						// checks if next diagonal space is open to the left && there is a piece below it
						if (board[row + 1][col - 1] == NO_SYMBOL
							&& board[row][col - 1] != NO_SYMBOL) {
							return col;
						}
					} catch (ArrayIndexOutOfBoundsException e) {}
				}

				// find horizontal win conditions
				for (int i = 1; i < WIN_LENGTH - 1; i++) {
					try {
						if (board[row][col + i] == symbol) {
							horiWin++;
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						break;
					}
				}
				if (horiWin == WIN_LENGTH - 1) {
					try {
						// checks if horizontal space to the right is available
						if (board[row][col + WIN_LENGTH - 1] == NO_SYMBOL) {
							// if the pattern is not on the first row
							if (row - 1 >= 0) {
								// then check if their is space underneath the winning spot
								if (board[row - 1][col + WIN_LENGTH - 1] != NO_SYMBOL) {
									return col + WIN_LENGTH;
								}
							} else {  // if it is on first row, then no need to check underneath
								return col + WIN_LENGTH;
							}
						}
						// checks if horizontal space to the left is available
						if (board[row][col - 1] == NO_SYMBOL) {
							// if the pattern is not on the first row
							if (row - 1 >= 0) {
								// then check if their is space underneath the winning spot
								if (board[row - 1][col - 1] != NO_SYMBOL) {
									return col;
								}
							} else {  // if it is on first row, then no need to check underneath
								return col;
							}
						}
					} catch (ArrayIndexOutOfBoundsException e) {}
				}

				// find vertical win conditions
				for (int i = 1; i < WIN_LENGTH - 1; i++) {
					try {
						if (board[row + i][col] == symbol) {
							vertWin++;
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						break;
					}
				}
				if (vertWin == WIN_LENGTH - 1) {
					try {
						if (board[row + WIN_LENGTH - 1][col] == NO_SYMBOL) {
							return col + 1;  // maybe no +1
						}
					} catch (ArrayIndexOutOfBoundsException e) {}
				}
			}
		}
		return -1;  // if no win was found on the board
	}


	public boolean isTie() {
		for (int i = 0; i < NUM_OF_ROWS; i++)
			for (int j = 0; j < NUM_OF_COLUMNS; j++)
				if (board[i][j] == '.')
					{return false;}

		return true;
	}
	

	public void reset() {
		for (int i = 0; i < NUM_OF_ROWS; i++)
			for (int j = 0; j < NUM_OF_COLUMNS; j++)
				board[i][j] = '.';
	}
	

	public static void main(String[] args) {
		Board b = new Board();
		b.board[0][2] = 'B';
		b.board[0][3] = 'B';
		b.board[0][4] = 'B';
		b.board[0][5] = 'A';
		b.printBoard();
	}
}
