import java.util.Arrays;

/*
 * @Author: Wang Naijia
 * @Date: 2021-08-02 10:55:19
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-08-02 12:43:44
 * @Descripttion: 
 */
class Solution743{
    public static void main(String[] args) {
        int[][] times = {{2,1,1},{2,3,1},{3,4,1}};
        int n = 4, k = 2;
        Solution743 solution = new Solution743();
        System.out.println(solution.networkDelayTime(times, n, k));
    }
    public int networkDelayTime(int[][] times, int n, int k){
        final int INF = Integer.MAX_VALUE / 2;
        
        // 邻接矩阵存储边信息
        int[][] g = new int[n][n];
        for(int i = 0; i<n; i++){
            Arrays.fill(g[i], INF); // 没有边相连的权值为无穷
        }
        for(int[] t : times){
            // t[0] 初始节点， t[1] 结尾节点， t[2] 边的权值
            int x = t[0] - 1, y = t[1] - 1;
            g[x][y] = t[2];
        }
        // 从源点到某点x的距离数组
        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        // 由于从k开始，所以该点距离设为0，即源点
        dist[k-1] = 0;
        // 节点是否被更新数组
        boolean[] used = new boolean[n];
        for(int i = 0; i < n; i++){
            int x = -1;
            for(int y = 0; y < n; y++){
                if(!used[y] && (x == -1 || dist[y] < dist[x])){
                    // 在还未确定最短路的点中，寻找距离最小的点
                    x = y; 
                }
            }
            // 用该点更新所有其他点的距离
            used[x] = true;
            for(int y = 0; y < n; y++){
                dist[y] = Math.min(dist[y], dist[x] + g[x][y]);
            }
        }
        // 找到距离最远的点
        int ans = Arrays.stream(dist).max().getAsInt();
        return ans == INF ? -1 : ans;
    }
}