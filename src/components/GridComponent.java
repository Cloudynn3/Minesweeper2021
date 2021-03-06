package components;

import controller.GameController;
import entity.GridStatus;
import minesweeper.GameInfo;
import minesweeper.GamePanel;
import minesweeper.MainFrame;
import minesweeper.Save_Load;

import java.awt.*;

import static minesweeper.GamePanel.getChessboard;

public class GridComponent extends BasicComponent {
    public static int gridSize = 30;

    private  int row;
    private  int col;
    private GridStatus status = GridStatus.Covered;
    private int content = 0;

    public GridComponent(int x, int y) {
        this.setSize(gridSize, gridSize);
        this.row = x;
        this.col = y;
    }

    public void setStatus(GridStatus status) {
        this.status = status;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getContent() {
        return content;
    }

    //ZWY点击键后是否得分或失误
    public boolean whetherGetMine(int x, int y){
        int[][]a= GamePanel.getChessboard();
        if (a[x][y]==-1){return true;}
        else return false;
    }

    //开花式开格子
//    public void bigBloom(int x, int y){
//        int rowLength = getChessboard().length;
//        int colLength = getChessboard()[0].length;
//            smallBoom(x,y);
//            while (x>0&&!whetherGetMine(x-1,y)&&(GamePanel.getMineField()[x-1][y].status == GridStatus.Covered)){
//            bigBloom(x-1,y);}
//            while (x<rowLength-1&&!whetherGetMine(x+1,y)&&(GamePanel.getMineField()[x+1][y].status == GridStatus.Covered))
//            bigBloom(x+1,y);
//            while (y<colLength-1&&!whetherGetMine(x,y+1)&&(GamePanel.getMineField()[x][y+1].status == GridStatus.Covered))
//            bigBloom(x,y+1);
//            while (y>0&&!whetherGetMine(x,y-1)&&(GamePanel.getMineField()[x][y-1].status == GridStatus.Covered))
//            bigBloom(x,y-1);
//
//    }


    //单个格子的小开花
//    public void smallBoom(int x, int y) {
//        int rowLength = getChessboard().length;
//        int colLength = getChessboard()[0].length;
//        if (!(GamePanel.getChessboard()[x][y] == -1)) {
//            if (0 < x && x < rowLength - 1 && y > 0 && y < colLength - 1) {
//                if (!whetherGetMine(x-1,y)) GamePanel.getMineField()[x-1][y].status = GridStatus.Clicked;repaint();
//                if (!whetherGetMine(x+1,y))GamePanel.getMineField()[x+1][y].status = GridStatus.Clicked;repaint();
//                if (!whetherGetMine(x,y-1))GamePanel.getMineField()[x][y-1].status = GridStatus.Clicked;repaint();
//                if (!whetherGetMine(x,y+1))GamePanel.getMineField()[x][y+1].status = GridStatus.Clicked;repaint();
//            }
//
//        }
//    }

    @Override
    public void onMouseLeftClicked() {
        Save_Load.saveDuringGame();
        if (this.getStatus() == GridStatus.Covered){
            GameController.addClickNum();
            GameController.addClick();
        //bigBloom(row,col);
            }
        //else {Save_Load.loadDuringGame();}
        //ZWY
       if (areYouLucky(GameController.getClickNum())) {
           while (areYouLucky(GameController.getClickNum())){
               MainFrame.controller.getGamePanel().generateChessBoard(MainFrame.getxCount(), MainFrame.getyCount(), MainFrame.getMineCount());
           for (int i = 0; i < getChessboard().length; i++) {
               for (int j = 0; j < getChessboard()[0].length; j++) {
                   GamePanel.getMineField()[i][j].setContent(GamePanel.getChessboard()[i][j]);
                   GamePanel.getMineField()[i][j].repaint();
               }
           }
       }
       }
        if (notHaveBeenClicked()) {
            System.out.printf("Gird (%d,%d) is left-clicked.\n", row + 1, col + 1);
            if (this.status == GridStatus.Covered) {
                if (!whetherGetMine(row, col)) {
                    this.status = GridStatus.Clicked;
                    repaint();
                } else {
                    this.status = GridStatus.Mine;
                    repaint();
                }
                //ZWY若踩雷，则扣一分
                if (whetherGetMine(row, col)) {
                    GameController.getOnTurn().costScore();
                }
                //bigBloom(row,col);
                if (MainFrame.controller.changePlayer(GameController.getClickNum(), MainFrame.controller.getTurnNum())
                &&!(notHaveBeenClicked())) {
                    MainFrame.controller.nextTurn();
                }
            }
        }

        GameInfo.updateMine();
        if (GameController.isOver()&&(!MainFrame.isIsSingle())){
            System.out.println("Game Over");
        }
        if (GameController.isSingleOver()&&MainFrame.isIsSingle()){
            System.out.println("OVER");
        }
        //TODO: 在左键点击一个格子的时候，还需要做什么？
    }

    @Override
    public void onMouseRightClicked() {
        Save_Load.saveDuringGame();
        if (this.getStatus() == GridStatus.Covered){
            GameController.addClickNum();
            GameController.addClick();
        }
       //else {Save_Load.loadDuringGame();}
            if (notHaveBeenClicked()) {
                System.out.printf("Gird (%d,%d) is right-clicked.\n", row + 1, col + 1);
                if (whetherGetMine(row, col)) {
                    this.status = GridStatus.Flag;
                    repaint();
                } else {
                    this.status = GridStatus.WrongFlag;
                    repaint();
                }
                if (!whetherGetMine(row, col)) {
                    GameController.getOnTurn().addMistake();
                } else {
                    GameController.getOnTurn().addScore();
                }
                if (MainFrame.controller.changePlayer(MainFrame.controller.getClickNum(), MainFrame.controller.getTurnNum())
                        &&!(notHaveBeenClicked())) {
                    MainFrame.controller.nextTurn();
                }

            }

        GameInfo.updateMine();
        if (GameController.isOver()&&(!MainFrame.isIsSingle())){
            System.out.println("Game Over");
        }
        if (GameController.isSingleOver()&&MainFrame.isIsSingle()){
            System.out.println("OVER");
        }
        //TODO: 在右键点击一个格子的时候，还需要做什么？

    }

    public void draw(Graphics g) {

        //作弊模式现实展示有雷模块
        if (this.status == GridStatus.Cheat){
            g.setColor(Color.PINK);
            g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
            g.setColor(Color.WHITE);
            g.drawString("M", getWidth() / 2 - 5, getHeight() / 2 + 5);
        }


        if (this.status == GridStatus.Covered) {
            g.setColor(Color.CYAN);
            g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
        }

        //ZWY踩雷
        if (this.status == GridStatus.Mine) {

            g.setColor(Color.BLUE);
            g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
            g.setColor(Color.WHITE);
            g.drawString("M", getWidth() / 2 - 5, getHeight() / 2 + 5);
        }

        if (this.status == GridStatus.Clicked) {

            g.setColor(Color.WHITE);
            g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
            if (content!=0){
            g.setColor(Color.BLACK);
            g.drawString(Integer.toString(content), getWidth() / 2 - 5, getHeight() / 2 + 5);
          }
        }

        //插错旗帜
        if (this.status == GridStatus.WrongFlag) {

            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
            g.setColor(Color.RED);
            g.drawString(Integer.toString(content), getWidth() / 2 - 5, getHeight() / 2 + 5);
        }

        if (this.status == GridStatus.Flag) {

            g.setColor(Color.YELLOW);
            g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
            g.setColor(Color.BLACK);
            g.drawString("F", getWidth() / 2 - 5, getHeight() / 2 + 5);
        }

    }

    public void setContent(int content) {
        this.content = content;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.printComponents(g);
        draw(g);
    }

    public GridStatus getStatus() {
        return status;
    }


    public boolean notHaveBeenClicked(){
        if (GamePanel.getMineField()[row][col].getStatus()==GridStatus.Covered){
            return true;
        }else return false;
    }

    //开门红判断
    public boolean areYouLucky(int clickNum){
        if (clickNum==1){
            if (GamePanel.getChessboard()[this.row][this.col]==-1){ return true;
            }
        }
        return false;
    }





}
