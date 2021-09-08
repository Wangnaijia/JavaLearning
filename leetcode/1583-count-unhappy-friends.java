import java.util.HashMap;

/*
 * @Author: Wang Naijia
 * @Date: 2021-08-14 20:02:10
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-08-14 20:17:35
 * @Descripttion: 
 */
import java.util.*;
class Solution1583{
    public static void main(String[] args) {
        int n = 4;
        int[][] preferences = {{1,2,3},{3,2,0},{3,1,0},{1,2,0}};
        int[][] pairs = {{0,1},{2,3}};
        Solution1583 solution = new Solution1583();
        System.out.println(solution.unhappyFriends(n, preferences, pairs));
    }
    Map<Integer, Map<Integer,Integer>> map = new HashMap<>(); 
    public int unhappyFriends(int n, int[][] preferences, int[][] pairs){
        int res = 0;
        int m = pairs.length;
        for(int i = 0; i < n;i++){
            int[] p = preferences[i];
            Map<Integer, Integer> cur = new HashMap<>();
            for(int j = 0; j < n - 1; j++) cur.put(p[j], n - j); // 存储坐标，越亲密，坐标越大
            map.put(i, cur);
        }
        for(int i = 0; i < m; i++){
            int x = pairs[i][0], y = pairs[i][1];
            boolean xok = false, yok = false;
            for(int j = 0; j < m; j++){
                if(i == j) continue;
                int u = pairs[j][0], v = pairs[j][1];
                if(!xok && check(x,y,u,v)) xok = true;
                if(!yok && check(y,x,u,v)) yok = true;
                if(xok && yok) break;
            }
            if(xok) res++;
            if(yok) res++;
        }
        return res;
    }
    boolean check(int x, int y, int u, int v){
        Map<Integer, Integer> xmap = map.get(x), ymap = map.get(y);
        Map<Integer, Integer> umap = map.get(u), vmap = map.get(v);
        if(xmap.get(u) > xmap.get(y) && umap.get(x) > umap.get(v)) return true;
        if(xmap.get(v) > xmap.get(y) && vmap.get(x) > vmap.get(u)) return true;
        return false;
    }
}