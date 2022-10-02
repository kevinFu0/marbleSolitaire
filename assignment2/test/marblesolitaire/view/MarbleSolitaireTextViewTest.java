package cs3500.marblesolitaire.view;


import org.junit.Before;
import org.junit.Test;

import java.io.IOException;


import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

import static org.junit.Assert.assertEquals;

/**
 * test class for MarbleSolitaireTextView.
 */
public class MarbleSolitaireTextViewTest {



  MarbleSolitaireModel m1 = new EnglishSolitaireModel();
  MarbleSolitaireModel m2 = new EnglishSolitaireModel(3, 0, 4);

  Appendable destination = new StringBuilder();
  MarbleSolitaireTextView v1 = new MarbleSolitaireTextView(m1, destination);

  @Before
  public void init() {
    m1 = new EnglishSolitaireModel();
    v1 = new MarbleSolitaireTextView(m1, destination);
  }

  /**
   * testing bad init given two null arguments.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBadInit() {
    v1 = new MarbleSolitaireTextView(null, null);
  }

  /**
   * testing bad init given null model.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithNullBoard() {
    v1 = new MarbleSolitaireTextView(null, destination);
  }

  /**
   * testing bad init given null Appendable.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithNullAppendable() {
    v1 = new MarbleSolitaireTextView(m1, null);
  }


  /**
   * testing toString for a standard board model.
   */
  @Test
  public void testToString() {
    assertEquals("    O O O\n" +
        "    O O O\n" +
        "O O O O O O O\n" +
        "O O O _ O O O\n" +
        "O O O O O O O\n" +
        "    O O O\n" +
        "    O O O", v1.toString());

    m1.move(5, 3, 3, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, m1.getSlotAt(5, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, m1.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, m1.getSlotAt(3, 3));

    assertEquals("    O O O\n" +
        "    O O O\n" +
        "O O O O O O O\n" +
        "O O O O O O O\n" +
        "O O O _ O O O\n" +
        "    O _ O\n" +
        "    O O O", v1.toString());
  }


  /**
   * testing renderBoard initial board.
   */
  @Test
  public void testRenderBoard() throws IOException {
    try {
      v1 = new MarbleSolitaireTextView(m1, destination);
      v1.renderBoard();
      assertEquals("    O O O\n" +
          "    O O O\n" +
          "O O O O O O O\n" +
          "O O O _ O O O\n" +
          "O O O O O O O\n" +
          "    O O O\n" +
          "    O O O\n", destination.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * testing renderBoard initial board after one move.
   */
  @Test
  public void testRenderBoardOneMove() throws IOException {
    try {
      v1 = new MarbleSolitaireTextView(m1, destination);
      m1.move(5, 3, 3, 3);
      v1.renderBoard();
      assertEquals("    O O O\n" +
          "    O O O\n" +
          "O O O O O O O\n" +
          "O O O O O O O\n" +
          "O O O _ O O O\n" +
          "    O _ O\n" +
          "    O O O\n", destination.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  /**
   * testing that a message can be sent to appendable destination.
   */
  @Test
  public void testRenderMessage1() throws IOException {
    try {
      Appendable in = new StringBuilder();
      MarbleSolitaireView v10 = new MarbleSolitaireTextView(m1, in);
      v10.renderMessage("hello testing");
      assertEquals("hello testing\n", in.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testCustomEmptyCell() {
    Appendable in = new StringBuilder("");

    MarbleSolitaireModel m5 = new EnglishSolitaireModel(3, 0, 2);
    MarbleSolitaireView v5 = new MarbleSolitaireTextView(m5, in);
    try {
      v5.renderBoard();
      assertEquals("    _ O O\n" +
          "    O O O\n" +
          "O O O O O O O\n" +
          "O O O O O O O\n" +
          "O O O O O O O\n" +
          "    O O O\n" +
          "    O O O\n", in.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  /**
   * testing rendering a board and a message.
   * the board also has one move
   */
  @Test
  public void testRenderMessageandBoard() {
    Appendable in = new StringBuilder("");
    MarbleSolitaireView v9 = new MarbleSolitaireTextView(m1, in);
    m1.move(5, 3, 3, 3);
    try {
      v9.renderBoard();
      v9.renderMessage("\nTesting rendering message and board!");
      assertEquals("    O O O\n" +
          "    O O O\n" +
          "O O O O O O O\n" +
          "O O O O O O O\n" +
          "O O O _ O O O\n" +
          "    O _ O\n" +
          "    O O O\n" +
          "\nTesting rendering message and board!\n", in.toString());
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }





}