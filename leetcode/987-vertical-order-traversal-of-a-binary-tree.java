/*
 * @Author: Wang Naijia
 * @Date: 2021-07-31 17:43:38
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-07-31 18:01:31
 * @Descripttion: 
 */
import java.util.*;
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) {
        this.val = val;
    }
    TreeNode(int val, TreeNode left, TreeNode right){
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution987 {
    PriorityQueue<int[]> q = new PriorityQueue<>((a,b)->{ // col, row, val
        if(a[0]!=b[0]) return a[0]-b[0];
        if(a[1]!=b[1]) return a[1]-b[1];
        return a[2]-b[2];
    });
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        int[] info = new int[] {0,0,root.val};
        q.add(info);
        dfs(root, info);
        List<List<Integer>> ans = new ArrayList<>();
        while(!q.isEmpty()){
            List<Integer> tmp = new ArrayList<>();
            int[] poll = q.peek(); // 堆
            while(!q.isEmpty() && q.peek()[0] == poll[0]) tmp.add(q.poll()[2]); // 弹出并返回堆首元素
            ans.add(tmp);
        }
        return ans;
    }
    void dfs(TreeNode root, int[] info){
        if(root.left != null){
            int[] left_info = new int[] {info[0] - 1, info[1] + 1, root.left.val};
            q.add(left_info);
            dfs(root.left, left_info);
        }
        if(root.right != null){
            int[] right_info = new int[] {info[0] + 1, info[1] + 1, root.right.val};
            q.add(right_info);
            dfs(root.right, right_info);
        }
    }
}