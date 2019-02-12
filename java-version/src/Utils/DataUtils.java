package Utils;

import Bean.LengthBean;
import Bean.PositionBean;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.FileHandler;

public class DataUtils {

    /*
注意将此部分放在单独线程做
 */

    private Map<String, Double> data_map;

    private List<PositionBean> positionBeanList;

    private List<LengthBean> lengthBeanList;

    public DataUtils() {
        data_map = new HashMap<>();
        positionBeanList = new ArrayList<PositionBean>();
        lengthBeanList = new ArrayList<LengthBean>();
    }

    public Map<String, Double> GetData(File file) throws IOException {


        BufferedReader scanner = null;

        scanner = new BufferedReader(new InputStreamReader(new FileInputStream(file), "gbk"));

        String info = "";

        String line = "";

        int num=0;
        while ((line = scanner.readLine()) != null) {
            if (line.equals("EOF")){
                break;
            }
            String[] split = line.split(" ");
            int a[] = new int[3];
            for (int o = 0; o < split.length; o++) {
                a[o] = Integer.parseInt(split[o]);
            }

            PositionBean positionBean = new PositionBean(a[0], a[1], a[2]);
            positionBeanList.add(positionBean);
            num++;
        }

        CalculateLength();

        for (LengthBean lengthBean : lengthBeanList) {
            String key = lengthBean.getA() + "," + lengthBean.getB();
            Double value = lengthBean.getLength();
            data_map.put(key, value);
        }

        data_map.put("city_num", Double.valueOf(num));
        return data_map;

    }

    private void CalculateLength() {
        for (int i = 0; i < positionBeanList.size(); i++) {
            for (int j = 0; j < positionBeanList.size(); j++) {
                Double l = Math.sqrt(Math.pow(positionBeanList.get(i).getX() - positionBeanList.get(j).getX(), 2) + Math.pow(positionBeanList.get(i).getY() - positionBeanList.get(j).getY(), 2));
                LengthBean lengthBean = new LengthBean(positionBeanList.get(i).getId(), positionBeanList.get(j).getId(), l);
                lengthBeanList.add(lengthBean);
            }
        }

    }

}
