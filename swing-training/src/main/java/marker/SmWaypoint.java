package marker;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * @author bespalko
 * @since 21.02.2018
 */
public class SmWaypoint {
  private static final String ONE_STORE = "1";
  private JComponent component;
  private JLabel markerLabel;
  private CenteredLabel shopLabel;
  private CenteredLabel numberLabel;
  private Font numberFont;
  private int nodesCount;
  private int exceptionsCount;
  private ArrayList<ActionListener> listeners;

  public SmWaypoint(GeoPosition gp, String tooltip, String command) {
    this(gp, Labels.SHOP5, tooltip, command);
  }

  public SmWaypoint(GeoPosition gp, Labels shopType, String tooltip, String command) {
    this(gp, shopType, Labels.CLUSTER, tooltip, command);
  }

  public SmWaypoint(GeoPosition gp,
                    Labels shopType,
                    Labels markerType,
                    String tooltip,
                    final String command) {
    listeners = new ArrayList<ActionListener>();
    numberFont = new Font("Tahoma", Font.PLAIN, 8);

    initLabels(shopType, markerType);

    JLayeredPane layeredPane = new JLayeredPane();
    layeredPane.add(numberLabel, 12);
    layeredPane.add(shopLabel, 11);
    layeredPane.add(markerLabel, 10);
    layeredPane.setOpaque(true);
    layeredPane.setPreferredSize(markerLabel.getPreferredSize());

    component = new JPanel();
    component.setToolTipText(tooltip);
    component.add(layeredPane);
    component.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        for (ActionListener al : listeners) {
          al.actionPerformed(new ActionEvent(component, ActionEvent.ACTION_PERFORMED, command));
        }
      }
    });
  }

  private void initLabels(Labels shopType, Labels markerType) {
    numberLabel = getCenteredLabel(Labels.EMPTY);
    numberLabel.setText(ONE_STORE);
    numberLabel.setFont(numberFont);
    numberLabel.setHorizontalAlignment(SwingConstants.CENTER);
    numberLabel.setVerticalAlignment(SwingConstants.CENTER);
    numberLabel.setVisible(false);

    shopLabel = getCenteredLabel(shopType);
    shopLabel.setVisible(true);

    markerLabel = new JLabel(markerType.getIcon());
    markerLabel.setBounds(0,
                          0,
                          markerType.getIcon().getIconWidth(),
                          markerType.getIcon().getIconHeight());
  }

  private CenteredLabel getCenteredLabel(Labels lType) {
    CenteredLabel label = new CenteredLabel(lType.getIcon());
    if (!lType.equals(Labels.EMPTY)) {
      label.setIcon(lType.getIcon());
    }
    label.setOpaque(false);
    label.setCenter(lType.getCenterX(), lType.getCenterY());
    label.centering();

    return label;
  }

  public JComponent getLayeredPane() {
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
    //layeredPane.repaint();
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
      if (url != null) {
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
