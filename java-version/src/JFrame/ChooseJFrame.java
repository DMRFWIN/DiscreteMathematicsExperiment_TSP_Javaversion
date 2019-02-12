package JFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseJFrame extends JFrame {

    private JButton b_ok;
    private JRadioButton randioButton1;
    private JRadioButton randioButton2;
    private ButtonGroup group;


    public ChooseJFrame() throws HeadlessException {
        setTitle("数据源选择");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit toolkit = getToolkit(); // 获得Toolkit对象
        Dimension dimension = toolkit.getScreenSize(); // 获得Dimension对象
        int screenHeight = dimension.height; // 获得屏幕的高度
        int screenWidth = dimension.width; // 获得屏幕的宽度
        int frm_Height = this.getHeight(); // 获得窗体的高度
        int frm_width = this.getWidth(); // 获得窗体的宽度
        setLocation((screenWidth - frm_width) / 2,
                (screenHeight - frm_Height) / 2); // 使用窗体居中显示

        getContentPane().setLayout(null);

        randioButton1 = new JRadioButton("手动输入数据", true);
        randioButton2 = new JRadioButton("从文件中导入数据");
        randioButton1.setBounds(100, 10, 290, 30);
        randioButton2.setBounds(100, 50, 290, 30);
        add(randioButton1);
        add(randioButton2);
        group = new ButtonGroup();
        group.add(randioButton1);
        group.add(randioButton2);


        b_ok = new JButton("确定");
        b_ok.setBounds(120, 90, 60, 30);
        add(b_ok);

        b_ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (randioButton1.isSelected()) {
                    GoTSPFrame();
                } else {
                    GoTSPFileFrame();
                }
            }
        });
    }

    private void GoTSPFileFrame() {
        TSPFileJFrame tspFileJFrame=new TSPFileJFrame();
        tspFileJFrame.setVisible(true);
        this.setVisible(false);
    }

    private void GoTSPFrame() {
        TSPJFrame tspjFrame=new TSPJFrame();
        tspjFrame.setVisible(true);
        this.setVisible(false);
    }


}
