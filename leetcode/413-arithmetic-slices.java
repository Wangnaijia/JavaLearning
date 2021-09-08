// /*
//  * @Author: Wang Naijia
//  * @Date: 2021-08-10 07:51:11
//  * @LastEditors: Wang Naijia
//  * @LastEditTime: 2021-08-10 08:08:44
//  * @Descripttion: 如果一个数列 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该数列为等差数列。
// 例如，[1,3,5,7,9]、[7,7,7,7] 和 [3,-1,-5,-9] 都是等差数列。
// 给你一个整数数组 nums ，返回数组 nums 中所有为等差数组的 子数组 个数。
// 子数组 是数组中的一个连续序列
//  */
// import java.util.*;
// class Solution413{
//     public static void main(String[] args) {
//         int[] nums = {1,2,3,4};
//         Solution413 solution = new Solution413();
//         System.out.println(solution.numberOfArithmeticSlices(nums));
//     }
//     public int numberOfArithmeticSlices(int[] nums){
//         int res = 0;
//         int n = nums.length;
//         if(n==1) return 0;
//         int d = nums[0] - nums[1], t = 0;
//         // 因为等差数列的长度至少为3，因此从2开始枚举
//         for(int i = 2; i < n; i++){
//             if(nums[i-1] - nums[i]==d){
//                 ++t;
//             } else{
//                 d = nums[i-1] - nums[i];
//                 t = 0;
//             }
//             res += t;
//         }
//         return res;
//     }
// }