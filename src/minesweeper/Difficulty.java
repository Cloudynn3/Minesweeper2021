package minesweeper;

public enum Difficulty {
    easy(9,9,10),
    medium(16, 16, 40),
    hard(16,30,99);
    //userDefine();



    private int x;
    private int y;
    private int mineNum;



    Difficulty(int x, int y, int mineNum){
        this.x = x;
        this.y = y;
        this.mineNum = mineNum;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getMineNum() {
        return mineNum;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setMineNum(int mineNum) {
        this.mineNum = mineNum;
    }

    public String DifficultTransform(Difficulty difficulty){
        if (difficulty==Difficulty.easy){return "E";}
        if (difficulty==Difficulty.medium){return "M";}
        if (difficulty==Difficulty.hard){return "H";}
        else return "N";
    }

}
