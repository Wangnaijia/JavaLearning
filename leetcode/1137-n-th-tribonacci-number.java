/*
 * @Author: Wang Naijia
 * @Date: 2021-08-08 07:34:38
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-08-08 07:38:36
 * @Descripttion: 
 */
class Solution1137 {
    int t0 = 0, t1 = 1, t2 = 1;
    public static void main(String[] args) {
        int n = 4;
        Solution1137 solution = new Solution1137();
        System.out.println(solution.tribonacci(n));
    }
    public int tribonacci(int n) {
        if(n == 0) return t0;
        else if(n == 1) return t1;
        else if(n == 2) return t2;
        else{
            return tribonacci(n - 3) + tribonacci(n - 2) + tribonacci(n - 1);
        }
    }
}