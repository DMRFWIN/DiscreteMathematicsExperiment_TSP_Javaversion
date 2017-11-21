package Manager;

import Bean.RoadBean;
import JFrame.ChooseJFrame;
import JFrame.TSPJFrame;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.Arrays;

public class JFrameManager {

    public static void main(String[] args) {

        /*TSPJFrame tspjFrame = new TSPJFrame();
        tspjFrame.setVisible(true);
        */


        ChooseJFrame chooseJFrame = new ChooseJFrame();
        chooseJFrame.setVisible(true);

        //int[] a = {1, 3, 4, 5};
        //String a = "asdff";
        //a = a.substring(1, a.length()-1);
        // String b=Arrays.toString(a);
        // b=b.substring(1,b.length()-1);

        // String[] split = b.split(",");
        // System.out.println(split[0]+" "+split[1]);
     /*   RoadBean a=new RoadBean("1","2");
        RoadBean b=new RoadBean("1","2");

        System.out.println(a);
        System.out.println(b);*/
/*
        Double a[][] = new Double[100][100];
        System.out.println(a[0][0]+"s");*/
//System.out.println(Arrays.toString(a));
    }
}
