package minesweeper;

import javax.swing.*;

public class SecondFrame extends JFrame {
    private static String gameLevel;
    public SecondFrame(){

        this.setTitle("Game Setting");
        this.setSize(500, 200);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(3);

        this.setLayout(null);

        JLabel secondText = new JLabel("Please Choose The Game Level");
        JButton Easy = new JButton("Easy");
        JButton Medium = new JButton("Medium");
        JButton Hard = new JButton("Hard");
        JButton SelfDesign = new JButton("Self-Setting");

        secondText.setBounds(150, 25, 400, 50);
        Easy.setBounds(30, 90, 100, 25);
        Medium.setBounds(140, 90, 100, 25);
        Hard.setBounds(250, 90, 100, 25);
        SelfDesign.setBounds(360, 90, 100, 25);
        add(secondText);
        add(Easy);
        add(Medium);
        add(Hard);
        add(SelfDesign);

        Easy.addActionListener(e -> {
            gameLevel = "Easy";
            System.out.println("Easy Level");
            dispose();

            ThirdFrame thirdFrame = new ThirdFrame(1);
            thirdFrame.setVisible(true);
        });
        Medium.addActionListener(e -> {
            gameLevel = "Medium";
            System.out.println("Medium Level");
            dispose();

            ThirdFrame thirdFrame = new ThirdFrame(1);
            thirdFrame.setVisible(true);
        });
        Hard.addActionListener(e -> {
            gameLevel = "Hard";
            System.out.println("Hard Level");
            dispose();

            ThirdFrame thirdFrame = new ThirdFrame(1);
            thirdFrame.setVisible(true);
        });
        SelfDesign.addActionListener(e -> {
            gameLevel = "option";
            System.out.println("Option to set level");
            dispose();

            LevelFrame levelFrame = new LevelFrame();
            levelFrame.setVisible(true);
        });

    }

    public static String getGameLevel(){
        return gameLevel;
    }
}
