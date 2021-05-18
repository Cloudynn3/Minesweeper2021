import minesweeper.InfoFrame;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        music();
        System.out.println(new File("D:\\JAVA File\\Game file\\Integrated1\\YYH.wav").getAbsolutePath());

        SwingUtilities.invokeLater(() -> {
            InfoFrame infoFrame = new InfoFrame();
            infoFrame.setVisible(true);

//            MainFrame mainFrame = new MainFrame(GameController.getDifficult(SecondFrame.getGameLevel()));
//            mainFrame.setVisible(true);

//            String a = SecondFrame.getGameLevel();
//            System.out.println(a);
            
        });

    }

    public static void music() {
        try {
            AudioPlayer.player.start(new AudioStream(new FileInputStream("YYH.wav")));
        } catch (IOException error) {
            error.printStackTrace();
        }
    }
}
