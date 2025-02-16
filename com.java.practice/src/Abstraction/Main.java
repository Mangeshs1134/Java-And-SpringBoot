package Abstraction;

public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.sayHello();
        dog.sleep();
        System.out.println(dog.voice);
    }
}
abstract class Animal{
    String voice;
//    constructor
    public Animal(String voice){
        this.voice = voice;
    }
    abstract void sayHello();
    void sleep(){
        System.out.println("Zzzz...");
    }
}
class Dog extends Animal{

    public Dog(){
        super("Bark: Bho Bho..");
    }

    @Override
    void sayHello(){
        System.out.println("Hello Guys...");
    }
}