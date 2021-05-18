package Frame;

import controller.GameController;
import minesweeper.MainFrame;
import minesweeper.ScoreBoard;
import minesweeper.ThirdFrame;

import javax.swing.*;
import java.awt.*;

import static entity.Player.getPlayerList;

public class EndFrame extends JFrame {
    public EndFrame(){

        this.setTitle("Game Over");
        this.setSize(692, 300);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(3);
        this.setBackground(Color.WHITE);

        this.setLayout(null);

        JButton RESUME = new JButton("RESUME");
        JButton EXIT = new JButton("EXIT");
        RESUME.setBounds(180, 190, 100, 30);
        EXIT.setBounds(400, 190, 100, 30);

        //show the winner
        JLabel winner = new JLabel();
        winner.setBounds(0,0,300,300);
        switch (GameController.getWinner()){
            case 0:
                winner.setText(String.format("<html><body>%s's Total Score Is %d With %d Mistakes<br>" +
                                "%s's Total Score Is %d With %d Mistakes<br>GAME ENDED IN DRAW !</body></html>",
                        ScoreBoard.getP1().getUserName(), ScoreBoard.getP1().getScore(), ScoreBoard.getP1().getMistake(),
                        ScoreBoard.getP2().getUserName(), ScoreBoard.getP2().getScore(), ScoreBoard.getP2().getMistake()));
            case 1:
                winner.setText(String.format("<html><body>%s's Total Score Is %d With %d Mistakes<br>" +
                                "%s's Total Score Is %d With %d Mistakes<br>THE WINNER IS %s !</body></html>",
                        ScoreBoard.getP1().getUserName(), ScoreBoard.getP1().getScore(), ScoreBoard.getP1().getMistake(),
                        ScoreBoard.getP2().getUserName(), ScoreBoard.getP2().getScore(), ScoreBoard.getP2().getMistake(),
                        ScoreBoard.getP1().getUserName()));
            case 2:
                winner.setText(String.format("<html><body>%s's Total Score Is %d With %d Mistakes<br>" +
                                "%s's Total Score Is %d With %d Mistakes<br>THE WINNER IS %s !</body></html>",
                        ScoreBoard.getP1().getUserName(), ScoreBoard.getP1().getScore(), ScoreBoard.getP1().getMistake(),
                        ScoreBoard.getP2().getUserName(), ScoreBoard.getP2().getScore(), ScoreBoard.getP2().getMistake(),
                        ScoreBoard.getP2().getUserName()));
        }

        //background picture
        JLabel background = new JLabel(new ImageIcon("D:\\JAVA File\\Game file\\Integrated1\\end.jpg"));
        background.setSize(692, 250);

        add(EXIT);
        add(RESUME);
        add(winner);
        add(background);

        RESUME.addActionListener(e -> {
            ThirdFrame.getMainFrame().dispose();
            dispose();
            String a = getPlayerList().get(0).getUserName();
            String b = getPlayerList().get(1).getUserName();
            int c = GameController.getTurnNum();
            MainFrame mainFrame = new MainFrame(MainFrame.getxCount(), MainFrame.getyCount(), MainFrame.getMineCount());
            getPlayerList().get(0).setUserName(a);
            getPlayerList().get(1).setUserName(b);
            MainFrame.controller.getScoreBoard().update();
            MainFrame.controller.setTurnNum(c);
            mainFrame.setVisible(true);
        });

        EXIT.addActionListener(e -> {
            System.out.println("CLOSE PROGRAMME");
            dispose();
            System.exit(-1);
        });
    }
}
