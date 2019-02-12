package Bean;

public class LengthBean {
    private int a;
    private int b;
    private Double length;

    public LengthBean(int a, int b, Double length) {
        this.a = a;
        this.b = b;
        this.length = length;
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

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }
}
