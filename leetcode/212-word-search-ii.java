/*
 * @Author: Wang Naijia
 * @Date: 2021-09-16 21:38:46
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-09-18 11:46:43
 * @Descripttion: 给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words，找出所有同时在二维网格和字典中出现的单词。
单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。
示例 1：
    输入：board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
    输出：["eat","oath"]
示例 2：
    输入：board = [["a","b"],["c","d"]], words = ["abcb"]
    输出：[]
提示：
    m == board.length
    n == board[i].length
    1 <= m, n <= 12
    board[i][j] 是一个小写英文字母
    1 <= words.length <= 3 * 104
    1 <= words[i].length <= 10
    words[i] 由小写英文字母组成
    words 中的所有字符串互不相同
 */
import java.util.*;

import javax.print.attribute.HashDocAttributeSet;
/**
 * dfs中对每个位置都需要搜索四联通的全部方向，导致无效路径只有达到10才会被剪枝
 * 可以使用Trie进行建树，对任意位置i,j而言，只有Trie中存在从字符a到b的边时，才在棋盘上搜索从a到b的相邻路径
 */
class Trie212{
    // 用TrieNode方式建树,同时将isEND标记属性直接换成记录当前字符s，这样在DFS过程中无须额外记录当前搜索字符串
    class TrieNode{
        String s;
        TrieNode[] tns = new TrieNode[26];
    }
    TrieNode root = new TrieNode();
    void insert(String s){
        TrieNode p = root;
        for(int i = 0; i < s.length(); i++){
            int u = s.charAt(i) - 'a';
            if(p.tns[u] == null) p.tns[u] = new TrieNode();
            p = p.tns[u];
        }
        p.s = s;
    }
    Set<String> set = new HashSet<>();
    char[][] board;
    int n , m;
    int[][] dirs = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
    boolean[][] vis = new boolean[15][15];
    public List<String> findWords(char[][] _board, String[] words){
        board = _board;
        m = board.length; n = board[0].length;
        for(String w : words) insert(w);
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                int u = board[i][j] - 'a';
                if(root.tns[u] != null){
                    vis[i][j] = true;
                    dfs(i, j, root.tns[u]);
                    vis[i][j] = false;
                }
            }
        }
        List<String> ans = new ArrayList<>();
        for(String s: set) ans.add(s);
        return ans;
    }
    void dfs(int i, int j, TrieNode node){
        if(node.s != null) set.add(node.s);
        for(int[] d : dirs){
            int dx = i + d[0], dy = j + d[1];
            if(dx < 0 || dx >= m || dy < 0  || dy >= n) continue;
            if(vis[dx][dy]) continue;
            int u = board[dx][dy] - 'a';
            if(node.tns[u] != null){
                vis[dx][dy] = true;
                dfs(dx, dy, node.tns[u]);
                vis[dx][dy] = false;
            }
        }
    }
}
class Solution212{
    public static void main(String[] args) {
        char[][] board = {{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
        String[] words = {"oath","pea","eat","rain"};
        Solution212 solution = new Solution212();
        System.out.println(solution.findWords(board, words));
    }
    public List<String> findWords(char[][] board, String[] words) {
        int m = board.length, n = board[0].length;
        HashMap<Character, List<Integer>> map = new HashMap<>();
        List<String> res =  new ArrayList<>();
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                List<Integer> boardlist = map.getOrDefault(board[i][j], new ArrayList<>());
                boardlist.add(i * n + j);
                map.put(board[i][j], boardlist);
            }
        }
        for(String word : words){
            int word_len = word.length();
            // 对每一个单词
            boolean near = true;
            Integer idx = -1;
            // 看单词中每一个字母
            for(int i = 0; i < word_len; i++){
                if(map.containsKey(word.charAt(i)) && near){
                    // 如果哈希表中含这个字母的键 并且之前的状态是near的
                    List<Integer> boardlist = map.get(word.charAt(i));
                    // 对哈希表中该字母的值进行遍历，查看是否相邻
                    for(int j = 0; j < boardlist.size(); j++){
                        // 如果第一个出现，将near置为true，idx设为当前值
                        if(idx == -1){
                            idx = boardlist.get(j);
                            break; // 看下一个字母
                        }
                        else{
                            // 如果第二次进入循环，查看当前boardlist[j]与idx的差值
                            if(checkNear(boardlist.get(j), idx, n)){
                                continue;
                            }else{
                                near = false;
                            }
                        }
                    }
                }
                else{
                    // 如果不含 或 near不满足了
                    near = false;
                    break; // 跳出当前循环，看下一个单词
                }
            }
            if(near == true) res.add(word);
        }
        return res;
    }
    public boolean checkNear(int i, int j, int n){
        if(i - j == 1 || i - j == -1 || i - j == n || i - j == -n){
            return true;
        }
        return false;
    }
}

/**
 * 采用回溯算法，起始将words出现的单词放到set中，然后用board中每个点作为起点作为爆搜（由于题目中一个单词中的每个格子只能被使用一次，还需要一个vis数组来记录访问过的位置）
 * 如果当前搜索到的字符串长度超过10，直接剪枝
 * 如果当前搜的字符串在set中，则直接添加到答案（为了防止下一次再搜索到该字符串，需要将该字符串从set中移除。）
 */
class DFS212{
    Set<String> set = new HashSet<>();
    List<String> ans = new ArrayList<>();
    char[][] board; // 全局
    int[][] dirs = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
    int n,m;
    boolean[][] vis = new boolean[15][15];
    public List<String> findWords(char[][] _board, String[] words){
        board = _board;
        m = board.length; n = board[0].length;
        for(String w : words) set.add(w);
        StringBuilder sb = new StringBuilder();
        // 按行按列搜索
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                vis[i][j] = true;
                // 一个一个字母添加
                sb.append(board[i][j]);
                dfs(i,j,sb);
                vis[i][j] = false;
                sb.deleteCharAt(sb.length()-1);
            }
        }
        return ans;
    }
    void dfs(int i, int j, StringBuilder sb){
        if(sb.length() > 10) return;
        if(set.contains(sb.toString())){
            // 直到添加到满足words中的字符串
            ans.add(sb.toString());
            set.remove(sb.toString());// set中去除已经查过的
        }
        for(int[] d : dirs){
            int dx = i + d[0], dy = j + d[1];
            if(dx < 0 || dx >= m || dy < 0 || dy >= n) continue;
            vis[dx][dy] = true;
            sb.append(board[dx][dy]);
            dfs(dx, dy, sb);
            vis[dx][dy] = false;
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}