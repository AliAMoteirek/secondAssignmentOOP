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
    ArrayList<Button> buttonsList = new ArrayList<>() ;
    Button emptyButton = new Button() ;
    JLabel winMessage = new JLabel() ;
    Button buttonClicked ;
    JButton newGameButton = new JButton("New Game") ;
    JButton solutionButton = new JButton("Solution") ;

    public PuzzleGame() {
        gamePanel.setLayout(new GridLayout(size,size)) ;

        for (int i = 0 ; i < ((size * size) - 1) ; i++) {
            Button button = new Button() ;
            button.setText(Integer.toString(i+1));
            button.addActionListener(this) ;
            gamePanel.add(button) ;
            buttonsList.add(button) ;
        }
        emptyButton.setVisible(false);
        buttonsList.add(emptyButton) ;
        gamePanel.add(emptyButton) ;

        while(true) {
            shuffleButtons() ;
            if (isGameSolvable()) {
                layoutButtons();
                break ;
            }
        }

        newGameButton.addActionListener(e -> {
            if (e.getSource() == newGameButton) {
                while(true) {
                    shuffleButtons() ;
                    if (isGameSolvable()) {
                        layoutButtons();
                        break ;
                    }
                }
                for (Button jButton : buttonsList) {
                    jButton.setBackground(new Color(103, 51, 150)) ;
                    jButton.setEnabled(true);
                }
                winMessage.setText("") ;
            }
        });

        solutionButton.addActionListener(e -> {
            if (e.getSource() == solutionButton) {
                getSolution();
            }
        });

        winMessage.setPreferredSize(new Dimension(220,90));
        winMessage.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 40));
        winMessage.setHorizontalAlignment(JLabel.CENTER);

        newGameButton.setPreferredSize(new Dimension (220,90));
        newGameButton.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,30));
        newGameButton.setFocusable(false);

        solutionButton.setPreferredSize(new Dimension (220,90));
        solutionButton.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,30));
        solutionButton.setFocusable(false);

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

    // https://ssaurel.medium.com/developing-a-15-puzzle-game-of-fifteen-in-java-dfe1359cc6e3
    /*
        a pair of tiles (a, b) form an inversion if a appears before b but a > b.
        For above example, consider the tiles written out in a row, like this:
        2 1 3 4 5 6 7 8 9 10 11 12 13 14 15 X
        The above grid forms only 1 inversion i.e. (2, 1).
     */
    private boolean isGameSolvable() {
        int countInversions = 0;
        for (int i = 0; i < buttonsList.size() - 1 ; i++) {
            for (int j = 0; j < i; j++) {
                if (Integer.parseInt(buttonsList.get(j).getText()) > Integer.parseInt(buttonsList.get(i).getText()))
                    countInversions++;
            }
        }
        return countInversions % 2 == 0;
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
            Button button = new Button() ;
            button.setText(Integer.toString(i+1));
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

    private boolean isSolved() {
        boolean condition = true;
        for (int i = 0 ; i < buttonsList.size() - 1 ; i++) {
            if (!buttonsList.get(i).getText().equals(Integer.toString(i + 1))) {
                condition = false  ;
            }
        }
        if(condition) {
            winMessage.setText("You won!") ;
        }
        return condition ;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        buttonClicked = (Button) e.getSource();
        int btnIndex = buttonsList.indexOf(buttonClicked) ;
        int emptyIndex = buttonsList.indexOf(emptyButton) ;
        if (btnIndex - 1 == emptyIndex || btnIndex + 1 == emptyIndex
                || btnIndex - 4 == emptyIndex || btnIndex + 4 == emptyIndex) {
            Collections.swap(buttonsList,btnIndex,emptyIndex);
            layoutButtons();
        }

        if(isSolved()) {
            for (Button jButton : buttonsList) {
                jButton.setBackground(new Color(144, 238, 144));
                jButton.setEnabled(false);
            }
            JOptionPane.showMessageDialog(null, "Congratulations! You won!");
            winMessage.setText("You won!") ;
        }
    }


    public static void main(String[] args) {
        new PuzzleGame() ;
    }
}