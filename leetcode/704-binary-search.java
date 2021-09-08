/*
 * @Author: Wang Naijia
 * @Date: 2021-09-06 15:52:19
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-09-06 16:06:06
 * @Descripttion: 
 */
class Solution704 {
    public static void main(String[] args) {
        int[] nums = {-1,0,3,5,9,12};
        int target = 9;
        Solution704 solution = new Solution704();
        System.out.println(solution.search(nums, target));
    }
    public int search(int[] nums, int target) {
        // int res = -1;
        int n = nums.length;  
        int left = 0, right = n - 1;
        while(left <= right){
            int mid = (left + right + 1) / 2;
            if(nums[mid] == target){
                return mid;
            } else if(nums[mid] > target){
                right = mid - 1;
            } else{
                left = mid + 1;
            }
        }
        return -1;
    }
}