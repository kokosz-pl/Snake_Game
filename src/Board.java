import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

public class Board extends JPanel implements ActionListener {

    private final static int Width = ConstValues.WINDOW_WIDTH.value;
    private final static int Height = ConstValues.WINDOW_HEIGHT.value;
    private final static int BallSize = ConstValues.BALL_SIZE.value;

    private Timer timer;
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
    private boolean in_game;

    Action upAction;
    Action downAction;
    Action leftAction;
    Action rightAction;

    public Board() {

        this.setBackground(Color.black);
        this.setPreferredSize(new Dimension(Width, Height));

        upAction = new UpAction();
        downAction = new DownAction();
        leftAction = new LeftAction();
        rightAction = new RightAction();

        this.getInputMap().put(KeyStroke.getKeyStroke("UP"), "upAction");
        this.getActionMap().put("upAction", upAction);

        this.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "downAction");
        this.getActionMap().put("downAction", downAction);

        this.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "leftAction");
        this.getActionMap().put("leftAction", leftAction);

        this.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "rightAction");
        this.getActionMap().put("rightAction", rightAction);


        this.apple_x = 250;
        this.apple_y = 250;

        this.snake = new Snake();

        this.in_game = true;
        this.right = true;

        loadImages();

        this.timer = new Timer(210, this);
        this.timer.start();

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
        if (this.in_game) {
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

        timer.stop();

        String msg = "Game Over";
        Font small = new Font("Arial", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (Width - metr.stringWidth(msg)) / 2, Height / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update();
    }

    private void update() {
        move();
        appleCollision();
        if (wallCollision() || snake.selfCollision()) {
            this.in_game = false;
        }

        repaint();
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

            newApple();

        }

    }

    private void newApple() {

        boolean end = true;

        while (end) {

            int x = (int) (Math.random() * 500);
            x /= BallSize;

            int y = (int) (Math.random() * 500);
            y /= BallSize;

            if (snake.checkNewApple(x * 10, y * 10)) {
                end = false;
                this.apple_x = x * BallSize;
                this.apple_y = y * BallSize;
            }
        }

    }

    private boolean wallCollision() {

        if ((snake.getHead_x() < 0 || snake.getHead_x() >= Width)
                || (snake.getHead_y() < 0 || snake.getHead_y() >= Height)) {
            return true;
        }
        return false;
    }

    private void move() {

        snake.updateCoordinates();

        if (up) {
            snake.setHead_y(snake.getHead_y() - BallSize);
        } else if (down) {
            snake.setHead_y(snake.getHead_y() + BallSize);
        } else if (right) {
            snake.setHead_x(snake.getHead_x() + BallSize);
        } else if (left) {
            snake.setHead_x(snake.getHead_x() - BallSize);
        }

    }

    public class UpAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (!down) {
                up = true;
                right = false;
                left = false;
            }
        }

    }

    public class DownAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {

            if ((!up)) {
                down = true;
                right = false;
                left = false;
            }
        }

    }

    public class LeftAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {

            if ((!right)) {
                left = true;
                up = false;
                down = false;
            }
        }

    }

    public class RightAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (!left) {
                right = true;
                up = false;
                down = false;
            }
        }

    }
}
