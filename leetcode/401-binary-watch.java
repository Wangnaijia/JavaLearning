/*
 * @Author: Wang Naijia
 * @Date: 2021-06-21 10:45:22
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-06-21 11:02:33
 * @Descripttion: 
 */
package leetcode;
import java.util.*;

class Solution401 {
    public static void main(String[] args){
        Solution401 solution = new Solution401();
        List<String> result = solution.readBinaryWatch(5);
        System.out.println("result::::"+result);
    }

    public List<String> readBinaryWatch(int turnedOn) {
        List<String> s = new LinkedList<>();
        // 直接遍历0:00 -> 12:00 每个时间有多少个1
        for (int i = 0; i < 12; i++){
            for (int j = 0; j < 60; j++){
                if (count1(i) + count1(j) == turnedOn){
                    s.add(i+":"+(j < 10 ? "0" + j : j));
                }
            }
        }
        return s;
        
    }
    /**
     * @description: 计算二进制中1的个数
     * @param {*}: n
     * @return {*}
     */    
    int count1(int n) {
        int res = 0;
        while (n != 0){
            n = n & (n - 1);
            res ++;
        }
        return res;
    }
    
}
