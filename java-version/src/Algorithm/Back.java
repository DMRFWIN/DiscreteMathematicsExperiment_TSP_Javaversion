package Algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Back {
    private int distance[][];
    private int x[];
    private int b[];
    private int cl = 0;
    private int k = 10000;


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
}
