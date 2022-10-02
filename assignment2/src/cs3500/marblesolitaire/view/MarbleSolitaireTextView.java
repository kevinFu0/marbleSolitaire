package cs3500.marblesolitaire.view;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * represents the view of an english solitaire game.
 */
public class MarbleSolitaireTextView implements MarbleSolitaireView {


  protected Appendable destination;

  protected MarbleSolitaireModelState model;

  /**
   * existing constructor takes in a model.
   * and sets Destination to System.out
   *
   * @param model the model
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Model can't be null");
    }
    this.model = model;
    this.destination = System.out;
  }


  /**
   * constructor takes in a model object and sets it to model.
   * sets Destination to system out.
   *
   * @param model MarbleSolitaireModel model
   * @throws IllegalArgumentException if the model is null
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model,
                                 Appendable destination) throws IllegalArgumentException {
    if (model == null || destination == null) {
      throw new IllegalArgumentException("model or Appendable cannot be null");
    }
    this.model = model;
    this.destination = destination;
  }

  /**
   * iterates through the model board and prints the corresponding.
   * string depending on slotSlate.
   * added if statements to get the spacing correct
   * the if statements make sure that there are no spaces
   * after the last marble in each row.
   *
   * @return a string representation of the board
   */
  public String toString() {
    String board = "";
    for (int i = 0; i < this.model.getBoardSize(); i++) {
      for (int j = 0; j < this.model.getBoardSize(); j++) {
        if (this.model.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Empty) {
          board += "_";
          if (j != this.model.getBoardSize() - 1 && this.model.getSlotAt(i, j + 1)
              != MarbleSolitaireModelState.SlotState.Invalid) {
            board += " ";
          }
        }
        if (this.model.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Marble) {
          board += "O";
          if (j != this.model.getBoardSize() - 1 && this.model.getSlotAt(i, j + 1)
              != MarbleSolitaireModelState.SlotState.Invalid) {
            board += " ";
          }
        }
        if (this.model.getSlotAt(i, j) == MarbleSolitaireModelState.SlotState.Invalid) {
          // only print invalids spaces in the front, not back
          if (j < (((this.model.getBoardSize()) + 2) / 3) - 1) {
            board += "  ";
          }
        }
      }
      if (i != this.model.getBoardSize() - 1) {
        board += "\n";
      }
    }
    return board;
  }

  /**
   * renders the state of the solitaire board to Destination.
   *
   * @throws IOException if toString fails
   */
  @Override
  public void renderBoard() throws IOException {
    try {
      this.destination.append(this.toString());
    } catch (IOException e) {
      throw new IllegalStateException("can't output board");
    }
  }

  /**
   * tranmits the message to the appendable destionation.
   *
   * @param message the String message to be transmitted
   * @throws IOException if the transmission fails
   */
  @Override
  public void renderMessage(String message) throws IOException {
    try {
      this.destination.append(message );
    } catch (IOException e) {
      throw new IllegalStateException("can't output message");
    }
  }


}
