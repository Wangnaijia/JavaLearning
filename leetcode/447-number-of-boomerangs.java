import java.util.HashMap;

/*
 * @Author: Wang Naijia
 * @Date: 2021-09-13 12:11:49
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-09-13 12:31:09
 * @Descripttion: 给定平面上 n 对 互不相同 的点 points ，其中 points[i] = [xi, yi] 。回旋镖 是由点 (i, j, k) 表示的元组 ，其中 i 和 j 之间的距离和 i 和 k 之间的距离相等（需要考虑元组的顺序）。
返回平面上所有回旋镖的数量。
示例 1：
    输入：points = [[0,0],[1,0],[2,0]]
    输出：2
    解释：两个回旋镖为 [[1,0],[0,0],[2,0]] 和 [[1,0],[2,0],[0,0]]
示例 2：
    输入：points = [[1,1],[2,2],[3,3]]
    输出：2
示例 3：
    输入：points = [[1,1]]
    输出：0
提示：
    n == points.length
    1 <= n <= 500
    points[i].length == 2
    -104 <= xi, yi <= 104
    所有点都 互不相同
 */
import java.util.*;
class Solution447{
    public static void main(String[] args) {
        int[][] points = {{0,0},{1,0},{2,0}};
        Solution447 soluiton = new Solution447();
        System.out.println(soluiton.numberOfBoomerangs(points));
    }
    
    public int numberOfBoomerangs(int[][] points){
        int ans = 0;
        for(int[] p : points){
            // 枚举每个V型的拐点，设points中有m个点到points[i]的距离相等，从这m个点中选两个点当作回旋镖的2个端点
            // 由于考虑顺序，因此方案数为在m个元素中选出2个不同元素的排列数Am2=m(m-1)
            Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
            // 遍历points并统计所有点到points[i]的距离，将每个距离出现的次数记录在哈希表中，然后遍历哈希表
            for(int[] q : points){
                int dis = (p[0] - q[0]) * (p[0] - q[0]) + (p[1] - q[1]) * (p[1] - q[1]);
                cnt.put(dis, cnt.getOrDefault(dis, 0) + 1);
            }
            for(Map.Entry<Integer, Integer> entry : cnt.entrySet()){
                int m = entry.getValue();
                ans += m * (m-1);
            }
        }
        return ans;
    }
    // 模拟法，超时
    public int mynumberOfBoomerangs(int[][] points){
        int res = 0, n = points.length;
        if(n==1) return 0;
        for(int i = 0; i < n; i++){
            for(int j = 0;j < n; j++){
                if(j==i) continue;
                for(int k = 0; k < n; k++){
                    if(k==j) continue;
                    if(Math.pow((points[i][0]-points[j][0]),2) + Math.pow((points[i][1]-points[j][1]),2) == Math.pow((points[i][0]-points[k][0]),2) + Math.pow((points[i][1]-points[k][1]),2)){
                        res++;
                    }
                }
            }
        }
        return res;
    }
}