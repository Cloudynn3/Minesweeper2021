package minesweeper;

import components.GridComponent;
import controller.GameController;

import javax.swing.*;
import java.awt.*;

public class GameInfo extends JPanel {
    private static JLabel leftLabel;
    private static JLabel rightLabel;
    private static JLabel mineNumbers = new JLabel();
    private TimePanel time;

    public GameInfo() {
        time = new TimePanel();

        init();
    }

    public GameInfo(int minute,int second,int msecond) {
        time = new TimePanel(minute,second,msecond);

        init();
    }

    private void init() {
        this.setBounds(10, 5, MainFrame.getyCount() * GridComponent.gridSize + 20, 60);
        this.setOpaque(false);

        this.setLayout(null);

        leftLabel = new JLabel(new ImageIcon("D:\\JAVA File\\Game file\\Integrated1\\time.png"));
        rightLabel = new JLabel(new ImageIcon("D:\\JAVA File\\Game file\\Integrated1\\mine.png"));
        leftLabel.setBounds(10, 5, 180, 60);
        rightLabel.setBounds((MainFrame.getyCount() * GridComponent.gridSize + 20) / 2, 5, 180, 60);

        time.setBounds(leftLabel.getWidth() * 2 / 5, 5, 100, 60);
        time.setOpaque(false);

        this.add(mineNumbers);
        this.add(time);
        this.add(leftLabel);
        this.add(rightLabel);

        updateMine();
    }

    //leftMinesNumber after every clicks
    public static void updateMine() {
        int mines = GameController.findRemainedMine();

        mineNumbers.setText(String.format("%d", mines));
        mineNumbers.setBounds((MainFrame.getyCount() * GridComponent.gridSize + 20) / 2 + rightLabel.getWidth() / 4, 5, 100, 60);
        mineNumbers.setFont(new Font("微软雅黑", Font.BOLD, 20));
        mineNumbers.setForeground(Color.orange);

    }

}
