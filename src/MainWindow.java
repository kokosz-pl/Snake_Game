import java.awt.EventQueue;
import javax.swing.JFrame;

public class MainWindow extends JFrame {

    public MainWindow() {
        add(new Board());
               
        setResizable(false);
        pack();
        
        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            JFrame window = new MainWindow();
            window.setVisible(true);
        });

    }
}