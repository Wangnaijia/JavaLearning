/*
 * @Author: Wang Naijia
 * @Date: 2021-06-20 20:49:43
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-06-20 20:51:38
 * @Descripttion: 
 */
import java.io.File;
 
public class CreateDir {
    public static void main(String[] args) {
        String dirname = "..\\dirs";
        File d = new File(dirname);
        // 现在创建目录
        d.mkdirs();
    }
}

