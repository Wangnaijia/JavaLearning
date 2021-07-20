/*
 * @Author: Wang Naijia
 * @Date: 2021-07-17 11:24:04
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-07-17 11:34:45
 * @Description: 
 */
class Solution152 {
    public static void main(String[] args) {
        int[] nums = {2,3,-2,4};
        Solution152 solution = new Solution152();
        int res = solution.maxProduct(nums);
        System.out.println(res);
    }
    public int maxProduct(int[] nums){
        int N = nums.length, ans = -Integer.MAX_VALUE;
        int[] f = new int[N+1], g = new int[N+1];
        f[0] = 1;
        g[0] = 1;
        for(int i=1; i<=N; i++){
            int x = nums[i-1];
            g[i] = Math.min(x, Math.min(g[i-1] * x, f[i-1] * x));
            f[i] = Math.max(x, Math.max(g[i-1] * x, f[i-1] * x));
            ans = Math.max(ans, f[i]);
        }
        return ans;
    }
    
}