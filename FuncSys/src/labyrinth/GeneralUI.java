package labyrinth;

import labyrinth.game.GameState;
import labyrinth.game.LabyrinthGame;
import labyrinth.game.LabyrinthUI;
import labyrinth.level.LabyrinthMap;
import labyrinth.level.MapLibrary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 23.08.13
 * Time: 18:33
 * To change this template use File | Settings | File Templates.
 */
public class GeneralUI {
    JFrame mainFrame;
    LabyrinthUI labUI;
    LabyrinthGame game;
    JPanel buttonPanel;
    JPanel infoPanel;
    JPanel bottomPanel;

    JButton turnButton;
    JButton goButton;
    JButton grabButton;
    JButton fsButton;
    JButton fs100Button;
    JButton resetButton;

    JLabel label;

    GameState state;

    ActionListener buttonListener;

    public GeneralUI(){
        mainFrame = new JFrame("FS");

        LabyrinthMap map = MapLibrary.mediumMapWithoutLava();
        state = new GameState(map);
        game = new LabyrinthGame(map, state);
        labUI = new LabyrinthUI(state, map);

        buttonPanel = new JPanel();
        this.initButtons();

        label = new JLabel(state.getStringPredicates());

        infoPanel = new JPanel();
        infoPanel.add(label, BorderLayout.CENTER);

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.PAGE_AXIS));
        bottomPanel.add(infoPanel);
        bottomPanel.add(buttonPanel);

        mainFrame.add(labUI, BorderLayout.CENTER);
        mainFrame.add(bottomPanel, BorderLayout.SOUTH);

        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);



        //System.out.println(state.getPlayerXinPixels());
        this.labUI.start();
        //game.start();
    }

    public void initButtons()
    {
        buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HandleButtons(e);
            }
        };

        turnButton = new JButton("Turn");
        goButton = new JButton("Go");
        grabButton = new JButton("Grab");
        fsButton = new JButton("FS step");
        fs100Button = new JButton("FS 100 steps");
        resetButton = new JButton("Reset");

        turnButton.addActionListener(buttonListener);
        goButton.addActionListener(buttonListener);
        grabButton.addActionListener(buttonListener);
        fsButton.addActionListener(buttonListener);
        fs100Button.addActionListener(buttonListener);
        resetButton.addActionListener(buttonListener);

        this.buttonPanel.add(turnButton, BorderLayout.CENTER);
        this.buttonPanel.add(goButton, BorderLayout.CENTER);
        this.buttonPanel.add(grabButton, BorderLayout.CENTER);
        this.buttonPanel.add(fsButton , BorderLayout.CENTER);
        this.buttonPanel.add(fs100Button , BorderLayout.CENTER);
        this.buttonPanel.add(resetButton , BorderLayout.CENTER);
    }

    public void HandleButtons(ActionEvent e)
    {

        if (e.getSource() == this.turnButton)
        {
            this.game.turnLeft();
            game.tick();
        }
        if (e.getSource() == this.grabButton)
        {
            this.game.grabGold();
            game.tick();
        }
        if (e.getSource() == this.goButton)
        {
            this.game.stepForward();
            game.tick();
        }
        if (e.getSource() == this.fsButton)
        {
            this.game.fsTick(1);
        }
        if (e.getSource() == this.fs100Button)
        {
            this.game.fsTick(100);
        }
        if (e.getSource() == this.resetButton)
        {
            this.game.resetGame();
        }
        label.setText(state.getStringPredicates());
    }
}
