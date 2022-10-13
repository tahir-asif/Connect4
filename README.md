# Connect4

### To Do List:

##### Important (DO before submitting):
- check if I need to do anything with human and AI constructors
- remove ArrayList from AIPlayer.java
- backwards horizontal win weird sometimes 
		Alice 5   5   4   2   5   3   
		1 2 3 4 5 6 7
		. . . . . . .
		. . . . O . .
		. . . . X . .
		. . . . O . .
		. . X X X . .
		O X O O X . .
		- - - - - - -
		found win X: 4 | found win O: -1 // It is Bob's turn.
		Bob 4   5   1   3   5   4   
		1 2 3 4 5 6 7
		. . . . . . .
		. . . . O . .
		. . . . X . .
		. . . O O . .
		. . X X X . .
		O X O O X . .
		- - - - - - -
		found win X: 2 | found win O: -1 // It is Alice's turn.
		Alice 5   5   4   2   5   3   2   
		1 2 3 4 5 6 7
		. . . . . . .
		. . . . O . .
		. . . . X . .
		. . . O O . .
		. X X X X . .
		O X O O X . .
		- - - - - - -
		found win X: -1 | found win O: -1 // Congratulations Alice, you have won!


##### Less Important (Maybe do before submitting):
- clean up board.findWin() and board.containsWin() functions
	- maybe get a parent function to handle some of the function
- possibly change NO_SYMBOL functionality
- maybe change board.reset()
- maybe improve board.print()
- possibly combine all win direction variables


##### Bonus (Do after submitting, if at all):
- sleep on AI turn
- input safety (probably using custom exceptions and handling)
- improve AI
	- possibly modify board.findWin() to check for 2 long straights and focus those
- have drop piece take a parameter called last move which is then stored in a global variable in board which is then used to check for wins only on new piece


### Connect 4 find win algorithm


_ X X X _	-> 	_ X X X O	->	_ X X X |		
			->	O X X X _	->	| X X X _		

_ X X _ X	->	_ X X O X	-> 	O X X _ X
_ X _ X X	->	_ X O X X	->	O X X _ X


O X X X |	->	| X X X O	->	O X X X O	->	| X X X |

##### horizontal algorithm:
r4
r3
r2 [  1  2  3  4  5  ]
r1 
r0

examine 2
if 2 == symbol
	if 3 & 4 not out of bounds
		examine 3 & 4
		if 3 & 4 not == symbol 
			continue
		else if 3 & 4 == symbol
			if 5 not out of bounds
				if 5r1 not out of bounds
					examine 5 & 5r1
					if 5 == open and 5r1 == closed
						return 5
				else
					examine 5
					if 5 == open
						return 5
			if 1 not out of bounds
				if 1r1 not out of bounds
					examine 1 & 1r1
					if 1 == open and 1r1 == closed
						return 1
				else
					examine 1
					if 1 == open
						return 1
		else
            if 5 not out of bounds
				if 5r1 not out of bounds
					examine 5 & 5r1
					if 5 == open and 5r1 == closed
						return 5
				else
					examine 5
					if 5 == open
						return 5

##### diagonal algorithm:
r7
r6                      5
r5                  4   _
r4              3   _   _
r3          2   _   _   _
r2      1   _   _   _   _
r1  _   _   _   _   _   _

examine 2
if 2 == symbol
	if 3 & 4 not out of bounds
		examine 3 & 4
		if 3 & 4 not == symbol 
			continue
		else if 3 & 4 == symbol
			if 5 not out of bounds
				examine 5 & 5r5
				if 5 == open and 5r5 == closed
					return 5
			if 1 not out of bounds
				if 1r1 not out of bounds
					examine 1 & 1r1
					if 1 == open and 1r1 == closed
						return 1
				else
					examine 1
					if 1 == open
						return 1
		else
			if 5 not out of bounds
				examine 5 & 5r1
				if 5 == open and 5r1 == closed
					return 5

##### inverse diagonal algorithm:
r7
r6	1
r5		2
r4			3
r3				4
r2					5
r1	_	_	_	_	_	_

examine 2
if 2 == symbol
	if 3 & 4 not out of bounds
		examine 3 & 4
		if 3 & 4 not == symbol 
			continue
		else if 3 & 4 == symbol
            if 5 not out of bounds
				if 5r1 not out of bounds
					examine 5 & 5r1
					if 5 == open and 5r1 == closed
						return 5
				else
					examine 5
					if 5 == open
						return 5
			if 1 not out of bounds
				examine 1 & 1r1
				if 1 == open and 1r1 == closed
					return 1
		else
			if 5 not out of bounds
				if 5r1 not out of bounds
					examine 5 & 5r1
					if 5 == open and 5r1 == closed
						return 5
