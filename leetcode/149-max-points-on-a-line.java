/*
 * @Author: Wang Naijia
 * @Date: 2021-06-24 01:39:56
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-06-24 10:15:01
 * @Descripttion: 给你一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点。求最多有多少个点在同一条直线上。
示例 1：
    输入：points = [[1,1],[2,2],[3,3]]
    输出：3
示例 2：
    输入：points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
    输出：4
提示：
    1 <= points.length <= 300
    points[i].length == 2
    -10^4 <= xi, yi <= 10^4
    points 中的所有点 互不相同
 */
package leetcode;
import java.util.*;
class Solution149 {
    public static void main(String[] args) {
        int[][] points = {{1,1},{3,2},{5,3},{4,1},{2,3},{1,4}};
        Solution149 solution = new Solution149();
        int res = solution.maxPoints(points);
        System.out.println(res);
    }
    public int maxPoints(int[][] points) {
        
        int N = points.length;
        // 如果点数小于2
        if (N < 3){
            return N;
        }
        int same = 1; // 计数重复点
        int maxlength = 0;
        for (int i = 0; i< N; i++){
            // 固定一个点points[i]
            for (int j = i+1; j < N; j++){
                int count = 0; // 记当前连成直线最多的点数
                long ydiff = points[j][1] - points[i][1];
                long xdiff = points[j][0] - points[i][0];
                for (int k = j+1; k<N; k++){
                    if (ydiff * (points[k][0] - points[i][0]) == xdiff * (points[k][1] - points[i][1])){
                        count ++;
                    }
                }
                maxlength = Math.max(maxlength, count+2); // 记录第i个点能取得的最大长度
            }
            // 如果最大长度超过数组长度的一半，没有比其更大的结果
            if(maxlength > (N/2)){
                break;
            }
        }
        return maxlength;
    }
}

