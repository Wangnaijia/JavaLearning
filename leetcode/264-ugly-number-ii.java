/*
 * @Author: Wang Naijia
 * @Date: 2021-08-09 09:51:50
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-08-09 09:58:07
 * @Descripttion: 找出第n个丑数（由2，3，5为质数）
 * 示例:
    输入: n = 10
    输出: 12
    解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
    说明:  
        1 是丑数。
        n 不超过1690。
 */
class Solution264{
    public static void main(String[] args) {
        Solution264 solution = new Solution264();
        System.out.println(solution.nthUglyNumber(10));
    }
    public int nthUglyNumber(int n){
        int res = 1;
        int[] dp = new int[n];
        dp[0] = 1; // 放进第一个丑数1
        int i2 = 0, i3 = 0, i5 = 0;
        for(int i = 1; i < n; i++){
            // 算出所有丑数，直到需要的第n个为止
            int ugly = Math.min(dp[i2] * 2, dp[i3] * 3);
            ugly = Math.min(ugly, dp[i5] * 5);
            dp[i] = ugly;
            if(dp[i]==dp[i2] * 2) i2++; // 指针移动，从小到大寻找丑数
            if(dp[i]==dp[i3]*3) i3++;
            if(dp[i]==dp[i5]*5) i5++;
        }
        return dp[n-1];
    }
}