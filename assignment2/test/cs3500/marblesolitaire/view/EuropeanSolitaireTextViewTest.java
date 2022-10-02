package cs3500.marblesolitaire.view;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;


import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;

import static org.junit.Assert.assertEquals;


/**
 * test class for testing a european view.(using same view as english)
 */
public class EuropeanSolitaireTextViewTest {

  MarbleSolitaireModel m1;
  MarbleSolitaireModel m2;
  MarbleSolitaireTextView v1;
  Appendable destination;

  @Before
  public void init() {
    m1 = new EuropeanSolitaireModel();
    m2 = new EuropeanSolitaireModel(5, 4, 4);
    destination = new StringBuilder();
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

  //tostring test1 for various models
  @Test
  public void testToString() {
    v1 = new MarbleSolitaireTextView(m2, destination);
    assertEquals("        O O O O O\n" +
        "      O O O O O O O\n" +
        "    O O O O O O O O O\n" +
        "  O O O O O O O O O O O\n" +
        "O O O O _ O O O O O O O O\n" +
        "O O O O O O O O O O O O O\n" +
        "O O O O O O O O O O O O O\n" +
        "O O O O O O O O O O O O O\n" +
        "O O O O O O O O O O O O O\n" +
        "  O O O O O O O O O O O\n" +
        "    O O O O O O O O O\n" +
        "      O O O O O O O\n" +
        "        O O O O O", v1.toString());
    m2 = new EuropeanSolitaireModel(5);
    v1 = new MarbleSolitaireTextView(m2);
    assertEquals("        O O O O O\n" +
        "      O O O O O O O\n" +
        "    O O O O O O O O O\n" +
        "  O O O O O O O O O O O\n" +
        "O O O O O O O O O O O O O\n" +
        "O O O O O O O O O O O O O\n" +
        "O O O O O O _ O O O O O O\n" +
        "O O O O O O O O O O O O O\n" +
        "O O O O O O O O O O O O O\n" +
        "  O O O O O O O O O O O\n" +
        "    O O O O O O O O O\n" +
        "      O O O O O O O\n" +
        "        O O O O O", v1.toString());
    m2 = new EuropeanSolitaireModel(5, 5);
    v1 = new MarbleSolitaireTextView(m2);
    assertEquals("    O O O\n" +
        "  O O O O O\n" +
        "O O O O O O O\n" +
        "O O O O O O O\n" +
        "O O O O O O O\n" +
        "  O O O O _\n" +
        "    O O O", v1.toString());


  }

  @Test
  public void testRenderBoard1() {
    try {
      v1.renderBoard();
      m1.move(1, 3, 3, 3);
      v1.renderMessage("\n");
      v1.renderBoard();
      assertEquals("    O O O\n" +
          "  O O O O O\n" +
          "O O O O O O O\n" +
          "O O O _ O O O\n" +
          "O O O O O O O\n" +
          "  O O O O O\n" +
          "    O O O\n" +
          "    O O O\n" +
          "  O O _ O O\n" +
          "O O O _ O O O\n" +
          "O O O O O O O\n" +
          "O O O O O O O\n" +
          "  O O O O O\n" +
          "    O O O", destination.toString());

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
      assertEquals("hello testing", in.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testRenderMessageandBoard() {
    Appendable in = new StringBuilder("");
    MarbleSolitaireView v9 = new MarbleSolitaireTextView(m1, in);
    m1.move(5, 3, 3, 3);
    try {
      v9.renderBoard();
      v9.renderMessage("\nTesting rendering message and board!");
      assertEquals("    O O O\n" +
          "  O O O O O\n" +
          "O O O O O O O\n" +
          "O O O O O O O\n" +
          "O O O _ O O O\n" +
          "  O O _ O O\n" +
          "    O O O\n" +
          "Testing rendering message and board!", in.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


}
