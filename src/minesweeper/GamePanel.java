package minesweeper;

import components.GridComponent;
import controller.GameController;
import entity.GridStatus;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GamePanel extends JPanel {
    private static GridComponent[][] mineField;
    private static int[][] chessboard;
    private final Random random = new Random();

    public static int[][] getChessboard() {
        return chessboard;
    }

    /**
     * 初始化一个具有指定行列数格子、并埋放了指定雷数的雷区。
     *
     * @param xCount    count of grid in column
     * @param yCount    count of grid in row
     * @param mineCount mine count
     */


    public GamePanel(int xCount, int yCount, int mineCount) {
        this.setVisible(true);
        this.setFocusable(true);
        this.setLayout(null);
        this.setBackground(Color.WHITE);
        this.setBounds(10,70,GridComponent.gridSize * yCount, GridComponent.gridSize * xCount);

        initialGame(xCount, yCount, mineCount);

        repaint();
    }



    public void initialGame(int xCount, int yCount, int mineCount) {
        GamePanel.mineField = new GridComponent[xCount][yCount];

        generateChessBoard(xCount, yCount, mineCount);

        for (int i = 0; i < xCount; i++) {
            for (int j = 0; j < yCount; j++) {
                GridComponent gridComponent = new GridComponent(i, j);
                if (GameController.getClickNum()!=0){
                    //this.remove(mineField[i][j]);
                    }// 放在前面
                gridComponent.setContent(chessboard[i][j]);
                gridComponent.setLocation(j * GridComponent.gridSize, i * GridComponent.gridSize);
                mineField[i][j] = gridComponent;

                //MainFrame里也要重新add



                this.add(mineField[i][j]);
            }
        }
        if (GameController.getClickNum()!=0){
            GameController.minusClickNum();
        }
    }


    public void generateChessBoard(int xCount, int yCount, int mineCount) {
        //todo: generate chessboard by your own algorithm
        int[][] chessboard1 = new int[xCount+2][yCount+2];
        chessboard = new int[xCount][yCount];
        for (int i = 0; i < mineCount; i++) {
            //ZWY通过死循环来实现不停的寻找，直到安置好雷
            while (true) {
                int row = random.nextInt(xCount-1)+1;
                int col = random.nextInt(yCount-1)+1;
                //ZWY用数字 -1 表示该区域有雷，如果该位置没有布雷，那么则放置
                if (chessboard1[row][col] != -1) {
                    chessboard1[row][col] = -1;
                    //ZWY跳出循环
                    break;
                }
            }
        }
        //ZWY密集判断：-1周围一圈和不能为-8
         boolean f = false;
        for (int i = 1; i < xCount + 1; i++) {
            for (int j = 1; j < yCount + 1; j++) {
                if (chessboard1[i][j]==-1&&(chessboard1[i-1][j-1]+chessboard1[i][j-1]+chessboard1[i+1][j-1]+chessboard1[i+1][j]+
                        chessboard1[i-1][j]+chessboard1[i-1][j+1]+chessboard1[i][j+1]+chessboard1[i+1][j+1]==-8)){
                    f = true;
                }
            }
        }
        //ZWY转移棋盘，把周围雷数填上
        for (int i = 1; i < xCount + 1; i++) {
            for (int j = 1; j < yCount + 1; j++) {
                if (chessboard1[i][j]==-1){chessboard[i-1][j-1]=-1;}
                else {chessboard[i-1][j-1]=-1*(chessboard1[i-1][j-1]+chessboard1[i][j-1]+chessboard1[i+1][j-1]+chessboard1[i+1][j]+
                        chessboard1[i-1][j]+chessboard1[i-1][j+1]+chessboard1[i][j+1]+chessboard1[i+1][j+1]);
            }
                //想办法不显示0，还没有做到
                if (chessboard[i-1][j-1]==0){}
            }
        }
        if (f){generateChessBoard(xCount, yCount, mineCount);}
    }

    /**
     * 获取一个指定坐标的格子。
     * 注意请不要给一个棋盘之外的坐标哦~
     *
     * @param x 第x列
     * @param y 第y行
     * @return 该坐标的格子
     */
    public static GridComponent  getGrid(int x, int y) {
        try {
            return mineField[x][y];
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }


    public static GridComponent[][] getMineField() {
        return mineField;
    }

    //ZWY作弊模式开启
    public void letsCheating(){
        int x = GamePanel.getChessboard().length;
        int y = GamePanel.getChessboard()[0].length;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {

                if (GamePanel.getMineField()[i][j].getStatus() == GridStatus.Covered
                        && GamePanel.getChessboard()[i][j]==-1){
                    //不知道哪种方法好一点，就先放在这里了
                    //GamePanel.getGrid(i,j).status = GridStatus.Cheat;
                    GamePanel.getMineField()[i][j].setStatus(GridStatus.Cheat) ;
                    repaint();
                }
            }
        }
    }

    //ZWY作弊模式关闭
    public void stopCheating(){
        int x = GamePanel.getChessboard().length;
        int y = GamePanel.getChessboard()[0].length;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {

                if (GamePanel.getMineField()[i][j].getStatus() == GridStatus.Cheat
                        && GamePanel.getChessboard()[i][j]==-1){
                    //不知道哪种方法好一点，就先放在这里了
                    //GamePanel.getGrid(i,j).status = GridStatus.Cheat;
                    GamePanel.getMineField()[i][j].setStatus(GridStatus.Covered) ;
                    repaint();
                }
            }
        }
    }





}
