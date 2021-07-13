/*
 * @Author: Wang Naijia
 * @Date: 2021-07-12 10:33:13
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-07-12 10:36:48
 * @Description: 题干同274.但数组是升序排列
 */
class Solution275{
    public static void main(String[] args) {
        int[] citations = {0,1,3,5,6};
        Solution275 solution = new Solution275();
        int res = solution.hIndex(citations);
        System.out.println(res);
    }
    public int hIndex(int[] citations){
        int n = citations.length;
        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (citations[mid] >= n - mid) {
                right = mid - 1; // 说明mid有下降的空间
            } else {
                left = mid + 1;
            }
        }
        return n - left;
    }
}
