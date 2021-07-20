/*
 * @Author: Wang Naijia
 * @Date: 2021-07-17 01:31:23
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-07-17 11:26:47
 * @Description: 
 */
class SolutionOffer42{
    public static void main(String[] args) {
        int[] nums = {1};
        SolutionOffer42 solution = new SolutionOffer42();
        int res = solution.maxSubArray(nums);
        System.out.println(res);
    }
    public int maxSubArray(int[] nums) {
        int N = nums.length;
        int[] f = new int[N];
        f[0] = nums[0];
        int ans = f[0];
        for(int i=1;i<N;i++) {
            f[i] = Math.max(nums[i], f[i-1]+nums[i]);
            ans = Math.max(ans, f[i]);
        }
        return ans;
    }
}