/*
 * @Author: Wang Naijia
 * @Date: 2021-08-25 09:48:55
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-08-25 10:09:26
 * @Descripttion: 给你一个有 n 个节点的 有向无环图（DAG），请你找出所有从节点 0 到节点 n-1 的路径并输出（不要求按特定顺序）
二维数组的第 i 个数组中的单元都表示有向图中 i 号节点所能到达的下一些节点，空就是没有下一个结点了。
译者注：有向图是有方向的，即规定了 a→b 你就不能从 b→a 。
示例 1：
    输入：graph = [[1,2],[3],[3],[]]
    输出：[[0,1,3],[0,2,3]]
    解释：有两条路径 0 -> 1 -> 3 和 0 -> 2 -> 3
示例 2：
    输入：graph = [[4,3,1],[3,2,4],[3],[4],[]]
    输出：[[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
示例 3：
    输入：graph = [[1],[]]
    输出：[[0,1]]
示例 4：
    输入：graph = [[1,2,3],[2],[3],[]]
    输出：[[0,1,2,3],[0,2,3],[0,3]]
示例 5：
    输入：graph = [[1,3],[2],[3],[]]
    输出：[[0,1,2,3],[0,3]]
提示：
    n == graph.length
    2 <= n <= 15
    0 <= graph[i][j] < n
    graph[i][j] != i（即，不存在自环）
    graph[i] 中的所有元素 互不相同
    保证输入为 有向无环图（DAG）
解题思路：
    我们可以使用深度优先搜索的方式求出所有可能的路径。具体地，我们从 0 号点出发，使用栈记录路径上的点。每次我们遍历到点 n-1，就将栈中记录的路径加入到答案中。
    特别地，因为本题中的图为有向无环图（DAG），搜索过程中不会反复遍历同一个点，因此我们无需判断当前点是否遍历过。
 */
import java.lang.Thread.State;
import java.util.*;
class Solution797{
    public static void main(String[] args) {
        int[][] graph = {{4,3,1},{3,2,4},{3},{4},{}};
        Solution797 solution = new Solution797();
        System.out.println(solution.allPathsSourceTarget(graph));
    }
    List<List<Integer>> ans = new ArrayList<List<Integer>>();
    Deque<Integer> stack = new ArrayDeque<Integer>();
    public List<List<Integer>> allPathsSourceTarget(int[][] graph){
        stack.offerLast(0);
        dfs(graph, 0, graph.length-1);
        return ans;
    }
    /**
     * @description: 
     * @param {int[][]} graph 
     * @param {int} x 起始节点
     * @param {int} n 目标节点
     * @return {*}
     *  从第0结点开始往下深度优先搜索，如果能到达第n-1结点，则说明该路径可以，然后加入结果；
     * 否则往回退，继续执行DFS，直到所有搜索完从第0结点到第n-1结点之间的所有路径。

     */    
    public void dfs(int[][] graph, int x, int n){
        if(x==n){
            ans.add(new ArrayList<Integer>(stack));
            return;
        }
        for(int y: graph[x]){
            stack.offerLast(y);
            dfs(graph, y, n);
            stack.pollLast(); // 回溯
        }
    }
}