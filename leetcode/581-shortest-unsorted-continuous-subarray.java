import java.util.ArrayList;
import java.util.Arrays;

/*
 * @Author: Wang Naijia
 * @Date: 2021-08-03 08:03:27
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-08-03 08:26:56
 * @Descripttion: 
 */
import java.util.*;
class Solution581{
    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        Solution581 solution = new Solution581();
        System.out.println(solution.findUnsortedSubarray(nums));
    }
    public int findUnsortedSubarray(int[] nums){
        int N = nums.length;
        int[] ori = new int[N];
        for(int i = 0; i < N; i++){
            ori[i] = nums[i];
        }
        Arrays.sort(nums);
        Boolean begin = false;
        int left = 0, right = 0; // 从左边开始，left读取ori，right读取nums
        for(int j = 0; j < N; j++){
            if(ori[j] == nums[j]){
                if(!begin) left++;
            } else {
                right = j + 1;
                begin = true;
            }
        }
        return right == 0 ? 0 : right - left;
    }
    /**
     * @description: 官方题解，找到两个相同的前缀和下标，进行相减
     * @param {int[]} nums 
     * @return {*}
     */    
    public int officalFindUnsortedSubarray(int[] nums){
        if(isSorted(nums)) return 0;
        int[] numsSorted = new int[nums.length];
        System.arraycopy(nums, 0, numsSorted, 0, nums.length);
        Arrays.sort(numsSorted);
        int left = 0;
        while(nums[left] == numsSorted[left]){
            left++;
        }
        int right = nums.length - 1;
        while(nums[right] == numsSorted[right]){
            right--;
        }
        return right - left + 1;
    }
    // 是否有序
    public boolean isSorted(int[] nums){
        for(int i = 1; i<nums.length;i++){
            if(nums[i] < nums[i-1]){ 
                return false;
            }
        }
        return true;
    }
}