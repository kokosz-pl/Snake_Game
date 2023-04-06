import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Board extends JPanel implements ActionListener {

    private final int Width = 500;
    private final int Height = 500;
    private boolean in_game;

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

    public Board() {

        addKeyListener(new DirectionListener());
        setBackground(Color.black);
        setFocusable(true);

        setPreferredSize(new Dimension(Width, Height));

        apple_x = 250;
        apple_y = 250;

        snake = new Snake();

        in_game = true;
        right = true;

        loadImages();

        timer = new Timer(210, this);
        timer.start();

    }

    private void loadImages() {

        ImageIcon icon = new ImageIcon("./Pictures/apple.png");
        apple = icon.getImage();

        icon = new ImageIcon("./Pictures/head.png");
        head = icon.getImage();

        icon = new ImageIcon("./Pictures/dot.png");
        tail = icon.getImage();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (in_game) {
            drawIcons(g);
        } else {
            endGame(g);
        }
    }

    private void drawIcons(Graphics g) {

        List<List<Integer>> coordinates = snake.getCoordinates();

        g.drawImage(apple, apple_x, apple_y, this);
        g.drawImage(head, snake.getHead_x(), snake.getHead_y(), this);

        for (int i = 1; i <= snake.getLength(); i++) {
            g.drawImage(tail, coordinates.get(i).get(0), coordinates.get(i).get(1), this);
        }

        for (int i = 1; i <= Width / 10; i++) {
            g.drawLine(0, i * 10, Width, i * 10);
        }
        for (int j = 1; j <= Height / 10; j++) {
            g.drawLine(j * 10, 0, j * 10, Height);
        }
        Toolkit.getDefaultToolkit().sync();

    }

    private void endGame(Graphics g) {

        timer.stop();

        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
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
            in_game = false;
        }
        repaint();
    }

    private void appleCollision() {

        if (snake.getHead_x() == apple_x && snake.getHead_y() == apple_y) {

            if (up) {
                snake.updateLenght(0, -10);
            } else if (down) {
                snake.updateLenght(0, 10);
            } else if (right) {
                snake.updateLenght(10, 0);
            } else if (left) {
                snake.updateLenght(-10, 0);
            }

            newApple();

        }

    }

    private void newApple() {

        boolean end = true;

        while (end) {

            int x = (int) (Math.random() * 500);
            x /= 10;

            int y = (int) (Math.random() * 500);
            y /= 10;

            if (snake.checkNewApple(x * 10, y * 10)) {
                end = false;
                this.apple_x = x * 10;
                this.apple_y = y * 10;
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
            snake.setHead_y(snake.getHead_y() - 10);
        } else if (down) {
            snake.setHead_y(snake.getHead_y() + 10);
        } else if (right) {
            snake.setHead_x(snake.getHead_x() + 10);
        } else if (left) {
            snake.setHead_x(snake.getHead_x() - 10);
        }

    }

    private class DirectionListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent evt) {

            int key = evt.getKeyCode();

            if ((key == KeyEvent.VK_UP) && (!down)) {

                up = true;
                right = false;
                left = false;
            }

            if ((key == KeyEvent.VK_DOWN) && (!up)) {
                down = true;
                right = false;
                left = false;
            }

            if ((key == KeyEvent.VK_LEFT) && (!right)) {
                left = true;
                up = false;
                down = false;
            }

            if ((key == KeyEvent.VK_RIGHT) && (!left)) {
                right = true;
                up = false;
                down = false;
            }
        }

    }

}
