package minesweeper;

import components.GridComponent;
import controller.GameController;
import entity.Player;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends JFrame {
    public static GameController controller;
    private static int xCount;
    private static int yCount;
    private static int mineCount;
    private static boolean isSingle =false;
    GamePanel gamePanel;
    GameInfo gameInfo;


    public MainFrame(Difficulty difficulty) {
        this.xCount = difficulty.getX();
        this.yCount = difficulty.getY();
        this.mineCount = difficulty.getMineNum();

        init();
        gameInfo = new GameInfo();
        this.add(gameInfo);
    }

    public MainFrame() {
        this.xCount = LevelFrame.getDefinedLength();
        this.yCount = LevelFrame.getDefinedWidth();
        this.mineCount = LevelFrame.getDefinedMineNumbers();

        init();
        gameInfo = new GameInfo();
        this.add(gameInfo);
    }

    public MainFrame(int x, int y, int m) {
        this.xCount = x;
        this.yCount = y;
        this.mineCount = m;

        init();
        gameInfo = new GameInfo();
        this.add(gameInfo);
    }

    public MainFrame(int x, int y, int m,int minute,int second,int msecond) {
        this.xCount = x;
        this.yCount = y;
        this.mineCount = m;

        init();
        gameInfo = new GameInfo();
        this.add(gameInfo);
    }

    //solo constructor
    public MainFrame(Difficulty difficulty, int x){
        this.xCount = difficulty.getX();
        this.yCount = difficulty.getY();
        this.mineCount = difficulty.getMineNum();
        this.isSingle = true;

        this.setTitle("Minesweeper2021");
        this.setLayout(null);
        this.setSize(yCount * GridComponent.gridSize + 40, xCount * GridComponent.gridSize + 220);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //background import
        JLabel bg = new JLabel(new ImageIcon("src/GamePic/background.jpg"));
        bg.setBounds(0, 0, yCount * GridComponent.gridSize + 40, xCount * GridComponent.gridSize + 220);
        this.getLayeredPane().add(bg, new Integer(Integer.MIN_VALUE));
        ((JPanel)this.getContentPane()).setOpaque(false); //设置透明

        Player p1 = new Player("1");
        Player p2 = new Player("2");

        controller = new GameController(p1, p2);

        GamePanel gamePanel = new GamePanel(xCount, yCount, mineCount);
        controller.setGamePanel(gamePanel);
        ScoreBoard scoreBoard = new ScoreBoard(p1, p2, xCount, yCount);
        controller.setScoreBoard(scoreBoard);
        GameInfo gameInfo = new GameInfo();

        this.add(gamePanel);
        this.add(gameInfo);
        this.add(scoreBoard);

        //初始化菜单
        initMenu();

        GameController.setTurnNum(99999);

        JButton load = new JButton("TimeTravel");
        load.setSize(120,20);
        load.setLocation(12,gamePanel.getHeight()+scoreBoard.getHeight()+22);
        this.add(load);
        load.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                Save_Load.loadDuringGameSingle();
            }
        });

        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);



        JButton cheating = new JButton("Sense");
        cheating.setSize(120,20);
        cheating.setLocation(12,gamePanel.getHeight()+scoreBoard.getHeight()+44);
        this.add(cheating);
        cheating.addMouseListener(new MouseAdapter() {
            int n = 0;
            @Override
            public void mouseClicked(MouseEvent e) {
                GameController.aStrangeSense();
                try {
                    Thread.sleep(2500);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
        });

        JButton clickBtn = new JButton("Bandage");
        clickBtn.setSize(120, 20);
        clickBtn.setLocation(12, gamePanel.getHeight() + scoreBoard.getHeight());
        add(clickBtn);
        clickBtn.addActionListener(e -> {
            MainFrame.controller.getP1().addLife();
            clickBtn.setVisible(false);
        });
    }


    private void init() {

        this.setTitle("Minesweeper2021");
        this.setLayout(null);
        this.setSize(yCount * GridComponent.gridSize + 40, xCount * GridComponent.gridSize + 220);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //background import
        JLabel bg = new JLabel(new ImageIcon("src/GamePic/background.jpg"));
        bg.setBounds(0, 0, yCount * GridComponent.gridSize + 40, xCount * GridComponent.gridSize + 220);
        this.getLayeredPane().add(bg, new Integer(Integer.MIN_VALUE));
        ((JPanel)this.getContentPane()).setOpaque(false); //设置透明


        Player p1 = new Player("1");
        Player p2 = new Player("2");


        controller = new GameController(p1, p2);

        gamePanel = new GamePanel(xCount, yCount, mineCount);
        controller.setGamePanel(gamePanel);
        ScoreBoard scoreBoard = new ScoreBoard(p1, p2, xCount, yCount);
        controller.setScoreBoard(scoreBoard);
//        gameInfo = new GameInfo();


        this.add(gamePanel);
//        this.add(gameInfo);
        this.add(scoreBoard);

        //初始化菜单
        initMenu();

        //Addition button ?
        JButton a = new JButton("Again");
        a.setSize(120, 20);
        a.setLocation(12, gamePanel.getHeight() + scoreBoard.getHeight());
        add(a);
        a.addActionListener(e -> {
            MainFrame.controller.playAgain();

        });
    }

    public void initMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu1 = new JMenu(" 菜单 ");
        JMenu menu2 = new JMenu(" 功能 ");
        JMenu menu3 = new JMenu(" 关于我们 ");
        JMenuItem item1 = new JMenuItem(" 游戏存档 ");
        JMenuItem item2 = new JMenuItem(" 重启游戏 ");
        JMenuItem item3 = new JMenuItem(" 换肤 ");
        JMenuItem item4 = new JMenuItem(" 作弊开 ");
        JMenuItem item5 = new JMenuItem(" 作弊关 ");
        JMenuItem item6 = new JMenuItem(" 悔棋 ");

        menuBar.add(menu1);
        menuBar.add(menu2);
        menuBar.add(menu3);
        menu1.add(item1);
        menu1.add(item2);
        menu2.add(item3);
        menu2.add(item4);
        menu2.add(item5);
        menu2.add(item6);
        setJMenuBar(menuBar);

        item1.addActionListener(e -> {
//           String fileName = JOptionPane.showInputDialog(this, "input here");
//           System.out.println("fileName :"+fileName);
            Save_Load.saveGame();
//            controller.readFileData(fileName);
//            controller.writeDataToFile(fileName);
        });

        item2.addActionListener(e -> {
            dispose();
            System.out.println("Restart Game");
            MainFrame.controller.restartGame();

            //Resume Time
            TimePanel.getTimer().restart();
        });

        item4.addActionListener(e -> {
            gamePanel.letsCheating();
        });

        item5.addActionListener(e -> {
            gamePanel.stopCheating();
        });

        item6.addActionListener(e -> {
            dispose();
            Save_Load.loadDuringGame();
        });

        menu3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Lalaland~");
            }
        });


        //Excess: time label
        MainFrame.controller.time();
    }

    public static int getxCount() {
        return xCount;
    }

    public static int getyCount() {
        return yCount;
    }

    public static int getMineCount() {
        return mineCount;
    }

    public static boolean isIsSingle() {
        return isSingle;
    }

}
