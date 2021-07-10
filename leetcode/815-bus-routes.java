/*
 * @Author: Wang Naijia
 * @Date: 2021-06-28 14:37:29
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-06-28 15:03:27
 * @Descripttion: 思路：这道题目就是个图的知识，
所以我们的出发点就是应该从图的角度来解。
首先呢，一个节点可能对应有多条节点(多条边)(一个公交车节点连接着多个公交车站节点，一个公交车站节点可能连接多个公交车节点) 所以我们需要遍历一遍，用Map存下
公交站节点所对应的多个公交车节点。 eg: 样例一 对应节点0和1.
我们从S开始，把所有符合公交车站节点S的对应的公交车节(这里是0)点加入队列中.
接着进行bfs搜索，每次搜索，我们将该队列中所有的公交车节点遍历一遍(类似层次遍历)，
若遇到公交车站节点T，直接返回ans，若没遇到，每次访问一个公交车站结点，就访问
该公交车站节点所连接的公交车节点，若该公交车节点没有访问过，就加入备用队列(存新添加的未访问的公交车节点)中。
 */

package leetcode;
import java.util.*;
class Solution815 {
    public static void main(String[] args) {
        int[][] r={{1, 2, 7}, {3, 6, 7}};
        System.out.println(new Solution815().numBusesToDestination(r,1,6));
    }
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if(source==target) return 0;
        Map<Integer, Set<Integer>> map = new HashMap<Integer, Set<Integer>>();
        for(int i=0; i < routes.length; ++i){
            for(int j: routes[i]){
                if(!map.containsKey(j)){
                    map.put(j, new HashSet<Integer>());
                }
                map.get(j).add(i);
            }
        }
        Queue<Integer> queue = new LinkedList<Integer>();
        Set<Integer> vis = new HashSet<Integer>();
        for(int st: map.get(source)){
            queue.offer(st);
            vis.add(st);
        }
        int ans = 1;
        while(!queue.isEmpty()){
            Queue<Integer> t = new LinkedList<Integer>(); // 备用节点 新添加的未访问过的节点
            while(!queue.isEmpty()){
                int curCar = queue.poll();
                for(int k: routes[curCar]){ // 遍历当前公交车节点所连接的所有公交车站
                    if(k==target){ // 遇到则直接返回结果
                        return ans;
                    }
                    for(int nextCar:map.get(k)){ // 遍历当前公交车站节点连接的所有公交车节点
                        if(!vis.contains(nextCar)){ // 未访问过的加入备用队列中
                            t.offer(nextCar);
                            vis.add(nextCar);
                        }
                    }
                }
            }
            ++ans;
            queue = t;
        }
        return -1;
    }       
}
