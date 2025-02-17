package Interfaces;

public class Interfaces {
    public static void main(String[] args) {
        SmartPhone realme = new SmartPhone();
        SmartPhone boat = new SmartWatch();
        Mobile test = new Test();
        realme.makeCall();
        boat.makeCall();
        test.makeCall();
    }
}

interface Mobile{
    void makeCall();
}
class Phone implements Mobile{
    @Override
    public void makeCall() {
        System.out.println("Calling >>>>...<<<<");
    }
}
class SmartPhone extends Phone{
    @Override
    public void makeCall() {
//        super.makeCall();
        System.out.println("Network Failed...");
    }
}
class SmartWatch extends SmartPhone{
    @Override
    public void makeCall() {
//        super.makeCall();
        System.out.println("He is Busy :-{");
    }
}
class Test implements Mobile{
    @Override
    public void makeCall() {
        System.out.println("test");
    }
}
//class Test2 implements Mobile{
//    @Override
//    public void makeCall() {
//
//    }
//}