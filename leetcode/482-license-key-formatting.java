/*
 * @Author: Wang Naijia
 * @Date: 2021-10-04 09:52:58
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-10-04 10:18:54
 * @Descripttion: 
 */
class Solution482 {
    public static void main(String[] args) {
        String s = "--a-a-a-a--";
        int k = 2;
        Solution482 solution = new Solution482();
        System.out.println(solution.licenseKeyFormatting(s, k));
    }
    public String licenseKeyFormatting(String s, int k){
        int n = s.length();
        StringBuffer sb = new StringBuffer();
        int flag = -1;
        for(int i = n; i>=0;i--){
            if(flag==-1){
                flag++;
                continue;
            }
            if(flag==k){
                flag = 0;
                i++;
                sb.append('-');
                continue;
            }
            if(s.charAt(i) == '-'){
                continue;
            }
            if(Character.isLowerCase(s.charAt(i))){
                sb.append(Character.toUpperCase(s.charAt(i)));
            } else{
                sb.append(s.charAt(i));
            }
            flag++;
        }
        if(sb.length() > 0){
            if(sb.charAt(sb.length()-1) == '-') sb.deleteCharAt(sb.length()-1);
        }
        
        return sb.reverse().toString();
    }
}
