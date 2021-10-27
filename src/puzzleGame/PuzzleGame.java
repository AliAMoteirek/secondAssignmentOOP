package puzzleGame;

import javax.swing.*;
import java.awt.*;

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


        topPanel.add(newGameButton) ;
        add(topPanel, "North") ;
        add(gamePanel, "Center") ;

        setSize(420,420) ;
        setVisible(true) ;
        setLocationRelativeTo(null) ;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE) ;
    }

    public static void main(String[] args) {
        new PuzzleGame() ;
    }
}
