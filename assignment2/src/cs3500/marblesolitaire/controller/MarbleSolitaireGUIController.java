package cs3500.marblesolitaire.controller;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.view.MarbleSolitaireGuiView;

/**
 * represents the controller for this marble solitaire gui.
 */
public class MarbleSolitaireGUIController implements ControllerFeatures {
  private MarbleSolitaireModel model;
  private MarbleSolitaireGuiView view;

  private int fromRow = -1;

  private int fromCol = -1;

  /**
   * constructs the information for this marble solitaire gui controller.
   * @param m the model
   */
  public MarbleSolitaireGUIController(MarbleSolitaireModel m) {
    model = m;
  }

  /**
   * constructs the info for the controller.
   * @param v the view
   */
  public void setView(MarbleSolitaireGuiView v) {
    view = v;
    v.accept(this);
    //provide view with all the callbacks
    view.refresh();
  }

  @Override
  public void slotClicked(int row, int col) throws IllegalArgumentException {
    if(!(this.model.isGameOver())) {
      //check if slot is marble
      if(this.model.getSlotAt(row, col) == MarbleSolitaireModelState.SlotState.Marble) {
        fromRow = row;
        fromCol = col;
      }
      //check if slot is empty
      if(this.model.getSlotAt(row, col) == MarbleSolitaireModelState.SlotState.Empty) {
        try {
          this.model.move(fromRow, fromCol, row, col);
          this.view.refresh();
          this.view.renderMessage("");
        }
        catch(IllegalArgumentException e) {
          this.view.renderMessage("invalid move");
        }
      }
    }
    else {
      this.view.renderMessage("GAME IS OVER");
    }
  }
}
