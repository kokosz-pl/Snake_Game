import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScoreBoard extends JPanel{

    private final static int Width = ConstValues.WINDOW_WIDTH.value;
    private final static int Height = ConstValues.WINDOW_HEIGHT.value;
    
    private int score;
    private JLabel scoreLabel;

    ScoreBoard(){

        this.setBackground(Color.gray);
        this.setPreferredSize(new Dimension(Width, (int) (Height * 0.05)));
        this.score = 0;

        this.scoreLabel = new JLabel("Score: " + this.score, JLabel.CENTER);
        this.scoreLabel.setFont(new Font("Arial", Font.BOLD, 19));
        this.add(this.scoreLabel);
        
    }
    public void updateScoreBoard(int new_score){
        this.score = new_score;
        scoreLabel.setText("Score: " + this.score);
    }
    
}
