import javax.swing.JFrame;

public class App {
    public static void main(String[] args) throws Exception {        
        JFrame jframe = new JFrame("PacMan");
        jframe.setSize(Constants.boardWidth, Constants.boardHeight);
        jframe.setLocationRelativeTo(null);
        jframe.setResizable(false);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Game game = new Game();
        jframe.add(game);
        jframe.pack();
        jframe.setVisible(true);
    }
}
