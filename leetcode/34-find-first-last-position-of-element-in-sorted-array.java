/*
 * @Author: Wang Naijia
 * @Date: 2021-07-11 10:47:05
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-07-11 11:12:48
 * @Description: 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
如果数组中不存在目标值 target，返回 [-1, -1]。
进阶：
你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
示例 1：
    输入：nums = [5,7,7,8,8,10], target = 8
    输出：[3,4]
示例 2：
    输入：nums = [5,7,7,8,8,10], target = 6
    输出：[-1,-1]
示例 3：
    输入：nums = [], target = 0
    输出：[-1,-1]
提示：
    0 <= nums.length <= 105
    -109 <= nums[i] <= 109
    nums 是一个非递减数组
    -109 <= target <= 109

 */
class Solution34 {
    public static void main(String[] args) {
        int[] nums = {5,7,7,8,8,10};
        int target = 8;
        Solution34 solution = new Solution34();
        int[] ans = solution.searchRange(nums, target);
        System.out.println(ans[0]);
        System.out.println(ans[1]);
    }
    public int[] searchRange(int[] nums, int target) {
        int N = nums.length;
        int l = 0, r = N-1;
        int[] ans = {-1,-1};
        if(N<1) return ans;
        if(N==1) return nums[N-1] == target ? new int[] {0,0} : ans;
        while(l <= r){
            int mid = l + r + 1 >> 1;
            // 如果nums[mid]==target 则往前判断是否第一次出现
            if(nums[mid] == target){
                // 如果mid前没有数字，或者mid前的数字不等于target，则当前nums[mid]是target第一次出现的下标
                if(mid-1<0 || nums[mid-1]!=target){
                    ans[0] = mid;
                    break;
                } else{
                    r = mid - 1;
                }
            } 
            if(nums[mid] > target){
                r = mid - 1;
            } 
            if(nums[mid] < target){
                l = mid + 1;
            }
        }
        // 如果第一次二分查找没有找到，说明数组中不存在和target相等的数字
        if(ans[0] == -1){
            return ans;
        }
        l = ans[0]; // 从mid开始取
        r = N-1;
        while(l <= r){
            int mid = l + r + 1 >> 1;
            // 如果nums[mid]==target 则往后判断是否第一次出现
            if(nums[mid] == target){
                // 如果mid后没有数字，或者mid后的数字不等于target，则当前nums[mid]是target最后一次出现的下标
                if(mid+1>=N || nums[mid+1]!=target){
                    ans[1] = mid;
                    break;
                } else{
                    l = mid + 1;
                }
            } 
            if(nums[mid] > target){
                r = mid - 1;
            } 
            if(nums[mid] < target){
                l = mid + 1;
            }
        }
        return ans;
    }
}
