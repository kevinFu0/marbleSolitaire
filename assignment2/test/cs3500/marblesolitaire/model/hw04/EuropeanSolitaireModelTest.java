package cs3500.marblesolitaire.model.hw04;

import org.junit.Before;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

import static org.junit.Assert.assertEquals;

/**
 * test class for european model.
 */
public class EuropeanSolitaireModelTest {

  private EuropeanSolitaireModel m1;
  private EuropeanSolitaireModel m2;

  @Before
  public void init() {
    m1 = new EuropeanSolitaireModel();
  }

  @Test
  public void testConstructor1() {
    m1 = new EuropeanSolitaireModel();
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, m1.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, m1.getSlotAt(0, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, m1.getSlotAt(1, 1));
    assertEquals(7, m1.getBoardSize());
  }

  @Test
  public void validConstructor2() {
    m2 = new EuropeanSolitaireModel(5);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, m2.getSlotAt(6, 6));
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidConstructor21() {
    m2 = new EuropeanSolitaireModel(6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidConstructor22() {
    m2 = new EuropeanSolitaireModel(-5);
  }

  @Test
  public void validConstructor3() {
    m2 = new EuropeanSolitaireModel(4, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, m2.getSlotAt(4, 3));
    m2 = new EuropeanSolitaireModel(5, 5);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, m2.getSlotAt(5, 5));
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidConstructor31() {
    m2 = new EuropeanSolitaireModel(7, 7);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidConstructor32() {
    m2 = new EuropeanSolitaireModel(1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidConstructor33() {
    m2 = new EuropeanSolitaireModel(0, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidConstructor34() {
    m2 = new EuropeanSolitaireModel(6, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidConstructor35() {
    m2 = new EuropeanSolitaireModel(6, 0);
  }

  @Test
  public void validConstructor4() {
    m2 = new EuropeanSolitaireModel(5, 5, 5);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, m2.getSlotAt(5, 5));
    m2 = new EuropeanSolitaireModel(3, 5, 1);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, m2.getSlotAt(5, 1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidConstructor41() {
    m2 = new EuropeanSolitaireModel(4, 3, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidConstructor42() {
    m2 = new EuropeanSolitaireModel(-5, 3, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidConstructor43() {
    m2 = new EuropeanSolitaireModel(3, 6, 0);
  }

  // testing bad moves on default board move from empty
  @Test(expected = IllegalArgumentException.class)
  public void testBadMove1() {
    m1.move(3, 3, 1, 1);
  }

  // bad from
  @Test(expected = IllegalArgumentException.class)
  public void testBadMove2() {
    m1.move(1, 0, 1, 1);
  }

  // bad to
  @Test(expected = IllegalArgumentException.class)
  public void testBadMove3() {
    m1.move(3, 4, 6, 6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadMove4() {
    m1.move(3, 3, 5, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadMove5() {
    m1.move(4, 3, 2, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadMove6() {
    m1.move(5, 3, 2, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadMove7() {
    m1.move(5, 3, 3, 5);
  }

  @Test
  public void testIsGameOverAndScore() {
    m2 = new EuropeanSolitaireModel();
    m2.move(1, 3, 3, 3);
    m2.move(4, 3, 2, 3);
    assertEquals(34, m2.getScore());
    assertEquals(false, m2.isGameOver());
    m2.move(6, 3, 4, 3);
    m2.move(3, 5, 3, 3);
    m2.move(3, 2, 3, 4);
    m2.move(1, 2, 3, 2);
    m2.move(3, 1, 3, 3);
    m2.move(3, 4, 3, 2);
    m2.move(5, 1, 3, 1);
    m2.move(5, 5, 3, 5);
    m2.move(2, 1, 4, 1);
    m2.move(2, 5, 4, 5);
    m2.move(2, 3, 2, 5);
    m2.move(0, 4, 2, 4);
    assertEquals(false, m2.isGameOver());
    m2.move(0, 2, 0, 4);
    m2.move(2, 5, 2, 3);
    m2.move(5, 4, 3, 4);
    m2.move(4, 2, 4, 4);
    m2.move(4, 0, 4, 2);
    m2.move(2, 0, 4, 0);
    m2.move(3, 4, 5, 4);
    m2.move(6, 4, 4, 4);
    m2.move(4, 5, 4, 3);
    m2.move(4, 2, 4, 4);
    m2.move(6, 2, 4, 2);
    m2.move(3, 2, 5, 2);
    assertEquals(true, m2.isGameOver());
  }


}