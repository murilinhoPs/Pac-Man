
import java.awt.*;
import java.util.HashSet;
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

    private HashSet<GameObject> walls;
    private HashSet<GameObject> foods;
    private HashSet<GameObject> ghosts;
    private GameObject pacman;

    Game() {
        setPreferredSize(dimensions);
        setBackground(Color.black);
        LoadImages();
        LoadMap();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // renderElements(g);
        draw(g);
    }

    private void LoadMap() {
        walls = new HashSet<>();
        foods = new HashSet<>();
        ghosts = new HashSet<>();

        for (int row = 0; row < Constants.rows; row++) {
            for (int column = 0; column < Constants.columns; column++) {
                char tile = tileMap[row].charAt(column);
                int x = column * Constants.tileSize;
                int y = row * Constants.tileSize;
                switch (tile) {
                    case 'X' ->
                        walls.add(new GameObject(wallImage, x, y, Constants.tileSize, Constants.tileSize, x, y));
                    case 'P' ->
                        pacman = new GameObject(pacmanRightImage, x, y, Constants.tileSize, Constants.tileSize, x, y);
                    case 'r' ->
                        ghosts.add(new GameObject(redGhostImage, x, y, Constants.tileSize, Constants.tileSize, x, y));
                    case 'b' ->
                        ghosts.add(new GameObject(blueGhostImage, x, y, Constants.tileSize, Constants.tileSize, x, y));
                    case 'o' ->
                        ghosts.add(new GameObject(orangeGhostImage, x, y, Constants.tileSize, Constants.tileSize, x, y));
                    case 'p' ->
                        ghosts.add(new GameObject(pinkGhostImage, x, y, Constants.tileSize, Constants.tileSize, x, y));
                    default ->
                        foods.add(new GameObject(null, x + 14, y + 14, 4, 4, x, y));
                }
                System.out.print(tile);
            }
        }
    }

    private void draw(Graphics g) {
        g.drawImage(pacman.sprite, pacman.posX, pacman.posY, pacman.width, pacman.height, null);

        for (GameObject wall : walls) {
            g.drawImage(wall.sprite, wall.posX, wall.posY, wall.width, wall.height, null);
        }
        for (GameObject ghost : ghosts) {
            g.drawImage(ghost.sprite, ghost.posX, ghost.posY, ghost.width, ghost.height, null);
        }
        g.setColor(Color.WHITE);
        for (GameObject food : foods) {
            g.fillRect(food.posX, food.posY, food.width, food.height);
        }
    }

    final void LoadImages() {
        wallImage = new ImageIcon(getClass().getResource("resources/wall.png")).getImage();
        cherryImage = new ImageIcon(getClass().getResource("resources/cherry.png")).getImage();

        blueGhostImage = new ImageIcon(getClass().getResource("resources/blueGhost.png")).getImage();
        orangeGhostImage = new ImageIcon(getClass().getResource("resources/orangeGhost.png")).getImage();
        pinkGhostImage = new ImageIcon(getClass().getResource("resources/pinkGhost.png")).getImage();
        redGhostImage = new ImageIcon(getClass().getResource("resources/redGhost.png")).getImage();

        pacmanUpImage = new ImageIcon(getClass().getResource("resources/pacmanUp.png")).getImage();
        pacmanDownImage = new ImageIcon(getClass().getResource("resources/pacmanDown.png")).getImage();
        pacmanLeftImage = new ImageIcon(getClass().getResource("resources/pacmanLeft.png")).getImage();
        pacmanRightImage = new ImageIcon(getClass().getResource("resources/pacmanRight.png")).getImage();

    }
}
