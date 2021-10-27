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
    JButton buttonClicked ;
    JLabel winMessage = new JLabel() ;
    JButton newGameButton = new JButton("New Game");
    JButton solutionButton = new JButton("Solution") ;

    public PuzzleGame() {
        gamePanel.setLayout(new GridLayout(size,size)) ;

        for (int i = 0 ; i < ((size * size) - 1) ; i++) {
            JButton button = new JButton(Integer.toString(i+1)) ;
            button.addActionListener(this) ;
            gamePanel.add(button) ;
            buttonsList.add(button) ;
        }
        emptyButton.setVisible(false);
        buttonsList.add(emptyButton) ;
        gamePanel.add(emptyButton) ;

        shuffleButtons() ;
        layoutButtons() ;


        solutionButton.addActionListener(e -> {
            if (e.getSource() == solutionButton) {
                buttonsList.clear();
                getSolution();
            }
        });


        gamePanel.setBackground(Color.lightGray);
        topPanel.add(solutionButton) ;
        topPanel.add(winMessage) ;
        topPanel.add(newGameButton) ;

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
            int emptyIndex = 0 ;
            for (int i = 0; i < buttonsList.size(); i++) {
                if (buttonsList.get(i).getText().equals("")) {
                    emptyIndex = i ;
                    break ;
                }
            } if(emptyIndex < buttonsList.size() - 1) {
                Collections.swap(buttonsList, emptyIndex, buttonsList.size() - 1);
            }
        }
    }

    public void layoutButtons() {
        gamePanel.removeAll();
        for (JButton button : buttonsList) {
            gamePanel.add(button);
        }
        gamePanel.revalidate();
        gamePanel.repaint();
    }

    public void getSolution() {
        buttonsList.clear();
        gamePanel.removeAll() ;
        for (int i = 0 ; i < ((size * size) - 1) ; i++) {
            JButton button = new JButton(Integer.toString(i+1)) ;
            button.addActionListener(this) ;
            gamePanel.add(button) ;
            buttonsList.add(button) ;
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