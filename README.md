# Connect4

### To Do List:

##### Important (DO before submitting):
- check if I need to do anything with human and AI constructors


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
- have drop piece take a paremeter called last move which is then stored in a global variable in board which is then used to check for wins only on new piece
