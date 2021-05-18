package controller;

import entity.GridStatus;
import entity.Player;
import minesweeper.*;

import java.util.Random;
import java.util.TimerTask;
import java.util.Timer;

public class GameController {

    private static Player onTurn;
    private Player p1;
    private Player p2;

    private Timer timer = new Timer();


    private static GamePanel gamePanel;
    private ScoreBoard scoreBoard;

    //ZWY记录回合数
    private static int roundNum = 1;
    //ZWY记录点击数
    private static int clickNum = 0;
    //ZWY玩家选择的回合点击数
    private static int turnNum = 1;
    //ZWY单人模式胜利数
    private static int winTime = 0;
    //ZWY给计时器用的点击数
    private static int click = 0;


    public GameController(Player p1, Player p2) {
           p1.setUserName(ThirdFrame.getP1username());
           p2.setUserName(ThirdFrame.getP2username());
        this.init(p1, p2);
        this.onTurn = p1;
        setTurnNum(ThirdFrame.getTurnTimes());

    }


    public boolean isStringNull(String s){
        if (s==null) return true;
        else return false;
    }

    //是否换人
    public boolean changePlayer(int clickNum, int turnNum){
        if (!MainFrame.isIsSingle()){scoreBoard.update();}
        else {scoreBoard.update(1);}
        if (clickNum%turnNum==0) {
            return true;
        }
        else return false;
    }
    /**
     * 初始化游戏。在开始游戏前，应先调用此方法，给予游戏必要的参数。
     *
     * @param p1 玩家1
     * @param p2 玩家2
     */
    public void init(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.onTurn = p1;

        //在初始化游戏的时候，还需要做什么？
    }

//    public void init(Player p1, Player p2, Player p3) {
//        this.p1 = p1;
//        this.p2 = p2;
//        this.p3 = p3;
//        this.onTurn = p1;
//
//        // 在初始化游戏的时候，还需要做什么？
//    }

    /**
     * 进行下一个回合时应调用本方法。
     * 在这里执行每个回合结束时需要进行的操作。
     * <p>
     * (目前这里没有每个玩家进行n回合的计数机制的，请自行修改完成哦~）
     */
    public void nextTurn() {

        if (onTurn == p1) {
            onTurn = p2;
            //ZWY
        } else if (onTurn == p2) {
            onTurn = p1;
            //ZWY
            roundNum++;
        }
        //仅点击没有点过的格子有效
        //if (GridComponent.haveBeenClicked()){
        if (onTurn==p1){System.out.println("Now it's Round " +roundNum );}
        System.out.println("Now it is " + onTurn.getUserName() + "'s turn.");
      scoreBoard.update();

        //}
        //TODO: 在每个回合结束的时候，还需要做什么 (例如...检查游戏是否结束？)

//   }

//            //仅点击没有点过的格子有效
//            //if (GridComponent.haveBeenClicked()){
//            if (onTurn==p1){System.out.println("Now it is " +roundNum +" rounds.");}
//            System.out.println("Now it is " + onTurn.getUserName() + "'s turn.");
//            scoreBoard.update();
//            //}
//            //TODO: 在每个回合结束的时候，还需要做什么 (例如...检查游戏是否结束？)
//
//        }
}


    /**
     * 获取正在进行当前回合的玩家。
     *
     * @return 正在进行当前回合的玩家
     */
    public  Player getOnTurnPlayer() {
        return onTurn;
    }

    public Player getP1() {
        return p1;
    }

    public Player getP2() {
        return p2;
    }

    public static GamePanel getGamePanel() {
        return GameController.gamePanel;
    }

    public ScoreBoard getScoreBoard() {
        return scoreBoard;
    }

    public  static void setGamePanel(GamePanel gamePanel) {
        GameController.gamePanel = gamePanel;
    }

    public void setScoreBoard(ScoreBoard scoreBoard) {
        this.scoreBoard = scoreBoard;
    }


    public void readFileData(String fileName) {
        //todo: read date from file

    }

    public void writeDataToFile(String fileName){
        //todo: write data into file
    }

    //ZWY
    public static int getClickNum() {
        return clickNum;
    }

    //ZWY
    public static int getRoundNum() {
        return roundNum;
    }


    //ZWY
    public static Player getOnTurn() {
        return onTurn;
    }


    //回合结束后遍历，检验游戏是否结束
    public static boolean isOver(){
        int n = findRemainedMine();
        if (n!=0){
        if(Math.abs(ScoreBoard.getP1().getScore()-ScoreBoard.getP2().getScore())>n){return true;}
        else return false;}
        else  return true;
    }

    //get the winner
    public static int getWinner(){
        int n = findRemainedMine();
        if(ScoreBoard.getP1().getScore() > ScoreBoard.getP2().getScore()){
            return 1;
        }else if(ScoreBoard.getP1().getScore() < ScoreBoard.getP2().getScore()){
            return 2;
        }else {
            if(ScoreBoard.getP1().getMistake() > ScoreBoard.getP2().getMistake()){
                return 2;
            }else if(ScoreBoard.getP1().getMistake() < ScoreBoard.getP2().getMistake()){
                return 1;
            }else {
                return 0;
            }
        }
    }


    //ZWY寻找剩余雷数
    public static int findRemainedMine(){
        int n = 0;
        int x = GamePanel.getChessboard().length;
        int y = GamePanel.getChessboard()[0].length;
        if (!MainFrame.isIsSingle()){
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (GamePanel.getMineField()[i][j].getStatus() == GridStatus.Covered||GamePanel.getMineField()[i][j].getStatus() == GridStatus.Cheat){
                    if (GamePanel.getChessboard()[i][j]==-1){n++;}
                }
            }
        }
        }
       if (MainFrame.isIsSingle()){for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (GamePanel.getMineField()[i][j].getStatus() == GridStatus.Covered){
                    if (GamePanel.getChessboard()[i][j]==-1){n++;}
                }
            }
        }}
        return n;
    }

//


    //ZWY找到三种情况下的赢家
//    public Player findWinner(){
//        if (findRemainedMine()==0){
//            if (ScoreBoard.getP1().getMistake()>ScoreBoard.getP2().getMistake()) {
//                return ScoreBoard.getP2();
//            }
//            if (ScoreBoard.getP1().getMistake()<ScoreBoard.getP2().getMistake()) {
//                return ScoreBoard.getP1();
//            }
//            if (ScoreBoard.getP1().getMistake()==ScoreBoard.getP2().getMistake()) {
//                return null;
//            }
//        }
//        if (findRemainedMine()!=0){
//            if (ScoreBoard.getP1().getScore()>ScoreBoard.getP2().getScore()){
//                return ScoreBoard.getP1();
//            }
//        }
//        return ScoreBoard.getP2();
//    }

    public static void setTurnNum(int turnNum) {
        GameController.turnNum = turnNum;
    }

    public static int getTurnNum() {
        return turnNum;
    }

    public static Difficulty getDifficult(String s){
        if ("Easy".equals(s)){return Difficulty.easy;}
        if ("Medium".equals(s)){return Difficulty.medium;}
        if ("Hard".equals(s)){return Difficulty.hard;}
        else return null;
    }

    public static void addClickNum() {
        clickNum++;
    }

    public static void minusClickNum() {
        clickNum--;
    }

    public static void setRoundNum(int roundNum) {
        GameController.roundNum = roundNum;
    }

    public static void setClickNum(int clickNum) {
        GameController.clickNum = clickNum;
    }

    public static boolean isSingleOver(){
        if (!MainFrame.isIsSingle()){return false;}
        if (MainFrame.controller.getP1().getLife()==0){
            return true;}
        if (MainFrame.controller.getP1().getMistake()==3){
            return true;}
        for (int i = 0; i < GamePanel.getMineField().length; i++) {
            for (int j = 0; j < GamePanel.getMineField()[0].length; j++) {
                if (GamePanel.getMineField()[i][j].getStatus()==GridStatus.Covered){
                    return false;
                }
            }
        }
        winTime++;
        return true;
    }
    //打完一关后load名字，life，刷新wrong,mistake

    public String hisWords(){
        if ((MainFrame.controller.getP1().getLife()==3)){return "End1";}
        //if (MainFrame.controller.getP1().getMistake()==3){return "End2";}
        if (MainFrame.controller.getP1().getLife()==2){return "End3";}
        if (MainFrame.controller.getP1().getLife()==1){return "End4";}
        if (MainFrame.controller.getP1().getLife()==0){return "End5";}
       return null;
    }

    public static int getWinTime() {
        return winTime;
    }

    public static void setWinTime(int winTime) {
        GameController.winTime = winTime;
    }

    public static void aStrangeSense(){
        int x = GamePanel.getChessboard().length;
        int y = GamePanel.getChessboard()[0].length;

        for (int i = 0; i < x; i++) {
            int h = 0;
            for (int j = 0; j < y; j++) {
                if (GamePanel.getMineField()[i][j].getStatus() == GridStatus.Covered){
                    if (GamePanel.getChessboard()[i][j]==-1){
                        GamePanel.getMineField()[i][j].setStatus(GridStatus.Cheat);
                        GamePanel.getMineField()[i][j].repaint();
                        h = 1;
                        break;
                    }
                }
            }
            if (h == 1){break;}
        }
    }




    public void time(){
        //Timer timer = new Timer();
        TimerTask timerTask = new TimerTask (){
            public void run() {
            scoreBoard.timeFlies();
            //System.out.println(scoreBoard.getTime());
            scoreBoard.update();
            if (scoreBoard.getTime()<=0) {
                setClickNum(getClickNum() + getTurnNum() - (getClickNum() % getTurnNum()));
                setClick(0);
                nextTurn();
                scoreBoard.newTurn();
            }
            if (changePlayer(getClick(), getTurnNum())&&getClick()!=0){
                setClick(0);
                scoreBoard.newTurn();
            }
            if (isOver()){
                timer.cancel();
            }
        }
    };
        timer.schedule(timerTask,1000L,1000L);
}
     public static void closeTimer() {
     MainFrame.controller.timer.cancel();
    }

    public static void restartGame(){

        MainFrame.controller.closeTimer();
        String a = MainFrame.controller.getP1().getUserName();
        String b = MainFrame.controller.getP2().getUserName();
        int c = GameController.getTurnNum();
        MainFrame mainFrame = new MainFrame(MainFrame.getxCount(), MainFrame.getyCount(), MainFrame.getMineCount());
        MainFrame.controller.getP1().setUserName(a);
        MainFrame.controller.getP2().setUserName(b);
        MainFrame.controller.getScoreBoard().update();
        MainFrame.controller.setTurnNum(c);
        MainFrame.controller.setClickNum(0);
        MainFrame.controller.setRoundNum(1);
        MainFrame.controller.scoreBoard.setTime(45);
        System.out.println(MainFrame.controller.getRoundNum());
        mainFrame.setVisible(true);
    }

    public static void addClick(){
        click++;
    }

    public static int getClick() {
        return click;
    }

    public static void setClick(int click) {
        GameController.click = click;
    }

    public void playAgain(){
        int x = GamePanel.getChessboard().length;
        int y = GamePanel.getChessboard()[0].length;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (GamePanel.getMineField()[i][j].getStatus() != GridStatus.Covered
                ){GamePanel.getMineField()[i][j].setStatus(GridStatus.Covered);
                    GamePanel.getMineField()[i][j].repaint();
                }
            }
        }
        MainFrame.controller.getP1().setScore(0);
        MainFrame.controller.getP1().setMistake(0);
        MainFrame.controller.getP2().setScore(0);
        MainFrame.controller.getP2().setMistake(0);
        scoreBoard.setTime(45);
        setClick(0);
        setClickNum(0);
        setRoundNum(0);
    }

    public boolean goodApple(){
        Random a = new Random();
        int t = a.nextInt(1);
        if(t==1){return true;}
        else return false;
    }

}
