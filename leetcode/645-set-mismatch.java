import java.util.HashMap;

/*
 * @Author: Wang Naijia
 * @Date: 2021-07-10 13:28:30
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-07-10 14:10:24
 * @Descripttion: 
 */
import java.util.*;
class Solution645 {
    public static void main(String[] args) {
        int[] nums = {1,2,2,4};
        Solution645 solution = new Solution645();
        int[] ans = solution.findErrorNums(nums);
        System.out.println(ans[0]+ans[1]);
    }
    public int[] findErrorNums(int[] nums) {
        Set set = new HashSet();
        int[] ans = new int[2];
        int res1=0, res2=0;
        for(int i = 0; i<nums.length; i++){
            res1+=i+1;
            if(!set.add(nums[i])){
                ans[0] = nums[i];
            } else {
                res2+=nums[i];
            }
        }
        ans[1] = res1 - res2;
        
        // int N = nums.length;
        // int[] ans = new int[2];
        // Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        // for(int i=0; i<N; i++){
        //     if(map.getOrDefault(nums[i], 0)==0){
        //         map.put(nums[i],1);
        //     } else{
        //         ans[0] = nums[i];
        //     }
        // }
        // for(int i=1; i<=N; i++){
        //     if(!map.containsKey(i)){
        //        ans[1] = i;
        //     } 
        // }
        return ans;
    }
}
