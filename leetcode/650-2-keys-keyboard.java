/*
 * @Author: Wang Naijia
 * @Date: 2021-09-19 17:05:40
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-09-19 17:09:14
 * @Descripttion: 
 */
class Solution650{
    public static void main(String[] args) {
        int n = 1;
        Solution650 solution = new Solution650();
        System.out.println(solution.minSteps(n));
    }
    public int minSteps(int n){
        int[] dp = new int[n+1];
        if(n == 1) return 0;
        for(int i = 2; i < n; i++){
            dp[i] = i;
            if(i % 2 == 0){
                dp[i] = dp[i/2] + 2;
            }
        }
        return dp[n];
    }
}