import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartScreen extends JPanel {

    private final static int Width = ConstValues.WINDOW_WIDTH.value;
    private final static int Height = ConstValues.WINDOW_HEIGHT.value +
            (int) (ConstValues.WINDOW_HEIGHT.value * 0.05);

    private JLabel titleLabel;
    private JLabel infoLabel;

    GridBagLayout layout;
    GridBagConstraints gridConstraints;
    private Insets separation;

    public StartScreen() {

        this.setBackground(Color.black);
        this.setPreferredSize(new Dimension(Width, Height));

        layout = new GridBagLayout();
        this.setLayout(layout);
        gridConstraints = new GridBagConstraints();
        separation = new Insets(0, 0, 40, 0);

        this.titleLabel = new JLabel("Snake");
        this.titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        this.titleLabel.setForeground(Color.green);
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        gridConstraints.insets = this.separation;
        layout.setConstraints(this.titleLabel, gridConstraints);
        this.add(this.titleLabel);

        this.infoLabel = new JLabel("Press Enter to Start");
        this.infoLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.infoLabel.setForeground(Color.white);
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        layout.setConstraints(this.infoLabel, gridConstraints);
        this.add(this.infoLabel);

    }

}