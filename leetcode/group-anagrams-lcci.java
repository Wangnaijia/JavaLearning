/*
 * @Author: Wang Naijia
 * @Date: 2021-07-18 09:16:51
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-07-18 10:21:30
 * @Descripttion:编写一种方法，对字符串数组进行排序，将所有变位词组合在一起。变位词是指字母相同，但排列不同的字符串。
注意：本题相对原题稍作修改
示例:
    输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
    输出:
    [
    ["ate","eat","tea"],
    ["nat","tan"],
    ["bat"]
    ]
说明：
    所有输入均为小写字母。
    不考虑答案输出的顺序。
 */
import java.util.*;
class SolutionLCCI2 {
    public static void main(String[] args) {
        String[] strs = {""};
        SolutionLCCI2 solution = new SolutionLCCI2();
        List<List<String>> groups = solution.MygroupAnagrams(strs);
        System.out.println(groups);
    }
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ans = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            int[] cnts = new int[26];
            for (char c : s.toCharArray()) cnts[c - 'a']++;
            StringBuilder sb = new StringBuilder();
            for (int i : cnts) sb.append(i + "_");
            String key = sb.toString();
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(s);
            map.put(key, list);
        }
        for (String key : map.keySet()) ans.add(map.get(key));
        return ans;
    }
    public List<List<String>> MygroupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        // Set strDict = new HashSet<>();
        HashSet<HashSet<Character>> strDicts = new HashSet<HashSet<Character>>();
        for(String str:strs){
            if(str == "" && strs.length == 1){
                List<String> group = new ArrayList<String>();
                group.add("");
                res.add(group);
                System.out.println(".....");
                return res;
            }
            // 当前字符串对应的哈希集合
            HashSet<Character> charDict = new HashSet<>();
            for(int i = 0; i < str.length();i++){
                charDict.add(str.charAt(i));
            }
            // 若字符串是由相同字母组成的，对应的哈希集合也相同
            strDicts.add(charDict);
        }
        // strDicts添加完后，把字符串放到对应的位置
        for(HashSet<Character> strDict : strDicts){
            List<String> group = new ArrayList<String>();
            for(String str:strs){
                for(int i = 0; i<str.length();i++){
                    if(!strDict.contains(str.charAt(i))) break;
                    if(i==str.length()-1){
                        group.add(str);
                    }
                }
            }
            res.add(group);
        }
        return res;
    }
}