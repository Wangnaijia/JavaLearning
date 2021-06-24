/*
 * @Author: Wang Naijia
 * @Date: 2021-06-18 10:14:09
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-06-18 10:24:01
 * @Descripttion: 
 */
package leetcode;

class Solution483 {
    public static void main(String[] args){
        String s = "4681";
        Solution483 solution = new Solution483();
        String ans = solution.smallestGoodBase(s);
        System.out.println(ans);
    }
    
    public String smallestGoodBase(String n){
        // 题目给范围为[3,10^18] 超过int存储的范围
        long nVal = Long.parseLong(n);
        int mMax = (int) Math.floor(Math.log(nVal)/Math.log(2));
        for (int m = mMax; m > 1; m--){
            int k = (int) Math.pow(nVal, 1.0/m);
            long mul = 1, sum = 1;
            for (int i = 0; i < m; i++){
                mul *= k;
                sum += mul;
            }
            if (sum == nVal){
                return Integer.toString(k);
            }
        }
        // 如果一直检查到m=2都没找到答案，则可以直接返回m=1对应的k值： n-1
        return Long.toString(nVal - 1);
    }
}
