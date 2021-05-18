package minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimePanel extends JPanel {

    public static Timer timer;
    int minute = 0, second = 0, mSecond = 0;
    String time = "0" + minute + ":0" + second;
    Font font = new Font("TimesRoman", Font.BOLD, 20);

    TimePanel() {
        timer = new Timer(20, new ActionListener() {    //TODO:set the delay !

            @Override
            public void actionPerformed(ActionEvent arg0) {
                mSecond++;
                if (mSecond > 59) {
                    mSecond = 0;
                    second++;
                }
                if (second > 59) {
                    second = 0;
                    minute++;
                }
                if (mSecond < 10 && minute >= 10 && second >= 10)
                    time = minute + ":" + second;
                else if (mSecond >= 10 && minute < 10 && second >= 10)
                    time = "0" + minute + ":" + second;
                else if (mSecond >= 10 && minute >= 10 && second < 10)
                    time = +minute + ":0" + second;
                else if (mSecond < 10 && minute < 10 && second < 10)
                    time = "0" + minute + ":0" + second;
                else if (mSecond < 10 && minute < 10 && second >= 10)
                    time = "0" + minute + ":" + second;
                else if (mSecond >= 10 && minute < 10 && second < 10)
                    time = "0" + minute + ":0" + second;
                else if (mSecond < 10 && minute >= 10 && second < 10)
                    time = minute + ":0" + second;
                else
                    time = minute + ":" + second;
                repaint();
            }
        });
        timer.start();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);


        g.setFont(font);
        g.setColor(Color.orange);
        int stringWidth = g.getFontMetrics().stringWidth(time);
        int xCoordinate = getWidth() / 2 - stringWidth / 2;
        int yCoordinate = getHeight() / 2 + stringWidth / 4;
        g.drawString(time, xCoordinate, yCoordinate);
    }

    public static Timer getTimer() {
        return timer;
    }

}