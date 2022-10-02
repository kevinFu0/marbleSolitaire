package cs3500.marblesolitaire.controller;


import java.io.InputStreamReader;

import java.io.PrintStream;


import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;

import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

// running main for testing purposes

/**
 * main class for testing the game by running it.
 */

public class ProgramTestMain {


  /**
   * main.
   * arg[0] should read "english", "european", or "triangular"
   *
   * @param args args
   */
  public static void main(String[] args) {


    MarbleSolitaireModel m1;
    MarbleSolitaireView v1;
    Appendable out = new PrintStream(System.out);
    Readable in = new InputStreamReader(System.in);

    if (args[0].equals("english")) {
      m1 = new EnglishSolitaireModel();
      v1 = new MarbleSolitaireTextView(m1, out);
      MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(m1, v1, in);
      controller.playGame();

    }
    if (args[0].equals("european")) {
      m1 = new EuropeanSolitaireModel();
      v1 = new MarbleSolitaireTextView(m1, out);
      MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(m1, v1, in);
      controller.playGame();
    }
    if (args[0].equals("triangular")) {
      m1 = new TriangleSolitaireModel();
      v1 = new TriangleSolitaireTextView(m1, out);
      MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(m1, v1, in);
      controller.playGame();
    }

  }

}
