
/*
 * @Author: Wang Naijia
 * @Date: 2021-08-12 07:56:23
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-08-12 09:08:53
 * @Descripttion: 
 */
class Solution516{
    public static void main(String[] args) {
        String s = "bbbab";
        Solution516 solution = new Solution516();
        System.out.println(solution.longestPalindromeSubseq(s));
    }
    public int longestPalindromeSubseq(String s){
        int n = s.length();
        // 状态：原字符串的子串是否为回文，i，j为s[i...j]
        int[][] dp = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(i==j) dp[i][j] = 1;
            }
        }
        // 由于状态转移关系为填表的右下值，因此要从下往上填
        for(int i = n - 1; i > -1; i--){
            for(int j = i + 1; j < n; j++){
                if(s.charAt(i) == s.charAt(j)){
                    dp[i][j] = dp[i+1][j-1] + 2; // 若两端字符相同，状态转移为里面的子串长度＋2
                } else {
                    dp[i][j] = Math.max(dp[i][j-1], dp[i+1][j]); // 若两端字符不同，状态只取左端点字符或右端点字符
                }
            }
        }
        return dp[0][n-1];
    }
}