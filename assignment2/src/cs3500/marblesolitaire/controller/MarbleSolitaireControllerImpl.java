package cs3500.marblesolitaire.controller;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * controller for marble solitaire.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {

  private MarbleSolitaireModel model;
  // output sent to the user will be transmitted to the view
  private MarbleSolitaireView view;
  // handles user input
  private Readable in;

  /**
   * constructor for controller.
   *
   * @param model marble solitaire model
   * @param view  marble solitaire view.
   * @param in    inputs from the user
   * @throws IllegalArgumentException if any of the arguments are null
   */
  public MarbleSolitaireControllerImpl(MarbleSolitaireModel model, MarbleSolitaireView view,
                                       Readable in) throws IllegalArgumentException {
    if (model == null || view == null || in == null) {
      throw new IllegalArgumentException("None of the arguments can be null");
    }
    this.model = model;
    this.view = view;
    this.in = in;
  }

  // runs the game
  // each transmission should end with a newline
  //
  @Override
  public void playGame() throws IllegalStateException, IllegalStateException {
    Scanner sc = new Scanner(in);

    //array to keep track of user inputs
    int[] inputs = new int[4];
    // intial display
    displayScoreandGame();

    String input;
    //running a while loop until the game is over
    while (!model.isGameOver()) {
      int counter = 0;
      // loop until we get four inputs
      while (counter < 4) {
        try {
          input = sc.next();
        } catch (NoSuchElementException e) {
          throw new IllegalStateException("can't read inputs");
        }


        // if input is q or Q, quit the game
        if (input.equalsIgnoreCase("q")) {
          try {
            this.view.renderMessage("Game quit!\n");
            this.view.renderMessage("State of game when quit:\n");
            this.view.renderBoard();
            this.view.renderMessage("\nScore: " + this.model.getScore() + "\n");
            // stops game
            return;
          } catch (IOException e) {
            throw new IllegalStateException("can't render board");
          }
        }
        // if input is > 0
        boolean valid = isValidInput(input);
        if (valid) {
          // parseint returns a int from a string
          inputs[counter] = Integer.parseInt(input) - 1;
        }
        // invalid integer, move counter back one
        else {
          counter--;
        }
        counter++;
      }

      // case where we get 4 valid inputs, time to move
      try {
        try {
          this.model.move(inputs[0], inputs[1], inputs[2], inputs[3]);
          this.view.renderMessage("\n");
        } catch (IOException e) {
          throw new IllegalStateException("can't output message");
        }
        // catch illegalArgument Exception for bad move
      } catch (IllegalArgumentException e) {
        try {
          this.view.renderMessage("Invalid move. Move must be horizontal or vertical"
              + " from a marble to an empty space exactly two spaces apart "
              + " and have a marble in-between the FROM and TO positions\n");
        } catch (IOException e2) {
          throw new IllegalStateException("can't output message");
        }
      }
      displayScoreandGame();
    }
    //closing scanner
    sc.close();
    // if game is over
    // display game over and score
    if (model.isGameOver()) {
      try {
        this.view.renderMessage("Game over!\n");
        this.view.renderBoard();
        this.view.renderMessage("\nScore: " + model.getScore() + "\n");
      } catch (IOException e) {
        throw new IllegalStateException("Can't render board");
      }
    }
  }

  // return true iff str is a pos number > 0
  //input has to be pos number > 0
  private boolean isValidInput(String str) {
    try {
      int i = Integer.parseInt(str);
      if (i > 0) {
        return true;
      }
    } catch (NumberFormatException nfe) {
      try {
        this.view.renderMessage("Please enter a valid input(q, Q, or pos int)\n");
      } catch (IOException e) {
        throw new IllegalStateException("can't render message");
      }
      return false;
    }
    return false;
  }

  //displays score at end
  private void displayScoreandGame() {
    try {
      this.view.renderBoard();
      this.view.renderMessage("\nScore: " + this.model.getScore() + "\n");
    } catch (IOException e) {
      throw new IllegalStateException("Can't render board");
    }
  }
}
