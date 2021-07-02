package demo;

public class P1 {

    public static void main(String[] args) {
        System.out.println("hello word !");
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
