package cs3500.marblesolitaire.controller;

/**
 * represents a controller for marbleSolitaire.
 */
public interface MarbleSolitaireController {

  /**
   * play a new game of Marble Solitaire.
   *
   * @throws IllegalStateException if the controller is unable
   *                               to successfully read input or transmit output
   */
  void playGame() throws IllegalStateException;

}
