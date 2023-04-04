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

    private Image head;
    private Image tail;
    private Image apple;
    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;
    private Snake snake;
    List<List<Integer>> coordinates;

    public Board() {

        addKeyListener(new DirectionListener());
        setBackground(Color.black);
        setFocusable(true);

        setPreferredSize(new Dimension(500, 500));

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

        coordinates = snake.getCoordinates();

        // System.out.println(coordinates);

        g.drawImage(apple, 250, 250, this);
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

        System.out.println(snake.getCoordinates());

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
            repaint();

        }

    }

}
