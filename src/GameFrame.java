import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class GameFrame extends JFrame implements ActionListener{

    Menu menu;
    Board gameBoard;

    public GameFrame(){

        this.menu = new Menu();
        add(this.menu);
        
        this.menu.exitButton.addActionListener(this);


        setResizable(false);
        pack();
        
        setTitle("Snake");
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menu.exitButton) {
            setVisible(false);
            dispose();
            System.exit(0);
            
        }
    }




}