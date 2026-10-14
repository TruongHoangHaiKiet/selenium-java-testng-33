package javaGeneric;

import java.util.ArrayList;

public class Person {
    public static void main(String[] args) {
        // non-generic
        ArrayList students = new ArrayList();
        students.add("Tran Van Nam");
        students.add(30);
        students.add(true);
        students.add(9.8f);

        // Validate => FAIL/ ERROR

        // Generic
        ArrayList<String> studentName = new ArrayList<String>();
        studentName.add("Tran Van Nam");
//        studentName.add(30);
//        studentName.add(true);
//        studentName.add(9.8f);
    }
}
