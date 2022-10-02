package cs3500.marblesolitaire;

import cs3500.marblesolitaire.controller.MarbleSolitaireGUIController;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireGuiView;
import cs3500.marblesolitaire.view.SwingGuiView;

/**
 * represents the main method for this {@code MarbleSolitaireGui}.
 */
public class MarbleSolitaireGUI {
  /**
   * the main method.
   * @param args the argument inputs list
   */
  public static void main(String[] args) {
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireGUIController controller = new MarbleSolitaireGUIController(model);
    MarbleSolitaireGuiView view = new SwingGuiView(model);
    controller.setView(view);
  }
}
