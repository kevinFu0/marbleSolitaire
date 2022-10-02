package cs3500.marblesolitaire.model.hw02;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * test class for englishSolitaireModel.
 */
public class EnglishSolitaireModelTest {

  private EnglishSolitaireModel m1;
  private EnglishSolitaireModel m2;
  private EnglishSolitaireModel m3;

  @Before
  public void init() {
    m1 = new EnglishSolitaireModel();
    m3 = new EnglishSolitaireModel();
  }

  //default constructor
  @Test
  public void testDefault() {
    m1 = new EnglishSolitaireModel();
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, m1.getSlotAt(3, 3));
  }

  /**
   * testing valid intialization for constructor2.
   */
  @Test
  public void validConstructor2() {
    m2 = new EnglishSolitaireModel(3, 4);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, m2.getSlotAt(3, 4));
  }

  /**
   * testing invalid intialization for contructor2.
   */
  @Test(expected = IllegalArgumentException.class)
  public void constructor2() {
    m2 = new EnglishSolitaireModel(0, 1);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, m1.getSlotAt(0, 1));
    m2 = new EnglishSolitaireModel(0, 5);
    m2 = new EnglishSolitaireModel(5, 0);
    m2 = new EnglishSolitaireModel(6, 6);
  }

  /**
   * testing valid intialization for constructor3.
   */
  @Test
  public void validConstructor3() {
    m2 = new EnglishSolitaireModel(3);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, m2.getSlotAt(3, 3));
    m2 = new EnglishSolitaireModel(5);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, m2.getSlotAt(6, 6));
  }

  /**
   * testing invalid intialization for contructor3.
   */
  @Test(expected = IllegalArgumentException.class)
  public void constructor3() {
    m2 = new EnglishSolitaireModel(-5);
    m2 = new EnglishSolitaireModel(6);
  }

  /**
   * testing valid intialization for constructor4.
   */
  @Test
  public void validConstructor4() {
    m2 = new EnglishSolitaireModel(5, 6, 6);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, m2.getSlotAt(6, 6));
    m2 = new EnglishSolitaireModel(5, 4, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, m2.getSlotAt(4, 3));
  }

  /**
   * testing invalid intialization for contructor4.
   */
  @Test(expected = IllegalArgumentException.class)
  public void constructor4() {
    m2 = new EnglishSolitaireModel(-5, 3, 3);
    m2 = new EnglishSolitaireModel(6, 3, 3);
    m2 = new EnglishSolitaireModel(3, 0, 1);
    m2 = new EnglishSolitaireModel(-10, 6, 6);
    m2 = new EnglishSolitaireModel(3, 0, 0);
  }

  /**
   * testing bad move on standard board.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBadMove() {
    // added from self eval 1
    m3.move(3,3, 5,3);
    m3.move(4,3,2,3);
    m3.move(5,3,2,3);
    m3.move(5,3, 3,5);



    m3.move(1, 1, 1, 1);
    m3.move(8, 8, 9, 7);
    m3.move(1, 3, 2, 3);
    m3.move(4, 2, 4, 4);
  }

  /**
   * testing move on standard board using getSLotAt.
   * (5,3) to (3,3)
   */
  @Test
  public void testOkMove() {
    m3.move(5, 3, 3, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, m3.getSlotAt(5, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, m3.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, m3.getSlotAt(3, 3));
  }

  /**
   * testing getboard size.
   * 5->13, 3->7
   */
  @Test
  public void testGetBoardSize() {
    assertEquals(7, m3.getBoardSize());
    m3 = new EnglishSolitaireModel(5);
    assertEquals(13, m3.getBoardSize());
  }

  /**
   * testing getSlotAt on an initial standard board state.
   */
  @Test
  public void testGetSlotAt() {
    m3 = new EnglishSolitaireModel();
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, m3.getSlotAt(5, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, m3.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, m3.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, m3.getSlotAt(0, 1));
  }

  /**
   * testing getScore on intial standard board state.
   * and states with a few moves
   */
  @Test
  public void testGetScore() {
    assertEquals(32, m3.getScore());
    m3.move(5, 3, 3, 3);
    assertEquals(31, m3.getScore());
    m3.move(2, 3, 4, 3);
    assertEquals(30, m3.getScore());
  }

  /**
   * testing is GameOver on a standard board game.
   */
  @Test
  public void testIsGameOver() {
    assertEquals(false, m3.isGameOver());
    m3 = new EnglishSolitaireModel();
    m3.move(5, 3, 3, 3);
    m3.move(2, 3, 4, 3);
    m3.move(0, 3, 2, 3);
    m3.move(3, 1, 3, 3);
    m3.move(3, 4, 3, 2);
    m3.move(3, 6, 3, 4);
    assertEquals(true, m3.isGameOver());
  }


}



