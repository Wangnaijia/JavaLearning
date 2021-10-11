/*
 * @Author: Wang Naijia
 * @Date: 2021-10-09 10:29:09
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-10-09 10:50:26
 * @Descripttion: 所有 DNA 都由一系列缩写为 'A'，'C'，'G' 和 'T' 的核苷酸组成，例如："ACGAATTCCG"。在研究 DNA 时，识别 DNA 中的重复序列有时会对研究非常有帮助。
编写一个函数来找出所有目标子串，目标子串的长度为 10，且在 DNA 字符串 s 中出现次数超过一次。
示例 1：
    输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
    输出：["AAAAACCCCC","CCCCCAAAAA"]
示例 2：
    输入：s = "AAAAAAAAAAAAA"
    输出：["AAAAAAAAAA"]
提示：
    0 <= s.length <= 105
    s[i] 为 'A'、'C'、'G' 或 'T'
 */
import java.util.*;
class Solution187{
    public List<String> findRepeatedDnaSequences(String s){
        List<String> res = new ArrayList<>();
        Set<String> stringSet = new HashSet<>(); // 用于去重
        if(s.length()<=10) return res;
        for(int i = 0; i < s.length() - 10;){
            // 移位字符数
            s = s + i * 'B';
            String new_s = i * 'B' + s;
            int count = 0; // 记录重复字符数量
            for(int j = 0; j < new_s.length(); j++){
                int tmp = s.charAt(j) - new_s.charAt(j);
                if(tmp == 0){
                    count++;
                }
            }
        }
        

    }
}