
import javax.swing.*;

public class SwingTest {
    public static void main(String[] args) {
        System.out.println("Creating window...");
        
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Test Window");
            frame.setSize(400, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            
            JLabel label = new JLabel("Hello! If you can see this, Swing is working!", JLabel.CENTER);
            frame.add(label);
            
            frame.setVisible(true);
            System.out.println("Window should be visible now!");
        });
    }
}