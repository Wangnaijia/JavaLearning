import java.util.LinkedList;

/*
 * @Author: Wang Naijia
 * @Date: 2021-08-06 09:44:17
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-08-06 10:02:09
 * @Descripttion: 存在一个由 n 个节点组成的无向连通图，图中的节点按从 0 到 n - 1 编号。
给你一个数组 graph 表示这个图。其中，graph[i] 是一个列表，由所有与节点 i 直接相连的节点组成。
返回能够访问所有节点的最短路径的长度。你可以在任一节点开始和停止，也可以多次重访节点，并且可以重用边。
示例 1：
    输入：graph = [[1,2,3],[0],[0],[0]]
    输出：4
    解释：一种可能的路径为 [1,0,2,0,3]
示例 2：
    输入：graph = [[1],[0,2,4],[1,3,4],[2],[1,2]]
    输出：4
    解释：一种可能的路径为 [0,1,4,2,3]
提示：
    n == graph.length
    1 <= n <= 12
    0 <= graph[i].length < n
    graph[i] 不包含 i
    如果 graph[a] 包含 b ，那么 graph[b] 也包含 a
    输入的图总是连通图
解题思路：
    由于题目需要我们求出「访问所有节点的最短路径的长度」，并且图中每一条边的长度均为 11，因此我们可以考虑使用广度优先搜索的方法求出最短路径。

    在常规的广度优先搜索中，我们会在队列中存储节点的编号。对于本题而言，最短路径的前提是「访问了所有节点」，因此除了记录节点的编号以外，我们还需要记录每一个节点的经过情况。因此，我们使用三元组 (u,mask,dist) 表示队列中的每一个元素，其中：u 表示当前位于的节点编号；mask 是一个长度为 n 的二进制数，表示每一个节点是否经过。如果 mask 的第 i 位是 1，则表示节点 i 已经过，否则表示节点 i 未经过；dist 表示到当前节点为止经过的路径长度。
    这样一来，我们使用该三元组进行广度优先搜索，即可解决本题。初始时，我们将所有的 (i, 2^i, 0)放入队列，表示可以从任一节点开始。在搜索的过程中，如果当前三元组中的 mask 包含 n 个 1（即 mask = 2^n - 1，那么我们就可以返回 dist 作为答案。
    细节
        为了保证广度优先搜索时间复杂度的正确性，即同一个节点 u 以及节点的经过情况 mask 只被搜索到一次，我们可以使用数组或者哈希表记录 (u,mask) 是否已经被搜索过，防止无效的重复搜索。

 */
import java.util.*;
class Solution847{
    public static void main(String[] args) {
        int[][] graph = {{1,2,3,4},{0,3},{0},{0,1},{0}};
        Solution847 solution = new Solution847();
        System.out.println(solution.shortestPathLength(graph));
    }
    public int shortestPathLength(int[][] graph){
        int n = graph.length;
        Queue<int[]> queue = new LinkedList<int[]>();
        boolean[][] seen = new boolean[n][1<<n]; // 状态压缩
        for(int i = 0; i < n; i++){
            queue.offer(new int[]{i,1<<i,0}); // 初始化节点i
            seen[i][1<<i] = true; // 自己到自己的置为true
        }
        int ans = 0;
        // bfs
        while(!queue.isEmpty()){
            int[] tuple = queue.poll();
            int u = tuple[0], mask = tuple[1], dist = tuple[2];
            if(mask == (1<<n) - 1){
                ans = dist;
                break;
            }
            // 搜索相邻的节点
            for(int v:graph[u]){
                // 将mask的第v位置为1
                int maskV = mask | (1 << v);
                if(!seen[v][maskV]){
                    queue.offer(new int[]{v, maskV, dist + 1});
                    seen[v][maskV] = true; // 同一节点以及节点的经过情况只能被搜索一次
                }
            }
        }
        return ans;
    }
}