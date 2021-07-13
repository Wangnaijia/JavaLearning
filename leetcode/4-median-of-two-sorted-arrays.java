/*
 * @Author: Wang Naijia
 * @Date: 2021-07-11 16:30:30
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-07-11 17:22:21
 * @Description: 
 */
class Solution4{
    public double findMedianSortedArrays(int[] nums1, int[] nums2){
        if(nums1.length > nums2.length){
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }
        // 令nums1长度小于nums2
        int m = nums1.length;
        int n = nums2.length;
        // 分割线左边元素需要满足的个数是 m +（n - m + 1）/ 2;
        int totalLeft = (m + n + 1)/2;
        
        // 在nums1的区间[0,m]内查找恰当的分割线
        // 使得nums1[i-1] <= nums2[j] && nums2[j-1] <= nums1[i]
        int left = 0;
        int right = m;

        while(left<right){
            int i = left + (right - left + 1)/2;
            int j = totalLeft - i;
            if(nums1[i-1] > nums2[j]){
                // 下一轮搜索的区间是[left, i-1]
                right = i - 1;
            } else{
                // 下一轮搜索的区间是[i, right]
                left = i;
            }
        }

        int i = left;
        int j = totalLeft - i;
        int nums1LeftMax = i == 0 ? Integer.MIN_VALUE : nums1[i-1]; //nums1分割线左边没有元素
        int nums1RightMin = i == m ? Integer.MAX_VALUE : nums1[i]; //nums1分割线右边没有元素
        int nums2LeftMax = j == 0 ? Integer.MIN_VALUE : nums2[j-1]; //nums2分割线左边没有元素
        int nums2RightMin = j == n ? Integer.MAX_VALUE : nums2[j]; //nums2分割线右边没有元素

        if(((m+n)%2) == 1){
            return Math.max(nums1LeftMax,nums2LeftMax);
        } else {
            return (double) ((Math.max(nums1LeftMax, nums2LeftMax) + Math.min(nums1RightMin, nums2RightMin))) / 2;
        }
    }
}