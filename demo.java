/*
 * @Author: Wang Naijia
 * @Date: 2021-06-08 19:47:56
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-06-19 16:10:41
 * @Descripttion: 
 */

// import java.util.*;
// import java.text.*;
  
// class DateDemo {
//     public static void main(String args[]) {
//         // 初始化 Date 对象
//         Date date = new Date();
//         SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//         // 使用 toString() 函数显示日期时间
//         System.out.println("当前时间为："+ft.format(date));
//    }
// }

// 使用BufferedReader在控制台读取字符
import java.io.*;

class BRRead{
    public static void main(String[] args) throws IOException{
        char c;
        //使用System.in创建BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("输入字符，按下'q'键退出");
        // 读取字符
        do{
            c = (char) br.read();
            System.out.println(c);
        } while(c!='q');
    }
}