package Bean;

import java.util.HashMap;
import java.util.Map;

public class RoadBean {


    private int a;
    private int b;

    public RoadBean(String a, String b) {
        if (a.compareTo(b) > 0) {
            String c = a;
            a = b;
            b = c;
        }
        this.a = Integer.parseInt(a);
        this.b = Integer.parseInt(b);
    }


    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }



    @Override
    public boolean equals(Object obj) {
        if (obj instanceof RoadBean) {
            RoadBean r = (RoadBean) obj;
            if (r.a == a && r.b == b) return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return a+","+b;
    }
}
