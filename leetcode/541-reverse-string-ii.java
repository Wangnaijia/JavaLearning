/*
 * @Author: Wang Naijia
 * @Date: 2021-08-20 11:10:11
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-08-20 12:04:10
 * @Descripttion: 
 */
class Solution541{
    public static void main(String[] args) {
        String s = "abcdefg";
        int k = 2;
        Solution541 solution = new Solution541();
        System.out.println(solution.reverseStr(s, k));
    }
    public String reverseStr(String s, int k){
        char[] chars = s.toCharArray();
        int n = s.length();
        int m = n % (2*k), round = n / (2*k);
        for(int i = 0; i < round; i++){
            int left = i*2*k, right = i*2*k+k-1;
            while(left <= right){
                char tmp = chars[right];
                chars[right] = chars[left];
                chars[left] = tmp;
                left++;
                right--;
            }
        }
        if(m < k){
            int left = round*2*k, right = n-1;
            while(left <= right){
                char tmp = chars[right];
                chars[right] = chars[left];
                chars[left] = tmp;
                left++;
                right--;
            }
            System.out.println("yes");
        } else{
            int left = round*2*k, right = round*2*k+k-1;
            while(left <= right){
                char tmp = chars[right];
                chars[right] = chars[left];
                chars[left] = tmp;
                left++;
                right--;
            }
        }
        return new String(chars);
    }
}
