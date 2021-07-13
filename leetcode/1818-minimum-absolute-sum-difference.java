/*
 * @Author: Wang Naijia
 * @Date: 2021-07-14 03:13:11
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-07-14 06:50:54
 * @Description: 
 */
import java.util.*;
class Solution1818 {
    public static void main(String[] args) {
        int[] nums1 = {1,10,4,4,2,7};
        int[] nums2 = {9,3,5,1,7,4};
        Solution1818 solution = new Solution1818();
        int res = solution.minAbsoluteSumDiff(nums1, nums2);
        System.out.println(res);
    }
    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        final int MOD = 1000000007;
        int n = nums1.length;
        int[] rec = new int[n];
        System.arraycopy(nums1, 0, rec, 0, n); //使用辅助数组 rec 记录nums1中所有的元素并排序
        Arrays.sort(rec);// 可以使用二分查找的方法快速找到nums1数组中尽可能接近 nums2[i] 的元素
        int sum = 0, maxn = 0;
        for(int i = 0; i < n; i++){
            int diff = Math.abs(nums1[i] - nums2[i]);
            sum = (sum + diff) % MOD;
            int j = binarySearch(rec, nums2[i]);
            // 当j=n时，nums2[i]最大，从左边逼近
            if(j < n){
                maxn = Math.max(maxn, diff - (rec[j] - nums2[i]));
            }
            // 否则同时逼近
            if(j > 0){
                maxn = Math.max(maxn, diff - (nums2[i] - rec[j-1]));
            }
        }
        // maxn记录替换方案带来的变化，maxn越大越好
        return (sum - maxn + MOD) % MOD;
    }
    // 遍历nums2， 使用二分查找最接近nums2[i]的两个元素
    public int binarySearch(int[] rec, int target){
        int low = 0, high = rec.length - 1;
        if(rec[high] < target){
            return high + 1;
        }
        while(low < high){
            int mid = (high - low) / 2 + low;
            if(rec[mid] < target){
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
    /**
     * @description: 直接找差值最大的替换为差值最小的，对 [1,28,21] [9,21,20] 用例出错
     * @param {int[]} nums1
     * @param {int[]} nums2
     * @return {*}
     */    
    public int minAbsoluteSumDiffMine(int[] nums1, int[] nums2) {
        int N = nums1.length;
        int res = 0;
        int diffMax = 0, diffMin = Integer.MAX_VALUE;
        int indexMax =0, indexMin = 0;
        for (int i = 0; i < N; i++){
            diffMax = Math.max(Math.abs(nums1[i] - nums2[i]), diffMax); // 选出最大差值的那个
            if(Math.abs(nums1[i] - nums2[i]) == diffMax){
                // 如果当前不更新indexmax
                indexMax = i;
            }
        }
        for(int i = 0; i< N; i++){
            diffMin = Math.min(Math.abs(nums1[i] - nums2[indexMax]), diffMin);
            if(Math.abs(nums1[i] - nums2[indexMax]) == diffMin){
                indexMin = i;
            }
        }
        nums1[indexMax] = nums1[indexMin];// 交换
        for(int i = 0; i<N; i++){
            res += Math.abs(nums1[i] - nums2[i]);
        }
        if(diffMax == 0) return 0; // 无需替换
        return res;
    }
}