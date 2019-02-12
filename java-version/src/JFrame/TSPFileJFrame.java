package JFrame;

import Algorithm.Greedy;
import Utils.DataUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class TSPFileJFrame extends JFrame {

    private JTable t_input;
    private JTable t_output;

    private JLabel l_result;
    private JButton b_output;

    private DefaultTableModel model1 = null;
    private DefaultTableModel model = null;


    private JButton b_file_chooser;

    private JFileChooser jfc = new JFileChooser(new File("."));

    private JPopupMenu jpm;
    private JMenuItem Delele;

    private JPopupMenu jpm1;


    private Map<String, Double> roadlist;

    private int citynum;


    public TSPFileJFrame() throws HeadlessException {

        super();
        InitData();
        InitView();
        InitListener();

    }

    private void InitData() {
        roadlist = new HashMap<>();
        citynum = 0;
    }


    private void InitView() {

        setTitle("TSP问题求解");
        setSize(1000, 800);
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

        String[][] datas = {};
        String[] titles = {"城市A", "城市B", "距离"};

        model = new DefaultTableModel(datas, titles);

        t_input = new JTable(model);
        t_input.setRowHeight(20);

        JScrollPane jScrollPane = new JScrollPane(t_input);
        jScrollPane.setBounds(0, 0, 490, 600);


        add(jScrollPane);

        String output_tiltle = "输出结果：";


        String[][] datas1 = {};
        String[] titles1 = {"结果路线（贪心法）"};

        model1 = new DefaultTableModel(datas1, titles1);

        t_output = new JTable(model1);
        t_output.setRowHeight(20);

        JScrollPane jScrollPane1 = new JScrollPane(t_output);
        jScrollPane1.setBounds(510, 0, 490, 600);

        add(jScrollPane1);


        b_file_chooser = new JButton("从本地文件导入数据");
        b_file_chooser.setBounds(155, 650, 180, 30);
        add(b_file_chooser);


        b_output = new JButton("计算");
        b_output.setBounds(665, 650, 180, 30);

        add(b_output);

         l_result = new JLabel("总路线长度：0.0" );
        l_result.setBounds(510,610,300,30);
        add(l_result);

    }

    private void InitListener() {


        b_output.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Greedy greedy = new Greedy();
                Map<String, String> result = greedy.GetMinRoadByTx(roadlist, citynum);

                String result_road = result.get("result_road");
                String result_value = result.get("result_value");

                result_road = result_road.substring(1, result_road.length() - 1);
                String[] split = result_road.split(",");

               l_result.setText("总路线长度："+result_value);

                for (int i = 0; i < split.length; i++) {
                    model1.addRow(new String[]{split[i]});
                }


            }
        });

        b_file_chooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //打开文件选择器对话框
                int status = jfc.showOpenDialog(getContentPane());
                //没有选打开按钮结果提示
                if (status != JFileChooser.APPROVE_OPTION) {
                    JOptionPane.showMessageDialog(null, "没有文件被选中！");
                } else {


                    //被选中的文件保存为文件对象
                    File file = jfc.getSelectedFile();
                    try {
                        TSPfromFile(file);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

    }

    private void TSPfromFile(File file) throws IOException {
        DataUtils dataUtils = new DataUtils();
        Map<String, Double> stringDoubleMap = dataUtils.GetData(file);
        roadlist = stringDoubleMap;

        File_TSP_InitTable(stringDoubleMap);

    }

    private void File_TSP_InitTable(Map<String, Double> stringDoubleMap) {

        DecimalFormat dcmFmt = new DecimalFormat("0.00");

        Double num = stringDoubleMap.get("city_num");
        stringDoubleMap.remove("city_num");

        String s_num = num.toString();
        s_num = s_num.substring(0, s_num.indexOf("."));

        citynum = Integer.parseInt(s_num);

        for (Map.Entry<String, Double> entry : stringDoubleMap.entrySet()) {

            String s = entry.getKey();
            String[] split = s.split(",");
            int a = Integer.parseInt(split[0]);
            int b = Integer.parseInt(split[1]);

            Double length = Double.valueOf(dcmFmt.format(entry.getValue()));

            model.addRow(new String[]{split[0], split[1], dcmFmt.format(entry.getValue())});

        }


    }


}
