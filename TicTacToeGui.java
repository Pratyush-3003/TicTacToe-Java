import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

public class TicTacToeGui extends JFrame implements ActionListener{

    private int xScore, oScore, moveCounter;
    private boolean isPlayerOne;
    private JLabel turnLabel, scoreLabel, resultLabel;
    private JButton[][] board;
    private JDialog resultDialog;

    public TicTacToeGui(){
        super("Tic Tac Toe (Java Swing)");
        
        System.out.println("Initializing TicTacToe GUI...");
        
        // Window setup
        setSize(540, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(Color.decode("#011627"));

        // Initialize variables
        board = new JButton[3][3];
        isPlayerOne = true;
        xScore = oScore = moveCounter = 0;

        createResultDialog();
        addGuiComponents();
        
        System.out.println("GUI components added successfully");
    }

    private void addGuiComponents(){
        // Turn label
        turnLabel = new JLabel("X", SwingConstants.CENTER);
        turnLabel.setFont(new Font("Dialog", Font.BOLD, 40));
        turnLabel.setOpaque(true);
        turnLabel.setBackground(Color.decode("#E71D36"));
        turnLabel.setForeground(Color.decode("#FDFFFC"));
        turnLabel.setBounds(0, 0, 540, 60);

        // Score label
        scoreLabel = new JLabel("X: 0 | O: 0", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Dialog", Font.BOLD, 24));
        scoreLabel.setForeground(Color.decode("#FDFFFC"));
        scoreLabel.setBounds(0, 80, 540, 40);

        // Game board panel
        JPanel boardPanel = new JPanel(new GridLayout(3, 3, 5, 5));
        boardPanel.setBounds(20, 140, 500, 500);
        boardPanel.setBackground(Color.decode("#011627"));

        // Create board buttons
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton button = new JButton();
                button.setFont(new Font("Dialog", Font.BOLD, 80));
                button.setBackground(Color.decode("#FDFFFC"));
                button.setForeground(Color.decode("#011627"));
                button.addActionListener(this);
                button.setFocusPainted(false);
                
                board[i][j] = button;
                boardPanel.add(button);
            }
        }

        // Reset button
        JButton resetButton = new JButton("Reset Game");
        resetButton.setFont(new Font("Dialog", Font.BOLD, 18));
        resetButton.setBackground(Color.decode("#FF9F1C"));
        resetButton.setForeground(Color.decode("#011627"));
        resetButton.setBounds(170, 660, 200, 40);
        resetButton.addActionListener(this);
        resetButton.setFocusPainted(false);

        // Add components
        add(turnLabel);
        add(scoreLabel);
        add(boardPanel);
        add(resetButton);
        
        System.out.println("All components added to frame");
    }

    private void createResultDialog(){
        resultDialog = new JDialog(this, "Game Result", true);
        resultDialog.setSize(300, 150);
        resultDialog.setLocationRelativeTo(this);
        resultDialog.setLayout(new BorderLayout());
        
        resultLabel = new JLabel("", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        
        JButton playAgainButton = new JButton("Play Again");
        playAgainButton.addActionListener(this);
        
        resultDialog.add(resultLabel, BorderLayout.CENTER);
        resultDialog.add(playAgainButton, BorderLayout.SOUTH);
        
        resultDialog.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                resetGame();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        
        if (command.equals("Reset Game")) {
            resetGame();
            xScore = oScore = 0;
            updateScoreLabel();
        } else if (command.equals("Play Again")) {
            resultDialog.setVisible(false);
            resetGame();
        } else {
            // Handle board button clicks
            JButton clickedButton = (JButton) e.getSource();
            
            if (clickedButton.getText().equals("")) {
                moveCounter++;
                
                if (isPlayerOne) {
                    clickedButton.setText("X");
                    clickedButton.setForeground(Color.decode("#E71D36"));
                    turnLabel.setText("O");
                    turnLabel.setBackground(Color.decode("#2EC4B6"));
                    
                    if (checkWin("X")) {
                        showResult("X Wins!");
                        xScore++;
                        updateScoreLabel();
                        return;
                    }
                } else {
                    clickedButton.setText("O");
                    clickedButton.setForeground(Color.decode("#2EC4B6"));
                    turnLabel.setText("X");
                    turnLabel.setBackground(Color.decode("#E71D36"));
                    
                    if (checkWin("O")) {
                        showResult("O Wins!");
                        oScore++;
                        updateScoreLabel();
                        return;
                    }
                }
                
                isPlayerOne = !isPlayerOne;
                
                if (moveCounter >= 9) {
                    showResult("It's a Draw!");
                }
            }
        }
    }

    private boolean checkWin(String player) {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0].getText().equals(player) && 
                board[i][1].getText().equals(player) && 
                board[i][2].getText().equals(player)) {
                return true;
            }
        }
        
        // Check columns
        for (int j = 0; j < 3; j++) {
            if (board[0][j].getText().equals(player) && 
                board[1][j].getText().equals(player) && 
                board[2][j].getText().equals(player)) {
                return true;
            }
        }
        
        // Check diagonals
        if (board[0][0].getText().equals(player) && 
            board[1][1].getText().equals(player) && 
            board[2][2].getText().equals(player)) {
            return true;
        }
        
        if (board[0][2].getText().equals(player) && 
            board[1][1].getText().equals(player) && 
            board[2][0].getText().equals(player)) {
            return true;
        }
        
        return false;
    }

    private void showResult(String result) {
        resultLabel.setText(result);
        resultDialog.setVisible(true);
    }

    private void updateScoreLabel() {
        scoreLabel.setText("X: " + xScore + " | O: " + oScore);
    }

    private void resetGame() {
        isPlayerOne = true;
        moveCounter = 0;
        turnLabel.setText("X");
        turnLabel.setBackground(Color.decode("#E71D36"));
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j].setText("");
            }
        }
    }
}