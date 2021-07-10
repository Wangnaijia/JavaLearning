import java.util.HashMap;

/*
 * @Author: Wang Naijia
 * @Date: 2021-07-08 05:26:12
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-07-08 19:32:27
 * @Descripttion: 
 */
import java.util.*;
class Solution930 {
    public int numSubarraysWithSum(int[] nums, int goal) {
        int N = nums.length;
        int[] sum = new int[N+1];
        for(int i = 1; i<=N; i++) sum[i] = sum[i-1] + nums[i-1];// 全部求出来
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0,1);
        int ans = 0;
        for(int i = 0; i<N; i++){
            int r = sum[i+1], l = r-goal; // 获取前缀和之差满足要求的项出现的次数
            ans += map.getOrDefault(l, 0);
            map.put(r, map.getOrDefault(r, 0) + 1);
        }
        return ans;
    }
}
