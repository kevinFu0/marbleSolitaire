package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;


/**
 * represents a triangle model of marble solitaire.
 */
public class TriangleSolitaireModel implements MarbleSolitaireModel {

  private int base;
  private int row;
  private int col;
  private SlotState[][] board;

  /**
   * default constructor creates a 5 row game with empty slot at 0,0.
   */
  public TriangleSolitaireModel() {
    this.base = 5;
    this.row = 0;
    this.col = 0;
    board = new SlotState[5][5];
    initBoard(0, 0);
  }


  /**
   * creates a board with dimension(# of slots in bottomost row).
   *
   * @param dimension the dimension
   * @throws IllegalArgumentException if the specified dimension is non-positive
   */
  public TriangleSolitaireModel(int dimension) throws IllegalArgumentException {
    if (dimension <= 0) {
      throw new IllegalArgumentException("has to be positive");
    }
    this.base = dimension;
    this.row = 0;
    this.col = 0;
    board = new SlotState[dimension][dimension];
    initBoard(0, 0);
  }

  /**
   * creates a 5 row board with empty slot at sRow sCol.
   *
   * @param sRow the row
   * @param sCol the col
   * @throws IllegalArgumentException if the position is invalid
   */
  public TriangleSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    this.base = 5;
    // calls checkValidPosn to check if valid position
    if (checkValidPosn(sRow, sCol)) {
      this.row = sRow;
      this.col = sCol;
      board = new SlotState[5][5];
      initBoard(sRow, sCol);
    } else {
      throw new IllegalArgumentException("The given position is invalid");
    }
  }

  /**
   * constructors a triangle model given dimension, row, and col.
   *
   * @param dimension the dimension
   * @param sRow      the row
   * @param sCol      the col
   * @throws IllegalArgumentException if the dimension or position is invalid
   */
  public TriangleSolitaireModel(int dimension, int sRow, int sCol) throws IllegalArgumentException {
    if (dimension <= 0) {
      throw new IllegalArgumentException("dimension has to be positive");
    } else {
      this.base = dimension;
    }
    if (checkValidPosn(sRow, sCol)) {
      row = sRow;
      col = sCol;
      board = new SlotState[dimension][dimension];
      initBoard(sRow, sCol);
    } else {
      throw new IllegalArgumentException("invalid position");
    }
  }

  /**
   * calls private helper function, which returns an.
   * int representing movement type. horizontal(1) left(2) right(3)
   * the helper function also throws exceptions
   *
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0)
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0)
   * @param toRow   the row number of the position to be moved to
   *                (starts at 0)
   * @param toCol   the column number of the position to be moved to
   *                (starts at 0)
   * @throws IllegalArgumentException if the move can't be made(thrown in helper method validMove)
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    int num = validMove(fromRow, fromCol, toRow, toCol);

    if (num == 0) {
      throw new IllegalArgumentException("move is invalid");
    }
    // horizontal move
    if (num == 1) {
      // setting corresponding marble states
      this.board[fromRow][fromCol] = SlotState.Empty;
      this.board[fromRow][(fromCol + toCol) / 2] = SlotState.Empty;
      this.board[toRow][toCol] = SlotState.Marble;
    }
    // left diagonal move
    if (num == 2) {
      // setting corresponding marble states
      this.board[fromRow][fromCol] = SlotState.Empty;
      this.board[(fromRow + toRow) / 2][fromCol] = SlotState.Empty;
      this.board[toRow][toCol] = SlotState.Marble;
    }
    // right diagonal move
    if (num == 3) {
      // setting corresponding marble states
      this.board[fromRow][fromCol] = SlotState.Empty;
      this.board[(fromRow + toRow) / 2][(fromCol + toCol) / 2] = SlotState.Empty;
      this.board[toRow][toCol] = SlotState.Marble;
    }
  }


  /**
   * uses private helper function validMove.
   * to check in all possible direction if a move is possible.
   *
   * @return t/f if the game is over
   */
  @Override
  public boolean isGameOver() {
    //iterate through the board
    for (int i = 0; i < getBoardSize(); i++) {
      for (int j = 0; j < getBoardSize(); j++) {
        if (checkValidPosn(i, j)) {
          // check horizontal left right
          try {
            if (validMove(i, j, i, j + 2) > 0) {
              return false;
            }
          } catch (IllegalArgumentException e) {
            e.getMessage();
          }
          try {
            if (validMove(i, j, i, j - 2) > 0) {
              return false;
            }
          } catch (IllegalArgumentException e) {
            e.getMessage();

          }
          try {
            // left diagonal up down
            if (validMove(i, j, i + 2, j) > 0) {
              return false;
            }
          } catch (IllegalArgumentException e) {
            e.getMessage();
          }
          try {
            if (validMove(i, j, i - 2, j) > 0) {
              return false;
            }
          } catch (IllegalArgumentException e) {
            e.getMessage();
          }
          try {
            // right diagonal up down
            if (validMove(i, j, i - 2, j - 2) > 0) {
              return false;
            }
          } catch (IllegalArgumentException e) {
            e.getMessage();
          }
          try {
            if (validMove(i, j, i + 2, j + 2) > 0) {
              return false;
            }
          } catch (IllegalArgumentException e) {
            e.getMessage();
          }
        }
      }
    }
    return true;
  }

  @Override
  public int getBoardSize() {
    return this.base;
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

  // checks if the given coordinate is in the triangle board
  private boolean checkValidPosn(int row, int col) {
    return !(row < 0 || row >= this.base || col < 0 || col > row || col >= this.base);
  }

  // initializes the triangle board with empty space at x, y
  private void initBoard(int x, int y) {
    for (int i = 0; i < base; i++) {
      for (int j = 0; j < base; j++) {
        // if col < row
        if (checkValidPosn(i, j)) {
          board[i][j] = SlotState.Marble;
        } else {
          board[i][j] = SlotState.Invalid;
        }
      }
    }
    board[x][y] = SlotState.Empty;
  }


  // helper method to check if a move is valid
  // returns 1 for horizontal, 2 for left, and 3 for right
  private int validMove(int fromX, int fromY, int toX, int toY) throws IllegalArgumentException {
    if (!checkValidPosn(fromX, fromY)) {
      throw new IllegalArgumentException("from position is invalid");
    }
    if (!checkValidPosn(toX, toY)) {
      throw new IllegalArgumentException("to position is invalid");
    }
    if (this.getSlotAt(fromX, fromY) != SlotState.Marble) {
      throw new IllegalArgumentException("from position must be a marble");
    }
    if (this.getSlotAt(toX, toY) != SlotState.Empty) {
      throw new IllegalArgumentException("to position must be an empty");
    }
    //horizontal movement
    if ((Math.abs(fromY - toY) == 2) && fromX == toX) {
      if (this.getSlotAt(fromX, (fromY + toY) / 2) != SlotState.Marble) {
        throw new IllegalArgumentException("a move must jump over a marble");
      }
      return 1;
    }
    // left diagonal either direction
    if (Math.abs(fromX - toX) == 2 && fromY == toY) {
      if (this.getSlotAt((fromX + toX) / 2, fromY) != SlotState.Marble) {
        throw new IllegalArgumentException("a move must jump over a marble");
      }
      return 2;
    }
    // right diagonal either direction
    if ((Math.abs(fromX - toX) == 2) && (Math.abs(fromY - toY) == 2)) {
      if (this.getSlotAt((fromX + toX) / 2, (fromY + toY) / 2) != SlotState.Marble) {
        throw new IllegalArgumentException("a move must jump over a marble");
      }
      return 3;
    }
    return 0;
  }
}
