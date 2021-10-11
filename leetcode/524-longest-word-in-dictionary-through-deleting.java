/*
 * @Author: Wang Naijia
 * @Date: 2021-09-14 12:48:26
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-09-14 13:51:48
 * @Descripttion: 给你一个字符串 s 和一个字符串数组 dictionary 作为字典，找出并返回字典中最长的字符串，该字符串可以通过删除 s 中的某些字符得到。
如果答案不止一个，返回长度最长且字典序最小的字符串。如果答案不存在，则返回空字符串。
示例 1：
    输入：s = "abpcplea", dictionary = ["ale","apple","monkey","plea"]
    输出："apple"
示例 2：
    输入：s = "abpcplea", dictionary = ["a","b","c"]
    输出："a"
提示：
    1 <= s.length <= 1000
    1 <= dictionary.length <= 1000
    1 <= dictionary[i].length <= 1000
    s 和 dictionary[i] 仅由小写英文字母组成

 */
import java.util.*;

class Solution524{
    public static void main(String[] args) {
        String s = "abpcplea";
        String a = "plea";
        List<String> dictionary = new ArrayList<>();
        dictionary.add("ale");
        dictionary.add("apple");
        dictionary.add("monkey");
        dictionary.add("plea");
        Solution524 solution = new Solution524();
        System.out.println(solution.findLongestWord(s, dictionary));

    }
    public String findLongestWord(String s, List<String> dictionary){
        // 对数组排序
        Collections.sort(dictionary, (a,b) -> {
            if(a.length() != b.length()) return b.length() - a.length();
            return a.compareTo(b);
        });
        for(String dic_s : dictionary){
            if(isContain(s, dic_s)) return dic_s;
        }
        String res = "";
        return res;
    }
    public boolean isContain(String s, String a){
        int j = 0;
        for(int i = 0; i < s.length(); i++){
            if(j==a.length()) return true;
            // 贪心匹配，最优先匹配先出现的字符
            if(a.charAt(j) == s.charAt(i)){
                j++;
            }
        }
        return false;
    }
}