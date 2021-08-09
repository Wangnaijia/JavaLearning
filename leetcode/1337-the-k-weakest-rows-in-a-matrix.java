import java.util.ArrayList;

/*
 * @Author: Wang Naijia
 * @Date: 2021-08-01 08:36:23
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-08-01 08:55:05
 * @Descripttion: 
 */
import java.util.*;
class Solution1337 {
    public static void main(String[] args) {
        int[][] mat = {{1,1,0,0,0},{1,1,1,1,0},{1,1,0,0,0},{1,0,0,0,0},{1,1,1,1,1}};
        int k = 3;
        Solution1337 solution = new Solution1337();
        System.out.println(solution.kWeakestRows(mat, k));
    }
    public int[] kWeakestRows(int[][] mat, int k) {
        int m = mat.length, n = mat.length;
        List<int[]> power = new ArrayList<int[]>();
        for(int i = 0; i < m; i++){
            int l = 0, r = n-1, pos = -1;
            while(l <= r){ // 二分法找1和0的分界
                int mid = l + r + 1 >> 1;
                if(mat[i][mid] == 0){
                    r = mid - 1;
                } else {
                    pos = mid;
                    l = mid + 1;
                }
            }
            power.add(new int[]{pos+1, i}); // 堆排序
        }
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>(){
            public int compare(int[] pair1, int[] pair2){
                if (pair1[0] != pair2[0]){
                    return pair1[0] - pair2[0]; // 数量不同，按数量大小排序
                } else {
                    return pair1[1] - pair2[1]; // 数量相同，按行号大小排序
                }
            }
        });
        for(int[] pair:power){
            pq.offer(pair);
        }
        int[] ans = new int[k];
        for(int i = 0; i < k; i++){
            ans[i] = pq.poll()[1];
        }
        return ans;
    }
}