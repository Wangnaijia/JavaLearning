import java.util.Arrays;

/*
 * @Author: Wang Naijia
 * @Date: 2021-08-23 17:37:58
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-08-23 17:54:02
 * @Descripttion: 
 */
class Solution1646{
    public static void main(String[] args) {
        int n = 3;
        Solution1646 solution = new Solution1646();
        System.out.println(solution.getMaximumGenerated(n));
    }
    public int getMaximumGenerated(int n){
        if(n == 0) return 0;
        int[] nums = new int[n+1];
        nums[0] = 0;
        nums[1] = 1;
        for(int i = 1; i <= n; i++){
            if(i % 2 == 0){
                nums[i] = nums[i / 2];
            } else{
                nums[i] = nums[(i - 1) / 2] + nums[(i + 1) / 2]; 
            }
        }
        // Arrays.sort(nums);
        int ans = 0;
        for (int i : nums) ans = Math.max(ans, i);
        // return nums[n];
        return ans;
    }
}
