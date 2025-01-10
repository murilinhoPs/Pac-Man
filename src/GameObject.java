
import java.awt.Image;

public class GameObject {
    Image sprite;
    int posX, posY, width, height, startX, startY;

    public GameObject(Image sprite,  int posX, int posY,  int width, int height, int startX, int startY) {
        this.sprite = sprite;
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.startX = this.posX;
        this.startY = this.posY;
    }
}
