package leetcode;
/*
 * @Author: Wang Naijia
 * @Date: 2021-06-17 10:19:45
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-06-18 10:15:54
 * @Descripttion: 有效数字（按顺序）可以分成以下几个部分：

一个 小数 或者 整数
    （可选）一个 'e' 或 'E' ，后面跟着一个 整数
小数（按顺序）可以分成以下几个部分：
    （可选）一个符号字符（'+' 或 '-'）
下述格式之一：
    至少一位数字，后面跟着一个点 '.'
    至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
    一个点 '.' ，后面跟着至少一位数字
整数（按顺序）可以分成以下几个部分：
    （可选）一个符号字符（'+' 或 '-'）
    至少一位数字
部分有效数字列举如下：
["2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"]
部分无效数字列举如下：
["abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"]
给你一个字符串 s ，如果 s 是一个 有效数字 ，请返回 true 。
示例 1：
    输入：s = "0"
    输出：true
示例 2：
    输入：s = "e"
    输出：false
示例 3：
    输入：s = "."
    输出：false
示例 4：
    输入：s = ".1"
    输出：true
提示：
    1 <= s.length <= 20
    s 仅含英文字母（大写和小写），数字（0-9），加号 '+' ，减号 '-' ，或者点 '.' 。
解题思路：正则、DFA状态机、模拟
字符串模拟：
    将字符串按照e/E进行分割后，如果存在e/E：左侧可以是 整数 或 浮点数，右侧必须是整数
    如果不存在e/E：整段可以是整数或浮点数
    关键：实现一个check函数用于判断整数或浮点数：
        +/-只出现在头部
        .最多出现一次
        至少存在一个数字
*/
class Solution{
    public static void main(String[] args){
        String s = "100e-7";
        Solution solution = new Solution();
        boolean ans = solution.isNumber(s);
        System.out.println(ans);
    }

    public boolean isNumber(String s){
        int n = s.length();
        char [] cs = s.toCharArray();
        int idx = -1;
        for (int i = 0; i < n; i++){
            if (cs[i] == 'e' || cs[i] == 'E'){
                if (idx == -1) idx = i;
                else return false; //重复出现的e/E不能算
            }
        }
        boolean ans = true;
        if (idx != -1){
            ans &= check(cs, 0, idx - 1, false);
            ans &= check(cs, idx + 1, n - 1, true);
        }else{
            ans &= check(cs, 0, n - 1, false);
        }
        return ans;
    }
    
    boolean check(char[] cs, int start, int end, boolean mustInteger){
        if (start > end) return false;
        if (cs[start] == '+' || cs[start] == '-') start ++;
        boolean hasDot = false, hasNum = false;
        for (int i = start; i <= end; i++){
            if(cs[i] == '.'){
                if (mustInteger || hasDot) return false;
                hasDot = true;
            }else if(cs[i]>='0' && cs[i] <= '9'){
                hasNum = true;
            }else{
                return false;
            }
        }
        return hasNum;
    }
}