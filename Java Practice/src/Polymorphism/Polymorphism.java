package Polymorphism;

public class Polymorphism {
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        CalculateWithGST calcGST = new CalculateWithGST();
        System.out.println(calc.add(2,3));
        System.out.println(calc.add(2,3, 6));
        System.out.println(calcGST.add(100));
    }
}
class Calculator{
//    Method OverLoading
    int add(int a, int b, int c){
        return a+b+c;
    }
    int add(int a, int b){
        return a+b;
    }
}
class CalculateWithGST extends Calculator {
//    method Overriding
    int add(int a){
        return a + ((a*18)/100);
    }
}