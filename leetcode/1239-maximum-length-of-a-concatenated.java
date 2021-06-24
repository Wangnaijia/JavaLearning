/*
 * @Author: Wang Naijia
 * @Date: 2021-06-19 10:02:09
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-06-19 13:09:32
 * @Descripttion:给定一个字符串数组 arr，字符串 s 是将 arr 某一子序列字符串连接所得的字符串，如果 s 中的每一个字符都只出现过一次，那么它就是一个可行解。
请返回所有可行解 s 中最长长度。

示例 1：
    输入：arr = ["un","iq","ue"]
    输出：4
    解释：所有可能的串联组合是 "","un","iq","ue","uniq" 和 "ique"，最大长度为 4。
示例 2：
    输入：arr = ["cha","r","act","ers"]
    输出：6
    解释：可能的解答有 "chaers" 和 "acters"。
示例 3：
    输入：arr = ["abcdefghijklmnopqrstuvwxyz"]
    输出：26
提示：
    1 <= arr.length <= 16
    1 <= arr[i].length <= 26
    arr[i] 中只含有小写英文字母
************* 解题 ************
构成可行解的每个字符串可视为一个字符集合，且集合不含重复元素
可以用过一个二进制数来表示该字符串的字符集合，二进制的第i位为1表示字符集合中含有第i个小写字母，为0表示字符集合中不含第i个小写字母；
遍历arr，从中筛选出无重复字母的字符串将其对应二进制数加入一数组，记为mask
下面用回溯法解决：
    - 用backtrack(pos,mask)表示递归的函数，其中pos表示当前递归到了数组masks中的第pos个数，mask表示当前连接得到的字符串对应二进制数为mask；
    - 对第pos个数，有两种方法：选或者不选。如果mask和mask[pos]无公共元素，则可以选，此时调用backtrack(pos+1, mask|mask[pos])进行递归；如果不选这个数，调用backtrack(pos+1, mask)进行递归；
    - 记masks的长度为n，当pos=n时，计算mask中1的个数，即为可行解的长度，用其更新可行解的最长长度。

二叉树题解：
https://leetcode-cn.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters/solution/jian-ji-de-chui-su-yi-dong-by-huwt/
 */
package leetcode;

import java.util.*;
class Solution1239 {
    public static void main(String[] args){
        List<String> s = Arrays.asList("cha","r","act","ers");
        Solution1239 solution = new Solution1239();
        int result = solution.maxLength(s);
        System.out.println(result);
    }

    int ans = 0;

    public int maxLength(List<String> arr) {
        List<Integer> masks = new ArrayList<Integer>();
        for(String s:arr){
            int mask = 0; // 二进制表示
            for(int i=0; i<s.length();++i){
                int ch = s.charAt(i) - 'a';
                if(((mask >> ch) & 1) != 0){
                    // 右移pos后，最右边表示pos位字符是否出现，&1==0表示未出现；&1=0表示已出现
                    // 若 mask 已有 ch，则说明 s 含有重复字母，无法构成可行解
                    mask = 0;
                    break;
                }
                // 1<<pos 将1左移pos位，将该字母位置改为1
                mask |= 1 << ch; // 将ch加入mask中
                System.out.println(mask);
            }
            if(mask > 0){
                masks.add(mask);
            }
        }
        
        backtrack(masks, 0, 0);
        return ans;
    }

    public void backtrack(List<Integer> masks, int pos, int mask){
        /**
         * @description: 
         * @param {
         *      masks 十进制数字表示 可行解字符串占位的二进制 数组
         *      pos 当前递归到了数组masks中的第pos个数
         *      mask 十进制数字表示 当前组合字符串占位的二进制
         * } 
         * @return {*}
         */        
        if(pos == masks.size()){
            ans = Math.max(ans, Integer.bitCount(mask));
            return;
        }
        if((mask & masks.get(pos)) == 0){// mask 和 masks[pos] 无公共元素
            backtrack(masks, pos+1, mask|masks.get(pos));
        }
        backtrack(masks, pos + 1, mask);

    }
}