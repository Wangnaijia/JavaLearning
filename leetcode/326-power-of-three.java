/*
 * @Author: Wang Naijia
 * @Date: 2021-09-23 19:20:39
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-09-23 19:23:03
 * @Descripttion: 
 */
class Solution326{
    public boolean isPowerOfThree(int n){
        while(n!=0 && n%3==0){
            n/=3;
        }
        return n==1;
    }
}