
import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import javax.swing.*;

public class Game extends JPanel {

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

    private Map<Character, Image> spriteMap;
    private HashSet<GameObject> walls;
    private HashSet<GameObject> foods;
    private HashSet<GameObject> ghosts;
    private GameObject pacman;

    Game() {
        Dimension dimensions = new Dimension(Constants.boardWidth, Constants.boardHeight);
        setPreferredSize(dimensions);
        setBackground(Color.black);
        LoadImages();
        LoadMap();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        draw(g2d);
    }

    private void LoadMap() {
        walls = new HashSet<>();
        foods = new HashSet<>();
        ghosts = new HashSet<>(4);

        for (int row = 0; row < Constants.rows; row++) {
            for (int column = 0; column < Constants.columns; column++) {
                char tile = tileMap[row].charAt(column);
                int x = column * Constants.tileSize;
                int y = row * Constants.tileSize;
                Image sprite = spriteMap.get(tile);
                switch (tile) {
                    case 'O' -> {
                        continue;
                    }
                    case 'X' ->
                        walls.add(new GameObject(sprite, x, y, Constants.tileSize, Constants.tileSize, x, y));
                    case 'P' ->
                        pacman = new GameObject(sprite, x, y, Constants.tileSize, Constants.tileSize, x, y);
                    case 'r', 'b', 'o', 'p' ->
                        ghosts.add(new GameObject(sprite, x, y, Constants.tileSize, Constants.tileSize, x, y));
                    default ->
                        foods.add(new GameObject(null, x + 14, y + 14, 4, 4, x, y));
                }
                System.out.print(tile);
            }
        }
    }

    private void draw(Graphics2D g2d) {
        g2d.drawImage(pacman.sprite, pacman.posX, pacman.posY, pacman.width, pacman.height, null);

        for (GameObject wall : walls) {
            g2d.drawImage(wall.sprite, wall.posX, wall.posY, wall.width, wall.height, null);
        }
        for (GameObject ghost : ghosts) {
            g2d.drawImage(ghost.sprite, ghost.posX, ghost.posY, ghost.width, ghost.height, null);
        }

        g2d.setColor(Color.WHITE);
        for (GameObject food : foods) {
            g2d.fillRect(food.posX, food.posY, food.width, food.height);
        }
    }

    final void LoadImages() {
        Image wallImage = new ImageIcon(getClass().getResource("resources/wall.png")).getImage();
        Image cherryImage = new ImageIcon(getClass().getResource("resources/cherry.png")).getImage();

        Image blueGhostImage = new ImageIcon(getClass().getResource("resources/blueGhost.png")).getImage();
        Image orangeGhostImage = new ImageIcon(getClass().getResource("resources/orangeGhost.png")).getImage();
        Image pinkGhostImage = new ImageIcon(getClass().getResource("resources/pinkGhost.png")).getImage();
        Image redGhostImage = new ImageIcon(getClass().getResource("resources/redGhost.png")).getImage();

        Image pacmanUpImage = new ImageIcon(getClass().getResource("resources/pacmanUp.png")).getImage();
        Image pacmanDownImage = new ImageIcon(getClass().getResource("resources/pacmanDown.png")).getImage();
        Image pacmanLeftImage = new ImageIcon(getClass().getResource("resources/pacmanLeft.png")).getImage();
        Image pacmanRightImage = new ImageIcon(getClass().getResource("resources/pacmanRight.png")).getImage();

        // Save initial sprites used on LoadingMap
        spriteMap = new HashMap<>();
        spriteMap.put('X', wallImage);
        spriteMap.put('P', pacmanRightImage);
        spriteMap.put('r', redGhostImage);
        spriteMap.put('b', blueGhostImage);
        spriteMap.put('o', orangeGhostImage);
        spriteMap.put('p', pinkGhostImage);
    }
}
