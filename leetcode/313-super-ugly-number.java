import java.util.PriorityQueue;

/*
 * @Author: Wang Naijia
 * @Date: 2021-08-09 09:07:27
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-08-09 09:30:31
 * @Descripttion: 超级丑数满足其所有质因数都出现在质数数组primes中，给一个整数n和一个整数数组primes，返回第n个超级丑数。
 */
class Solution313{
    public static void main(String[] args) {
        int n = 12;
        int[] primes = {2,7,13,19};
        Solution313 solution = new Solution313();
        System.out.println(solution.nthSuperUglyNumber(n, primes));
    }
    public int nthSuperUglyNumber(int n, int[] primes){
        // primes是递增变化的，用动态规划：每个质因子要生成新的丑数时比较谁更大，只要更新每个质因子对应的丑数坐标即可
        int[] dp = new int[n];// dp[i] 表示第i+1个丑数
        dp[0] = 1;
        // 以primes数组的最大数建立map
        int[] map = new int[primes[primes.length - 1] + 1];

        for(int i = 1; i < n; i++){
            int min = Integer.MAX_VALUE;
            for(int prime : primes){
                int x = dp[map[prime]] * prime; //
                min = Math.min(min, x);
            }
            // 更新dp[i]
            dp[i] = min;
            for(int prime : primes){
                if(min == dp[map[prime]] * prime){
                    map[prime]++;
                }
            }
        }
        return dp[n-1];
    }
}