/*
 * @Author: Wang Naijia
 * @Date: 2021-07-20 15:00:12
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-07-20 15:00:29
 * @Description: 
 */
import java.util.*;
class Solution1838 {
    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int N = nums.length;
        int right = 0, left = 0;
        int max = 1;
        long total = 0; // 变化到对应元素所需要的所有步数
        for(right = 1; right < N; right++ ){ // 右边界主动移动
            total +=(long) (nums[right] - nums[right - 1]) * (right - left);
            if(total > k){
                total -= nums[right] - nums[left]; // 不能包括左端点
                left++; // 左边界被动移动
            }
            max = Math.max(max, right - left + 1);
        }
        return max;
    }
}