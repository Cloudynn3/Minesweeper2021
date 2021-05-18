package minesweeper;

import controller.GameController;

import javax.swing.*;
import java.util.Random;

public class ThirdFrame extends JFrame {
    private static int clickTimes;
    private static String P1username;
    private static String P2username;
    private static MainFrame mainFrame;
    Random ran = new Random();
    int gameType;

    public ThirdFrame(int gameType) {
        this.gameType = gameType;

        this.setTitle("Game Setting");
        this.setSize(500, 260);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(3);

        this.setLayout(null);

        JLabel roundTimes = new JLabel();
        roundTimes.setText("<html><body>Please input a number(1~5) to <br>decide times every round:</body></html>");
        roundTimes.setBounds(50, 15, 200, 40);
        this.add(roundTimes);

        JLabel P1 = new JLabel();
        P1.setText("P1 username:");
        P1.setBounds(60, 75, 100, 40);
        this.add(P1);

        JLabel P2 = new JLabel();
        P2.setText("P2 username:");
        P2.setBounds(60, 120, 100, 50);
        this.add(P2);

        //input textField
        JTextField clickNumbers = new JTextField();
        clickNumbers.setBounds(300, 25, 150, 30);
        this.add(clickNumbers);

        JTextField P1input = new JTextField();
        P1input.setBounds(300, 80, 150, 30);
        this.add(P1input);

        JTextField P2input = new JTextField();
        P2input.setBounds(300, 130, 150, 30);
        this.add(P2input);

        JButton confirm = new JButton("Confirm");
        confirm.setBounds(330, 180, 80, 30);
        this.add(confirm);

        //listener of confirm button
        confirm.addActionListener(e -> {
            boolean correct = true;

            try {
                clickTimes = Integer.parseInt(clickNumbers.getText());
            } catch (NumberFormatException E) {
                System.out.println("INPUT ERROR!");
                JOptionPane.showMessageDialog(P1input, "YOUR INPUT \"clickTimes\" " +
                        "IS NOT LEGAL.\nPLEASE TRY AGAIN!", "Input Error", JOptionPane.WARNING_MESSAGE);
                correct = false;

                if(gameType == 1){
                    ThirdFrame thirdFrame = new ThirdFrame(1);
                    thirdFrame.setVisible(true);
                }else if(gameType == 2){
                    ThirdFrame thirdFrame = new ThirdFrame(2);
                    thirdFrame.setVisible(true);
                }
            }

            if (P1input.getText().equals("")) {
                System.out.println("User didn't input P1 username.");
                P1username = "User#" + (ran.nextInt(9000) + 1000);
            } else {
                P1username = P1input.getText();
            }

            if (P2input.getText().equals("")) {
                System.out.println("User didn't input P2 username.");
                P2username = "User#" + (ran.nextInt(9000) + 1000);
            } else {
                P2username = P2input.getText();
            }

            System.out.printf("Setting: Every player clicks %d times per round!\n", clickTimes);
            System.out.println("Player 1 : " + P1username);
            System.out.println("Player 2 : " + P2username);
            dispose();

            //Open Game Main Frame

            if(correct){
                if(gameType == 1) {
                    mainFrame = new MainFrame(GameController.getDifficult(SecondFrame.getGameLevel()));
                    mainFrame.setVisible(true);
                }else if(gameType == 2){
                    mainFrame = new MainFrame();
                    mainFrame.setVisible(true);
                }
            }
        });
    }

    public static int getTurnTimes() {
        return clickTimes;
    }

    public static String getP1username() {
        return P1username;
    }

    public static String getP2username() {
        return P2username;
    }

    public static MainFrame getMainFrame() {
        return mainFrame;
    }

}
