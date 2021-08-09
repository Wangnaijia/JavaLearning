import java.util.*;

/*
 * @Author: Wang Naijia
 * @Date: 2021-08-07 11:58:25
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-08-07 12:28:05
 * @Descripttion: 
 */
class Solution457{
    public static void main(String[] args) {
        int[] nums = {2,-1,1,2,2};
        Solution457 solution = new Solution457();
        System.out.println(solution.circularArrayLoop(nums));
    }
    // 邻接表存储的图
    List<List<Integer>> graph = new ArrayList<List<Integer>>();
    // 入度数组
    int[] Indeg = new int[5005];

    // 拓扑排序
    boolean toposort(int n){
        Queue<Integer> q = new LinkedList<Integer>();
        // 首先将入度为0的点入队列
        for(int i = 0; i < n; i++){
            if(Indeg[i] == 0){
                q.offer(i);
            }
        }
        while(!q.isEmpty()){
            // 每次弹出队头元素
            int cur = q.poll();
            for(int x : graph.get(cur)){
                // 将以其为起点的有向边删除，更新终点入度
                Indeg[x]--;
                if(Indeg[x] == 0) q.offer(x);
            }
        }
        // 若仍有入度不为0的点，说明图中有环
        for(int i = 0; i < n; i++){
            if(Indeg[i] != 0) return true;
        }
        return false;
    }
    public boolean circularArrayLoop(int[] nums){
        int n = nums.length;
        for(int i = 0; i < n; i++){
            graph.add(new ArrayList<Integer>());
        }
        // 先处理正向边nums[i]>0的情况
        for(int i = 0; i < n; i++){
            int end = ((i + nums[i]) % n + n) % n;
            if(nums[i] <= 0 || i == end) continue;
            graph.get(i).add(end);
            Indeg[end]++;
        }
        if(toposort(n)) return true;
        graph.clear();
        for(int i = 0; i < n; i++){
            graph.add(new ArrayList<Integer>());
        }
        // 再处理负向边nums[i]<0的情况
        for(int i = 0; i < n; i++){
            int end = ((i + nums[i]) % n + n) % n;
            if(nums[i] >= 0 || i == end) continue;
            graph.get(i).add(end);
            Indeg[end]++;
        }
        if(toposort(n)) return true;
        return false;
    }
}