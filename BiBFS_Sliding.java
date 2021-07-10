
/*
 * @Author: Wang Naijia
 * @Date: 2021-06-26 13:23:58
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-06-26 14:21:42
 * @Descripttion: 
 */
import java.util.*;
class BiBFS_Sliding {
    public static void main(String[] args) {
        int[][] s = { {3,0,1},{4,2,5}};
        BiBFS_Sliding solution = new BiBFS_Sliding();
        int res = solution.slidingPuzzle(s);
        System.out.println(res);
    }
    // 邻接节点的下标
    static int[][] neigh={{1,3},{0,2,4},{1,5},{0,4},{1,3,5},{2,4}};
    public int slidingPuzzle(int[][] board) {
        String target="123450";
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<board.length;i++){
            for (int j=0;j<board[i].length;j++){
                
                
                sb.append(board[i][j]);
            }
        }
        String num=sb.toString();
        if(num.equals(target))
            return 0;
        Queue<String> queue1=new LinkedList<>(),queue2=new LinkedList<>();
        queue1.offer(num);
        queue2.offer(target);
        Map<String,Integer> map1=new HashMap<>(),map2=new HashMap<>();
        map1.put(num,0);
        map2.put(target,0);
        while (!queue1.isEmpty()&&!queue2.isEmpty()){ 
            int t=-1;
           if(queue1.size()<=queue2.size()){
               t=update(queue1,map1,map2);
           }else{
               t=update(queue2,map2,map1);
           }
           if(t!=-1){
               return t;
           }
        }
        return -1;
    }
    
    public int update(Queue<String> queue,Map<String,Integer>map1,Map<String,Integer> map2){
        int size=queue.size();
        while (size-->0){
            String temp=queue.poll();
            int step=map1.get(temp);
            int x=temp.indexOf("0");
            // 可以选择的交换方式
            for(int i=0;i<neigh[x].length;i++){
                String s=swap(temp,x,neigh[x][i]);
                if(map1.containsKey(s))
                    continue;
                if(map2.containsKey(s)){
                    return step+1+map2.get(s);
                }
                queue.offer(s);
                map1.put(s,step+1);
            }
        }
        return -1;
    }

    public String swap(String str,int m,int n){
        StringBuilder sb=new StringBuilder(str);
        char l=sb.charAt(m);
        char r=sb.charAt(n);
        sb.setCharAt(m,r);
        sb.setCharAt(n,l);
        return sb.toString();
    }
}