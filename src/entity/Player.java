package entity;

import java.util.Random;
import java.util.ArrayList;

public class Player {
    private static Random ran = new Random();

    private String userName;
    private int score = 0;
    private int mistake = 0;
    private int wrong = 0;
    private int life = 3;
    public static ArrayList<Player> PlayerList = new ArrayList<Player>();

    /**
     * 通过特定名字初始化一个玩家对象。
     * @param userName 玩家的名字
     */
    public Player(String userName){
        this.userName = userName;
        PlayerList.add(this);
    }

    /**
     * 通过默认名字初始化一个玩家对象。
     */
//    public Player(){
//        userName = "User#"+(ran.nextInt(9000)+1000);
//        PlayerList.add(this);
//    }

    /**
     * 为玩家加一分。
     */
    public void addScore(){
        score++;
    }

    /**
     * 为玩家扣一分。
     */
    public void costScore(){
        score--;
        wrong++;
        life--;
    }

    /**
     * 为玩家增加一次失误数。
     */
    public void addMistake() { mistake++; }


    public int getScore(){
        return score;
    }
    public String getUserName(){ return userName; }
    public int getMistake(){ return mistake; }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setPlayerList(ArrayList<Player> playerList) {
        PlayerList = playerList;
    }

    public static ArrayList<Player> getPlayerList() {
        return PlayerList;
    }

    public void setMistake(int mistake) {
        this.mistake = mistake;
    }

    public int getWrong() {
        return wrong;
    }

    public void setWrong(int wrong) {
        this.wrong = wrong;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void addLife(){
        if(life<3){
        life++;}
    }

    public void costLife(){
        life--;
    }

}
