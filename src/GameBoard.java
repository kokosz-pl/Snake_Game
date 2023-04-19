
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Image;
import java.awt.Toolkit;

public class GameBoard extends JPanel {

    private final static int Width = ConstValues.WINDOW_WIDTH.value;
    private final static int Height = ConstValues.WINDOW_HEIGHT.value;
    private final static int BallSize = ConstValues.BALL_SIZE.value;

    private Image head;
    private Image tail;
    private Image apple;
    private Snake snake;
    private int apple_x;
    private int apple_y;
    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;
    private boolean inGame;
    private int score;

    public GameBoard() {

        this.setBackground(Color.black);
        this.setPreferredSize(new Dimension(Width, Height));

        this.apple_x = Width / 2;
        this.apple_y = Height / 2;
        this.score = 0;

        this.snake = new Snake();

        this.inGame = true;
        this.right = true;

        loadImages();

    }

    public boolean getLeft() {
        return this.left;
    }

    public void setLeft(boolean newLeft) {
        this.left = newLeft;
    }

    public boolean getRight() {
        return this.right;
    }

    public void setRight(boolean newRight) {
        this.right = newRight;
    }

    public boolean getUp() {
        return this.up;
    }

    public void setUp(boolean newUp) {
        this.up = newUp;
    }

    public boolean getDown() {
        return this.down;
    }

    public void setDown(boolean newDown) {
        this.down = newDown;
    }

    public int getScore() {
        return this.score;
    }

    public boolean getInGame() {
        return this.inGame;
    }

    private void loadImages() {

        ImageIcon icon = new ImageIcon("./Pictures/apple.png");
        this.apple = icon.getImage();

        icon = new ImageIcon("./Pictures/head.png");
        this.head = icon.getImage();

        icon = new ImageIcon("./Pictures/dot.png");
        this.tail = icon.getImage();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.inGame) {
            drawIcons(g);
        } else {
            endGame(g);
        }
    }

    private void drawIcons(Graphics g) {

        int[] coordinates_arr = new int[2];

        g.drawImage(this.apple, this.apple_x, this.apple_y, this);
        g.drawImage(this.head, snake.getHead_x(), snake.getHead_y(), this);

        for (int i = 1; i <= snake.getLength(); i++) {
            coordinates_arr = snake.getCoordinates(i);
            g.drawImage(this.tail, coordinates_arr[0], coordinates_arr[1], this);
        }

        for (int i = 1; i <= Width / BallSize; i++) {
            g.drawLine(0, i * BallSize, Width, i * BallSize);
        }
        for (int j = 1; j <= Height / BallSize; j++) {
            g.drawLine(j * BallSize, 0, j * BallSize, Height);
        }
        Toolkit.getDefaultToolkit().sync();

    }

    private void endGame(Graphics g) {

        String msg = "Game Over";
        Font small = new Font("Arial", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (Width - metr.stringWidth(msg)) / 2, (int) (Height * 0.4));

        msg = "Press Space to Restart";
        g.drawString(msg, (Width - metr.stringWidth(msg)) / 2, (int) (Height * 0.5));

        msg = "Press ESC to Quit";
        g.drawString(msg, (Width - metr.stringWidth(msg)) / 2, (int) (Height * 0.6));

    }

    private void appleCollision() {

        if (snake.getHead_x() == this.apple_x && snake.getHead_y() == this.apple_y) {

            if (up) {
                snake.updateLenght(0, -BallSize);
            } else if (down) {
                snake.updateLenght(0, BallSize);
            } else if (right) {
                snake.updateLenght(BallSize, 0);
            } else if (left) {
                snake.updateLenght(-BallSize, 0);
            }

            this.score++;
            newApple();

        }

    }

    private void newApple() {

        int[] newCoordinates = snake.newApplePosition();

        this.apple_x = newCoordinates[0];
        this.apple_y = newCoordinates[1];

    }

    private boolean wallCollision() {

        if ((snake.getHead_x() < 0 || snake.getHead_x() >= Width)
                || (snake.getHead_y() < 0 || snake.getHead_y() >= Height)) {
            return true;
        }
        return false;
    }

    private void move() {

        if (up) {
            snake.updateCoordinates(0, -BallSize);
        } else if (down) {
            snake.updateCoordinates(0, BallSize);
        } else if (right) {
            snake.updateCoordinates(BallSize, 0);
        } else if (left) {
            snake.updateCoordinates(-BallSize, 0);
        }

    }

    public void update() {
        move();
        appleCollision();
        if (wallCollision() || snake.selfCollision()) {
            this.inGame = false;
        }

        repaint();
    }

}
