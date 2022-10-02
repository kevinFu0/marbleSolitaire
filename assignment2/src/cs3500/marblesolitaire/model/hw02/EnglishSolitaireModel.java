package cs3500.marblesolitaire.model.hw02;


/**
 * English solitaire game model.
 */
public class EnglishSolitaireModel implements MarbleSolitaireModel {
  private int armThickness; // has to be odd
  // represents the starting empty position
  private int emptyX;
  private int emptyY;
  private SlotState[][] board;


  /**
   * initialize game with armThickness = 3, empty cell at center.
   * default constructor with no args.
   */
  public EnglishSolitaireModel() {
    armThickness = 3;
    emptyX = 3;
    emptyY = 3;
    board = new SlotState[7][7];
    initBoard(3, 3);
  }

  /**
   * constructors an english model given x, y.
   * where x y is the location of the empty cell
   * @param sRow x pos of empty cell
   * @param sCol y pos of empty cell
   * @throws IllegalArgumentException if the position is invalid
   */
  public EnglishSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    armThickness = 3;
    // valid position
    if (checkValidPosn(sRow, sCol)) {
      emptyX = sCol;
      emptyY = sRow;
      this.board = new SlotState[7][7];
      initBoard(sCol, sRow);
    }
    // else throw
    else {
      throw new IllegalArgumentException("Invalid empty cell position (" + sRow + "," + sCol + ")");
    }
  }

  /**
   * constructs a model given arm thickness.
   *
   * @param at arm thickness
   * @throws IllegalArgumentException if the arm thickness is not pos and odd
   */
  public EnglishSolitaireModel(int at) throws IllegalArgumentException {
    if (at > 0 && at % 2 == 1) {
      armThickness = at;
      int mid = (getBoardSize() - 1) / 2;
      this.board = new SlotState[getBoardSize()][getBoardSize()];
      initBoard(mid, mid);
    } else {
      throw new IllegalArgumentException("arm thickness must be positive and odd");
    }
  }

  /**
   * constructs a model given arm thickness, row and column of empty cell.
   *
   * @param at arm thickness
   * @param x  row
   * @param y  col
   * @throws IllegalArgumentException if invalid arm thickness or position
   */
  public EnglishSolitaireModel(int at, int x, int y) throws IllegalArgumentException {
    if (at > 0 && at % 2 == 1) {
      armThickness = at;
    } else {
      throw new IllegalArgumentException("Invalid arm thickness");
    }
    if (checkValidPosn(x, y)) {
      emptyX = y;
      emptyY = x;
      this.board = new SlotState[getBoardSize()][getBoardSize()];
      initBoard(y, x);
    } else {
      throw new IllegalArgumentException("Invalid position");
    }
  }


  // only valid moves at start
  // (5,3),(1,3),(3,1),(3, 5) to (3,3)
  // calls helper function validmove to check if a move is valid
  // if it is, move the marble and remove middle marble
  @Override
  public void move(int fromCol, int fromRow, int toCol, int toRow) throws IllegalArgumentException {
    // if the move is valid
    if (validMove(fromRow, fromCol, toRow, toCol)) {
      // set original cell is empty
      board[fromRow][fromCol] = SlotState.Empty;
      //setting middle cell to empty
      if (fromRow == toRow) {
        board[fromRow][(fromCol + toCol) / 2] = SlotState.Empty;
      }
      if (fromCol == toCol) {
        board[(fromRow + toRow) / 2][fromCol] = SlotState.Empty;
      }
      //setting to cell to marble
      board[toRow][toCol] = SlotState.Marble;
    } else {
      throw new IllegalArgumentException("Move is invalid.");
    }
  }

  // calls validMove on every
  // marble left in every direction
  // note: validMove will return false if the given position is invalid
  @Override
  public boolean isGameOver() {
    //iterate through the board
    for (int i = 0; i < getBoardSize(); i++) {
      for (int j = 0; j < getBoardSize(); j++) {
        if (checkValidPosn(i, j)) {
          //checks right left up down
          if (validMove(i, j, i + 2, j)) {
            return false;
          }
          if (validMove(i, j, i - 2, j)) {
            return false;
          }
          if (validMove(i, j, i, j + 2)) {
            return false;
          }
          if (validMove(i, j, i, j - 2)) {
            return false;
          }
        }
      }
    }
    //return true otherwise
    return true;
  }

  @Override
  public int getBoardSize() {
    // arm 5 -> 13
    // arm 3 -> 7
    return armThickness * 3 - 2;
  }

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    // check within boundaries
    if (row >= 0 && row < getBoardSize() && col >= 0 && col < getBoardSize()) {
      return board[col][row];
    } else {
      throw new IllegalArgumentException("position is outside of board");
    }
  }

  // Score is the number of SlotState = Marble
  @Override
  public int getScore() {
    int count = 0;
    //iterate through board and count SlotState.Marble
    for (int i = 0; i < getBoardSize(); i++) {
      for (int j = 0; j < getBoardSize(); j++) {
        if (checkValidPosn(i, j)) {
          if (board[i][j] == SlotState.Marble) {
            count++;
          }
        }
      }
    }
    return count;
  }

  // initializes a 2-d array of SlotSlate
  // with an empty space at x,y
  private void initBoard(int x, int y) {
    // iterate through the board
    // assigns marbles and invalid based on posn
    for (int i = 0; i < getBoardSize(); i++) {
      for (int j = 0; j < getBoardSize(); j++) {
        if (checkValidPosn(i, j)) {
          board[i][j] = SlotState.Marble;
        } else {
          board[i][j] = SlotState.Invalid;
        }
      }
    }
    //assigning empty cell
    board[x][y] = SlotState.Empty;
  }


  // helper method to check if a position exist
  // in the current game board, returns t if valid
  // in standard game, invalid positions are in the top left, top right
  // bottom left and bottom right of the "square"
  private boolean checkValidPosn(int x, int y) {
    return ((x >= 0 && y >= 0) &&  // x and y have to be pos
        (x < getBoardSize() && y < getBoardSize()) &&  //x and y are in the "square"
        !(x < armThickness - 1 && y < armThickness - 1) && // top left invalid
        !(x < armThickness - 1 && y > (2 * armThickness - 2)) && // top right invalid
        !(x > (armThickness * 2 - 2) && y < armThickness - 1) && //bottom left invalid
        !(x > (armThickness * 2 - 2) && y > (armThickness * 2 - 2))); //bottom right invalid
  }

  // helper method to check if a move is valid
  private boolean validMove(int fromX, int fromY, int toX, int toY) {
    boolean validInit = false;
    boolean validTo = false;
    boolean validDistance = false;
    // location of the middle cell
    int midX = 0;
    int midY = 0;
    // checking init position has marble
    if (checkValidPosn(fromX, fromY)) {
      if (board[fromX][fromY] == SlotState.Marble) {
        validInit = true;
      }
    }
    //checking to position is empty
    if (checkValidPosn(toX, toY)) {
      if (board[toX][toY] == SlotState.Empty) {
        validTo = true;
      }
    }
    // check if the two position are two spaces apart
    if (validInit && validTo) {
      if ((Math.abs(fromX - toX) == 2 && fromY == toY)
          || (fromX == toX && (Math.abs(fromY - toY) == 2))) {
        validDistance = true;
      }
    }
    // checking if the middle cell is empty
    if (validDistance) {
      if (fromX == toX) {
        midX = fromX;
        midY = (fromY + toY) / 2;
      }
      if (fromY == toY) {
        midX = (fromX + toX) / 2;
        midY = fromY;
      }
      // if the middle cell has a marble
      if (board[midX][midY] == SlotState.Marble) {
        return true;
      }
    }
    return false;
  }


}
