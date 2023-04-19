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

    private GridBagLayout layout;
    private GridBagConstraints gridConstraints;

    private Action upAction;
    private Action downAction;
    private Action leftAction;
    private Action rightAction;
    private Action restartAction;
    private Action closeAction;
    private Action startAction;

    private boolean inStartScreen;

    public GameFrame() {

        this.userInterface = new StartScreen();
        this.add(this.userInterface);
        inStartScreen = true;

        upAction = new UpAction();
        downAction = new DownAction();
        leftAction = new LeftAction();
        rightAction = new RightAction();
        restartAction = new RestartAction();
        closeAction = new CloseAction();
        startAction = new StartAction();

        this.userInterface.getInputMap().put(KeyStroke.getKeyStroke("UP"), "upAction");
        this.userInterface.getActionMap().put("upAction", upAction);

        this.userInterface.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "downAction");
        this.userInterface.getActionMap().put("downAction", downAction);

        this.userInterface.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "leftAction");
        this.userInterface.getActionMap().put("leftAction", leftAction);

        this.userInterface.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "rightAction");
        this.userInterface.getActionMap().put("rightAction", rightAction);

        this.userInterface.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "restartAction");
        this.userInterface.getActionMap().put("restartAction", restartAction);

        this.userInterface.getInputMap().put(KeyStroke.getKeyStroke("ESCAPE"), "closeAction");
        this.userInterface.getActionMap().put("closeAction", closeAction);

        this.userInterface.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "startAction");
        this.userInterface.getActionMap().put("startAction", startAction);

        this.setResizable(false);
        this.pack();

        this.setTitle("Snake");
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void setUI() {

        this.userInterface = null;
        this.userInterface = new JPanel();

        layout = new GridBagLayout();
        this.userInterface.setLayout(layout);
        gridConstraints = new GridBagConstraints();

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

        this.revalidate();

        timer = new Timer(210, this);
        timer.start();

    }

    private void restartGame() {
        this.gameBoard = null;
        this.gameBoard = new GameBoard();

        this.gridConstraints.gridx = 0;
        this.gridConstraints.gridy = 1;
        this.layout.setConstraints(this.gameBoard, this.gridConstraints);
        this.userInterface.add(this.gameBoard);

        timer.start();
    }

    private void closeWindow() {
        this.setVisible(false);
        this.dispose();
        System.exit(0);
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

            if (gameBoard != null) {
                if (!gameBoard.getDown()) {
                    gameBoard.setUp(true);
                    gameBoard.setRight(false);
                    gameBoard.setLeft(false);
                }
            }
        }

    }

    public class DownAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (gameBoard != null) {
                if ((!gameBoard.getUp())) {
                    gameBoard.setDown(true);
                    gameBoard.setRight(false);
                    gameBoard.setLeft(false);
                }
            }

        }
    }

    public class LeftAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (gameBoard != null) {
                if ((!gameBoard.getRight())) {
                    gameBoard.setLeft(true);
                    gameBoard.setUp(false);
                    gameBoard.setDown(false);
                }
            }
        }

    }

    public class RightAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (gameBoard != null) {
                if (!gameBoard.getLeft()) {
                    gameBoard.setRight(true);
                    gameBoard.setUp(false);
                    gameBoard.setDown(false);
                }
            }
        }

    }

    public class RestartAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (gameBoard != null) {
                if (!gameBoard.getInGame()) {
                    restartGame();
                }
            }

        }

    }

    public class StartAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (inStartScreen) {
                inStartScreen = false;
                setUI();
            }
        }

    }

    public class CloseAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            closeWindow();
        }

    }
}
