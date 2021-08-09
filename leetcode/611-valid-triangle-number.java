import java.util.*;

/*
 * @Author: Wang Naijia
 * @Date: 2021-08-04 09:32:16
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-08-04 14:16:20
 * @Descripttion: 
 */
class Solution611{
    public static void main(String[] args) {
        int[] nums = {2,2,3,4};
        Solution611 solution = new Solution611();
        System.out.println(solution.triangleNumber(nums));
    }
    public int triangleNumber(int[] nums){
        Arrays.sort(nums);
        int res = 0; // 返回方法数
        // 二重枚举
        for(int i = 0; i < nums.length; i++){
            for(int j = i + 1; j < nums.length; j++){
                int left = j + 1, right = nums.length - 1, k = j;
                while(left <= right){
                    // 二分查找
                    int mid = (left + right) / 2;
                    if(nums[mid] < nums[i] + nums[j]){
                        k = mid; // 找到的c的最大值
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
                res += k - j;
            }
        }
        
        return res;
    }
    /**
     * @description: 双指针法：固定最长的一条边（逆向遍历），用双指针扫描，如果nums[l] + nums[r] > nums[i]，说明nums[l+1] + nums[r] > nums[i],..., nums[r-1] + nums[r] > nums[i],满足条件的有r-1种，r左移进入下一轮；如果nums[l] + nums[r] <= nums[i], l右移进入下一轮。
     * @param {int[]} three：有序的三边长[a,b,c]
     * @return {*}
     */    
    public int triangleNumberBi(int[] nums){
        Arrays.sort(nums);
        int n = nums.length;
        int res = 0;
        for(int i = n-1; i >= 2; i--){
            int l = 0, r = i - 1;
            while(l<r){
                if(nums[l] + nums[r] > nums[i]){
                    res += r - 1;
                    --r;
                } else {
                    ++l;
                }
            }
        }
        return res;
    }
}