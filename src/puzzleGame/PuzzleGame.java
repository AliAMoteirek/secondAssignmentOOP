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

        topPanel.add(solutionButton) ;
        topPanel.add(winMessage) ;
        topPanel.add(newGameButton) ;

        newGameButton.addActionListener(e -> {
            if (e.getSource() == newGameButton) {
                shuffleButtons() ;
            }
        });

        solutionButton.addActionListener(e -> {
            if (e.getSource() == solutionButton) {
                buttonsList.clear();
                getSolution();
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

    public void getSolution() {
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

    private boolean isGameSolved() {
        for (int i = 0 ; i < buttonsList.size() - 1 ; i++) {
            if (!buttonsList.get(i).getText().equals(String.valueOf(i + 1))){
                return false ;
            }
        }
        return true ;
    }

    public void swapButtons () {
        Point tempLocation = buttonClicked.getLocation() ;
        buttonClicked.setLocation(emptyButton.getLocation());
        emptyButton.setLocation(tempLocation);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        buttonClicked = (JButton) e.getSource();
        double bcX = buttonClicked.getBounds().getX();
        double bcY = buttonClicked.getBounds().getY();
        double ebX = emptyButton.getBounds().getX();
        double ebY = emptyButton.getBounds().getY();

        if (!isGameSolved()) {
            if ((bcX == ebX) && (ebY == (bcY + buttonClicked.getBounds().getHeight())) ||
                    ((bcY == ebY) && (ebX == bcX + emptyButton.getBounds().getWidth()))) {
                swapButtons();

            } else if ((bcX == ebX) && (ebY == Math.abs(bcY - buttonClicked.getBounds().getHeight())) ||
                    ((bcY == ebY) && (ebX == Math.abs(bcX - emptyButton.getBounds().getWidth())))) {
                swapButtons();
            }
        } else {
            for (JButton jButton : buttonsList) {
                jButton.setBackground(new Color(144, 238, 144));
                jButton.setEnabled(false);
            }
            JOptionPane.showMessageDialog(null, "Congratulations! You won!");
            winMessage.setText("You won!");
        }
    }



    public static void main(String[] args) {
        new PuzzleGame() ;
    }
}
