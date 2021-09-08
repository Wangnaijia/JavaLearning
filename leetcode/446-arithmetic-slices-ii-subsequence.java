/*
 * @Author: Wang Naijia
 * @Date: 2021-08-11 09:00:15
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-08-11 09:24:29
 * @Descripttion: 
 */
import java.util.*;
class Solution446{
    public static void main(String[] args) {
        int[] nums = {2,4,6,8,10};
        Solution446 solution = new Solution446();
        System.out.println(solution);;
    }
    public int numsOfSubsequence(int[] nums){
        int res = 0;
        int n = nums.length;
        // dp数组中的每个元素为哈希映射（公差为diff的等差数列数目）
        Map<Long, Integer>[] dp = new Map[n];
        for(int i = 0; i < n; i++) dp[i] = new HashMap<Long, Integer>();
        for(int i = 1; i < n; i++){ 
            for(int j = 0; j < i; j++){
                // 公差最多到当前遍历的数列下标
                long diff = (long)nums[i] - (long)nums[j]; // 等号右边的式子也要提前转化为long，否则会溢出
                // 当前公差对应的数组个数
                int temp = dp[j].getOrDefault(diff, 0);
                res += temp;
                dp[i].put(diff, dp[i].getOrDefault(diff, 0)+temp+1);
            }
        }
        return res;
    }
}
