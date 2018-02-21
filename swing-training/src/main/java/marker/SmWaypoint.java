package marker;

import java.awt.*;
import javax.swing.*;

/**
 * @author bespalko
 * @since 21.02.2018
 */
public class SmWaypoint {
  public static final String ONE_STORE = "1";
  private JPanel component;
  private Labels markerType;
  private Labels shopType;
  private CenteredLabel shopLabel;
  private CenteredLabel numberLabel;
  private int nodesCount;
  private int exceptionsCount;

  public SmWaypoint() {
    this(Labels.SHOP5);
  }

  public SmWaypoint(Labels shopType) {
    this(shopType, Labels.CLUSTER);
  }

  public SmWaypoint(Labels shopType, Labels markerType) {
    component = new JPanel();
    component.setLayout(null);
    component.setOpaque(false);
    component.setBorder(BorderFactory.createLineBorder(Color.BLACK));

    JLabel markerLabel = new JLabel(markerType.getIcon());
    Dimension markerLabelSize = markerLabel.getPreferredSize();
    markerLabel.setBounds(component.getX(),
                          component.getY(),
                          markerLabelSize.width,
                          markerLabelSize.height);
    component.add(markerLabel);

    shopLabel = getCenteredLabel(component, shopType);
    component.add(shopLabel);

    numberLabel = getCenteredLabel(component, Labels.EMPTY);
    numberLabel.setText(ONE_STORE);
    numberLabel.setFont(new Font("Tahoma", Font.PLAIN, 8));
    numberLabel.setVisible(false);
    component.add(numberLabel);

    this.markerType = markerType;
    this.shopType = shopType;
    component.setSize(50, 50);
    component.setVisible(true);
  }

  private CenteredLabel getCenteredLabel(JPanel parent, Labels lType) {
    CenteredLabel label = new CenteredLabel(parent);
    label.setIcon(lType.getIcon());
    label.setCenter(lType.getCenterX(), lType.getCenterY());
    label.centering();
    return label;
  }

  public JComponent getComponent() {
    return component;
  }

  public int getExceptionsCount() {
    return exceptionsCount;
  }

  public void setExceptionsCount(int exceptionsCount) {
    this.exceptionsCount = exceptionsCount;
  }

  public int getNumber() {
    return nodesCount;
  }

  public void setNumber(int nodesCount) {
    assert nodesCount > 0 : "Nodes number < 0!";
    if (this.nodesCount == nodesCount) {
      return;
    }
    if (nodesCount == 1) {
      shopLabel.setVisible(true);
      numberLabel.setVisible(false);
    } else {
      shopLabel.setVisible(false);
      String newNumber = String.valueOf(nodesCount);
      String oldNumber = numberLabel.getText();
      numberLabel.setText(newNumber);
      if (newNumber.length() != oldNumber.length()) {
        numberLabel.centering();
      }
      numberLabel.setVisible(true);

    }
    this.nodesCount = nodesCount;
    component.repaint();
  }

  public enum Labels {
    X5("/image/marker-5.png", 13, 13),
    CAROUSEL("/image/marker-carousel.png", 13, 13),
    SHOP5("/image/marker-5.png", 13, 13),
    CLUSTER("/image/marker-cluster.png", 13, 13),
    CROSSING("/image/marker-crossing.png", 13, 13),
    EXPRESS("/image/marker-express.png", 13, 13),
    EMPTY("/image/marker-empty.png", 13, 13);

    private ImageIcon icon;
    private int centerX;
    private int centerY;

    Labels(String url, int centerX, int centerY) {
      this.icon = new ImageIcon(getClass().getResource(url));
      this.centerX = centerX;
      this.centerY = centerY;
    }

    public int getCenterX() {
      return centerX;
    }

    public int getCenterY() {
      return centerY;
    }

    public ImageIcon getIcon() {
      return icon;
    }
  }

  /**
   * JLabel, который центрируется вокруг заданной точки
   */
  private class CenteredLabel extends JLabel {
    private JComponent parent;
    private int centerX;
    private int centerY;

    public CenteredLabel(JComponent parent) {
      this.parent = parent;
    }

    public void setCenter(int centerX, int centerY) {
      this.centerX = centerX;
      this.centerY = centerY;
    }

    public void centering() {
      Dimension thisSize = this.getPreferredSize();
      //this.setAlignmentX(0);
      //this.setAlignmentY(0);
      int leftBound = centerX - thisSize.width / 2;
      int topBound = centerY - thisSize.height / 2;
      this.setLocation(leftBound, topBound);
      //this.setBounds(leftBound, topBound, thisSize.width, thisSize.height);
    }
  }
}
