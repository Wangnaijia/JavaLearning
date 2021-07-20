/*
 * @Author: Wang Naijia
 * @Date: 2021-07-16 09:14:36
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-07-16 09:36:32
 * @Description: 
 */
class SolutionOffer53 {
    public static void main(String[] args) {
        int[] nums = {2,2};
        int target = 2;
        SolutionOffer53 solution = new SolutionOffer53();
        int res = solution.search(nums, target);
        System.out.println(res);
    }
    public int search(int[] nums, int target) {
        int res = -1;
        int N = nums.length;
        int left = 0, right = N - 1;
        while(left <= right){
            int mid = (left + right + 1) >> 1;
            // 如果nums[mid]==target,则往前判断是否第一次出现
            if (target == nums[mid]){
                // 如果mid前没有数字，或mid前的数字不等于target，则当前nums[mid]是target第一次出现的下标
                if(mid - 1 < 0 || nums[mid-1]!=target){
                    res = mid;
                    break;
                } else{
                    right = mid - 1; // 向前移动
                }
            }
            if(nums[mid] > target){
                right = mid - 1;
            }
            if(nums[mid] < target){
                left = mid + 1;
            }
        } 
        // 如果第一次二分查找没有找到，说明数组中不存在和target相等的数字
        if(res == -1){
            return 0;
        }
        left = res; // 从mid开始取
        right = N - 1;
        while(left <= right){
            int mid = (left + right + 1)>>1;
            // 如果nums[mid]==target, 则往后判断是否最后一次出现
            if(nums[mid] == target){
                // 如果mid后没有数字，或mid后的数字不等于target，则当前nums[mid]是target最后一次出现的下标
                if(mid + 1 >= N || nums[mid+1]!=target ){
                    return mid - res + 1;
                } else{
                    left = mid + 1;
                }
            }
            if(nums[mid] > target){
                right = mid - 1;
            } 
            if(nums[mid] < target){
                left = mid + 1;
            }
        }
        return 0;
    }
    
}