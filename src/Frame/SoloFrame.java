package Frame;

import minesweeper.Difficulty;
import minesweeper.MainFrame;

import javax.swing.*;
import java.util.Random;

public class SoloFrame extends JFrame {
    public static String name;
    Random ran = new Random();
    int counter = 0;

    public SoloFrame(){
        this.setTitle("Adventurer");
        this.setSize(500, 260);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(3);

        this.setLayout(null);

        //TODO: insert background pic.

        JTextField P1input = new JTextField();
        P1input.setBounds(300, 80, 150, 30);
        this.add(P1input);

        JButton confirm = new JButton("Confirm");
        confirm.setBounds(330, 180, 80, 30);
        this.add(confirm);

        confirm.addActionListener(e -> {

            if (P1input.getText().equals("")) {
                System.out.println("User didn't input P1 username.");
                name = "Adventurer#" + (ran.nextInt(9000) + 1000);
            } else {
                name = P1input.getText();
            }

            System.out.println("Adventurer's Name : " + name);
            dispose();

            //Open Story
            StoryFrame();
        });

        this.setVisible(true);
    }

    public void StoryFrame(){
        //story label
        JLabel plot = new JLabel("<html><body>Xxxx 年，A国地质家们在位于北纬49度58分，西经165度43分的***山脉深处发现了一种新型矿石，其具有极高的能量密度，<br>" +
                "一立方米的纯化矿石足以支撑一架大型客机飞跃大半个地球。这种矿石被命名为“比特”，一度被称为拯救人类能源危机的希望。<br>" +
                "世界上的大国开始争相开采，但是随着世界各地的矿场相继发生了爆炸事故后，人们发现，“比特”周围往往分布着一种一旦接触<br>" +
                "空气很快便会被迅速氧化进而发生剧烈的爆炸的矿物“礌”。由于“比特”性质极不稳定，目前只有人工开采一种途径，矿场上的鲜血事故<br>" +
                "层出不穷......最终，在联合国进行了4天3夜的讨论后，宣布了长时间封闭所有的正式矿场的决定。</body></html>"

        );
        plot.setBounds(50,0,800,400);
        Story(plot);
    }

    public void StoryFrame(int x){
        JLabel plot = new JLabel();
        plot.setBounds(50,0,800,400);
        switch (x){
            case 1:
                plot.setText("<html><body>当然，被封闭的只有正式矿场....</body></html>");
                plot.setBounds(50,0,800,400);
                Story(plot);
                break;
            case 2:
                plot.setText("<html><body>我的名字是xxx，17岁，在我很小的时候小时候父母就因意外去世了，是爷爷一直将我抚养长大。<br>" +
                        "而爷爷在半年前遭遇了事故，被诊断为XXX，至今仍然在昏迷中，为了爷爷的手术费用，我来到了这个偏远的矿场。为了钱，<br>" +
                        "我当起了“欧米茄”矿工。这是一个非法的黑心矿场，设施简陋，卫生条件也极其恶劣，老鼠和蟑螂是每一个人的室友，空气中总是<br>" +
                        "弥漫着腐败的气味...（呆久了连时间的流逝都感觉怪怪的）来到这里的全都是像我一样走投无路，无依无靠的人，就算突然消失了<br>" +
                        "也不会有任何人注意。这片矿场几乎每天都有爆炸声，陪伴着人们清醒，工作，饮食，入梦。每一天都有人失去了一部分身体，有人<br>" +
                        "昏迷不醒，有人被拖去掩埋，有人消失不见......过去的故事告诉我，不要和任何人扯上关系，因为任何人都可能突然消失不见......</body></html>"

                );
                plot.setBounds(50,0,800,400);
                Story(plot);
                break;
            case 3:
                plot.setText("<html><body>值得庆幸的是，我运气很好，直到现在也基本上只是一些皮外伤和轻微的脑震荡（可能和这种奇怪的感觉<br>" +
                        "相关吧），虽然矿场非常黑心，但是还是给每片矿区配备了一块绷带。爷爷的医药费也马上就要筹备完全了，只需要再开采最后4块<br>" +
                        "矿区，只要我把这最后4块矿区的矿石开采出来，我就可以永永远远的离开这个鬼地方，回家了！一定要等着我，爷爷！<br>" +
                        "就快了，我就快要成功了！</body></html>"

                );
                plot.setBounds(50,0,800,400);
                Story(plot);
                break;
            case 4:
                plot.setText("<html><body>记住，一片矿区不能少获取2块“比特”以上。（Mistake<3）\n" +
                        "你最多承受两次爆炸。\n" +
                        "绷带可以在受伤的时候紧急处理。\n" +
                        "别忘了你与众不同的“特别之处”。\n" +
                        "去吧，去那最后的矿区吧，祝你好运。\n</body></html>"

                );

        }
    }

    public void Story(JLabel plot){

        JFrame frame1 = new JFrame();

        frame1.setSize(980, 660);
        frame1.setLocationRelativeTo(null);
        frame1.setAlwaysOnTop(true);
        frame1.setDefaultCloseOperation(3);
        frame1.setLayout(null);

        frame1.add(plot);
        frame1.setVisible(true);

        JButton confirm = new JButton();
        if(counter == 3){
            confirm.setText("OK");
            confirm.setBounds(730, 400, 80, 30);
            frame1.add(confirm);
        }
        else {
            confirm.setText("NEXT");
        confirm.setBounds(730, 400, 80, 30);
        frame1.add(confirm);
        }

        confirm.addActionListener(e -> {
            counter++;

            if(counter == 4){
                frame1.dispose();

                MainFrame soloGame = new MainFrame(Difficulty.medium,1);
                soloGame.controller.getP1().setUserName("Jack");
                soloGame.controller.getP2().setUserName(" ");
                soloGame.controller.getP1().setMistake(0);
                soloGame.controller.getP1().setScore(0);
                soloGame.controller.getP2().setMistake(0);
                soloGame.controller.getP2().setScore(0);
            }else {
                frame1.dispose();
                StoryFrame(counter);
            }
        });
    }

}
