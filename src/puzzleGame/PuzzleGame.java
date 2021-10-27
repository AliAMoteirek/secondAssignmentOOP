package puzzleGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class PuzzleGame extends JFrame implements ActionListener {

    final int size = 4 ;
    JPanel gamePanel = new JPanel() ;
    JPanel topPanel = new JPanel() ;
    ArrayList<JButton> buttonsList = new ArrayList<>();
    JButton emptyButton = new JButton("") ;
    JButton newGameButton = new JButton("New Game");

    public PuzzleGame() {
        gamePanel.setLayout(new GridLayout(size,size)) ;

        for (int i = 0 ; i < ((size * size) - 1) ; i++) {
            JButton button = new JButton(Integer.toString(i+1)) ;
            button.addActionListener(this) ;
            button.setOpaque(true);
            gamePanel.add(button) ;
            buttonsList.add(button) ;
            emptyButton.setVisible(false);
            buttonsList.add(emptyButton) ;
            gamePanel.add(emptyButton) ;
            gamePanel.revalidate();
            gamePanel.repaint();
        }

        shuffleButtons();

        topPanel.add(newGameButton) ;

        newGameButton.addActionListener(e -> {
            if (e.getSource() == newGameButton) {
                shuffleButtons() ;
            }
        });

        add(gamePanel, "Center") ;
        add(topPanel, "North") ;
        setTitle("The Fiftheenth Puzzel");
        setSize(820,820) ;
        setVisible(true) ;
        setLocationRelativeTo(null) ;
        setDefaultCloseOperation(EXIT_ON_CLOSE) ;

    }

    public void shuffleButtons() {
        if (buttonsList != null) {
            Collections.shuffle(buttonsList);
            layoutButtons();
        }
    }

    public void layoutButtons() {
        gamePanel.removeAll();
        for (JButton button : buttonsList) {
            gamePanel.add(button);
        }
        emptyButton.setVisible(false);
        buttonsList.add(emptyButton) ;
        gamePanel.add(emptyButton) ;
        gamePanel.revalidate();
        gamePanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }



    public static void main(String[] args) {
        new PuzzleGame() ;
    }


}
