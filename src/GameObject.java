
import java.awt.Image;

public class GameObject {

    Image sprite;
    int posX, posY, width, height;
    int startX, startY;

    int velocityX, velocityY = 0;
    char direction;

    public GameObject(Image sprite, int posX, int posY, int width, int height, int startX, int startY) {
        this.sprite = sprite;
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.startX = this.posX;
        this.startY = this.posY;
    }

    public void updateMovement(char direction) {
        this.direction = direction;
        updateVelocity();
        this.posX += velocityX;
        this.posY += velocityY;
    }

    private void updateVelocity() {
        switch (this.direction) {
            case 'U' -> {
                this.velocityX = 0;
                this.velocityY = -Constants.tileSize / 4;
            }
            case 'D' -> {
                this.velocityX = 0;
                this.velocityY = Constants.tileSize / 4;
            }
            case 'L' -> {
                this.velocityX = -Constants.tileSize / 4;
                this.velocityY = 0;
            }
            case 'R' -> {
                this.velocityX = Constants.tileSize / 4;
                this.velocityY = 0;
            }
        }
    }
}
