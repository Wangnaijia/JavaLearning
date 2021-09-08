/*
 * @Author: Wang Naijia
 * @Date: 2021-08-18 09:05:32
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-08-18 09:55:53
 * @Descripttion: 
 */
class Solution552{
    public static void main(String[] args) {
        int n = 10101;
        Solution552 solution = new Solution552();
        System.out.println(solution.checkRecord(n));
    }
    public int checkRecord(int n){
        final int mod = 1000000007;
        // dp[i][j][k]表示含有i个A，结尾处为连续的j个L的长度为k的序列
        long[][][] dp = new long[2][3][n+2];
        // 边界条件
        dp[0][0][1] = 1;
        dp[0][1][1] = 1;
        dp[1][0][1] = 1;
        // 状态转移
        for(int k = 2; k <= n+1; k++){
            dp[0][0][k] = (dp[0][0][k-1] + dp[0][1][k-1] + dp[0][2][k-1]) % mod;
            dp[1][0][k] = (dp[0][0][k-1] + dp[0][1][k-1] + dp[0][2][k-1] + dp[1][0][k-1] + dp[1][1][k-1] + dp[1][2][k-1]) % mod;
            dp[0][1][k] = dp[0][0][k-1] % mod;
            dp[1][1][k] = dp[1][0][k-1] % mod;
            dp[0][2][k] = dp[0][1][k-1] % mod;
            dp[1][2][k] = dp[1][1][k-1] % mod;
        }
        return (int)dp[1][0][n+1];

    }
}
