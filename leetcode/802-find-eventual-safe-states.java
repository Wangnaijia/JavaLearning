/*
 * @Author: Wang Naijia
 * @Date: 2021-08-05 12:51:00
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-08-05 13:00:30
 * @Descripttion: 
 */
import java.util.*;
class Solution802{
    public static void main(String[] args) {
        
    }
    public List<Integer> eventualSafeNodes(int[][] graph){
        int n = graph.length;
        // 反图，邻接表存储
        List<List<Integer>> new_graph = new ArrayList<List<Integer>>();
        // 节点入度
        int[] Indeg = new int[n];
        for(int i = 0; i < n; i++){
            new_graph.add(new ArrayList<Integer>());
        }    
        for(int i = 0; i < n; i++){
            // 第i个节点的出度边的始终节点调换
            for(int j = 0; j < graph[i].length; j++){
                // 出度转入度
                new_graph.get(graph[i][j]).add(i);
            }
            // 原数组记录的节点出度，在反图种就是入度
            Indeg[i] = graph[i].length;
        }
        // 拓扑排序
        Queue<Integer> q = new LinkedList<Integer>();
        // 首先将入度为0的点存入队列
        for(int i = 0; i < n; i++){
            if(Indeg[i] == 0){
                q.offer(i);
            }
        }
        while(!q.isEmpty()){
            // 每次弹出头元素
            int cur = q.poll();
            for(int x : new_graph.get(cur)){
                // 将以其为起点的有向边删除，更新终点入度
                Indeg[x]--;
                if(Indeg[x]==0) q.offer(x);
            }
        }
        // 最终入度为0的所有点均为安全点
        List<Integer> ret = new ArrayList<Integer>();
        for(int i = 0; i < n; i++){
            if(Indeg[i] == 0) ret.add(i);
        }
        return ret;
    }
}