package Encapsulation;

public class Main {
    public static void main(String[] args) {
        Car car = new Car("red");
        car.getColor();
        car.getSpeed();
        car.setSpeed(20);
        car.getSpeed();
    }
}
class Car{
    private int speed;
    private String color;
    public Car(String color){
        this.color = color;
    }
    public void setSpeed(int speed){
        if (speed>0){
            this.speed = speed;
        }else {
            System.out.println("Speed should be > 0");
        }
    }
    public void getColor(){
        System.out.println("Color of car : "+ this.color);
    }public void getSpeed(){
        System.out.println("Speed of car : "+ this.speed);
    }

}