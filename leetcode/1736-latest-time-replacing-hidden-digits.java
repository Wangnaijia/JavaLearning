/*
 * @Author: Wang Naijia
 * @Date: 2021-07-24 11:17:53
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-07-24 11:38:48
 * @Descripttion: 
 */
class Solution1736 {
    public String maximumTime(String time) {
        String res = time.replace("?", "9");
        String hours, minutes;
        for(int i = 0; i< 2; i++){
            int tmp = (int)res.charAt(0) * 10 + (int)res.charAt(1);
            tmp = Math.min(tmp, 23);
            hours = Integer.toString(tmp);
        }
        for(int j = 3; j < 5; j++){
            int tmp = (int)res.charAt(0) * 10 + (int)res.charAt(1);
            tmp = Math.min(tmp, 23);
            hours = Integer.toString(tmp);
        }
        return res;

    }
}