package Loops;

import java.util.ArrayList;

public class WhileAndDoWhile {
    public static void main(String[] args) {
//        while
        int i =0;
        while (4>i){
            System.out.println(i++);
        }


//        do while
        do {
            System.out.println("Do while");
        } while (3>4);


//        ArrayList --> forEach loop
        ArrayList <Integer> array = new ArrayList<>();
        array.add(2);
        array.add(4);
        array.add(6);
        array.add(8);
        for (int b:array){
            System.out.println(b);
            System.out.println(array.get(3));

        }

//    arrays in java
        int[] arr = {2,3,4,5};
        System.out.println(arr);
        for (int x: arr){
            System.out.println(x);
        }
    }
}
