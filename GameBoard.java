import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class GameBoard extends JPanel implements ActionListener, KeyListener {
    private Timer timer;
    private Paddle paddle;
    private Ball ball;
    private List<Brick> bricks;
    private boolean gameOver;
    private boolean gameWon;
    private int boardWidth = 800;
    private int boardHeight = 600;
    private int numBricksRows = 5;
    private int numBricksCols = 10;
    private int paddleSpeed = 15; // Define paddleSpeed here

    public GameBoard() {
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setBackground(Color.WHITE);
        setFocusable(true);
        addKeyListener(this);

        paddle = new Paddle(boardWidth / 2 - 50, boardHeight - 30, 100, 10, paddleSpeed);

        // Calculate the center position for the ball
        int ballDiameter = 20;
        int ballX = boardWidth / 2 - ballDiameter / 2;
        int ballY = boardHeight / 2 - ballDiameter / 2;
        ball = new Ball(ballX, ballY, ballDiameter, 3, 3);

        bricks = new ArrayList<>();
        int brickWidth = boardWidth / numBricksCols;
        int brickHeight = 30;
        for (int i = 0; i < numBricksRows; i++) {
            for (int j = 0; j < numBricksCols; j++) {
                bricks.add(new Brick(j * brickWidth, i * brickHeight, brickWidth, brickHeight));
            }
        }

        timer = new Timer(10, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver && !gameWon) {
            ball.move();
            checkCollisions();
        }
        repaint();
    }

    private void checkCollisions() {
        if (ball.getX() < 0 || ball.getX() + ball.getBounds().width > boardWidth) {
            ball.reverseX();
        }
        if (ball.getY() < 0) {
            ball.reverseY();
        }
        if (ball.getBounds().intersects(paddle.getBounds())) {
            ball.reverseY();
        }
        for (Brick brick : bricks) {
            if (!brick.isDestroyed() && ball.getBounds().intersects(brick.getBounds())) {
                ball.reverseY();
                brick.setDestroyed(true);
                break;
            }
        }
        if (ball.getY() > boardHeight) {
            gameOver = true;
        }
        gameWon = bricks.stream().allMatch(Brick::isDestroyed);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paddle.draw(g);
        ball.draw(g);
        for (Brick brick : bricks) {
            brick.draw(g);
        }
        if (gameOver) {
            g.setColor(Color.RED);
            g.drawString("Game Over", boardWidth / 2 - 30, boardHeight / 2);
        }
        if (gameWon) {
            g.setColor(Color.GREEN);
            g.drawString("You Win!", boardWidth / 2 - 30, boardHeight / 2);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            paddle.moveLeft();
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            paddle.moveRight(boardWidth);
        }

        // Example of increasing paddle speed dynamically:
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            paddle.increaseSpeed(5); // Increase speed by 5
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}
