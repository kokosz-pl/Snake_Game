import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
// import java.awt.Toolkit;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Board extends JPanel {

    private final int Width = 500;
    private final int Height = 500;

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
    List<List<Integer>> coordinates;

    public Board() {

        addKeyListener(new DirectionListener());
        setBackground(Color.black);
        setFocusable(true);

        setPreferredSize(new Dimension(Width, Height));

        apple_x = 250;
        apple_y = 250;

        snake = new Snake();

        loadImages();

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
        drawIcons(g);
    }

    private void drawIcons(Graphics g) {

        System.out.println(snake.getHead_x() + " " + snake.getHead_y());


        coordinates = snake.getCoordinates();

        // System.out.println(coordinates);

        g.drawImage(apple, apple_x, apple_y, this);
        g.drawImage(head, snake.getHead_x(), snake.getHead_y(), this);

        for (int i = 1; i <= snake.getLength(); i++) {
            g.drawImage(tail, coordinates.get(i).get(0), coordinates.get(i).get(1), this);
        }

        // Toolkit.getDefaultToolkit().sync();

    }

    // @Override
    // public void actionPerformed(ActionEvent e) {

    // System.out.println(head_x + " " + head_y);

    // move();

    // repaint();
    // }

    private void checkApple() {

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

        }

    }

    private void checkCollision() {

        System.out.println(snake.getHead_x() + " " + snake.getHead_y());

        if (snake.getHead_x() < 0 || snake.getHead_x() >= Width) {
            System.out.println("End Game");
        } else if (snake.getHead_y() < 0 || snake.getHead_y() >= Width) {
            System.out.println("End Game");
        }

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
            move();
            checkCollision();
            checkApple();
            repaint();

        }

    }

}
