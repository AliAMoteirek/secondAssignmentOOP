package puzzleGame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PuzzleGame extends JFrame  {
    final int size = 4 ;
    JPanel gamePanel = new JPanel() ;
    JPanel topPanel = new JPanel() ;
    ArrayList<Button> buttonsList = new ArrayList<>() ;
    Button emptyButton = new Button() ;
    JLabel winMessage = new JLabel() ;
    Button buttonClicked ;
    JButton newGameButton = new JButton("New Game") ;
    JButton solutionButton = new JButton("Solution") ;
    Logic l = new Logic() ;

    public PuzzleGame() {
        gamePanel.setLayout(new GridLayout(size,size)) ;
        getSolution() ;
        while(true) {
            l.shuffleButtons(buttonsList);
            if (l.isGameSolvable(buttonsList)) {
                l.layoutButtons(gamePanel,buttonsList);
                break ;
            }
        }

        newGameButton.addActionListener(e -> {
            if (e.getSource() == newGameButton) {
                while(true) {
                    l.shuffleButtons(buttonsList) ;
                    if (l.isGameSolvable(buttonsList)) {
                        l.layoutButtons(gamePanel,buttonsList);
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
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE) ;
    }

    public void getSolution() {
        buttonsList.clear();
        gamePanel.removeAll() ;
        for (int i = 0 ; i < ((size * size) - 1) ; i++) {
            Button button = new Button() ;
            button.setText(Integer.toString(i+1));
            button.addActionListener(e -> {
                buttonClicked = (Button) e.getSource();
                l.swapButtons(buttonsList,gamePanel,buttonClicked,emptyButton);

                if(l.isSolved(buttonsList)) {
                    for (Button jButton : buttonsList) {
                        jButton.setBackground(new Color(144, 238, 144));
                        jButton.setEnabled(false);
                    }
                    winMessage.setText("You won!") ;
                    JOptionPane.showMessageDialog(null, "Congratulations! You won!");
                }
            }) ;
            gamePanel.add(button) ;
            buttonsList.add(button) ;
        }
        emptyButton.setVisible(false);
        buttonsList.add(emptyButton) ;
        gamePanel.add(emptyButton) ;
        gamePanel.revalidate();
        gamePanel.repaint();
    }

    public static void main(String[] args) {
        new PuzzleGame() ;
    }
}