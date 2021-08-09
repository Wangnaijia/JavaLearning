package thread_test;
/*
 * @Author: Wang Naijia
 * @Date: 2021-07-18 10:34:45
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-07-18 10:36:28
 * @Descripttion: 通过实现Runnable接口创建线程
 */
public class DisplayMessage implements Runnable{
    private String message;
    
    public DisplayMessage(String message){
        this.message = message;
    }

    public void run(){
        while(true){
            System.out.println(message);
        }
    }
}

