import java.util.Arrays;

/*
 * @Author: Wang Naijia
 * @Date: 2021-08-26 09:47:57
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-08-26 10:23:36
 * @Descripttion: 
 */
class Solution881{
    public static void main(String[] args) {
        int[] people = {1,2,};
        int limit = 3;
        Solution881 solution = new Solution881();
        System.out.println(solution.numRescueBoats(people, limit));
    }
    public int numRescueBoats(int[] people, int limit){
        int res = 0, n = people.length;
        Arrays.sort(people);
        int left = 0, right = n - 1;
        while(left <= right){
            if(people[left] + people[right] <= limit){
                left++;
            }
            right--;
            res++;
        }
        return res;
    }
}