package puzzleGame;

import java.util.ArrayList;

public class Logic {

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

}
