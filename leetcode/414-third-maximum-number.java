import java.util.Arrays;
import java.util.Set;

/*
 * @Author: Wang Naijia
 * @Date: 2021-10-06 10:33:44
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-10-06 11:16:24
 * @Descripttion: 
 */
import java.util.*;
class Solution414{
    public static void main(String[] args) {
        int[] nums = {3,2,1};
        Solution414 solution = new Solution414();
        System.out.println(solution.thirdMax(nums));
    }
    public int thirdMax(int[] nums){
        Set<Integer> set = new HashSet<>();
        int res = Integer.MIN_VALUE;
        // set.addAll(Arrays.asList(nums));
        for(int num:nums){
            set.add(num);
        }
        if(set.size() < 3){
            for(int s:set){
                if(s > res) res = s;
            }
            System.out.println("into the if");
            return res;
        }
        return findKth(set, 3);
    }
    public int findKth(Set<Integer> set, int k){
        // 找出set中第k小的数字
        int min_num = 0;
        for(int i = 0; i < k; i++){
            min_num = Integer.MIN_VALUE;
            for(int s:set){
                if(s > min_num) min_num = s;
            }
            set.remove(min_num);
        }
        return min_num;
    }
}