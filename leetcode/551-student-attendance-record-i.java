/*
 * @Author: Wang Naijia
 * @Date: 2021-08-17 01:13:55
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-08-17 01:27:51
 * @Descripttion: 
 */
class Solution551{
    public static void main(String[] args) {
        String s = "PPALLL";
        Solution551 solution = new Solution551();
        System.out.println(solution.checkRecord(s));
    }
    public boolean checkRecord(String s){
        int numL = 0, numA = 0;
        int n = s.length();
        for(int i = 0; i < n; i++){
            if(numA > 1) return false;
            if(s.charAt(i)=='A'){
                numA++;
            } 
            if(s.charAt(i)=='L'){
                numL++;
                if(numL >= 3) return false;
            } else{
                numL = 0;
            }
        }
        return true;
    }
}