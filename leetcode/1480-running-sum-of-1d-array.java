/*
 * @Author: Wang Naijia
 * @Date: 2021-08-28 14:54:29
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-08-28 15:04:34
 * @Descripttion: 
 */
class Solution1480{
    public int[] runningSum(int[] nums){
        int[] res = new int[nums.length];
        res[0] = nums[0];
        for(int i = 1; i < nums.length; i++){
            res[i] = res[i-1] + nums[i];
        }
        return res;
    }
}