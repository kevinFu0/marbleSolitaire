package cs3500.marblesolitaire.model.hw04;

/**
 * represents a european solitaire model.
 */
public class EuropeanSolitaireModel extends AbstractSolitaireModel {

  /**
   * creates a euro game w/ at 3 and empty slot at center.
   */
  public EuropeanSolitaireModel() {
    super();
  }

  /**
   * creates a euro game with armthickness at.
   *
   * @param at arm thickness
   */
  public EuropeanSolitaireModel(int at) {
    super(at);
  }

  /**
   * creates a default euro game with empty slot at sRow, sCol.
   *
   * @param sRow row
   * @param sCol col
   */
  public EuropeanSolitaireModel(int sRow, int sCol) {
    super(sRow, sCol);
  }

  /**
   * creates a euro game with arm thickness at and empty row and col.
   *
   * @param at   arm thickness
   * @param sRow row
   * @param sCol col
   */
  public EuropeanSolitaireModel(int at, int sRow, int sCol) {
    super(at, sRow, sCol);
  }

  // checks valid position for an octagonal game
  // default game -> 7 x 7
  // top left invalid (0,0), (0,1), (1,0)
  // top right invalid (0,5), (0,6), (1,6)
  // bottom left invalid (5,0) (6,0) (6,1)
  // bottom right invalid (6,5) (5,6) (6,6)
  @Override
  protected boolean checkValidPosn(int x, int y) {
    return ((x >= 0 && y >= 0) &&  // x and y have to be pos
        (x < getBoardSize() && y < getBoardSize())  // x and y are in the "square"
        && !(x < armThickness - 1 && y < (getBoardSize() / 2) -  // top left (1,1) is valid
            ((armThickness + (2 * x)) / 2))
        && !(x < armThickness - 1 && y > (getBoardSize() / 2) // top right (1,5) is valid
            + ((armThickness + (2 * x)) / 2))
        && !(x > (getBoardSize() / 2) + ((armThickness + (2 * y)) / 2)
            && y < armThickness - 1)
        && !(x > armThickness * 2 - 2 && y > (getBoardSize() / 2) // bottom left (5, 1) is valid
            + ((armThickness + ((getBoardSize() - x - 1) * 2)) / 2))); //bottom right (5,5) is valid
  }

}
