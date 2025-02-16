package Inhertance;

public class Main {
    public static void main(String[] args) {
    Dog dog = new Dog();
    Cat cat = new Cat();
    VodoFoneDog voda = new VodoFoneDog();
    cat.eat();
    dog.bark();
    cat.bark();
    voda.bark();

    }
}
class Animal{
    void eat(){
        System.out.println("Chow Chow...");
    }
}
class Dog extends  Animal{
    void bark(){
        System.out.println("Bho Bho...");
    }
}
class Cat extends Animal{
    void bark(){
        System.out.println("Miyaw Miyaw...");
    }
}
class VodoFoneDog extends Dog{
    void bark(){
        System.out.println("Miyaw Bho...");
    }
}