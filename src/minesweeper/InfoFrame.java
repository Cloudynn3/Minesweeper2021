package minesweeper;

import Frame.SoloFrame;

import javax.swing.*;

public class InfoFrame extends JFrame {

    public InfoFrame(){
        this.setTitle("Minesweeper2021");
        this.setSize(1280, 720);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(3);

        this.setLayout(null);

        JLabel background = new JLabel(new ImageIcon("src/GamePic/Enter.jpg"));
        background.setSize(1280,720);

        //初始游戏窗口
        JButton start = new JButton("MULTI PLAYER");
        JButton solo = new JButton("SOLO CAMPAIGN");
        JButton load = new JButton("LOAD GAME");

        start.setBounds(300, 500, 150, 40);
        solo.setBounds(550, 500, 150, 40);
        load.setBounds(800, 500, 150, 40);

        add(start);
        add(solo);
        add(load);
        add(background);


        start.addActionListener(e -> {
            System.out.println("Restart Game");
            dispose();

            SecondFrame secondFrame = new SecondFrame();
            secondFrame.setVisible(true);
        });
        load.addActionListener(e -> {
            Save_Load.loadGame();
            System.out.println("Load Game");
            dispose();

        });
        solo.addActionListener(e -> {

            System.out.println("SOLO CAMPAIGN");
            dispose();

            new SoloFrame();
        });
    }
}
