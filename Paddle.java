import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Paddle {
    private int x, y;
    private int width, height;
    private int moveSpeed;

    public Paddle(int x, int y, int width, int height, int moveSpeed) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.moveSpeed = moveSpeed;
    }

    public void moveLeft() {
        if (x - moveSpeed > 0) {
            x -= moveSpeed;
        }
    }

    public void moveRight(int boardWidth) {
        if (x + width + moveSpeed < boardWidth) {
            x += moveSpeed;
        }
    }

    public void increaseSpeed(int increment) {
        moveSpeed += increment;
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(x, y, width, height);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
