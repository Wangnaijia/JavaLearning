/*
 * @Author: Wang Naijia
 * @Date: 2021-09-22 14:23:54
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-09-22 14:26:00
 * @Descripttion: 
 */
class Solution58{
    public int lengthOfLastWord(String s){
        int index = s.length() - 1;
        while(s.charAt(index)==' '){
            index--;
        }
        int wordLength = 0;
        while(index >= 0 && s.charAt(index) != ' '){
            wordLength++;
            index--;
        }
        return wordLength;
    }
}