import java.util.*;

/*
 * @Author: Wang Naijia
 * @Date: 2021-08-19 08:46:11
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-08-19 10:01:03
 * @Descripttion: 
 */
class Solution345{
    public static void main(String[] args) {
        String s = "leetcode";
        Solution345 solution = new Solution345();
        System.out.println(solution.reverseVowels(s));
    }
    /**
     * @description: 双指针前后扫描，当左右指针都是元音进行互换并移到下一位
     * @param {*}
     * @return {*}
     */    
    public String reverseVowels(String s){
        Character[] vowel_list = {'a','o','e','i','u','A','E','I','O','U'};
        HashSet<Character> vowels = new HashSet<>(Arrays.asList(vowel_list));
        char[] chars = s.toCharArray();
        int l = 0, r = s.length() - 1;
        while(l<r){
            if(vowels.contains(chars[l]) && vowels.contains(chars[r])){
                char tmp = chars[l];
                chars[l++] = chars[r];
                chars[r--] = tmp;
            } else if(vowels.contains(chars[l])){
                r--;
            } else{
                l++;
            }
        }
        return new String(chars);
    }
    /**
     * @description: 通过，5%的内存和时间
     * @param {*}
     * @return {*}
     */    
    public String myreverseVowels(String s){
        Deque<Character> stack = new LinkedList<>();
        Character[] vowel_list = {'a','o','e','i','u','A','E','I','O','U'};
        HashSet<Character> vowels = new HashSet<>(Arrays.asList(vowel_list));
        int n = s.length();
        Character poll_char;
        String res = "";
        for(int i = 0; i < n; i++){
            if(vowels.contains(s.charAt(i))){
                stack.push(s.charAt(i));
            }
        }
        for(int i = 0; i < n; i++){
            if(vowels.contains(s.charAt(i))){
                poll_char = stack.pop();
                res += poll_char;
            } else{
                res += s.charAt(i);
            }
        }
        return res;
    }
}