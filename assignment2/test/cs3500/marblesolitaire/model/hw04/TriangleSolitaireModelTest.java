package cs3500.marblesolitaire.model.hw04;

import org.junit.Before;
import org.junit.Test;


import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;


import static org.junit.Assert.assertEquals;

/**
 * test class for triangle solitaire model.
 */
public class TriangleSolitaireModelTest {

  private TriangleSolitaireModel m1;
  private TriangleSolitaireModel m2;

  @Before
  public void init() {
    m1 = new TriangleSolitaireModel();
  }

  //default constructor
  @Test
  public void testDefault() {
    m1 = new TriangleSolitaireModel();
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, m1.getSlotAt(0, 0));
    assertEquals(5, m1.getBoardSize());
  }

  /**
   * testing valid intialization for constructor2.
   */
  @Test
  public void validConstructor2() {
    m2 = new TriangleSolitaireModel(3, 2);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, m2.getSlotAt(3, 2));
  }

  //testing invalid intialization for constructor2
  @Test(expected = IllegalArgumentException.class)
  public void invalidConstructor21() {
    m2 = new TriangleSolitaireModel(-1, 2);
  }

  //testing invalid intialization for constructor2
  @Test(expected = IllegalArgumentException.class)
  public void invalidConstructor22() {
    m2 = new TriangleSolitaireModel(2, -1);
  }

  //testing invalid intialization for constructor2
  @Test(expected = IllegalArgumentException.class)
  public void invalidConstructor23() {
    m2 = new TriangleSolitaireModel(1, 2);
  }

  //testing invalid intialization for constructor2
  @Test(expected = IllegalArgumentException.class)
  public void invalidConstructor24() {
    m2 = new TriangleSolitaireModel(5, 1);
  }

  //testing invalid intialization for constructor2
  @Test(expected = IllegalArgumentException.class)
  public void invalidConstructor25() {
    m2 = new TriangleSolitaireModel(4, 5);
  }

  /**
   * testing valid intialization for constructor3.
   */
  @Test
  public void validConstructor3() {
    m2 = new TriangleSolitaireModel(5);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, m2.getSlotAt(0, 0));
    m2 = new TriangleSolitaireModel(7);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, m2.getSlotAt(0, 0));
  }

  //testing invalid intialization for constructor3
  @Test(expected = IllegalArgumentException.class)
  public void invalidConstructor31() {
    m2 = new TriangleSolitaireModel(-1);
  }

  /**
   * testing valid intialization for constructor4.
   */
  @Test
  public void validConstructor4() {
    m2 = new TriangleSolitaireModel(5, 1, 0);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, m2.getSlotAt(1, 0));
    m2 = new TriangleSolitaireModel(7, 3, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, m2.getSlotAt(3, 3));
  }

  //testing invalid intialization for constructor4
  @Test(expected = IllegalArgumentException.class)
  public void invalidConstructor41() {
    m2 = new TriangleSolitaireModel(-1, 0, 0);
  }

  //testing invalid intialization for constructor4
  @Test(expected = IllegalArgumentException.class)
  public void invalidConstructor42() {
    m2 = new TriangleSolitaireModel(5, 0, 3);
  }


  // testing bad moves on default board move from empty
  @Test(expected = IllegalArgumentException.class)
  public void testBadMove1() {
    m1.move(0,0,1,1);
  }

  // testing bad moves on default board bad from
  @Test(expected = IllegalArgumentException.class)
  public void testBadMove2() {
    m1.move(6,6,1,1);
  }

  // testing bad moves on default board bad to
  @Test(expected = IllegalArgumentException.class)
  public void testBadMove3() {
    m1.move(3,2,6,6);
  }

  // testing bad moves on default board-- to must be empty
  @Test(expected = IllegalArgumentException.class)
  public void testBadMove4() {
    m1.move(3,0,1,0);
  }

  // testing bad moves horizontal jumping over empty not marble
  @Test(expected = IllegalArgumentException.class)
  public void testBadMove5() {
    m2 = new TriangleSolitaireModel(4,2);
    m2.move(4,0, 4,2);
    m2.move(4,3, 4, 1);
    m2.move(4,4, 4, 2);
  }

  // testing bad moves left jumping over empty not marble
  @Test(expected = IllegalArgumentException.class)
  public void testBadMove6() {
    m2 = new TriangleSolitaireModel(2,1);
    m2.move(4,1,2,1);
    m2.move(2,1, 4, 1);
  }

  // testing bad moves right jumping over empty not marble
  @Test(expected = IllegalArgumentException.class)
  public void testBadMove7() {
    m2 = new TriangleSolitaireModel(2,1);
    m2.move(4,3,2,1);
    m2.move(2,1,4,3);
  }

  @Test
  // testing is game over and valid moves and score
  public void testGameOverAndScore() {
    m2 = new TriangleSolitaireModel();
    m2.move(2,0,0,0);
    m2.move(4,0,2,0);
    assertEquals(false, m2.isGameOver());
    assertEquals(12, m2.getScore());
    m2.move(4,2,4,0);
    m2.move(3,2,3,0);
    m2.move(4,4,4,2);
    m2.move(3,0,1,0);
    m2.move(2,2,4,4);
    m2.move(0,0,2,0);
    m2.move(2,0,2,2);
    assertEquals(false, m2.isGameOver());
    m2.move(2,2,0,0);
    assertEquals(true, m2.isGameOver());
    assertEquals(4, m2.getScore());
  }









}