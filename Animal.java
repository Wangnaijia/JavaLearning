
/*
 * @Author: Wang Naijia
 * @Date: 2021-06-21 12:38:15
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-06-21 13:05:50
 * @Descripttion: 
 */
public class Animal {
    private String name;
    private int id;
    // 已经定义了自动的构造函数，编译器不会创建默认的构造函数
    // public Animal(String myName, int myid){
    //     name = myName;
    //     id = myid;
    // }
    public void eat(){
        System.out.println(name + "正在吃");
    }
    public void sleep(){
        System.out.println(name + "正在睡");
    }
    public void introduction(){
        System.out.println("大家好我是"+ id +"号" + name +".");
    }
}
// Animal类作为一个父类，老鼠类和企鹅类可以继承这个类，使代码更简洁，提高代码的复用性
/**由于父类的构造函数是有参的，所以编译不会为你自动调用默认的构造函数，此时子类在自己的构造函数中必须显示地调用父类的构造函数 */
class Mouse extends Animal{
    // public Mouse(String myName, int myid){ // 子类的构造函数
    //     super(myName, myid); // 显示调用父类的构造函数，且必须是第一行调用
    // }
    
    public void eat(){
        System.out.println("dog: eat");
    }

    public void eatTest(){
        this.eat();
        super.eat();
    }
}

class Test {
    public static void main(String[] args) {
        Animal a = new Animal();
        a.eat();
        Mouse d = new Mouse();
        d.eatTest();
    }
    
}
