/*
 * @Author: Wang Naijia
 * @Date: 2021-07-09 10:41:39
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-07-09 03:51:37
 * @Descripttion: 
 */
import java.util.*;
class SolutionLcci10 {
    public static void main(String[] args){
        int[] nums = {3, 2, 3};
        SolutionLcci10 solution = new SolutionLcci10();
        int result = solution.majorityElement(nums);
        System.out.println(result);
    }
    public int majorityElement(int[] nums) {
        int N = nums.length;
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i<N; i++){
            map.put(nums[i],map.getOrDefault(nums[i], 0)+1);
            if (map.getOrDefault(nums[i], 0) > (N / 2)){
                return nums[i];
            }
        }
        return -1;
    }
}

