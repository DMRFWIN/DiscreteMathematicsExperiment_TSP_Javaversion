package JFrame;

import Algorithm.Back;
import Algorithm.Greedy;
import Bean.RoadBean;
import Utils.DataUtils;
import com.sun.java.swing.plaf.gtk.GTKConstants;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Position;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.List;

public class TSPJFrame extends JFrame {


    /*private JLabel l_howtouse;
    private JLabel l_whatcando;
    */

    private JLabel l_start;
    private JLabel l_end;
    private JLabel l_length;
    private JLabel l_input_title;
    private JLabel l_output_tiltle;


    private JTable t_input;
    private DefaultTableModel model = null;

    private JTable t_output;
    private DefaultTableModel model1 = null;


    private JTextField t_start;
    private JTextField t_end;
    private JTextField t_length;

    private JButton b_add;
    private JButton b_output;
    private JButton b_file_chooser;

    private JFileChooser jfc = new JFileChooser(new File("."));

    private JPopupMenu jpm;
    private JMenuItem Delele;

    private JPopupMenu jpm1;


    private Map<String, Integer> roadBeans;

    private String str_num = "";

    public TSPJFrame() throws HeadlessException {

        super();
        InitData();
        InitView();
        InitListener();

    }

    private void InitData() {
        roadBeans = new HashMap<>();
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

        b_file_chooser = new JButton("从本地文件导入数据");
        b_file_chooser.setBounds(110, 50, 200, 30);
        add(b_file_chooser);

        l_start = new JLabel("城市A：");
        l_start.setBounds(100, 100, 50, 30);

        t_start = new JTextField();
        t_start.setBounds(150, 100, 40, 30);

        add(l_start);
        add(t_start);

        l_end = new JLabel("城市B：");
        l_end.setBounds(220, 100, 50, 30);

        t_end = new JTextField();
        t_end.setBounds(270, 100, 40, 30);

        add(l_end);
        add(t_end);


        l_length = new JLabel("距离：");
        l_length.setBounds(340, 100, 50, 30);

        t_length = new JTextField();
        t_length.setBounds(390, 100, 40, 30);

        add(l_length);
        add(t_length);

        b_add = new JButton("添加");
        b_add.setBounds(460, 100, 60, 30);

        add(b_add);


        b_output = new JButton("计算");
        b_output.setBounds(550, 100, 60, 30);

        add(b_output);

        String input_title = "输入数据：";

        l_input_title = new JLabel(input_title);
        l_input_title.setBounds(0, 150, 100, 30);

        String[][] datas = {};
        String[] titles = {"城市A", "城市B", "距离"};

        model = new DefaultTableModel(datas, titles);

        t_input = new JTable(model);
        t_input.setRowHeight(20);

        JScrollPane jScrollPane = new JScrollPane(t_input);
        jScrollPane.setBounds(0, 185, 490, 650);


        add(jScrollPane);
        add(l_input_title);

        String output_tiltle = "输出结果：";
        l_output_tiltle = new JLabel(output_tiltle);
        l_output_tiltle.setBounds(510, 150, 100, 30);

        add(l_output_tiltle);

        String[][] datas1 = {};
        String[] titles1 = {"贪心法", "回溯法"};

        model1 = new DefaultTableModel(datas1, titles1);

        t_output = new JTable(model1);
        t_output.setRowHeight(20);

        JScrollPane jScrollPane1 = new JScrollPane(t_output);
        jScrollPane1.setBounds(510, 185, 490, 650);

        //model1.addRow(new String[]{"1", "2"});
        add(jScrollPane1);


    }

    private void InitListener() {
        t_input.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                if (e.getClickCount() == MouseEvent.BUTTON2) {
                    int i = t_input.rowAtPoint(e.getPoint());

                    jpm = new JPopupMenu();

                    Delele = new JMenuItem("删除该条路线" + i);
                    jpm.add(Delele);

                    int x = e.getX();
                    int y = e.getY();

                    jpm.show(t_input, e.getX(), e.getY());

                } else if (e.getClickCount() == MouseEvent.BUTTON1) {

                }
            }
        });

        b_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String a = t_start.getText().trim();
                String b = t_end.getText().trim();
                String l = t_length.getText().trim();

                if (a.equals("") || b.equals("")) {
                    JOptionPane.showMessageDialog(null, "城市名不可为空！");
                } else if (l.equals("")) {
                    JOptionPane.showMessageDialog(null, "城市之间距离不能为空！");
                } else {
                    GetData(a, b, l);
                }

            }
        });


        b_output.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Greedy greedy = new Greedy();
                Map<String, String> result = greedy.GetMinRoadByTx(roadBeans, str_num);

                String result_road = result.get("result_road");
                String result_value = result.get("result_value");

                result_road = result_road.substring(1, result_road.length() - 1);
                String[] split = result_road.split(",");


                Back back = new Back();
                Map<String, String> result2 = back.GetMinRoadByBack(roadBeans, str_num);

                String result_road2 = result2.get("result_road");
                String result_value2 = result2.get("result_value");

                result_road2 = result_road2.substring(1, result_road2.length() - 1);
                String[] split2 = result_road2.split(",");

                for (int i = 0; i < split.length; i++) {
                    model1.addRow(new String[]{split[i], split2[i]});
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
        File_TSP_InitTable(stringDoubleMap);

    }

    private void File_TSP_InitTable(Map<String, Double> stringDoubleMap) {

        DecimalFormat dcmFmt = new DecimalFormat("0.00");

        Double num = stringDoubleMap.get("city_num");
        stringDoubleMap.remove("city_num");

        String s_num = num.toString();
        s_num=s_num.substring(0,s_num.indexOf("."));

        int citynum = Integer.parseInt(s_num);

        for (Map.Entry<String, Double> entry : stringDoubleMap.entrySet()) {

            String s = entry.getKey();
            String[] split = s.split(",");
            int a = Integer.parseInt(split[0]);
            int b = Integer.parseInt(split[1]);

            Double length = Double.valueOf(dcmFmt.format(entry.getValue()));

            model.addRow(new String[]{split[0], split[1], dcmFmt.format(entry.getValue())});

        }

        Greedy greedy = new Greedy();
        Map<String, String> result = greedy.GetMinRoadByTx(stringDoubleMap, citynum);

        String result_road = result.get("result_road");
        String result_value = result.get("result_value");

        result_road = result_road.substring(1, result_road.length() - 1);
        String[] split = result_road.split(",");


       /* Back back = new Back();
        Map<String, String> result2 = back.GetMinRoadByBack(stringDoubleMap, citynum);

        String result_road2 = result2.get("result_road");
        String result_value2 = result2.get("result_value");

        result_road2 = result_road2.substring(1, result_road2.length() - 1);
        String[] split2 = result_road2.split(",");
*/
        for (int i = 0; i < split.length; i++) {
            model1.addRow(new String[]{split[i]});
        }


    }

    private void GetData(String a, String b, String l) {
        Integer v = Integer.parseInt(l);
        if (v <= 0) {
            JOptionPane.showMessageDialog(null, "城市距离不合法！");
        } else {
            t_start.setText("");
            t_end.setText("");
            t_length.setText("");
            RoadBean roadBean = new RoadBean(a, b);
            if (roadBeans.size() == 0) {
                roadBeans.put(roadBean.toString(), v);//实际存储的时候将a和b排序进行存储
                model.addRow(new String[]{a, b, l});//显示给用户的时候按照用户的输入顺序来显示

            } else if (roadBeans.get(roadBean.toString()) == null) {
                if (!str_num.contains(a)) {
                    str_num += a;
                }

                if (!str_num.contains(b)) {
                    str_num += b;
                }

                roadBeans.put(roadBean.toString(), v);//实际存储的时候将a和b排序进行存储
                model.addRow(new String[]{a, b, l});//显示给用户的时候按照用户的输入顺序来显示
            } else {
                roadBeans.remove(roadBeans.toString());
                roadBeans.put(roadBean.toString(), v);
            }


        }
    }

}
