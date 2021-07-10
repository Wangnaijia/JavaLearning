
/*
 * @Author: Wang Naijia
 * @Date: 2021-06-28 15:29:12
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-06-28 16:07:25
 * @Descripttion: 题给我们的数据是公交路线，我们如果将车站放入队列进行bfs的话，就会发现接下来寻找可以到达的车站要巨大的时间复杂度，即遍历全部的数据。
不过我们可以把公交路线转换成这个车站可以坐哪几辆车(就像生活中那样)，这样我们就可以轻松知道我们现在位于的车站可以到达接下来哪些车站。
不过这样依旧会超时，我们可以分析一下，一辆车我们已经上过了，其实下一次再遇到这样的车了我们就没必要再上了。所以我们需要两个visited,一个来标记我们的车站是否来过，一个来标记这辆车是否上过。
 */
import java.util.*;
public class BusBFS {
    public static void main(String[] args) {
        int[][] r={{1, 2, 7}, {3, 6, 7}};
        System.out.println(new BusBFS().numBusesToDestination(r,1,6));
    }
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if(source==target) return 0;
        Queue<Integer> q = new LinkedList<>();
        int n = routes.length;
        q.offer(source);
        boolean[] visited = new boolean[n];
        Map<Integer, Integer> vis = new HashMap<Integer, Integer>();
        vis.put(source, 0);
        Map<Integer, Set<Integer>> idx = new HashMap<Integer, Set<Integer>>();
        // 数据转换
        for(int i = 0; i < n; ++i){
            for(int x : routes[i]){
                if(!idx.containsKey(x)){
                    idx.put(x, new HashSet<Integer>());
                }
                idx.get(x).add(i);
            }
        }
        while(! q.isEmpty()){
            int cur = q.poll();
            if(cur == target) return vis.get(cur);
            for( int i : idx.get(cur)){
                // 上过的车就不用再上了
                if(visited[i]==true) {
                    continue;
                }
                visited[i] = true;
                for( int j : routes[i]){
                    // 只有没去过的车站再去
                    if(!vis.containsKey(j)){
                        q.offer(j);
                        vis.put(j, vis.get(cur)+1);
                    }
                }
            }
        }
        return -1;
    }
}