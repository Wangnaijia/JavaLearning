/*
 * @Author: Wang Naijia
 * @Date: 2021-06-22 10:16:56
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-06-22 12:36:29
 * @Descripttion: 
 */
package leetcode;
import java.util.*;

class SolutionOffer38 {
    int N = 10;
    List<String> list = new ArrayList<>();
    boolean[] vis = new boolean[N];
    
    public static void main(String[] args) {
        String s = "abc";
        SolutionOffer38 solution = new SolutionOffer38();
        String [] res = solution.permutation(s);
        for(String ch : res){
            System.out.println(ch);
        }
    }

    public String[] permutation(String s){
        char[] cs = s.toCharArray();
        Arrays.sort(cs);
        dfs(cs, 0, "");
        String[] res = new String[list.size()];
        int idx = 0;
        for (String str: list) res[idx++] = str;
        return res;
    }
    /**
     * @description: dfs决策当前字符串的某一位填入什么字符
     * @param {char[]} cs 原字符串
     * @param {int} u 当前决策到目标字符串中的哪一位
     * @param {String} cur 当前目标字符串
     * @return {*}
     */    
    void dfs(char[] cs, int u, String cur){
        int n = cs.length;
        if (n == u){
            list.add(cur); // 将当前目标字符串加入到res的list中
            return;
        }
        for (int i = 0; i < n; i++){
            // 去重是因为c0和c1，只对c0的情况进行排列，c1的情况直接跳过
            if (i > 0 && !vis[i-1] && cs[i] == cs[i-1]) continue;
            if (!vis[i]){
                vis[i] = true;
                dfs(cs, u+1, cur + String.valueOf(cs[i]));
                vis[i] = false;
            }
        }
    }
    /**
     * @description: 计算阶乘 n! = n * (n-1) *... * 2 * 1
     * @param {int} n
     * @return {*} 
     */    
    private static int factorial(int n){
        return (n > 1) ? n * factorial(n-1) : 1;
    }

}
