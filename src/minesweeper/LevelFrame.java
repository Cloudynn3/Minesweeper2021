package minesweeper;

import javax.swing.*;

public class LevelFrame extends JFrame {
    private static int definedLength;
    private static int definedWidth;
    private static int definedMineNumbers;

    public LevelFrame() {
        this.setTitle("Level Setting");
        this.setSize(500, 310);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(3);

        this.setLayout(null);

        JLabel instruct = new JLabel("<html><body>YOU CAN SET THE CHESSBOARD SIZE ACCORDING TO <br>" +
                "YOUR PREFERENCE ~</body></html>");
        instruct.setBounds(100, 10, 400, 50);
        this.add(instruct);


        JLabel JWidth = new JLabel();
        JWidth.setText("<html><body>Grid numbers of length :<br>(no larger than 30)</body></html>");
        JWidth.setBounds(60, 65, 200, 40);
        this.add(JWidth);

        JLabel JHeight = new JLabel();
        JHeight.setText("<html><body>Grid numbers of width :<br>(no larger than 24)</body></html>");
        JHeight.setBounds(60, 125, 200, 40);
        this.add(JHeight);

        JLabel JMineNumbers = new JLabel();
        JMineNumbers.setText("<html><body>Total mines number :<br>(NO MORE THAN 1/2 OF GRIDS)</body></html>");
        JMineNumbers.setBounds(60, 170, 200, 50);
        this.add(JMineNumbers);

        //input textField
        JTextField input1 = new JTextField();
        input1.setBounds(300, 75, 150, 30);
        this.add(input1);

        JTextField input2 = new JTextField();
        input2.setBounds(300, 130, 150, 30);
        this.add(input2);

        JTextField input3 = new JTextField();
        input3.setBounds(300, 180, 150, 30);
        this.add(input3);

        JButton confirm = new JButton("Confirm");
        confirm.setBounds(330, 230, 80, 30);
        this.add(confirm);


//        listener of confirm button
        confirm.addActionListener(e -> {

            if (Integer.parseInt(input1.getText()) > 30 || Integer.parseInt(input1.getText()) <= 0 ||
                    Integer.parseInt(input2.getText()) > 24 || Integer.parseInt(input2.getText()) <= 0 ||
                    Integer.parseInt(input3.getText()) >
                            (Integer.parseInt(input1.getText()) * Integer.parseInt(input2.getText())) / 2
                    || Integer.parseInt(input2.getText()) <= 0) {
                System.out.println("INPUT ERROR!");
                JOptionPane.showMessageDialog(input1, "YOUR INPUT IS NOT LEGAL.\n  PLEASE TRY AGAIN!",
                        "Input Error", JOptionPane.WARNING_MESSAGE);

                dispose();
                LevelFrame levelFrame = new LevelFrame();
                levelFrame.setVisible(true);
            } else {
                definedLength = Integer.parseInt(input1.getText());
                definedWidth = Integer.parseInt(input2.getText());
                definedMineNumbers = Integer.parseInt(input3.getText());

                System.out.printf("Length(%d)  Width(%d)  MineNumbers(%d)",definedLength,definedWidth,definedMineNumbers);
                dispose();


                ThirdFrame thirdFrame = new ThirdFrame(2);
                thirdFrame.setVisible(true);
            }
        });
    }

    public static int getDefinedLength() {
        return definedLength;
    }

    public static int getDefinedWidth() {
        return definedWidth;
    }

    public static int getDefinedMineNumbers() {
        return definedMineNumbers;
    }

}
