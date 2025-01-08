
import java.awt.*;
import javax.swing.*;

public class Game extends JPanel {

    Dimension dimensions = new Dimension(Constants.boardWidth, Constants.boardHeight);

    private Image wallImage;
    private Image cherryImage;
    private Image foodImage;
    private Image blueGhostImage;
    private Image orangeGhostImage;
    private Image pinkGhostImage;
    private Image redGhostImage;

    private Image pacmanUpImage;
    private Image pacmanDownImage;
    private Image pacmanLeftImage;
    private Image pacmanRightImage;

    private String[] tileMap = {
        "XXXXXXXXXXXXXXXXXXX",
        "X        X        X",
        "X XX XXX X XXX XX X",
        "X                 X",
        "X XX X XXXXX X XX X",
        "X    X       X    X",
        "XXXX XXXX XXXX XXXX",
        "OOOX X       X XOOO",
        "XXXX X XXrXX X XXXX",
        "O       bpo       O",
        "XXXX X XXXXX X XXXX",
        "OOOX X       X XOOO",
        "XXXX X XXXXX X XXXX",
        "X        X        X",
        "X XX XXX X XXX XX X",
        "X  X     P     X  X",
        "XX X X XXXXX X X XX",
        "X    X   X   X    X",
        "X XXXXXX X XXXXXX X",
        "X                 X",
        "XXXXXXXXXXXXXXXXXXX"
    };

    Game() {
        setPreferredSize(dimensions);
        setBackground(Color.black);
        LoadImages();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // draw(g);
        for (int row = 0; row < Constants.rows; row++) {
            System.out.println(" ");
            System.out.println("Mapa: ");
            for (int column = 0; column < Constants.columns; column++) {
                char tile = tileMap[row].charAt(column);
                int x = column * Constants.tileSize;
                int y = row * Constants.tileSize;
                if (tile == 'X') {
                    g.drawImage(wallImage, x, y, Constants.tileSize, Constants.tileSize, null);
                }
                if (tile == 'P') {
                    g.drawImage(pacmanRightImage, x, y, Constants.tileSize, Constants.tileSize, null);
                }
                if (tile == ' ') {
                    g.drawImage(foodImage,  x + 14, y + 14, 4, 4, null);
                }
                if (tile == 'r') {
                    g.drawImage(redGhostImage, x, y, Constants.tileSize, Constants.tileSize, null);
                }
                if (tile == 'b') {
                    g.drawImage(blueGhostImage, x, y, Constants.tileSize, Constants.tileSize, null);
                }
                if (tile == 'o') {
                    g.drawImage(orangeGhostImage, x, y, Constants.tileSize, Constants.tileSize, null);
                }
                if (tile == 'p') {
                    g.drawImage(pinkGhostImage, x, y, Constants.tileSize, Constants.tileSize, null);
                }

                System.out.print(tile);
            }
        }
    }

    final void LoadImages() {
        wallImage = new ImageIcon(getClass().getResource("resources/wall.png")).getImage();
        blueGhostImage = new ImageIcon(getClass().getResource("resources/blueGhost.png")).getImage();
        orangeGhostImage = new ImageIcon(getClass().getResource("resources/orangeGhost.png")).getImage();
        pinkGhostImage = new ImageIcon(getClass().getResource("resources/pinkGhost.png")).getImage();
        redGhostImage = new ImageIcon(getClass().getResource("resources/redGhost.png")).getImage();

        pacmanUpImage = new ImageIcon(getClass().getResource("resources/pacmanUp.png")).getImage();
        pacmanDownImage = new ImageIcon(getClass().getResource("resources/pacmanDown.png")).getImage();
        pacmanLeftImage = new ImageIcon(getClass().getResource("resources/pacmanLeft.png")).getImage();
        pacmanRightImage = new ImageIcon(getClass().getResource("resources/pacmanRight.png")).getImage();
        cherryImage = new ImageIcon(getClass().getResource("resources/cherry.png")).getImage();
        foodImage = new ImageIcon(getClass().getResource("resources/powerFood.png")).getImage();

    }
}
