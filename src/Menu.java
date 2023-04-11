import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu extends JPanel {

    private final static int Width = ConstValues.WINDOW_WIDTH.value;
    private final static int Height = ConstValues.WINDOW_HEIGHT.value;

    Board gameBoard;

    JButton playButton;
    JButton resetButton;
    JButton scoresButton;
    JButton exitButton;
    JLabel title;

    public Menu() {

        setBackground(Color.black);
        setFocusable(true);
        setPreferredSize(new Dimension(Width, Height));
        setLayout(new GridBagLayout());
        GridBagConstraints gridConstraints = new GridBagConstraints();
        gridConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridConstraints.fill = GridBagConstraints.BOTH;
        gridConstraints.insets = new Insets(20, 0, 0, 0);

        title = new JLabel();
        title.setText("<html><body style='font-size:20px' height='50px'> Snake Game </body></html>");
        title.setForeground(Color.green);
        this.setConstraints(gridConstraints, 0, 0, GridBagConstraints.CENTER);
        this.add(title, gridConstraints);

        playButton = new JButton();
        playButton.setText("Play");
        this.setConstraints(gridConstraints, 0, 1, GridBagConstraints.CENTER);
        this.add(playButton, gridConstraints);

        scoresButton = new JButton();
        scoresButton.setText("Scores Table");
        this.setConstraints(gridConstraints, 0, 2, GridBagConstraints.CENTER);
        this.add(scoresButton, gridConstraints);

        exitButton = new JButton();
        this.setConstraints(gridConstraints, 0, 3, GridBagConstraints.CENTER);
        exitButton.setText("Exit");
        this.add(exitButton, gridConstraints);

    }

    private void setConstraints(GridBagConstraints c, int grid_x, int grid_y, int anchor) {
        c.gridx = grid_x;
        c.gridy = grid_y;
        c.anchor = anchor;
    }
}