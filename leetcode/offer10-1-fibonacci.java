/*
 * @Author: Wang Naijia
 * @Date: 2021-09-04 10:29:23
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-09-04 10:38:11
 * @Descripttion: 
 */
class SolutionOffer10_1{
    public static void main(String[] args) {
        int n = 48;
        SolutionOffer10_1 solution = new SolutionOffer10_1();
        System.out.println(solution.fib(n));
    }
    public int fib(int n){
        if(n==0) return 0;
        if(n==1) return 1;
        int[] f = new int[n+1];
        f[0] = 0;
        f[1] = 1;
        for(int i = 2; i <= n; i++){
            f[i] = f[i-1] % 1000000007  + f[i-2] % 1000000007;
        }
        return f[n] % 1000000007;
    }
}