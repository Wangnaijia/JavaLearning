/*
 * @Author: Wang Naijia
 * @Date: 2021-06-29 12:20:12
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-06-29 12:35:31
 * @Descripttion: 
 */
package leetcode;

class Solution168 {
    public static void main(String[] args) {
        int columnNumber = 28;
        System.out.println(new Solution168().convertToTitle(columnNumber));
    }
    public String convertToTitle(int columnNumber) {
        String[] excel = {"Z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y"};
        
        StringBuilder ans = new StringBuilder();
        while(columnNumber>0){
            columnNumber--;
            
            ans.append((char)(columnNumber%26+'A'));
            columnNumber /= 26;
        }
        return ans.reverse().toString();
    }
    
}

