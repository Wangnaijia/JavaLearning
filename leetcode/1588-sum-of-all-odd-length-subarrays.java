/*
 * @Author: Wang Naijia
 * @Date: 2021-08-29 10:55:56
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-08-29 11:02:09
 * @Descripttion: 
 */
class Solution1588{
    public static void main(String[] args) {
        int[] arr = {1,4,2,5,3};
        Solution1588 solution = new Solution1588();
        System.out.println(solution.sumOddLengthSubarrays(arr));
    }
    public int sumOddLengthSubarrays(int[] arr){
        int res = 0;
        for(int i = 0; i < arr.length; i++){
            int left = i + 1, right = arr.length - i;
            int left_even = (left + 1) / 2, right_even = (right + 1) / 2;
            int left_odd = left / 2, right_odd = right / 2;
            res += (left_even * right_even + left_odd * right_odd) * arr[i];
        }
        return res;
    }
}