package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.controller.ControllerFeatures;

/**
 * represents a board panel GUI that has features.
 */
public interface BoardPanelWithFeatures {
  /**
   * allows the view to communicate with the controller features.
   * @param features an object that holds the information for features of this program
   */
  public void accept(ControllerFeatures features);
}
