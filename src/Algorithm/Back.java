package Algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Back {
    private int distance[][];
    private Double distance2[][];
    private int x[];
    private int b[];
    private int cl = 0;
    private int k = 10000;

    private Double cl2 = 0.0;
    private Double k2 = 10000.0;

    private int cityNum;

    public Back() {
    }

    public Map<String, String> GetMinRoadByBack(Map<String, Integer> roadBeans, String str_num) {
        InitData(roadBeans, str_num);

        int i;

        Traveling(2);

        b[cityNum] = b[0];

        Map<String, String> result = new HashMap<>();
        result.put("result_road", Arrays.toString(b));
        result.put("result_value", String.valueOf(k));

        return result;
    }

    private void Traveling(int t) {
        int j;
        if (t > cityNum) {
            if (distance[x[cityNum]][1] != -1 && (cl + distance[x[cityNum]][1] < k)) {
                for (j = 1; j <= cityNum; j++)
                    b[j - 1] = x[j];
                k = cl + distance[x[cityNum]][1];
            }
        } else {
            for (j = t; j <= cityNum; j++) {
                if (distance[x[t - 1]][x[j]] != -1 && (cl + distance[x[t - 1]][x[j]] < k)) {

                    int p = x[t];
                    x[t] = x[j];
                    x[j] = p;

                    cl += distance[x[t - 1]][x[t]];
                    Traveling(t + 1);
                    cl -= distance[x[t - 1]][x[t]];

                    p = x[t];
                    x[t] = x[j];
                    x[j] = p;
                }
            }
        }
    }


    private void InitData(Map<String, Integer> roadBeans, String str_num) {

        cityNum = str_num.length();

        distance = new int[cityNum + 1][cityNum + 1];

        x = new int[cityNum + 1];
        b = new int[cityNum + 1];

        for (int i = 1; i <= cityNum; i++) {
            x[i] = i;
            b[i] = 0;
        }


        for (Map.Entry<String, Integer> entry : roadBeans.entrySet()) {

            String s = entry.getKey();
            String[] split = s.split(",");
            int a = Integer.parseInt(split[0]);
            int b = Integer.parseInt(split[1]);

            int length = entry.getValue();


            distance[a][b] = length;
        }
    }

    public Map<String, String> GetMinRoadByBack(Map<String, Double> stringDoubleMap, int citynum) {
        this.cityNum = citynum;
        InitData(stringDoubleMap);


        int i;

        Traveling(2);

        b[cityNum] = b[0];

        Map<String, String> result = new HashMap<>();
        result.put("result_road", Arrays.toString(b));
        result.put("result_value", String.valueOf(k2));

        return result;
    }


    private void Traveling2(int t) {
        int j;
        if (t > cityNum) {
            if (distance2[x[cityNum]][1] != -1 && (cl2 + distance2[x[cityNum]][1] < k2)) {
                for (j = 1; j <= cityNum; j++)
                    b[j - 1] = x[j];
                k2 = cl2 + distance2[x[cityNum]][1];
            }
        } else {
            for (j = t; j <= cityNum; j++) {
                if (distance2[x[t - 1]][x[j]] != -1 && (cl2 + distance2[x[t - 1]][x[j]] < k2)) {

                    int p = x[t];
                    x[t] = x[j];
                    x[j] = p;

                    cl2 += distance2[x[t - 1]][x[t]];
                    System.out.println("t current:" + t);
                    Traveling2(t + 1);
                    cl2 -= distance2[x[t - 1]][x[t]];

                    p = x[t];
                    x[t] = x[j];
                    x[j] = p;
                }
            }
        }
    }

    private void InitData(Map<String, Double> stringDoubleMap) {

        /*distance2 = new Double[cityNum + 1][cityNum + 1];

        x = new int[cityNum + 1];
        b = new int[cityNum + 1];

        for (int i = 1; i <= cityNum; i++) {
            x[i] = i;
            b[i] = 0;
        }


        for (Map.Entry<String, Double> entry : stringDoubleMap.entrySet()) {

            String s = entry.getKey();
            String[] split = s.split(",");
            int a = Integer.parseInt(split[0]);
            int b = Integer.parseInt(split[1]);

            Double length = entry.getValue();


            distance2[a][b] = length;
        }*/

        distance = new int[cityNum + 1][cityNum + 1];

        x = new int[cityNum + 1];
        b = new int[cityNum + 1];

        for (int i = 1; i <= cityNum; i++) {
            x[i] = i;
            b[i] = 0;
        }


        for (Map.Entry<String, Double> entry : stringDoubleMap.entrySet()) {

            String s = entry.getKey();
            String[] split = s.split(",");
            int a = Integer.parseInt(split[0]);
            int b = Integer.parseInt(split[1]);

            Double l = entry.getValue();
            String l_s = l.toString();
            l_s = l_s.substring(0, l_s.indexOf("."));
            int length = Integer.parseInt(l_s);


            distance[a][b] = length;
        }
    }
}
