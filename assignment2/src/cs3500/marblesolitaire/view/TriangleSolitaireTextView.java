package cs3500.marblesolitaire.view;

import java.io.IOException;


import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * represents a triangle marble solitaire view.
 */
public class TriangleSolitaireTextView implements MarbleSolitaireView {

  private Appendable destination;
  private MarbleSolitaireModelState model;


  /**
   * constructs a triangle solitaire view given model.
   *
   * @param model marble solitaire model
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState model) {
    if (model == null) {
      throw new IllegalArgumentException("Model can't be null");
    }
    this.model = model;
    this.destination = System.out;
  }

  /**
   * constructs a triangle solitaire. view given model and appendable.
   *
   * @param model       marblesolitairemodel.
   * @param destination appendable object.
   * @throws IllegalArgumentException if the model or appendable is null
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState model,
                                   Appendable destination) throws IllegalArgumentException {
    if (model == null || destination == null) {
      throw new IllegalArgumentException("model or Appendable cannot be null");
    }
    this.model = model;
    this.destination = destination;
  }

  /**
   * returns a string of the triangle board.
   * @return string
   */
  public String toString() {
    String board = "";
    for (int i = 0; i < model.getBoardSize(); i++) {
      // spacing in front
      for (int j = model.getBoardSize(); j > i + 1; j--) {
        board += " ";
      }
      // iterating through the board
      for (int k = 0; k < model.getBoardSize(); k++) {
        if (model.getSlotAt(i, k) == MarbleSolitaireModelState.SlotState.Marble) {
          board += "O";
        }
        if (model.getSlotAt(i, k) == MarbleSolitaireModelState.SlotState.Empty) {
          board += "_";
        }
        // spacing between slots
        if (k < i) {
          board += " ";
        }
      }
      if (i < model.getBoardSize() - 1) {
        board += "\n";
      }
    }
    return board;
  }


  @Override
  public void renderBoard() throws IOException {
    try {
      this.destination.append(this.toString());
    } catch (IOException e) {
      throw new IllegalStateException("can't output board");
    }
  }


  @Override
  public void renderMessage(String message) throws IOException {
    try {
      this.destination.append(message);
    } catch (IOException e) {
      throw new IllegalStateException("can't output message");
    }
  }
}
