
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

//TODO: implements KeyListener in another file (organization)
public class Game extends JPanel implements ActionListener, KeyListener {

    private final String[] tileMap = {
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
    private Set<GameObject> walls;
    private Set<GameObject> foods;
    private Set<GameObject> ghosts;
    private GameObject pacman;

    private Timer gameLoop;

    Game() {
        setPreferredSize(new Dimension(Constants.boardWidth, Constants.boardHeight));
        setBackground(Color.black);

        addKeyListener(this);
        setFocusable(true);

        LoadImages();
        LoadMap();

        gameLoop = new Timer(50, this);
        gameLoop.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        setupGameGraphics(g2d);
        draw(g2d);
    }

    private void draw(Graphics2D g2d) {
        g2d.drawImage(pacman.sprite, pacman.posX, pacman.posY, pacman.width, pacman.height, null);
        drawGameObjects(g2d, walls);
        drawGameObjects(g2d, ghosts);
        drawFoods(g2d);
    }

    private void move() {
        pacman.posX += pacman.velocityX;
        pacman.posY += pacman.velocityY;
    }

    private void LoadMap() {
        walls = new HashSet<>();
        foods = new HashSet<>();
        ghosts = new HashSet<>(4);

        // Just to debug the tilemap
        StringBuilder debug = new StringBuilder();
        for (int row = 0; row < Constants.rows; row++) {
            for (int column = 0; column < Constants.columns; column++) {
                char tile = tileMap[row].charAt(column);
                int x = column * Constants.tileSize;
                int y = row * Constants.tileSize;

                createGameObject(tile, x, y);
                debug.append(tile);
            }
            debug.append('\n');
        }
        System.err.println(debug);
    }

    private void createGameObject(char tile, int x, int y) {
        Image sprite = spriteMap.get(tile);

        if (sprite == null) {
            if (tile != 'O') {
                foods.add(new GameObject(null, x + 14, y + 14, 4, 4, x, y));
            }
            return;
        }

        GameObject obj = new GameObject(sprite, x, y, Constants.tileSize, Constants.tileSize, x, y);
        switch (tile) {
            case 'X' ->
                walls.add(obj);
            case 'P' ->
                pacman = obj;
            case 'r', 'b', 'o', 'p' ->
                ghosts.add(obj);
        }
    }

    private void drawFoods(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        for (GameObject food : foods) {
            g2d.fillRect(food.posX, food.posY, food.width, food.height);
        }
        g2d.dispose();
    }

    private void drawGameObjects(Graphics2D g, Set<GameObject> objects) {
        for (GameObject obj : objects) {
            g.drawImage(obj.sprite, obj.posX, obj.posY,
                    obj.width, obj.height, null);
        }
    }

    private void LoadImages() {
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

    private void setupGameGraphics(Graphics2D g2d) {
        // Configuração ideal para jogos arcade
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.err.println("KeyEvent: " + e.getKeyCode());

        final int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP ->
                pacman.updateMovement('U');
            case KeyEvent.VK_DOWN ->
                pacman.updateMovement('D');
            case KeyEvent.VK_LEFT ->
                pacman.updateMovement('L');
            case KeyEvent.VK_RIGHT ->
                pacman.updateMovement('R');
        }
    }
}
