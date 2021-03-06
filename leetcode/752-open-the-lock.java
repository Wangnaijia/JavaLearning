/*
 * @Author: Wang Naijia
 * @Date: 2021-06-25 10:03:31
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-06-25 10:52:19
 * @Descripttion: 你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。每个拨轮可以自由旋转：例如把 '9' 变为 '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。
锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
字符串 target 代表可以解锁的数字，你需要给出解锁需要的最小旋转次数，如果无论如何不能解锁，返回 -1 。
示例 1:
    输入：deadends = ["0201","0101","0102","1212","2002"], target = "0202"
    输出：6
    解释：
    可能的移动序列为 "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"。
    注意 "0000" -> "0001" -> "0002" -> "0102" -> "0202" 这样的序列是不能解锁的，
    因为当拨动到 "0102" 时这个锁就会被锁定。
示例 2:
    输入: deadends = ["8888"], target = "0009"
    输出：1
    解释：
    把最后一位反向旋转一次即可 "0000" -> "0009"。
示例 3:
    输入: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
    输出：-1
    解释：
    无法旋转到目标数字且不被锁定。
示例 4:
    输入: deadends = ["0000"], target = "8888"
    输出：-1
提示：
    1 <= deadends.length <= 500
    deadends[i].length == 4
    target.length == 4
    target 不在 deadends 之中
    target 和 deadends[i] 仅由若干位数字组成

 */
package leetcode;
import java.util.*;

class Solution752 {
    public static void main(String[] args) {
        String[] s = {"0201","0101","0102","1212","2002"};
        String target = "0202";
        Solution752 solution = new Solution752();
        int res = solution.openLock(s, target);
        System.out.println(res);
    }
    /**
     * @description: 图的BFS，列出起始点的可行点
     * @param {String[]} deadends  一组死亡数字
     * @param {String} target 目标数字
     * @return {int} 返回最小步数
     */     
    public int openLock(String[] deadends, String target){
        HashSet<String> dead = new HashSet<>(Arrays.asList(deadends));
        Set<String> visited = new HashSet<>();
        String start = "0000";
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        int res = 0;
        if (dead.contains(target) || dead.contains("0000")) return -1;
        while(!queue.isEmpty()){
            int len = queue.size();
            for (int i = 0; i < len; i++){
                String cur = queue.poll(); // 取Q的首元素，出队
                if(target.equals(cur)){
                    return res;
                }
                List<String> nexts = getNexts(cur); // 获取当前元素u的邻接节点
                for(String s: nexts){
                    // 过滤这一层已经遍历的节点和死亡节点
                    if(!dead.contains(s) && !visited.contains(s)){
                        // 过滤后所有的与u相邻的节点入队
                        visited.add(s);
                        queue.offer(s);
                    }
                }
            }
            res++;
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
