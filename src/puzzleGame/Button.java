package puzzleGame;
import javax.swing.* ;
import javax.swing.border.BevelBorder;
import java.awt.* ;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Button extends JButton{

    public Button() {

        setPreferredSize(new Dimension(100,100)) ;
        setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,30)) ;
        setBorder(BorderFactory.createLineBorder(
                new Color (224,215,208),5,false)) ;
        setBackground(new Color (103, 51, 150)) ;
        setForeground(Color.WHITE) ;
        setOpaque(true) ;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                JButton button = (JButton) e.getComponent();
                button.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED)) ;
            }

            @Override
            public void mouseExited(MouseEvent e) {
                JButton button = (JButton) e.getComponent();
                button.setBorder(BorderFactory.createLineBorder(
                        new Color (224,215,208),2,true)) ;
            }
        });
    }
}
