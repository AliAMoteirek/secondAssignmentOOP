package puzzleGame;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Collections;

public class PuzzleGame extends JFrame {
    JPanel gamePanel = new JPanel() ;
    JPanel topPanel = new JPanel() ;
    JButton[] button = new JButton[16] ;
    JButton newGameButton = new JButton("New Game");

    public PuzzleGame() {
        gamePanel.setLayout(new GridLayout(4,4)) ;

        for (int i = 0 ; i < button.length ; i++) {
            button[i] = new JButton();
            if (i != button.length - 1) {
                button[i].setText(Integer.toString(i + 1));
            }
            gamePanel.add(button[i]) ;
        }

        shuffleButtons() ;

        newGameButton.addActionListener(e -> {
            if (e.getSource() == newGameButton) {
                shuffleButtons();
            }
        });

        topPanel.add(newGameButton) ;
        add(topPanel, "North") ;
        add(gamePanel, "Center") ;

        setSize(420,420) ;
        setVisible(true) ;
        setLocationRelativeTo(null) ;
        setDefaultCloseOperation(EXIT_ON_CLOSE) ;
    }

    public void shuffleButtons() {
        if (button != null) {
            Collections.shuffle(Arrays.asList(button));
            layoutButtons();
        }
    }

    public void layoutButtons() {
        gamePanel.removeAll();
        for (JButton button : button) {
            gamePanel.add(button);
        }
        gamePanel.revalidate();
        gamePanel.repaint();
    }

    public static void main(String[] args) {
        new PuzzleGame() ;
    }
}
