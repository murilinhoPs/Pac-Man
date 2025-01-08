import java.awt.*;
import javax.swing.*;

public class Game extends JPanel {
    Dimension dimensions = new Dimension(Constants.boardWidth, Constants.boardHeight);

    Game() {
        setPreferredSize(dimensions);
        setBackground(Color.black);
    }
}
