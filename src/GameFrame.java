import javax.swing.JFrame;

public class GameFrame extends JFrame{

    Board gameBoard;

    public GameFrame() {

       this.gameBoard = new Board(); 
       this.add(this.gameBoard);


        this.setResizable(false);
        this.pack();

        this.setTitle("Snake");
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}