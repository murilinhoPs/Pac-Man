
import java.awt.Image;
import java.util.Set;

public class GameObject {

    private CollisionListener collisionListener;

    Image sprite;
    int posX, posY, width, height;
    int startX, startY;
    char direction;
    int velocityX, velocityY = 0;

    public GameObject(Image sprite, int posX, int posY, int width, int height, int startX, int startY) {
        this.sprite = sprite;
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.startX = this.posX;
        this.startY = this.posY;
    }

    public void setCollisionListener(CollisionListener listener) {
        this.collisionListener = listener;
    }

    public void updateMovement(char direction, Set<GameObject> walls) {
        char prevDirection = this.direction; // store current dir before changing it
        this.direction = direction;
        updateVelocity();
        this.posX += velocityX;
        this.posY += velocityY;
        
        var collidingObj = checkCollisions(walls);
        if (collidingObj != null) {
            this.posX -= this.velocityX;
            this.posY -= this.velocityY;
            this.direction = prevDirection;
            if (collisionListener != null) {
                collisionListener.onCollision(this, collidingObj);
            }
            updateVelocity();
        }
    }

    private void updateVelocity() {
        switch (this.direction) {
            case 'U' -> {
                this.velocityX = 0;
                this.velocityY = -Constants.tileSize / Constants.baseVelocity;
            }
            case 'D' -> {
                this.velocityX = 0;
                this.velocityY = Constants.tileSize / Constants.baseVelocity;
            }
            case 'L' -> {
                this.velocityX = -Constants.tileSize / Constants.baseVelocity;
                this.velocityY = 0;
            }
            case 'R' -> {
                this.velocityX = Constants.tileSize / Constants.baseVelocity;
                this.velocityY = 0;
            }
        }
    }

    private GameObject checkCollisions(Set<GameObject> walls) {
        for (GameObject wall : walls) {
            if (Utils.hasCollision(this, wall)) {
                return wall;
            }
        }
        return null;
    }
}
