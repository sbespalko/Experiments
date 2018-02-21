package marker;

import java.awt.*;
import javax.swing.*;

public class SwingPaint1 {

  JButton button = new JButton("+7");
  SmWaypoint wayPoint = new SmWaypoint();

  public static void main(String[] args) {

    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        new SwingPaint1().createAndShowGUI();
      }
    });
  }

  private void createAndShowGUI() {
    System.out.println("Created GUI on EDT? "+
                       SwingUtilities.isEventDispatchThread());
    JFrame f = new JFrame("Swing Paint Demo");
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JPanel panel = new JPanel();
    panel.add(wayPoint.getComponent(), BorderLayout.NORTH);
    panel.setSize(50,50);
    panel.setVisible(true);
    f.getContentPane().add(wayPoint.getComponent());

 /*   f.getContentPane().add(button, BorderLayout.SOUTH);

    button.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        wayPoint.setNumber(wayPoint.getNumber() + 7);
      }
    });*/
    f.setSize(300,300);
    f.setVisible(true);
  }
}

