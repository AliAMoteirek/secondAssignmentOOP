package puzzleGame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Logic {

    // https://ssaurel.medium.com/developing-a-15-puzzle-game-of-fifteen-in-java-dfe1359cc6e3
    /*
        a pair of tiles (a, b) form an inversion if a appears before b but a > b.
        For above example, consider the tiles written out in a row, like this:
        2 1 3 4 5 6 7 8 9 10 11 12 13 14 15 X
        The above grid forms only 1 inversion i.e. (2, 1).
     */

    public boolean isGameSolvable(ArrayList<Button> buttonsList) {
        int countInversions = 0;
        for (int i = 0; i < buttonsList.size() - 1 ; i++) {
            for (int j = 0; j < i; j++) {
                if (Integer.parseInt(buttonsList.get(j).getText()) > Integer.parseInt(buttonsList.get(i).getText()))
                    countInversions++;
            }
        }
        return countInversions % 2 == 0;
    }

    public boolean isSolved(List<Button> buttonsList) {
        boolean condition = true;
        for (int i = 0 ; i < buttonsList.size() - 1 ; i++) {
            if (!buttonsList.get(i).getText().equals(Integer.toString(i + 1))) {
                condition = false  ;
            }
        }
        return condition ;
    }

    public void swapButtons(List<Button> buttonsList, JPanel gamePanel, Button buttonClicked, Button emptyButton) {
        int btnIndex = buttonsList.indexOf(buttonClicked) ;
        int emptyIndex = buttonsList.indexOf(emptyButton) ;
        if (btnIndex - 1 == emptyIndex || btnIndex + 1 == emptyIndex
                || btnIndex - 4 == emptyIndex || btnIndex + 4 == emptyIndex) {
            Collections.swap(buttonsList,btnIndex,emptyIndex);
            layoutButtons(gamePanel, buttonsList) ;
        }
    }

    public void layoutButtons(JPanel gamePanel, List<Button> buttonsList) {
        gamePanel.removeAll();
        for (JButton button : buttonsList) {
            gamePanel.add(button);
        }
        gamePanel.revalidate();
        gamePanel.repaint();
    }

    public void shuffleButtons(ArrayList<Button> buttonsList) {
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


}
