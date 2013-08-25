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

    JButton turnButton;
    JButton goButton;
    JButton grabButton;
    JButton fsButton;
    JButton resetButton;

    ActionListener buttonListener;

    public GeneralUI(){
        mainFrame = new JFrame("FS");
        GameState state = new GameState();
        LabyrinthMap map = MapLibrary.simpleMap1();
        game = new LabyrinthGame(map, state);
        labUI = new LabyrinthUI(state, map);

        buttonPanel = new JPanel();
        this.initButtons();

        infoPanel = new JPanel();
        buttonPanel.add(new JLabel("test"), BorderLayout.NORTH);
        //.add(infoPanel, BorderLayout.NORTH);

        mainFrame.add(labUI, BorderLayout.CENTER);
        mainFrame.add(buttonPanel, BorderLayout.SOUTH);

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
        resetButton = new JButton("Reset");

        turnButton.addActionListener(buttonListener);
        goButton.addActionListener(buttonListener);
        grabButton.addActionListener(buttonListener);
        fsButton.addActionListener(buttonListener);
        resetButton.addActionListener(buttonListener);

        this.buttonPanel.add(turnButton, BorderLayout.CENTER);
        this.buttonPanel.add(goButton, BorderLayout.CENTER);
        this.buttonPanel.add(grabButton, BorderLayout.CENTER);
        this.buttonPanel.add(fsButton , BorderLayout.CENTER);
        this.buttonPanel.add(resetButton , BorderLayout.CENTER);
    }

    public void HandleButtons(ActionEvent e)
    {
        if (e.getSource() == this.turnButton)
        {
            this.game.turnLeft();
        }
        if (e.getSource() == this.grabButton)
        {
            this.game.grabGold();
        }
        if (e.getSource() == this.goButton)
        {
            this.game.stepForward();
        }
        if (e.getSource() == this.fsButton)
        {
            this.game.fsTick();
        }
        if (e.getSource() == this.resetButton)
        {
            this.game.resetGame();
        }

    }
}
