package cs3500.marblesolitaire.view;

import java.awt.Image;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import cs3500.marblesolitaire.controller.ControllerFeatures;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * represents the view for this marble solitaire gui.
 */
public class BoardPanel extends JPanel implements BoardPanelWithFeatures {
  private MarbleSolitaireModelState modelState;
  private Image emptySlot;
  private Image marbleSlot;
  private Image blankSlot;
  private final int cellDimension;
  private int originX;
  private int originY;

  /**
   * represents the board panel for this marble solitaire gui.
   * @param state the model
   * @throws IllegalStateException if the icons are not found
   */

  public BoardPanel(MarbleSolitaireModelState state) throws IllegalStateException {
    super();
    this.modelState = state;
    this.setBackground(Color.WHITE);
    this.cellDimension = 50;
    try {
      emptySlot = ImageIO.read(new FileInputStream("res/empty.png"));
      emptySlot = emptySlot.getScaledInstance(cellDimension, cellDimension, Image.SCALE_DEFAULT);

      marbleSlot = ImageIO.read(new FileInputStream("res/marble.png"));
      marbleSlot = marbleSlot.getScaledInstance(cellDimension, cellDimension, Image.SCALE_DEFAULT);

      blankSlot = ImageIO.read(new FileInputStream("res/blank.png"));
      blankSlot = blankSlot.getScaledInstance(cellDimension, cellDimension, Image.SCALE_DEFAULT);

      this.setPreferredSize(
          new Dimension((this.modelState.getBoardSize() + 4) * cellDimension
              , (this.modelState.getBoardSize() + 4) * cellDimension));
    } catch (IOException e) {
      throw new IllegalStateException("Icons not found!");
    }

  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    originX = (int) (this.getPreferredSize().getWidth() / 2
        - this.modelState.getBoardSize() * cellDimension / 2);
    originY = (int) (this.getPreferredSize().getHeight() / 2
        - this.modelState.getBoardSize() * cellDimension / 2);

    for (int row = 0; row < this.modelState.getBoardSize(); row++) {
      for (int col = 0; col < this.modelState.getBoardSize(); col++) {
        Image slot = slotImage(this.modelState.getSlotAt(row, col));
        g.drawImage(slot, this.originX + (cellDimension * col),
            this.originY + (cellDimension * row), this);
      }
    }
  }

  /**
   * determines which image to be displayed based on the slot state given.
   * @param state the slot state
   * @return the image that corresponds with the slot state
   */
  private Image slotImage(MarbleSolitaireModelState.SlotState state) {
    if (state == MarbleSolitaireModelState.SlotState.Empty) {
      return this.emptySlot;
    } else if (state == MarbleSolitaireModelState.SlotState.Marble) {
      return this.marbleSlot;
    } else {
      return this.blankSlot;
    }
  }

  /**
   *
   * @param features an object that holds the information for features of this program
   */
  @Override
  public void accept(ControllerFeatures features) {
    this.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        int row = (e.getY() - originY) / cellDimension;
        int col = (e.getX() - originX) / cellDimension;
        features.slotClicked(row,col);
      }
    });
  }
}
