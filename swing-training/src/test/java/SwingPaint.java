import static org.mockito.Mockito.mock;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import marker.GeoPosition;
import marker.SmWaypoint;

public class SwingPaint {

  private GeoPosition gp = mock(GeoPosition.class);
  private SmWaypoint wayPoint = new SmWaypoint(gp, "tooltip", "command");
  private JButton button = new JButton("+7");

  public static void main(String... args) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        new SwingPaint().createAndShowGUI();
      }
    });
  }

  private void createAndShowGUI() {
    System.out.println("Created GUI on EDT? " + SwingUtilities.isEventDispatchThread());
    JFrame frame = new JFrame("Swing Paint Demo");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JComponent newContentPane = wayPoint.getComponent();
    newContentPane.add(button);
    //newContentPane.setOpaque(true);
    frame.setContentPane(newContentPane);

    button.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        wayPoint.setNumber(wayPoint.getNumber() + 7);
      }
    });
    //Display the window.
    frame.pack();
    frame.setVisible(true);
  }

}

