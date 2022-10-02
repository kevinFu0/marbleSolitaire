package cs3500.marblesolitaire.model.hw04;


import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * abstract class representing a solitaire model.
 */
public abstract class AbstractSolitaireModel implements MarbleSolitaireModel {

  protected int armThickness; // has to be odd
  // represents the starting empty position
  protected int emptyRow;
  protected int emptyCol;
  protected SlotState[][] board;

  /**
   * default board with at 3 and empty slot at 3,3.
   */
  public AbstractSolitaireModel() {
    this.armThickness = 3;
    this.emptyRow = 3;
    this.emptyCol = 3;
    board = new SlotState[7][7];
    initBoard(3, 3);
  }

  /**
   * creates a board with arm thickness and empty slot at center.
   *
   * @param at arm thickness
   * @throws IllegalArgumentException at has to be positive and odd
   */
  public AbstractSolitaireModel(int at) throws IllegalArgumentException {
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
   * constructors a default board with empty at sRow, sCol.
   *
   * @param sRow row
   * @param sCol col
   * @throws IllegalArgumentException if the given row col is invalid
   */
  public AbstractSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    armThickness = 3;
    // valid position
    if (checkValidPosn(sRow, sCol)) {
      emptyRow = sRow;
      emptyCol = sCol;
      this.board = new SlotState[7][7];
      initBoard(sRow, sCol);
    }
    // else throw
    else {
      throw new IllegalArgumentException("Invalid empty cell position (" + sRow + "," + sCol + ")");
    }
  }

  /**
   * creates a model given arm thickness, empty row and empty col.
   *
   * @param at arm thickness
   * @param x  row
   * @param y  col
   * @throws IllegalArgumentException if arm thickness or position is invalid
   */
  public AbstractSolitaireModel(int at, int x, int y) throws IllegalArgumentException {
    if (at > 0 && at % 2 == 1) {
      armThickness = at;
    } else {
      throw new IllegalArgumentException("Invalid arm thickness");
    }
    if (checkValidPosn(x, y)) {
      emptyRow = x;
      emptyCol = y;
      this.board = new SlotState[getBoardSize()][getBoardSize()];
      initBoard(x, y);
    } else {
      throw new IllegalArgumentException("Invalid position");
    }
  }


  // checks if a posn is valid in a model
  protected abstract boolean checkValidPosn(int row, int col);


  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
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

  /**
   * checks if the game is over by checking is there are.
   * any valid moves left.
   * @return t/f
   */
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
      return board[row][col];
    } else {
      throw new IllegalArgumentException("position is outside of board");
    }
  }

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

  protected void initBoard(int x, int y) {
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


  // helper method to check if a move is valid
  protected boolean validMove(int fromX, int fromY, int toX, int toY) {
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
