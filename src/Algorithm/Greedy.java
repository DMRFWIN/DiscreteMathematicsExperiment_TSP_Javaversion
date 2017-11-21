package Algorithm;

import Bean.RoadBean;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Greedy {


    private Map<RoadBean, Double> roadBeanst;

    private int cityNum; // 城市数量
    private int[][] distance; // 距离矩阵
    private Double[][] distance2;

    private int[] colable;//代表列，也表示是否走过，走过置0
    private int[] row;//代表行，选过置0




    public Map<String, String> GetMinRoadByTx(Map<String, Integer> roadBeans, String str_num) {

        Map<String, String> result = new HashMap<>();

        InitData(roadBeans,str_num);

        int[] temp = new int[cityNum];
        int[] path =new int[cityNum+1];
        int path_num=0;
        path[path_num++]=1;


        int s = 0;//计算距离
        int i = 0;//当前节点
        int j = 0;//下一个节点
        //默认从0开始
        while (row[i] == 1) {
            //复制一行
            for (int k = 0; k < cityNum; k++) {
                temp[k] = distance[i][k];
                //System.out.print(temp[k]+" ");
            }
            //System.out.println();
            //选择下一个节点，要求不是已经走过，并且与i不同
            j = selectmin(temp);
            //找出下一节点
            row[i] = 0;//行置0，表示已经选过
            colable[j] = 0;//列0，表示已经走过

            path[path_num++]=j+1;
            //System.out.println(i + "-->" + j);
            //System.out.println(distance[i][j]);
            if (distance[i][j] == 0) {
                s += distance[j][i];
            } else {
                s += distance[i][j];
            }

            i = j;//当前节点指向下一节点
        }

        result.put("result_road", Arrays.toString(path));
        result.put("result_value", String.valueOf(s));

        return result;
    }


    public int selectmin(int[] p) {
        int j = 0, m = p[0], k = 0;
        //寻找第一个可用节点，注意最后一次寻找，没有可用节点
        while (colable[j] == 0) {
            j++;
            //System.out.print(j+" ");
            if (j >= cityNum) {
                //没有可用节点，说明已结束，最后一次为 *-->0
                m = p[0];
                break;
                //或者直接return 0;
            } else {
                m = p[j];
            }
        }
        //从可用节点J开始往后扫描，找出距离最小节点
        for (; j < cityNum; j++) {
            if (colable[j] == 1) {
                if (m >= p[j]) {
                    m = p[j];
                    k = j;
                }
            }
        }
        return k;
    }


    private void InitData(Map<String, Integer> roadBeans, String str_num) {

        cityNum = str_num.length();

        distance = new int[cityNum][cityNum];

        colable = new int[cityNum];
        colable[0] = 0;
        for (int i = 1; i < cityNum; i++) {
            colable[i] = 1;
        }

        row = new int[cityNum];
        for (int i = 0; i < cityNum; i++) {
            row[i] = 1;
        }

        for (Map.Entry<String, Integer> entry : roadBeans.entrySet()) {

            String s = entry.getKey();
            String[] split = s.split(",");
            int a = Integer.parseInt(split[0]);
            int b = Integer.parseInt(split[1]);

            int length = entry.getValue();

            a--;
            b--;

            distance[a][b] = length;
        }
    }

    public Map<String,String> GetMinRoadByTx(Map<String, Double> stringDoubleMap, int citynum) {
        Map<String, String> result = new HashMap<>();
        this.cityNum = citynum;
        InitData(stringDoubleMap);


        Double[] temp = new Double[cityNum];

        for (int i=0;i<cityNum;i++){
            temp[i]=0.0;
        }
        int[] path =new int[cityNum+1];
        int path_num=0;
        path[path_num++]=1;


        Double s = 0.0;//计算距离
        int i = 0;//当前节点
        int j = 0;//下一个节点
        //默认从0开始
        while (row[i] == 1) {
            //复制一行
            for (int k = 0; k < cityNum; k++) {
                temp[k] = distance2[i][k];
                //System.out.print(temp[k]+" ");
            }
            //System.out.println();
            //选择下一个节点，要求不是已经走过，并且与i不同
            j = selectmin(temp);
            //找出下一节点
            row[i] = 0;//行置0，表示已经选过
            colable[j] = 0;//列0，表示已经走过

            path[path_num++]=j+1;
            //System.out.println(i + "-->" + j);
            //System.out.println(distance[i][j]);
            if (distance2[i][j] == 0) {
                s += distance2[j][i];
            } else {
                s += distance2[i][j];
            }

            i = j;//当前节点指向下一节点
        }

        result.put("result_road", Arrays.toString(path));
        result.put("result_value", String.valueOf(s));

        return result;

    }

    private int selectmin(Double[] p) {
        int j = 0, k = 0;
        Double  m = p[0];
        //寻找第一个可用节点，注意最后一次寻找，没有可用节点
        while (colable[j] == 0) {
            j++;
            //System.out.print(j+" ");
            if (j >= cityNum) {
                //没有可用节点，说明已结束，最后一次为 *-->0
                m = p[0];
                break;
                //或者直接return 0;
            } else {
                m = p[j];
            }
        }
        //从可用节点J开始往后扫描，找出距离最小节点
        for (; j < cityNum; j++) {
            if (colable[j] == 1) {
                if (m >= p[j]) {
                    m = p[j];
                    k = j;
                }
            }
        }
        return k;
    }

    private void InitData(Map<String, Double> stringDoubleMap) {


        distance2 = new Double[cityNum][cityNum];

        colable = new int[cityNum];
        colable[0] = 0;
        for (int i = 1; i < cityNum; i++) {
            colable[i] = 1;
        }

        row = new int[cityNum];
        for (int i = 0; i < cityNum; i++) {
            row[i] = 1;
        }

        for (Map.Entry<String, Double> entry : stringDoubleMap.entrySet()) {

            String s = entry.getKey();
            String[] split = s.split(",");
            int a = Integer.parseInt(split[0]);
            int b = Integer.parseInt(split[1]);

            Double length = entry.getValue();
            a--;
            b--;


            distance2[a][b] = length;
        }

    }
}
