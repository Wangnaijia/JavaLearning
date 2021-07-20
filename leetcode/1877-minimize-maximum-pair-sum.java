
/*
 * @Author: Wang Naijia
 * @Date: 2021-07-20 15:02:51
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-07-20 15:06:41
 * @Description: 
 */
import java.util.*;
class Solution1877 {
    public static void main(String[] args) {
        int[] nums = {3,5,4,2,4,6};
        Solution1877 solution = new Solution1877();
        int res = solution.minPairSum(nums);
        System.out.println(res);
    }
    public int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int N = nums.length;
        int max = 0;
        for(int i = 0; i<N; i++){
            max = Math.max(nums[i] + nums[N - i - 1], max);
        }
        return max;
    }
}