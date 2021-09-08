/*
 * @Author: Wang Naijia
 * @Date: 2021-08-13 13:13:29
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-08-14 21:32:45
 * @Descripttion: 
 */
class Solution233{
    public static void main(String[] args) {
        int n = 13;
        Solution233 solution = new Solution233();
        System.out.println(solution.countDigitOne(n));
    }
    public int countDigitOne(int n){
        int i = 1;
        int ans = 0;
        while (n / i > 0) {
            //前面的数
            int front = n / (i * 10);
            //当前位的数
            int cur = n / i - front * 10;
            //后面的数
            int behind = n - front * (i * 10) - cur * i;
            if (cur == 0) {
                ans += front * i;
            } else if (cur == 1) {
                ans += front * i + behind + 1;
            } else {
                ans += (front + 1) * i;
            }
            i *= 10;
        }
        return ans;
            
    }
}