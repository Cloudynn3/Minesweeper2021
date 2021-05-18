package minesweeper;

import components.GridComponent;
import entity.Player;

import javax.swing.*;
import java.awt.*;

/**
 * 此类的对象是一个计分板容器，通过传入玩家对象，
 * 可以用update()方法实时更新玩家的分数以及失误数。
 */
public class ScoreBoard extends JPanel {

    static int Time = 45;
    static Player p1;
    static Player p2;
    JLabel score1 = new JLabel();
    JLabel score2 = new JLabel();
    JLabel score3 = new JLabel();

    /**
     * 通过进行游戏的玩家来初始化计分板。这里只考虑了两个玩家的情况。
     * 如果想要2-4人游戏甚至更多，请自行修改(建议把所有玩家存在ArrayList)~
     *
     * @param p1 玩家1
     * @param p2 玩家2
     */
    public ScoreBoard(Player p1, Player p2, int xCount, int yCount) {
        JLabel title = new JLabel("    ~ Score Board ~ ");
        title.setFont(new Font("LeviBrush",Font.BOLD,20));   //todo:set my own fonts.
        title.setForeground(Color.LIGHT_GRAY);
        this.add(title);
        this.setSize(yCount * GridComponent.gridSize, 80);
        this.setLocation(yCount * GridComponent.gridSize/4, xCount * GridComponent.gridSize+70);
        this.setOpaque(false);

        this.p1 = p1;
        this.p2 = p2;
        this.add(score1);
        this.add(score2);
        this.add(score3);

        this.setLayout(new BoxLayout(this, 1));
        update();
    }

    public static void setP1(Player p1) {
        ScoreBoard.p1 = p1;
    }

    public static void setP2(Player p2) {
        ScoreBoard.p2 = p2;
    }

    /**
     * 刷新计分板的数据。
     * 计分板会自动重新获取玩家的分数，并更新显示。
     */
    public void update() {
        if (!MainFrame.isIsSingle()){
            score1.setText(String.format("     %s : %d score (+ %d mistake)", p1.getUserName(), p1.getScore(), p1.getMistake()));
            score2.setText(String.format("     %s : %d score (+ %d mistake)", p2.getUserName(), p2.getScore(), p2.getMistake()));
            score3.setText(String.format("     Time left: %d ", ScoreBoard.getTime()));
            score1.setForeground(Color.LIGHT_GRAY);
            score2.setForeground(Color.LIGHT_GRAY);
            score3.setForeground(Color.LIGHT_GRAY);}
    }

    public void update(int s) {
        score1.setText(String.format("     %s : still has %d lives, lose %d bits", p1.getUserName(), p1.getLife(), p1.getMistake()));

    }

    public JLabel getScore1() {
        return score1;
    }

    public JLabel getScore2() {
        return score2;
    }

    public static Player getP1() {
        return p1;
    }

    public static Player getP2() {
        return p2;
    }

    public static void timeFlies(){
        Time--;
    }

    public static int getTime() {
        return Time;
    }

    public static void setTime(int time) {
        Time = time;
    }

    public static void newTurn(){
        setTime(45);
    }

    public static boolean isRunOutOfTime(){
        if (Time<0){
            return true;
        }
        else return false;
    }
}
