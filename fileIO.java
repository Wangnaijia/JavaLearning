import java.io.*;

// import jdk.internal.org.jline.utils.InputStreamReader;

class fileIO {
    public static void main(String[] args) throws IOException{
        // 以下代码由于是二进制写入，会存在乱码
        // try{
        //     byte bWrite[] ={11,21,3,40,5};
        //     OutputStream os = new FileOutputStream("test.txt");
        //     for(int x = 0; x < bWrite.length; x++){
        //         os.write(bWrite[x]);  // writes the bytes 
        //     }
        //     os.close();
        //     InputStream is = new FileInputStream("test.txt");
        //     int size = is.available();  //判断文件的可读范围

        //     for (int i = 0; i < size; i++){
        //         System.out.print((char) is.read() + " ");
        //     }
        //     is.close();
        // } catch (IOException e){
        //     System.out.print("Exception");
        // }
        File f = new File("a.txt");
        FileOutputStream fop = new FileOutputStream(f);
        // 构建FileOutputStram 对象，文件不存在会自动创建

        OutputStreamWriter writer = new OutputStreamWriter(fop, "UTF-8");
        // 构建OutputStreamWriter对象，参数可以指定编码，默认为操作系统默认编码，windows上面是gbk
        writer.append("中文输入");
        // 写入到缓冲区
        writer.append("\r\n");
        // 换行
        writer.append("English");
        // 刷新缓冲区，写入到文件
        writer.close();
        // 关闭写入流，同时会把缓冲区内容写入文件
        fop.close();
        // 关闭输出流，释放系统资源
        FileInputStream fip = new FileInputStream(f);
        // 构建FileInputStream对象
        InputStreamReader reader = new InputStreamReader(fip, "UTF-8");
        // 构建InputStreamReader 对象，编码与写入相同

        StringBuffer sb = new StringBuffer();
        while(reader.ready()){
            sb.append((char) reader.read());
            // 转成char加入到StringBuffer对象中
        }
        System.out.println(sb.toString());
        reader.close();
        // 关闭读取流
        fip.close();
        // 关闭输入流，释放系统资源
    }
    
}
/*
 * @Author: Wang Naijia
 * @Date: 2021-06-19 16:24:09
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-06-19 16:25:04
 * @Descripttion: 
 */
