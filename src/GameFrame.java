import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.KeyStroke;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameFrame extends JFrame implements ActionListener {

    private Timer timer;
    private GameBoard gameBoard;
    private ScoreBoard scoreBoard;
    private JPanel userInterface;

    Action upAction;
    Action downAction;
    Action leftAction;
    Action rightAction;
    Action restartAction;

    public GameFrame() {

        setUI();

        upAction = new UpAction();
        downAction = new DownAction();
        leftAction = new LeftAction();
        rightAction = new RightAction();
        restartAction = new RestartAction();

        this.gameBoard.getInputMap().put(KeyStroke.getKeyStroke("UP"), "upAction");
        this.gameBoard.getActionMap().put("upAction", upAction);

        this.gameBoard.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "downAction");
        this.gameBoard.getActionMap().put("downAction", downAction);

        this.gameBoard.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "leftAction");
        this.gameBoard.getActionMap().put("leftAction", leftAction);

        this.gameBoard.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "rightAction");
        this.gameBoard.getActionMap().put("rightAction", rightAction);

        this.gameBoard.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "restartAction");
        this.gameBoard.getActionMap().put("restartAction", restartAction);

        this.setResizable(false);
        this.pack();

        this.setTitle("Snake");
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void setUI() {

        this.userInterface = new JPanel();

        GridBagLayout layout = new GridBagLayout();
        this.userInterface.setLayout(layout);
        GridBagConstraints gridConstraints = new GridBagConstraints();

        this.scoreBoard = new ScoreBoard();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        layout.setConstraints(this.scoreBoard, gridConstraints);
        this.userInterface.add(this.scoreBoard);

        this.gameBoard = new GameBoard();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        layout.setConstraints(this.gameBoard, gridConstraints);
        this.userInterface.add(this.gameBoard);

        this.add(this.userInterface);

        timer = new Timer(210, this);
        timer.start();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.gameBoard.getInGame()) {
            this.gameBoard.update();
            this.scoreBoard.updateScoreBoard(this.gameBoard.getScore());

        } else {
            timer.stop();
        }
    }

    public class UpAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (!gameBoard.getDown()) {
                gameBoard.setUp(true);
                gameBoard.setRight(false);
                gameBoard.setLeft(false);
            }
        }

    }

    public class DownAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {

            if ((!gameBoard.getUp())) {
                gameBoard.setDown(true);
                gameBoard.setRight(false);
                gameBoard.setLeft(false);
            }
        }

    }

    public class LeftAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {

            if ((!gameBoard.getRight())) {
                gameBoard.setLeft(true);
                gameBoard.setUp(false);
                gameBoard.setDown(false);
            }
        }

    }

    public class RightAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (!gameBoard.getLeft()) {
                gameBoard.setRight(true);
                gameBoard.setUp(false);
                gameBoard.setDown(false);
            }
        }

    }

    public class RestartAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            timer.start();
            gameBoard.restartGame();
            
        }

    }
}
