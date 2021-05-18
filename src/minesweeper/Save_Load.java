package minesweeper;

import controller.GameController;
import entity.GridStatus;

import java.io.*;

import static entity.Player.PlayerList;

public class Save_Load {
    public static void saveGame() {
        try {
            File file = new File("src/File/test.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter("src\\File\\test.txt"));
            writer.write(String.valueOf(MainFrame.getxCount()));
            writer.write("\n");
            writer.write(String.valueOf(MainFrame.getyCount()));
            writer.write("\n");
            writer.write(String.valueOf(MainFrame.getMineCount()));
            writer.write("\n");
            for (int i = 0; i < GamePanel.getMineField().length; i++) {
                for (int j = 0; j < GamePanel.getMineField()[0].length; j++) {
                    writer.write((GamePanel.getMineField()[i][j].getStatus().toString()));
                    writer.write("\n");
                    writer.write(String.valueOf(GamePanel.getMineField()[i][j].getContent()));
                    writer.write("\n");
                }
            }

            writer.write(MainFrame.controller.getP1().getUserName());
            writer.write("\n");
            writer.write(String.valueOf(MainFrame.controller.getP1().getScore()));
            writer.write("\n");
            writer.write(String.valueOf(MainFrame.controller.getP1().getMistake()));
            writer.write("\n");
            writer.write(MainFrame.controller.getP2().getUserName());
            writer.write("\n");
            writer.write(String.valueOf(MainFrame.controller.getP2().getScore()));
            writer.write("\n");
            writer.write(String.valueOf(MainFrame.controller.getP2().getMistake()));
            writer.write("\n");


            writer.write(String.valueOf(GameController.getClickNum()));
            writer.write("\n");
            writer.write(String.valueOf(GameController.getTurnNum()));
            writer.write("\n");
            writer.write(String.valueOf(GameController.getRoundNum()));
            writer.write("\n");
            writer.write(String.valueOf(ScoreBoard.getTime()));
            writer.write("\n");
            System.out.println("Save Down");
            writer.close();

        } catch (IOException e) {
            System.out.println("fail");
        }

    }

    public static void loadGame() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src\\File\\test.txt"));

            int x = Integer.parseInt(reader.readLine());
            int y = Integer.parseInt(reader.readLine());
            int m = Integer.parseInt(reader.readLine());
            MainFrame mainFrame = new MainFrame(x, y, m);  //todo: @→ MainFrame mainFrame = new MainFrame(x,y,m,minute,second,msecond);
            GamePanel n = GameController.getGamePanel();
//            GameController.setGamePanel(n);
//            n.generateChessBoard(x,y,m);
            mainFrame.setVisible(true);
            for (int i = 0; i < n.getMineField().length; i++) {
                for (int j = 0; j < n.getMineField()[0].length; j++) {
                    n.getMineField()[i][j].setStatus(GridStatus.getGridStatus(reader.readLine()));
                    int content = Integer.parseInt(reader.readLine());
                    n.getMineField()[i][j].setContent(content);
                    n.getChessboard()[i][j] = content;
                    n.getMineField()[i][j].repaint();
                }
            }

            MainFrame.controller.getP1().setUserName(reader.readLine());
            MainFrame.controller.getP1().setScore(Integer.parseInt(reader.readLine()));
            MainFrame.controller.getP1().setMistake(Integer.parseInt(reader.readLine()));
            MainFrame.controller.getP2().setUserName(reader.readLine());
            MainFrame.controller.getP2().setScore(Integer.parseInt(reader.readLine()));
            MainFrame.controller.getP2().setMistake(Integer.parseInt(reader.readLine()));
            MainFrame.controller.getScoreBoard().update();

            GameController.setClickNum(Integer.parseInt(reader.readLine()));
            GameController.setTurnNum(Integer.parseInt(reader.readLine()));
            GameController.setRoundNum(Integer.parseInt(reader.readLine()));

            ScoreBoard.setTime(Integer.parseInt(reader.readLine()));
            System.out.println("Load Down");

            reader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void saveDuringGame() {
        try {
            File file = new File("src/File/duringGame.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter("src\\File\\duringGame.txt"));
            writer.write(String.valueOf(MainFrame.getxCount()));
            writer.write("\n");
            writer.write(String.valueOf(MainFrame.getyCount()));
            writer.write("\n");
            writer.write(String.valueOf(MainFrame.getMineCount()));
            writer.write("\n");
            for (int i = 0; i < GamePanel.getMineField().length; i++) {
                for (int j = 0; j < GamePanel.getMineField()[0].length; j++) {
                    writer.write((GamePanel.getMineField()[i][j].getStatus().toString()));
                    writer.write("\n");
                    writer.write(String.valueOf(GamePanel.getMineField()[i][j].getContent()));
                    writer.write("\n");
                }
            }

            writer.write(MainFrame.controller.getP1().getUserName());
            writer.write("\n");
            writer.write(String.valueOf(MainFrame.controller.getP1().getScore()));
            writer.write("\n");
            writer.write(String.valueOf(MainFrame.controller.getP1().getMistake()));
            writer.write("\n");
            writer.write(MainFrame.controller.getP2().getUserName());
            writer.write("\n");
            writer.write(String.valueOf(MainFrame.controller.getP2().getScore()));
            writer.write("\n");
            writer.write(String.valueOf(MainFrame.controller.getP2().getMistake()));
            writer.write("\n");


            writer.write(String.valueOf(GameController.getClickNum()));
            writer.write("\n");
            writer.write(String.valueOf(GameController.getTurnNum()));
            writer.write("\n");
            writer.write(String.valueOf(GameController.getRoundNum()));
            writer.write("\n");

            writer.write(String.valueOf(GameController.getWinTime()));
            writer.write("\n");
            writer.write(String.valueOf(MainFrame.controller.getP1().getLife()));
            writer.write("\n");
            writer.write(String.valueOf(MainFrame.controller.getP1().getWrong()));
            writer.write("\n");
            writer.write(String.valueOf(ScoreBoard.getTime()));
            writer.write("\n");
            System.out.println(GameController.getClickNum());
            writer.close();

        } catch (IOException e) {
            System.out.println("fail");
        }

    }

    public static void loadDuringGame() {
        try {
            MainFrame.controller.closeTimer();
            BufferedReader reader = new BufferedReader(new FileReader("src\\File\\duringGame.txt"));

            int x = Integer.parseInt(reader.readLine());
            int y = Integer.parseInt(reader.readLine());
            int m = Integer.parseInt(reader.readLine());
            MainFrame mainFrame = new MainFrame(x, y, m);   //todo:
            GamePanel n = GameController.getGamePanel();

            mainFrame.setVisible(true);
            for (int i = 0; i < n.getMineField().length; i++) {
                for (int j = 0; j < n.getMineField()[0].length; j++) {
                    n.getMineField()[i][j].setStatus(GridStatus.getGridStatus(reader.readLine()));
                    int content = Integer.parseInt(reader.readLine());
                    n.getMineField()[i][j].setContent(content);
                    n.getChessboard()[i][j] = content;
                    n.getMineField()[i][j].repaint();
                }
            }

            MainFrame.controller.getP1().setUserName(reader.readLine());
            MainFrame.controller.getP1().setScore(Integer.parseInt(reader.readLine()));
            MainFrame.controller.getP1().setMistake(Integer.parseInt(reader.readLine()));
            MainFrame.controller.getP2().setUserName(reader.readLine());
            MainFrame.controller.getP2().setScore(Integer.parseInt(reader.readLine()));
            MainFrame.controller.getP2().setMistake(Integer.parseInt(reader.readLine()));
            if (MainFrame.isIsSingle()) {
                MainFrame.controller.getScoreBoard().update(1);
            } else {
                MainFrame.controller.getScoreBoard().update();
            }

            GameController.setClickNum(Integer.parseInt(reader.readLine()));
            GameController.setTurnNum(Integer.parseInt(reader.readLine()));
            GameController.setRoundNum(Integer.parseInt(reader.readLine()));


            GameController.setWinTime(Integer.parseInt(reader.readLine()));
            MainFrame.controller.getP1().setLife(Integer.parseInt(reader.readLine()));
            MainFrame.controller.getP1().setWrong(Integer.parseInt(reader.readLine()));

            ScoreBoard.setTime(Integer.parseInt(reader.readLine()));

            //System.out.println(GameController.getClickNum());
            System.out.println("Come back");

            reader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public static void saveSingle() {
        try {
            File file = new File("src/File/single.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter("src\\File\\single.txt"));
            writer.write(String.valueOf(PlayerList.get(0).getLife()));
            writer.write(String.valueOf(GameController.getWinTime()));
            System.out.println("Save Down");
            writer.close();

        } catch (IOException e) {
            System.out.println("fail");
        }
    }

    public static void loadDuringGameSingle() {
        try {
            MainFrame.controller.closeTimer();

            BufferedReader reader = new BufferedReader(new FileReader("src\\File\\duringGame.txt"));

            int x = Integer.parseInt(reader.readLine());
            int y = Integer.parseInt(reader.readLine());
            int m = Integer.parseInt(reader.readLine());
            MainFrame mainFrame = new MainFrame(Difficulty.medium, 1);
            GamePanel n = GameController.getGamePanel();

            mainFrame.setVisible(true);
            for (int i = 0; i < n.getMineField().length; i++) {
                for (int j = 0; j < n.getMineField()[0].length; j++) {
                    n.getMineField()[i][j].setStatus(GridStatus.getGridStatus(reader.readLine()));
                    int content = Integer.parseInt(reader.readLine());
                    n.getMineField()[i][j].setContent(content);
                    n.getChessboard()[i][j] = content;
                    n.getMineField()[i][j].repaint();
                }
            }

            MainFrame.controller.getP1().setUserName(reader.readLine());
            MainFrame.controller.getP1().setScore(Integer.parseInt(reader.readLine()));
            MainFrame.controller.getP1().setMistake(Integer.parseInt(reader.readLine()));
            MainFrame.controller.getP2().setUserName(reader.readLine());
            MainFrame.controller.getP2().setScore(Integer.parseInt(reader.readLine()));
            MainFrame.controller.getP2().setMistake(Integer.parseInt(reader.readLine()));
            MainFrame.controller.getScoreBoard().update(1);


            GameController.setClickNum(Integer.parseInt(reader.readLine()));
            GameController.setTurnNum(Integer.parseInt(reader.readLine()));
            GameController.setRoundNum(Integer.parseInt(reader.readLine()));


            GameController.setWinTime(Integer.parseInt(reader.readLine()));
            MainFrame.controller.getP1().setLife(Integer.parseInt(reader.readLine()));
            MainFrame.controller.getP1().setWrong(Integer.parseInt(reader.readLine()));

            ScoreBoard.setTime(Integer.parseInt(reader.readLine()));

            //System.out.println(GameController.getClickNum());
            System.out.println("Come to the back");

            reader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

