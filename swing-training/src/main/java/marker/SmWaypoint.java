package marker;

import java.awt.*;
import javax.swing.*;

/**
 * @author bespalko
 * @since 21.02.2018
 */
public class SmWaypoint {
  public static final String ONE_STORE = "1";
  private JLayeredPane component;
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
    component = new JLayeredPane();
    component.setOpaque(true);

    numberLabel = getCenteredLabel(component, Labels.EMPTY);
    numberLabel.setText(ONE_STORE);
    numberLabel.setFont(new Font("Tahoma", Font.PLAIN, 8));
    numberLabel.setHorizontalAlignment(SwingConstants.CENTER);
    numberLabel.setVerticalAlignment(SwingConstants.CENTER);
    numberLabel.setVisible(false);
    component.add(numberLabel, 12);

    shopLabel = getCenteredLabel(component, shopType);
    shopLabel.setVisible(true);
    component.add(shopLabel, 11);

    JLabel markerLabel = new JLabel(markerType.getIcon());
    markerLabel.setBounds(0,
                          0,
                          markerType.getIcon().getIconWidth(),
                          markerType.getIcon().getIconHeight());
    component.add(markerLabel,10);

    component.setPreferredSize(markerLabel.getPreferredSize());

    this.markerType = markerType;
    this.shopType = shopType;
  }

  private CenteredLabel getCenteredLabel(Labels lType) {
    CenteredLabel label = new CenteredLabel(lType.getIcon());
    if(!lType.equals(Labels.EMPTY)) {
      label.setIcon(lType.getIcon());
    }
    label.setOpaque(false);
    label.setCenter(lType.getCenterX(), lType.getCenterY());
    label.centering();

    return label;
  }

  public JComponent getComponent() {
    JPanel panel = new JPanel();
    panel.add(component);
    panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    return panel;
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
    EMPTY(null, 13, 13);

    private ImageIcon icon;
    private int centerX;
    private int centerY;

    Labels(String url, int centerX, int centerY) {
      if(url != null) {
        this.icon = new ImageIcon(getClass().getResource(url));
      }
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
    private double centerX;
    private double centerY;

    public CenteredLabel(Icon icon) {
      super(icon);
    }

    public CenteredLabel(String text) {
      super(text);
    }

    public void setCenter(int centerX, int centerY) {
      this.centerX = centerX;
      this.centerY = centerY;
    }

    public void centering() {
      Dimension thisSize = this.getPreferredSize();
      int leftBound = (int) (centerX - thisSize.width / 2);
      int topBound = (int) (centerY - thisSize.height / 2);
      //this.setLocation(leftBound, topBound);
      this.setBounds(leftBound, topBound, thisSize.width, thisSize.height);
    }
  }
}
