import java.util.*;

/*
 * @Author: Wang Naijia
 * @Date: 2021-08-21 10:00:56
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-08-21 11:19:19
 * @Descripttion: 
 */
class Solution443{
    public static void main(String[] args) {
        char[] chars = {'a','b','b','b','c','c','c'};
        Solution443 solution = new Solution443();
        System.out.println(solution.compress(chars));
    }
    /**
     * @description: 用读写指针
     * @param {char[]} chars
     * @return {*}
     */    
    public int compress(char[] chars){
        int write = 0, read = 0;
        while(read < chars.length){
            // 连续读了多少个
            int readStart = read;
            while(read+1 < chars.length && chars[read+1] == chars[read]){
                read++;
            }
            chars[write++] = chars[readStart];
            // 数量大于1时才需要记录数量
            if(readStart != read){
                int count = read - readStart + 1;
                int writeStart = write;
                // 先写个位
                while(count != 0){
                    chars[write++] = (char)(count % 10 + '0');
                    count /= 10;
                }
                // 需要按高位到低位来写，所以要倒一下
                reverse(chars, writeStart, write - 1);
            }
            // 读指针移动到下一个字符的位置
            read++;
        }
        // 返回写指针的位置
        return write;
    }
    private void reverse(char[] chars, int left, int right){
        while(left < right){
            char t = chars[right];
            chars[right] = chars[left];
            chars[left] = t;
            left++;
            right--;
        }
    }
    /**
     * @description: 题目要求只能原地修改，所以不满足要求
     * @param {char[]} chars
     * @return {*}
     */    
    public int mycompress(char[] chars){
        Map<Character,Integer> map = new HashMap<>();
        int n = chars.length;
        StringBuilder sb = new StringBuilder();
        for(int i=0; i < n; i++){
            map.put(chars[i], map.getOrDefault(chars[i], 0) + 1);
        }
        int len = map.keySet().size(), m = 0;
        for(char key : map.keySet()){
            sb.append(key);
            m = map.get(key); // 该字母对应的数值
            if(m == 1) continue;
            while(m != 0){
                m /= 10;
                len++; // 每当m有值n就+1
            }
            String nums = String.valueOf(len);
            sb.append(nums);
        }
        return sb.length();
    }
}