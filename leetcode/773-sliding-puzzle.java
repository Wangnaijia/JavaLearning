/*
 * @Author: Wang Naijia
 * @Date: 2021-06-26 12:23:19
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-06-26 13:41:44
 * @Descripttion: 在一个 2 x 3 的板上（board）有 5 块砖瓦，用数字 1~5 来表示, 以及一块空缺用 0 来表示.
一次移动定义为选择 0 与一个相邻的数字（上下左右）进行交换.
最终当板 board 的结果是 [[1,2,3],[4,5,0]] 谜板被解开。
给出一个谜板的初始状态，返回最少可以通过多少次移动解开谜板，如果不能解开谜板，则返回 -1 。
示例：
    输入：board = [[1,2,3],[4,0,5]]
    输出：1
    解释：交换 0 和 5 ，1 步完成

    输入：board = [[1,2,3],[5,4,0]]
    输出：-1
    解释：没有办法完成谜板

    输入：board = [[4,1,2],[5,0,3]]
    输出：5
    解释：
        最少完成谜板的最少移动次数是 5 ，
        一种移动路径:
        尚未移动: [[4,1,2],[5,0,3]]
        移动 1 次: [[4,1,2],[0,5,3]]
        移动 2 次: [[0,1,2],[4,5,3]]
        移动 3 次: [[1,0,2],[4,5,3]]
        移动 4 次: [[1,2,0],[4,5,3]]
        移动 5 次: [[1,2,3],[4,5,0]]
    输入：board = [[3,2,4],[1,5,0]]
    输出：14
提示：
    board 是一个如上所述的 2 x 3 的数组.
    board[i][j] 是一个 [0, 1, 2, 3, 4, 5] 的排列.

 */
package leetcode;

import java.util.*;

class Solution773 {
    public static void main(String[] args) {
        int[][] s = { {4,1,2},{5,0,3}};
        Solution773 solution = new Solution773();
        int res = solution.slidingPuzzle(s);
        System.out.println(res);
    }
    class Node{
        String str;
        int x, y;
        Node(String _str, int _x, int _y){
            str = _str; x = _x; y = _y;
        }
    }
    int n = 2, m = 3;
    String s, e;
    int x, y;
    /**
     * @description: 返回解开迷板所需次数；为方便将二维矩阵转成字符串（一维矩阵）处理
     * @param {int[][]} board
     * @return {*}
     */    
    public int slidingPuzzle(int[][] board){
        s = "";
        e = "123450";
        for (int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                s += board[i][j];
                if(board[i][j] == 0){
                    x = i; y = j;
                }
            }
        }

        int ans = bfs();
        return ans;
    }
    int[][] dirs = new int[][] {{1,0}, {-1,0}, {0,1},{0,-1}};
    int bfs(){
        Deque<Node> d = new ArrayDeque<>();
        // 字典存储 str和步数
        Map<String, Integer> map = new HashMap<>();
        Node root = new Node(s, x, y);
        // 初始节点入队
        d.addLast(root);
        map.put(s, 0);
        while(!d.isEmpty()){
            Node poll = d.pollFirst();
            int step = map.get(poll.str);
            if(poll.str.equals(e)) return step;
            int dx = poll.x, dy = poll.y;
            for(int[] di : dirs){
                int nx = dx + di[0], ny = dy + di[1];
                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                String nStr = update(poll.str, dx, dy, nx, ny);
                // visited 的过滤掉
                if(map.containsKey(nStr)) continue;
                Node next = new Node(nStr, nx, ny);
                // 调换后的board入队
                d.addLast(next);
                map.put(nStr, step+1);
            }
        }
        return -1;
    }
    String update(String cur, int i, int j, int p, int q){
        char[] cs = cur.toCharArray();
        char tmp = cs[i*m+j];
        cs[i*m+j] = cs[p*m + q];
        cs[p*m + q] = tmp;
        return String.valueOf(cs);
    }
}

