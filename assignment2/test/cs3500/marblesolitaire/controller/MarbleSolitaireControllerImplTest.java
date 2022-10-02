package cs3500.marblesolitaire.controller;


import org.junit.Before;
import org.junit.Test;


import java.io.StringReader;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

import static org.junit.Assert.assertEquals;

/**
 * test class for marbleSolitaireControllerImpl.
 */
public class MarbleSolitaireControllerImplTest {

  /**
   * mock for testing marbleSolitaire.
   */
  public class MockMarbleSolitaire implements MarbleSolitaireModel {

    private final StringBuilder log;

    public MockMarbleSolitaire(StringBuilder log) {
      this.log = log;
    }

    @Override
    public void move(int fromRow, int fromCol, int toRow, int toCol)
        throws IllegalArgumentException {
      log.append(String.format("In move: fromRow = %d, fromCol = %d, toRow = %d, toCol = %d\n",
          fromRow, fromCol, toRow, toCol));
    }

    @Override
    public boolean isGameOver() {
      log.append("In isGameOver\n");
      return false;
    }

    @Override
    public int getBoardSize() {
      log.append("In getBoardSize\n");
      return 0;
    }

    @Override
    public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
      log.append("In getSlotAt: row = %d, col = %d\n");
      return null;
    }

    @Override
    public int getScore() {
      log.append("In getScore\n");
      return 0;
    }
  }


  private Appendable out;
  private MarbleSolitaireModel m1;
  private MarbleSolitaireView v1;
  private MarbleSolitaireController controller;

  @Before
  public void init() {
    out = new StringBuilder();
    m1 = new EnglishSolitaireModel();
    v1 = new MarbleSolitaireTextView(m1, out);
  }


  /**
   * testing null model.
   */
  @Test(expected = IllegalArgumentException.class)
  public void NullModelConstructor() {
    Readable in = new StringReader("doesn't matter");
    MarbleSolitaireModel m1 = new EnglishSolitaireModel();
    MarbleSolitaireView v1 = new MarbleSolitaireTextView(m1);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(null, v1, in);
  }

  /**
   * testing null view.
   */
  @Test(expected = IllegalArgumentException.class)
  public void NullViewConstructor() {
    Readable in = new StringReader("doesn't matter");
    MarbleSolitaireModel m1 = new EnglishSolitaireModel();
    MarbleSolitaireView v1 = new MarbleSolitaireTextView(m1);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(m1, null, in);
  }

  /**
   * testing null Readable.
   */
  @Test(expected = IllegalArgumentException.class)
  public void NullReadableConstructor() {
    Readable in = new StringReader("doesn't matter");
    MarbleSolitaireModel m1 = new EnglishSolitaireModel();
    MarbleSolitaireView v1 = new MarbleSolitaireTextView(m1);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(m1, v1, null);
  }

  /**
   * testing quit game after one move.
   */
  @Test
  public void testQuitGameAfterOneMove() {
    Readable in = new StringReader("6 4 4 4 q");
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(m1, v1, in);
    controller.playGame();
    assertEquals("    O O O\n" +
        "    O O O\n" +
        "O O O O O O O\n" +
        "O O O _ O O O\n" +
        "O O O O O O O\n" +
        "    O O O\n" +
        "    O O O\n" +
        "Score: 32\n" +
        "\n" +
        "    O O O\n" +
        "    O O O\n" +
        "O O O O O O O\n" +
        "O O O O O O O\n" +
        "O O O _ O O O\n" +
        "    O _ O\n" +
        "    O O O\n" +
        "Score: 31\n" +
        "Game quit!\n" +
        "State of game when quit:\n" +
        "    O O O\n" +
        "    O O O\n" +
        "O O O O O O O\n" +
        "O O O O O O O\n" +
        "O O O _ O O O\n" +
        "    O _ O\n" +
        "    O O O\n" +
        "Score: 31\n", out.toString());
  }

  /**
   * testing game end after series of moves.
   */
  @Test
  public void testIsGameOver() {
    Readable in = new StringReader("6 4 4 4  3 4 5 4  1 4 3 4  4 6 4 4  4 3 4 5  4 1 4 3 q");
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(m1, v1, in);
    controller.playGame();
    assertEquals("    O O O\n" +
        "    O O O\n" +
        "O O O O O O O\n" +
        "O O O _ O O O\n" +
        "O O O O O O O\n" +
        "    O O O\n" +
        "    O O O\n" +
        "Score: 32\n" +
        "\n" +
        "    O O O\n" +
        "    O O O\n" +
        "O O O O O O O\n" +
        "O O O O O O O\n" +
        "O O O _ O O O\n" +
        "    O _ O\n" +
        "    O O O\n" +
        "Score: 31\n" +
        "\n" +
        "    O O O\n" +
        "    O O O\n" +
        "O O O _ O O O\n" +
        "O O O _ O O O\n" +
        "O O O O O O O\n" +
        "    O _ O\n" +
        "    O O O\n" +
        "Score: 30\n" +
        "\n" +
        "    O _ O\n" +
        "    O _ O\n" +
        "O O O O O O O\n" +
        "O O O _ O O O\n" +
        "O O O O O O O\n" +
        "    O _ O\n" +
        "    O O O\n" +
        "Score: 29\n" +
        "\n" +
        "    O _ O\n" +
        "    O _ O\n" +
        "O O O O O O O\n" +
        "O O O O _ _ O\n" +
        "O O O O O O O\n" +
        "    O _ O\n" +
        "    O O O\n" +
        "Score: 28\n" +
        "\n" +
        "    O _ O\n" +
        "    O _ O\n" +
        "O O O O O O O\n" +
        "O O _ _ O _ O\n" +
        "O O O O O O O\n" +
        "    O _ O\n" +
        "    O O O\n" +
        "Score: 27\n" +
        "\n" +
        "    O _ O\n" +
        "    O _ O\n" +
        "O O O O O O O\n" +
        "_ _ O _ O _ O\n" +
        "O O O O O O O\n" +
        "    O _ O\n" +
        "    O O O\n" +
        "Score: 26\n" +
        "Game over!\n" +
        "    O _ O\n" +
        "    O _ O\n" +
        "O O O O O O O\n" +
        "_ _ O _ O _ O\n" +
        "O O O O O O O\n" +
        "    O _ O\n" +
        "    O O O\n" +
        "Score: 26\n", out.toString());
  }

  /**
   * testing game over with a different set of moves.
   */
  @Test
  public void testisGameOver2() {
    Readable in = new StringReader("2 4 4 4  5 4 3 4  7 4 5 4  4 6 4 4  4 3 4 5  4 1 4 3 q");
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(m1, v1, in);
    controller.playGame();
    assertEquals("    O O O\n" +
        "    O O O\n" +
        "O O O O O O O\n" +
        "O O O _ O O O\n" +
        "O O O O O O O\n" +
        "    O O O\n" +
        "    O O O\n" +
        "Score: 32\n" +
        "\n" +
        "    O O O\n" +
        "    O _ O\n" +
        "O O O _ O O O\n" +
        "O O O O O O O\n" +
        "O O O O O O O\n" +
        "    O O O\n" +
        "    O O O\n" +
        "Score: 31\n" +
        "\n" +
        "    O O O\n" +
        "    O _ O\n" +
        "O O O O O O O\n" +
        "O O O _ O O O\n" +
        "O O O _ O O O\n" +
        "    O O O\n" +
        "    O O O\n" +
        "Score: 30\n" +
        "\n" +
        "    O O O\n" +
        "    O _ O\n" +
        "O O O O O O O\n" +
        "O O O _ O O O\n" +
        "O O O O O O O\n" +
        "    O _ O\n" +
        "    O _ O\n" +
        "Score: 29\n" +
        "\n" +
        "    O O O\n" +
        "    O _ O\n" +
        "O O O O O O O\n" +
        "O O O O _ _ O\n" +
        "O O O O O O O\n" +
        "    O _ O\n" +
        "    O _ O\n" +
        "Score: 28\n" +
        "\n" +
        "    O O O\n" +
        "    O _ O\n" +
        "O O O O O O O\n" +
        "O O _ _ O _ O\n" +
        "O O O O O O O\n" +
        "    O _ O\n" +
        "    O _ O\n" +
        "Score: 27\n" +
        "\n" +
        "    O O O\n" +
        "    O _ O\n" +
        "O O O O O O O\n" +
        "_ _ O _ O _ O\n" +
        "O O O O O O O\n" +
        "    O _ O\n" +
        "    O _ O\n" +
        "Score: 26\n" +
        "Game over!\n" +
        "    O O O\n" +
        "    O _ O\n" +
        "O O O O O O O\n" +
        "_ _ O _ O _ O\n" +
        "O O O O O O O\n" +
        "    O _ O\n" +
        "    O _ O\n" +
        "Score: 26\n", out.toString());

  }


  /**
   * testing invalid input.
   */
  @Test
  public void testInvalidInput() {
    Readable in = new StringReader("6 4 f 4 q");
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(m1, v1, in);
    controller.playGame();
    assertEquals("    O O O\n" +
        "    O O O\n" +
        "O O O O O O O\n" +
        "O O O _ O O O\n" +
        "O O O O O O O\n" +
        "    O O O\n" +
        "    O O O\n" +
        "Score: 32\n" +
        "Please enter a valid input(q, Q, or pos int)\n" +
        "Game quit!\n" +
        "State of game when quit:\n" +
        "    O O O\n" +
        "    O O O\n" +
        "O O O O O O O\n" +
        "O O O _ O O O\n" +
        "O O O O O O O\n" +
        "    O O O\n" +
        "    O O O\n" +
        "Score: 32\n", out.toString());
  }

  /**
   * testing bad move.
   */
  @Test
  public void testBadMove() {
    Readable in = new StringReader("6 4 4 3 q");
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(m1, v1, in);
    controller.playGame();
    assertEquals("    O O O\n" +
        "    O O O\n" +
        "O O O O O O O\n" +
        "O O O _ O O O\n" +
        "O O O O O O O\n" +
        "    O O O\n" +
        "    O O O\n" +
        "Score: 32\n" +
        "Invalid move. Move must be horizontal or vertical " +
        "from a marble to an empty space " +
        "exactly" + " two spaces apart  and have a marble " +
        "in-between the FROM and TO positions\n" +
        "    O O O\n" +
        "    O O O\n" +
        "O O O O O O O\n" +
        "O O O _ O O O\n" +
        "O O O O O O O\n" +
        "    O O O\n" +
        "    O O O\n" +
        "Score: 32\n" +
        "Game quit!\n" +
        "State of game when quit:\n" +
        "    O O O\n" +
        "    O O O\n" +
        "O O O O O O O\n" +
        "O O O _ O O O\n" +
        "O O O O O O O\n" +
        "    O O O\n" +
        "    O O O\n" +
        "Score: 32\n", out.toString());
  }

  @Test
  public void testIsGameOverEuro() {
    Readable in = new StringReader("2 4 4 4  5 4 3 4  7 4 5 4  " +
        "4 6 4 4  4 3 4 5  2 3 4 3  4 2 4 4  4 5 4 3  6 2 4 2 " +
        "6 6 4 6  3 2 5 2  3 6 5 6  3 4 3 6  1 5 3 5  1 3 1 5 " +
        "3 6 3 4  6 5 4 5  5 3 5 5  5 1 5 3  3 1 5 1  4 5 6 5 " +
        "7 5 5 5  5 6 5 4  5 3 5 5  7 3 5 3  4 3 6 3 q");
    m1 = new EuropeanSolitaireModel();
    v1 = new MarbleSolitaireTextView(m1, out);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(m1, v1, in);
    controller.playGame();
    assertEquals("    O O O\n" +
        "  O O O O O\n" +
        "O O O O O O O\n" +
        "O O O _ O O O\n" +
        "O O O O O O O\n" +
        "  O O O O O\n" +
        "    O O O\n" +
        "Score: 36\n" +
        "\n" +
        "    O O O\n" +
        "  O O _ O O\n" +
        "O O O _ O O O\n" +
        "O O O O O O O\n" +
        "O O O O O O O\n" +
        "  O O O O O\n" +
        "    O O O\n" +
        "Score: 35\n" +
        "\n" +
        "    O O O\n" +
        "  O O _ O O\n" +
        "O O O O O O O\n" +
        "O O O _ O O O\n" +
        "O O O _ O O O\n" +
        "  O O O O O\n" +
        "    O O O\n" +
        "Score: 34\n" +
        "\n" +
        "    O O O\n" +
        "  O O _ O O\n" +
        "O O O O O O O\n" +
        "O O O _ O O O\n" +
        "O O O O O O O\n" +
        "  O O _ O O\n" +
        "    O _ O\n" +
        "Score: 33\n" +
        "\n" +
        "    O O O\n" +
        "  O O _ O O\n" +
        "O O O O O O O\n" +
        "O O O O _ _ O\n" +
        "O O O O O O O\n" +
        "  O O _ O O\n" +
        "    O _ O\n" +
        "Score: 32\n" +
        "\n" +
        "    O O O\n" +
        "  O O _ O O\n" +
        "O O O O O O O\n" +
        "O O _ _ O _ O\n" +
        "O O O O O O O\n" +
        "  O O _ O O\n" +
        "    O _ O\n" +
        "Score: 31\n" +
        "\n" +
        "    O O O\n" +
        "  O _ _ O O\n" +
        "O O _ O O O O\n" +
        "O O O _ O _ O\n" +
        "O O O O O O O\n" +
        "  O O _ O O\n" +
        "    O _ O\n" +
        "Score: 30\n" +
        "\n" +
        "    O O O\n" +
        "  O _ _ O O\n" +
        "O O _ O O O O\n" +
        "O _ _ O O _ O\n" +
        "O O O O O O O\n" +
        "  O O _ O O\n" +
        "    O _ O\n" +
        "Score: 29\n" +
        "\n" +
        "    O O O\n" +
        "  O _ _ O O\n" +
        "O O _ O O O O\n" +
        "O _ O _ _ _ O\n" +
        "O O O O O O O\n" +
        "  O O _ O O\n" +
        "    O _ O\n" +
        "Score: 28\n" +
        "\n" +
        "    O O O\n" +
        "  O _ _ O O\n" +
        "O O _ O O O O\n" +
        "O O O _ _ _ O\n" +
        "O _ O O O O O\n" +
        "  _ O _ O O\n" +
        "    O _ O\n" +
        "Score: 27\n" +
        "\n" +
        "    O O O\n" +
        "  O _ _ O O\n" +
        "O O _ O O O O\n" +
        "O O O _ _ O O\n" +
        "O _ O O O _ O\n" +
        "  _ O _ O _\n" +
        "    O _ O\n" +
        "Score: 26\n" +
        "\n" +
        "    O O O\n" +
        "  O _ _ O O\n" +
        "O _ _ O O O O\n" +
        "O _ O _ _ O O\n" +
        "O O O O O _ O\n" +
        "  _ O _ O _\n" +
        "    O _ O\n" +
        "Score: 25\n" +
        "\n" +
        "    O O O\n" +
        "  O _ _ O O\n" +
        "O _ _ O O _ O\n" +
        "O _ O _ _ _ O\n" +
        "O O O O O O O\n" +
        "  _ O _ O _\n" +
        "    O _ O\n" +
        "Score: 24\n" +
        "\n" +
        "    O O O\n" +
        "  O _ _ O O\n" +
        "O _ _ _ _ O O\n" +
        "O _ O _ _ _ O\n" +
        "O O O O O O O\n" +
        "  _ O _ O _\n" +
        "    O _ O\n" +
        "Score: 23\n" +
        "\n" +
        "    O O _\n" +
        "  O _ _ _ O\n" +
        "O _ _ _ O O O\n" +
        "O _ O _ _ _ O\n" +
        "O O O O O O O\n" +
        "  _ O _ O _\n" +
        "    O _ O\n" +
        "Score: 22\n" +
        "\n" +
        "    _ _ O\n" +
        "  O _ _ _ O\n" +
        "O _ _ _ O O O\n" +
        "O _ O _ _ _ O\n" +
        "O O O O O O O\n" +
        "  _ O _ O _\n" +
        "    O _ O\n" +
        "Score: 21\n" +
        "\n" +
        "    _ _ O\n" +
        "  O _ _ _ O\n" +
        "O _ _ O _ _ O\n" +
        "O _ O _ _ _ O\n" +
        "O O O O O O O\n" +
        "  _ O _ O _\n" +
        "    O _ O\n" +
        "Score: 20\n" +
        "\n" +
        "    _ _ O\n" +
        "  O _ _ _ O\n" +
        "O _ _ O _ _ O\n" +
        "O _ O _ O _ O\n" +
        "O O O O _ O O\n" +
        "  _ O _ _ _\n" +
        "    O _ O\n" +
        "Score: 19\n" +
        "\n" +
        "    _ _ O\n" +
        "  O _ _ _ O\n" +
        "O _ _ O _ _ O\n" +
        "O _ O _ O _ O\n" +
        "O O _ _ O O O\n" +
        "  _ O _ _ _\n" +
        "    O _ O\n" +
        "Score: 18\n" +
        "\n" +
        "    _ _ O\n" +
        "  O _ _ _ O\n" +
        "O _ _ O _ _ O\n" +
        "O _ O _ O _ O\n" +
        "_ _ O _ O O O\n" +
        "  _ O _ _ _\n" +
        "    O _ O\n" +
        "Score: 17\n" +
        "\n" +
        "    _ _ O\n" +
        "  O _ _ _ O\n" +
        "_ _ _ O _ _ O\n" +
        "_ _ O _ O _ O\n" +
        "O _ O _ O O O\n" +
        "  _ O _ _ _\n" +
        "    O _ O\n" +
        "Score: 16\n" +
        "\n" +
        "    _ _ O\n" +
        "  O _ _ _ O\n" +
        "_ _ _ O _ _ O\n" +
        "_ _ O _ _ _ O\n" +
        "O _ O _ _ O O\n" +
        "  _ O _ O _\n" +
        "    O _ O\n" +
        "Score: 15\n" +
        "\n" +
        "    _ _ O\n" +
        "  O _ _ _ O\n" +
        "_ _ _ O _ _ O\n" +
        "_ _ O _ _ _ O\n" +
        "O _ O _ O O O\n" +
        "  _ O _ _ _\n" +
        "    O _ _\n" +
        "Score: 14\n" +
        "\n" +
        "    _ _ O\n" +
        "  O _ _ _ O\n" +
        "_ _ _ O _ _ O\n" +
        "_ _ O _ _ _ O\n" +
        "O _ O O _ _ O\n" +
        "  _ O _ _ _\n" +
        "    O _ _\n" +
        "Score: 13\n" +
        "\n" +
        "    _ _ O\n" +
        "  O _ _ _ O\n" +
        "_ _ _ O _ _ O\n" +
        "_ _ O _ _ _ O\n" +
        "O _ _ _ O _ O\n" +
        "  _ O _ _ _\n" +
        "    O _ _\n" +
        "Score: 12\n" +
        "\n" +
        "    _ _ O\n" +
        "  O _ _ _ O\n" +
        "_ _ _ O _ _ O\n" +
        "_ _ O _ _ _ O\n" +
        "O _ O _ O _ O\n" +
        "  _ _ _ _ _\n" +
        "    _ _ _\n" +
        "Score: 11\n" +
        "\n" +
        "    _ _ O\n" +
        "  O _ _ _ O\n" +
        "_ _ _ O _ _ O\n" +
        "_ _ _ _ _ _ O\n" +
        "O _ _ _ O _ O\n" +
        "  _ O _ _ _\n" +
        "    _ _ _\n" +
        "Score: 10\n" +
        "Game over!\n" +
        "    _ _ O\n" +
        "  O _ _ _ O\n" +
        "_ _ _ O _ _ O\n" +
        "_ _ _ _ _ _ O\n" +
        "O _ _ _ O _ O\n" +
        "  _ O _ _ _\n" +
        "    _ _ _\n" +
        "Score: 10\n", out.toString());
  }

  @Test
  public void testIsGameOverTriangle() {
    Readable in = new StringReader("3 1 1 1  5 1 3 1  5 3 5 1  4 3 4 1  " +
        "5 5 5 3  4 1 2 1  3 3 5 5  1 1 3 1  3 1 3 3  3 3 1 1 q");
    m1 = new TriangleSolitaireModel();
    v1 = new TriangleSolitaireTextView(m1, out);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(m1, v1, in);
    controller.playGame();
    assertEquals("    _\n" +
        "   O O\n" +
        "  O O O\n" +
        " O O O O\n" +
        "O O O O O\n" +
        "Score: 14\n" +
        "\n" +
        "    O\n" +
        "   _ O\n" +
        "  _ O O\n" +
        " O O O O\n" +
        "O O O O O\n" +
        "Score: 13\n" +
        "\n" +
        "    O\n" +
        "   _ O\n" +
        "  O O O\n" +
        " _ O O O\n" +
        "_ O O O O\n" +
        "Score: 12\n" +
        "\n" +
        "    O\n" +
        "   _ O\n" +
        "  O O O\n" +
        " _ O O O\n" +
        "O _ _ O O\n" +
        "Score: 11\n" +
        "\n" +
        "    O\n" +
        "   _ O\n" +
        "  O O O\n" +
        " O _ _ O\n" +
        "O _ _ O O\n" +
        "Score: 10\n" +
        "\n" +
        "    O\n" +
        "   _ O\n" +
        "  O O O\n" +
        " O _ _ O\n" +
        "O _ O _ _\n" +
        "Score: 9\n" +
        "\n" +
        "    O\n" +
        "   O O\n" +
        "  _ O O\n" +
        " _ _ _ O\n" +
        "O _ O _ _\n" +
        "Score: 8\n" +
        "\n" +
        "    O\n" +
        "   O O\n" +
        "  _ O _\n" +
        " _ _ _ _\n" +
        "O _ O _ O\n" +
        "Score: 7\n" +
        "\n" +
        "    _\n" +
        "   _ O\n" +
        "  O O _\n" +
        " _ _ _ _\n" +
        "O _ O _ O\n" +
        "Score: 6\n" +
        "\n" +
        "    _\n" +
        "   _ O\n" +
        "  _ _ O\n" +
        " _ _ _ _\n" +
        "O _ O _ O\n" +
        "Score: 5\n" +
        "\n" +
        "    O\n" +
        "   _ _\n" +
        "  _ _ _\n" +
        " _ _ _ _\n" +
        "O _ O _ O\n" +
        "Score: 4\n" +
        "Game over!\n" +
        "    O\n" +
        "   _ _\n" +
        "  _ _ _\n" +
        " _ _ _ _\n" +
        "O _ O _ O\n" +
        "Score: 4\n", out.toString());


  }



  /**
   * using mock to test correct input given to move.
   */
  @Test
  public void testMoveInput() {
    Readable in = new StringReader("6 4 4 4 q");
    StringBuilder log = new StringBuilder();
    MarbleSolitaireModel m7 = new MockMarbleSolitaire(log);
    MarbleSolitaireView v7 = new MarbleSolitaireTextView(m7, out);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(m7, v7, in);
    controller.playGame();
    String[] output = log.toString().split("\n");
    assertEquals("In move: fromRow = 5, fromCol = 3, toRow = 3, toCol = 3", output[3]);
  }


  /**
   * testing that an illegal state exception is throw if the program is not quit.
   */
  @Test(expected = IllegalStateException.class)
  public void testNoQuit() {
    Readable r7 = new StringReader("2 4 4 4");
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(m1, v1, r7);
    controller.playGame();
  }

  /**
   * testing that a move can be made after reciving some bad input.
   */
  @Test
  public void testMoveAfterBadInput() {
    Readable r7 = new StringReader("6 f 4 4 4 q");
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(m1, v1, r7);
    controller.playGame();
    assertEquals("    O O O\n" +
        "    O O O\n" +
        "O O O O O O O\n" +
        "O O O _ O O O\n" +
        "O O O O O O O\n" +
        "    O O O\n" +
        "    O O O\n" +
        "Score: 32\n" +
        "Please enter a valid input(q, Q, or pos int)\n" +
        "\n" +
        "    O O O\n" +
        "    O O O\n" +
        "O O O O O O O\n" +
        "O O O O O O O\n" +
        "O O O _ O O O\n" +
        "    O _ O\n" +
        "    O O O\n" +
        "Score: 31\n" +
        "Game quit!\n" +
        "State of game when quit:\n" +
        "    O O O\n" +
        "    O O O\n" +
        "O O O O O O O\n" +
        "O O O O O O O\n" +
        "O O O _ O O O\n" +
        "    O _ O\n" +
        "    O O O\n" +
        "Score: 31\n", out.toString());
  }







  /**
   * testing illegalstate exception if the given input is empty.
   */
  @Test (expected = IllegalStateException.class)
  public void testEmptyInput() {
    Readable in = new StringReader("");
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(m1, v1, in);
    controller.playGame();
  }

  /**
   * testing illegalstate exception if given input is a negative number.
   */
  @Test (expected = IllegalStateException.class)
  public void testNegativeInput() {
    Readable in = new StringReader("-9");
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(m1, v1, in);
    controller.playGame();
  }

  /**
   * testing illegalstate exception if given input is random letters.
   */
  @Test (expected = IllegalStateException.class)
  public void testLettersInput() {
    Readable in = new StringReader("hello");
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(m1, v1, in);
    controller.playGame();
  }




}