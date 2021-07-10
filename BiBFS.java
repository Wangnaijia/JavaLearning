/*
 * @Author: Wang Naijia
 * @Date: 2021-06-25 11:01:31
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-06-25 13:04:49
 * @Descripttion: 
 */
import java.util.*;
class BiBFS {
    public static void main(String[] args) {
        String[] s = {"0201","0101","0102","1212","2002"};
        String target = "0000";
        BiBFS solution = new BiBFS();
        int res = solution.openLock1(s, target);
        System.out.println(target.getClass().getName().toString());
    }
    
    /**
     * @description: 图的BFS，列出起始点的可行点
     * @param {String[]} deadends  一组死亡数字
     * @param {String} target 目标数字
     * @return {int} 返回最小步数
     */     
    public int openLock1(String[] deadends, String target){
        HashSet<String> dead = new HashSet<>(Arrays.asList(deadends));
        /**
         * m1 和 m2 分别记录两个方向出现的数字是经过多少次转换而来的
         * e.g.
         * m1 = {"9000":1} 代表9000由"0000"经过1次转换而来
         * m2 = {"1002":3} 代表1002由"0000"经过3次转换而来
         */
        Map<String, Integer> m1 = new HashMap<>(), m2 = new HashMap<>();
        String start = "0000";
        Queue<String> queue1 = new LinkedList<>();// 从起点开始搜索
        Queue<String> queue2 = new LinkedList<>(); //从终点开始搜索
        queue1.offer(start);
        queue2.offer(target);
        m1.put(start, 0);
        m2.put(target, 0);
        
        int res = -1;
        if (dead.contains(target) || dead.contains("0000")) return -1;
        // if (target == "0000") return 0;
        /**
         * 只有两个队列都不空，才有必要继续往下搜索；
         * 如果其中一个队列空了，说明从某个方向搜到底都搜不到该方向的目标节点，反向搜索也没必要进行了
         */
        while(!queue1.isEmpty() && !queue2.isEmpty()){
            if (target == "0000") return 0;
            if (queue1.size() <= queue2.size()){
               res = update(queue1, m1, m2, target, dead); // 优先搜索队列内元素少的方向
            }
            else{
                res = update(queue2, m2, m1, target, dead);
            }
            if (res!=-1) return res;
        }
        return -1;
    }
    /**
     * @description: 从queue中取出一个数字进行扩展
     * @param queue 待搜索队列
     * @param cur 当前方向的距离字典
     * @param other 反向方向的距离字典
     * @return
     */
    int update(Queue<String> queue, Map<String, Integer> cur, Map<String, Integer> other, String target, HashSet<String> dead){
        // 从queue中取出一个数字进行扩展
        String poll = queue.poll(); // 取Q的首元素，出队
        List<String> nexts = getNexts(poll); // 获取当前元素u的邻接节点
        for(String s: nexts){
            // 如果该数字在当前方向上被记录过 或 属于死亡节点,跳过
            if(cur.containsKey(s) || dead.contains(s)) continue;
            // 如果该数字在另一方向出现过，说明找到了联通双向的最短路
            if(other.containsKey(s)){
                return cur.get(poll) + 1 + other.get(s);
            }else{
                // 过滤后所有的与u相邻的节点入队
                queue.add(s);
                cur.put(s, cur.get(poll) + 1);
            }
        }
        return -1;
    }

    /**
     * @description: 获取邻接的所有节点
     * @param {String} cur 当前节点
     * @return {*}
     */    
    public List<String> getNexts(String cur){
        List<String> list = new ArrayList<>();

        for(int i = 0; i<4; i ++){
            StringBuilder curSb = new StringBuilder(cur);
            // 处理两端的字符，'0'->'9'
            curSb.setCharAt(i, cur.charAt(i)=='0'?'9':(char)(cur.charAt(i)-1));
            list.add(curSb.toString());
            curSb.setCharAt(i, (char)cur.charAt(i)=='9'?'0':(char)(cur.charAt(i)+1));
            list.add(curSb.toString());
        }
        return list;
    }
}
