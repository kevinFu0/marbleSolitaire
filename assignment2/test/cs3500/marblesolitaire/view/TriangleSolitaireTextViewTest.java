package cs3500.marblesolitaire.view;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;

import static org.junit.Assert.assertEquals;


/**
 * test class for TriangleSolitaireTextView.
 */
public class TriangleSolitaireTextViewTest {
  MarbleSolitaireModel m1;
  MarbleSolitaireModel m2;
  TriangleSolitaireTextView v1;
  Appendable destination;

  @Before
  public void init() {
    m1 = new TriangleSolitaireModel();
    m2 = new TriangleSolitaireModel(6);
    destination = new StringBuilder();
    v1 = new TriangleSolitaireTextView(m1, destination);
  }

  /**
   * testing bad init given two null arguments.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBadInit() {
    v1 = new TriangleSolitaireTextView(null, null);
  }

  /**
   * testing bad init given null model.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithNullBoard() {
    v1 = new TriangleSolitaireTextView(null, destination);
  }

  /**
   * testing bad init given null Appendable.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithNullAppendable() {
    v1 = new TriangleSolitaireTextView(m1, null);
  }


  @Test
  // to string for various models
  public void testToString1() {
    v1 = new TriangleSolitaireTextView(m2);
    assertEquals("     _\n" +
        "    O O\n" +
        "   O O O\n" +
        "  O O O O\n" +
        " O O O O O\n" +
        "O O O O O O", v1.toString());
    m2 = new TriangleSolitaireModel(4,1);
    v1 = new TriangleSolitaireTextView(m2);
    assertEquals("    O\n" +
        "   O O\n" +
        "  O O O\n" +
        " O O O O\n" +
        "O _ O O O", v1.toString());
    m2 = new TriangleSolitaireModel(5, 1, 1);
    v1 = new TriangleSolitaireTextView(m2);
    assertEquals("    O\n" +
        "   O _\n" +
        "  O O O\n" +
        " O O O O\n" +
        "O O O O O", v1.toString());
  }

  @Test
  public void testRenderBoard1() {
    try {
      v1.renderBoard();
      m1.move(2,0,0,0);
      v1.renderMessage("\n");
      v1.renderBoard();
      assertEquals(
          "    _\n" +
          "   O O\n" +
          "  O O O\n" +
          " O O O O\n" +
          "O O O O O\n" +
          "    O\n" +
          "   _ O\n" +
          "  _ O O\n" +
          " O O O O\n" +
          "O O O O O", destination.toString());

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
      TriangleSolitaireTextView v10 = new TriangleSolitaireTextView(m1, in);
      v10.renderMessage("hello testing");
      assertEquals("hello testing", in.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testRenderMessageandBoard() {
    Appendable in = new StringBuilder("");
    MarbleSolitaireView v9 = new TriangleSolitaireTextView(m1, in);
    m1.move(2, 0, 0, 0);
    try {
      v9.renderBoard();
      v9.renderMessage("\nTesting rendering message and board!");
      assertEquals("    O\n" +
          "   _ O\n" +
          "  _ O O\n" +
          " O O O O\n" +
          "O O O O O\n" +
          "Testing rendering message and board!", in.toString());
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }





}