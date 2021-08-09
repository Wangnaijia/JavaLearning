/*
 * @Author: Wang Naijia
 * @Date: 2021-07-30 14:28:39
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-07-30 14:39:03
 * @Descripttion: 
 */
class Solution171 {
    public static void main(String[] args) {
        String columnTitle = "ABA";
        Solution171 solution = new Solution171();
        int res = solution.titleToNumber(columnTitle);
        System.out.println(res);
    }
    public int titleToNumber(String columnTitle){
        int n = columnTitle.length();
        int res = 0;
        for(int i = 0; i < n; i++){
            int num = columnTitle.charAt(i) - 'A' + 1;
            res = res * 26 + num;
        }
        return res;
    }
}
