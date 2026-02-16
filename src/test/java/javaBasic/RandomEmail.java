package javaBasic;

import java.util.Random;

public class RandomEmail {
    public static void main(String[] args) {
        Random rand = new Random();
        System.out.println(rand.nextBoolean());
        System.out.println(rand.nextDouble());
        System.out.println(rand.nextFloat());
        System.out.println(rand.nextInt(99));
        System.out.println(rand.nextInt(999));
        System.out.println(rand.nextInt(9999));
        System.out.println(rand.nextInt(99999));
        System.out.println(rand.nextLong());
    }
}
