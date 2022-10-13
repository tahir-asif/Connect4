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
		int vertWin;
		boolean doubleBreak = false;

		if (oppositeSymbol) {
			for (int row = 0; row < NUM_OF_ROWS; row++) {
				for (int col = 0; col < NUM_OF_COLUMNS; col++) {
					if (board[row][col] == NO_SYMBOL || board[row][col] == symbol) {
						continue;
					} else {
						symbol = board[row][col];
						doubleBreak = true;
						break;
					}
				}
				if (doubleBreak == true) {break;}
			}
		}

		for (int row = 0; row < NUM_OF_ROWS; row++) {
			for (int col = 0; col < NUM_OF_COLUMNS; col++) {
				if (board[row][col] != symbol) {continue;}

				// finding horizontal win condition
				if (col + 2 < NUM_OF_COLUMNS) {  // prevents out of bounds errors
					if (board[row][col + 1] == symbol && board[row][col + 2] == symbol) {
						if (col + 3 < NUM_OF_COLUMNS) {  // prevent out of bounds errors
							if (row - 1 >= 0) {  // prevents out of bounds errors
								if (board[row][col + 3] == NO_SYMBOL && board[row - 1][col + 3] != NO_SYMBOL) {
									return col + 3 + 1;  // +1 is to 1 index instead of 0 index
								}
							} else if (board[row][col + 3] == NO_SYMBOL) {
								return col + 3 + 1;
							}
						}
						if (col - 1 >= 0) {  // prevents out of bounds errors
							if (row - 1 >= 0) {  // prevents out of bounds errors
								if (board[row][col - 1] == NO_SYMBOL && board[row - 1][col - 1] != NO_SYMBOL) {
									return col - 1 + 1;  // +1 is to 1 index instead of 0 index
								}
							} else if (board[row][col - 1] == NO_SYMBOL) {
								return col - 1 + 1;
							}
						}
					} else if ((board[row][col + 1] == symbol && board[row][col + 2] == NO_SYMBOL)) {
						if (col + 3 < NUM_OF_COLUMNS) {  // prevent out of bounds errors
							if (row - 1 >= 0) {  // prevents out of bounds errors
								if (board[row][col + 3] == symbol && board[row - 1][col + 2] != NO_SYMBOL) {
									return col + 2 + 1;  // +1 is to 1 index instead of 0 index
								}
							} else if (board[row][col + 3] == symbol) {
								return col + 2 + 1;
							}
						}
					} else if (board[row][col + 1] == NO_SYMBOL && board[row][col + 2] == symbol) {
						if (col + 3 < NUM_OF_COLUMNS) {  // prevent out of bounds errors
							if (row - 1 >= 0) {  // prevents out of bounds errors
								if (board[row][col + 3] == symbol && board[row - 1][col + 1] != NO_SYMBOL) {
									return col + 1 + 1;  // +1 is to 1 index instead of 0 index
								}
							} else if (board[row][col + 3] == symbol) {
								return col + 1 + 1;
							}
						}
					}
				}

				// finding diagonal win condition
				if (col + 2 < NUM_OF_COLUMNS && row + 2 < NUM_OF_ROWS) {  // prevents out of bounds errors
					if (board[row + 1][col + 1] == symbol && board[row + 2][col + 2] == symbol) {
						if (col + 3 < NUM_OF_COLUMNS && row + 3 < NUM_OF_ROWS) {  // prevent out of bounds errors
							if (board[row + 3][col + 3] == NO_SYMBOL && board[row + 2][col + 3] != NO_SYMBOL) {
								return col + 3 + 1;  // +1 is to 1 index instead of 0 index
							}
						}
						if (col - 1 >= 0 && row - 1 >= 0) {  // prevents out of bounds errors
							if (row - 2 >= 0) {  // prevents out of bounds errors
								if (board[row - 1][col - 1] == NO_SYMBOL && board[row - 2][col - 1] != NO_SYMBOL) {
									return col - 1 + 1;  // +1 is to 1 index instead of 0 index
								}
							} else if (board[row - 1][col - 1] == NO_SYMBOL) {
								return col - 1 + 1; 
							}
						}
					} else if ((board[row + 1][col + 1] == symbol && board[row + 2][col + 2] == NO_SYMBOL)) {
						if (col + 3 < NUM_OF_COLUMNS && row + 3 < NUM_OF_ROWS) {  // prevent out of bounds errors
							if (board[row + 3][col + 3] == symbol && board[row + 1][col + 2] != NO_SYMBOL) {
								return col + 2 + 1;  // +1 is to 1 index instead of 0 index
							}
						}
					} else if (board[row + 1][col + 1] == NO_SYMBOL && board[row + 2][col + 2] == symbol) {
						if (col + 3 < NUM_OF_COLUMNS && row + 3 < NUM_OF_ROWS) {  // prevent out of bounds errors
							if (board[row + 3][col + 3] == symbol && board[row][col + 1] != NO_SYMBOL) {
								return col + 1 + 1;  // +1 is to 1 index instead of 0 index
							}
						}
					}
				}

				// finding inverse diagonal win condition
				if (col + 2 < NUM_OF_COLUMNS && row - 2 >= 0) {  // prevents out of bounds errors
					if (board[row - 1][col + 1] == symbol && board[row - 2][col + 2] == symbol) {
						if (col + 3 < NUM_OF_COLUMNS && row - 3 >= 0) {  // prevent out of bounds errors
							if (row - 4 >= 0) {
								if (board[row - 3][col + 3] == NO_SYMBOL && board[row - 4][col + 3] != NO_SYMBOL) {
									return col + 3 + 1;  // +1 is to 1 index instead of 0 index
								}
							} else if (board[row - 3][col + 3] == NO_SYMBOL) {
								return col + 3 + 1;
							}
						}
						if (col - 1 >= 0 && row + 1 < NUM_OF_ROWS) {  // prevents out of bounds errors
							if (board[row + 1][col - 1] == NO_SYMBOL && board[row][col - 1] != NO_SYMBOL) {
								return col - 1 + 1;  // +1 is to 1 index instead of 0 index
							}
						}
					} else if ((board[row - 1][col + 1] == symbol && board[row - 2][col + 2] == NO_SYMBOL)) {
						if (col + 3 < NUM_OF_COLUMNS && row - 3 >= 0) {  // prevent out of bounds errors
							if (board[row - 3][col + 3] == symbol && board[row - 3][col + 2] != NO_SYMBOL) {
								return col + 2 + 1;  // +1 is to 1 index instead of 0 index
							}
						}
					} else if (board[row - 1][col + 1] == NO_SYMBOL && board[row - 2][col + 2] == symbol) {
						if (col + 3 < NUM_OF_COLUMNS && row - 3 >= 0) {  // prevent out of bounds errors
							if (board[row - 3][col + 3] == symbol && board[row - 2][col + 1] != NO_SYMBOL) {
								return col + 1 + 1;  // +1 is to 1 index instead of 0 index
							}
						}
					}
				}

				// find vertical win conditions
				vertWin = 1;

				for (int i = 1; i < WIN_LENGTH - 1; i++) {
					try {
						if (board[row + i][col] == symbol) {vertWin++;}
					} catch (ArrayIndexOutOfBoundsException e) {break;}
				}
				
				if (vertWin == WIN_LENGTH - 1) {
					try {
						if (board[row + WIN_LENGTH - 1][col] == NO_SYMBOL) {return col + 1;}
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
		b.board[0][1] = 'X';
		b.board[0][3] = 'O';
		b.board[0][5] = 'O';
		b.board[0][6] = 'X';
		b.printBoard();
		System.out.println(b.findWin('X', true));
	}
}
