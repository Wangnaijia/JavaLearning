/*
 * @Author: Wang Naijia
 * @Date: 2021-08-16 08:35:46
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-08-16 09:41:37
 * @Descripttion: 假设有从 1 到 N 的 N 个整数，如果从这 N 个数字中成功构造出一个数组，使得数组的第 i 位 (1 <= i <= N) 满足如下两个条件中的一个，我们就称这个数组为一个优美的排列。条件：
 * 第 i 位的数字能被 i 整除
 * i 能被第 i 位上的数字整除
 * 现在给定一个整数 N，请问可以构造多少个优美的排列？
 * 解题思路：使用回溯法解决，从左至右依次向目标排列中放入数即可。
 * 1. 定义backtrack(index, n)表示尝试向位置index放入数，其中n为排列的长度，在当前函数中，首先找到一个符合条件的未被使用过的数，然后递归执行backtrack(index+1,n),当该函数执行完毕，回溯到当前层，再尝试下一个符合条件的未被使用的数即可
 * 回溯过程中，我们可以用 vis 数组标记哪些数被使用过，每次我们选中一个数 x，我们就将 vis[x] 标记为 true，回溯完成后，我们再将其置为 false。
 * 特别地，为了优化回溯效率，我们可以预处理每个位置的符合条件的数有哪些，用二维数组 match 保存。当我们尝试向位置 index 放入数时，我们只需要遍历 match[index] 即可。

 */
import java.util.*;

class Solution526{
    public static void main(String[] args) {
        int n = 15;
        Solution526 solution = new Solution526();
        System.out.println(solution.countArrangement(n));
    }
    
    int res = 0;
    boolean[] vis;
    List<Integer>[] match;
    public int countArrangement(int n){
        vis = new boolean[n+1];
        match = new List[n+1];
        for(int i = 0; i <= n; i++){
            match[i] = new ArrayList<Integer>();
        }
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                if(i % j == 0 || j % i == 0){
                    match[i].add(j);
                }
            }
        }
        backtrack(1, n);
        return res;
    }
    /**
     * @description: 尝试向index位置放入数
     * @param {int} index
     * @param {int} n
     * @return {*}
     */    
    public void backtrack(int index, int n){
        if(index == n+1){
            res++;
            return;
        }
        for(int x : match[index]){
            if(!vis[x]){
                vis[x] = true;
                backtrack(index+1, n);
                vis[x] = false;
            }
        }
    }
}