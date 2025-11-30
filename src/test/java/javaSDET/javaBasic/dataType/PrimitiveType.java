package javaSDET.javaBasic.dataType;

public class PrimitiveType {
    public static void main(String[] args) {
        int a = 50;
        int b = a;
        System.out.println(a);
        System.out.println(b);

        a = 100;
        System.out.println(a);
        System.out.println(b);

        System.out.println("===================================");

        Honda honda = new Honda();
        honda.a = 777;

        Huyndai huyndai = new Huyndai();
        huyndai.b = a;

        System.out.println(honda.a);
        System.out.println(huyndai.b);
    }
}
