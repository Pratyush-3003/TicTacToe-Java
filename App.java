import javax.swing.*;

public class App {
    public static void main(String[] args) {
        System.out.println("Starting TicTacToe application...");
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run(){
                try {
                    System.out.println("Creating TicTacToeGui...");
                    TicTacToeGui game = new TicTacToeGui();
                    System.out.println("Setting visible...");
                    game.setVisible(true);
                    System.out.println("TicTacToe window should be visible now!");
                } catch (Exception e) {
                    System.err.println("Error creating TicTacToe: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        });
    }
}`