/*
 * @Author: Wang Naijia
 * @Date: 2021-07-29 14:34:32
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-07-29 14:39:31
 * @Descripttion: 
 */
import java.util.*;
class Solution1107 {
    public List<Integer> pathInZigZagTree(int label){
        List<Integer> ans = new ArrayList<>();
        while(label>0){
            ans.add(label);
            label >>= 1;
        }
        Collections.reverse(ans);
        int l, r, deep = ans.size();
        for(int i = 0; i < deep; i++){
            // 判断第i层是否需要修改(如果deep为偶数，只对奇数深度进行修改；如果deep为奇数，只对偶数深度进行修改)
            if((deep & 1) != (i & 1)) continue;
            l = (1 << i);
            r = l + l - 1;
            ans.set(i, r-ans.get(i) + l);
        }
        return ans;
    }   
}
