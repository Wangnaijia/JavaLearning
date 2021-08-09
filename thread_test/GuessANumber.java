package thread_test;
/*
 * @Author: Wang Naijia
 * @Date: 2021-07-18 10:36:44
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-07-18 10:39:37
 * @Descripttion: 通过继承Thread类创建线程
 */
public class GuessANumber extends Thread{
    private int number;
    public GuessANumber(int number){
        this.number = number;
    }

    public void run(){
        int counter = 0;
        int guess = 0;
        do{
            guess = (int) (Math.random()*100 + 1);
            System.out.println(this.getName() + "guesses" + guess);
            counter++; 
        } while(guess != number);
        System.out.println("** Correct!" + this.getName() + "in" + counter + "guesses.**");
    }
}
