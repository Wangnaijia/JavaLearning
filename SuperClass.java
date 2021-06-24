/*
 * @Author: Wang Naijia
 * @Date: 2021-06-21 15:43:42
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-06-21 16:31:10
 * @Descripttion: 
 */

class SuperClass {
    private int n;
    SuperClass(){
        System.out.println("SuperClass()");
    }
    SuperClass(int n){
        System.out.println("SuperClass(int n)");
        this.n = n;
    }
}
// SubClass类继承
class SubClass extends SuperClass{
    private int n;
    
    SubClass(){ // 自动调用父类的无参数构造器
        System.out.println("SubClass");
    }

    public SubClass(int n){
        super(300);
        System.out.println("SubClass(int n)" + n);
        this.n = n;
    }
}

// SubClass2类继承
class SubClass2 extends SuperClass{
    private int n;

    SubClass2(){
        super(400); // 调用父类中带有参数的构造器
        System.out.println("SubClass2");
    }

    public SubClass2(int n){
        System.out.println("SubClass2(int n):" + n);
        this.n = n;
    }
}

class TestSuperSub {
    public static void main (String args[]){
        System.out.println("--------SubClass类继承---------");
        SubClass sc1 = new SubClass();
        SubClass sc2 = new SubClass(100);
        System.out.println("--------SubClass2类继承--------");
        SubClass2 sc3 = new SubClass2();
        SubClass2 sc4 = new SubClass2(200);
    }
    
}
