package marker;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class SwingPaint1 {

  static JButton button = new JButton("+7");
  static SmWaypoint wayPoint = new SmWaypoint();

  public static void main(String[] args) {

    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        createAndShowGUI();
      }
    });
  }

  private static void createAndShowGUI() {
    System.out.println("Created GUI on EDT? "+
                       SwingUtilities.isEventDispatchThread());
    JFrame frame = new JFrame("Swing Paint Demo");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //Create and set up the content pane.
    JComponent newContentPane = wayPoint.getLayeredPane();
    newContentPane.add(button);
    newContentPane.setOpaque(true); //content panes must be opaque
    frame.setContentPane(newContentPane);

    button.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        wayPoint.setNumber(wayPoint.getNumber()+7);
      }
    });
    //Display the window.
    frame.pack();
    frame.setVisible(true);
  }
}

