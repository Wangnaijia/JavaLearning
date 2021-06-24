/*
 * @Author: Wang Naijia
 * @Date: 2021-06-20 21:00:01
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-06-20 21:03:42
 * @Descripttion: 
 */
import java.io.File;
public class DeleteFileDemo {
    public static void main(String[] args) {
        File folder = new File("D:\\Myfiles\\JavaLearning\\test");
        deleteFolder(folder);
    }

    // 删除文件及目录
    public static void deleteFolder(File folder){
        File[] files = folder.listFiles();
        if(files != null){
            for (File f: files){
                if(f.isDirectory()){
                    System.out.println("delete folder:" + f);
                    deleteFolder(f);
                } else {
                    System.out.println("delete file:" + f);
                    f.delete();
                }
            }
        }
        folder.delete();
    }
}

