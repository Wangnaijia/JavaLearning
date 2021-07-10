package animals;

public class MammalInt implements Animal {
    public void eat(){
        System.out.println("Mammal eats");
    }

    public void travel(){
        System.out.println("Mammal travels");
    }
    public int noOfLegs(){
        return 0;
    }
    public static void main(String args[]){
        MammalInt m = new MammalInt();
        m.eat();
        m.travel();
    }
    
}
/*
 * @Author: Wang Naijia
 * @Date: 2021-07-01 15:19:50
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-07-01 15:20:30
 * @Descripttion: 
 */
