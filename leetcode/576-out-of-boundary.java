/*
 * @Author: Wang Naijia
 * @Date: 2021-08-15 15:12:19
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-08-15 15:19:43
 * @Descripttion: 
 */
class Solution576{
    public static void main(String[] args) {
        int m = 2, n = 2, maxMove = 2, startRow = 0, startColumn = 0;
        Solution576 solution = new Solution576();
        System.out.println(solution.findPaths(m, n, maxMove, startRow, startColumn));
    }
    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn){
        if(maxMove<=0) return 0;
        int mod = 1000000007;
        int[][][] dp = new int[m][n][maxMove+1];
        int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
        for(int k = 1; k <= maxMove; ++k){
            for(int x = 0; x < m; ++x){
                for(int y = 0; y < n; ++y){
                    for(int[] dir : dirs){
                        int nx = x + dir[0];
                        int ny = y + dir[1];
                        // 边界处理，无论是第几步只要位置在边界都包含一步出界的情况
                        if(nx < 0 || nx >= m || ny < 0 || ny >= n){
                            dp[x][y][k]+=1;
                        } else{
                            dp[x][y][k] = (dp[x][y][k] + dp[nx][ny][k-1]) % mod;
                        }
                    }
                }
            }
        }
        return dp[startRow][startColumn][maxMove];
    }
}