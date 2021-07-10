/*
 * @Author: Wang Naijia
 * @Date: 2021-06-30 10:39:57
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-06-30 13:52:34
 * @Descripttion: 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
示例 1：
    输入：root = [1,2,3,null,null,4,5]
    输出：[1,2,3,null,null,4,5]
示例 2：
    输入：root = []
    输出：[]
示例 3：
    输入：root = [1]
    输出：[1]
示例 4：
    输入：root = [1,2]
    输出：[1,2]
提示：
    树中结点数在范围 [0, 104] 内
    -1000 <= Node.val <= 1000

 */
/**
 * Definition for a binary tree node.
 * 
 */

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
package leetcode;

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution297{
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder ser = new StringBuilder();
        Queue<TreeNode> Q = new LinkedList<>();
        Q.add(root);
        int size;
        while((size = Q.size()) > 0){
            while(size-- > 0){
                TreeNode cur = Q.poll();
                if(cur == null){
                    ser.append("n,");
                    continue;
                } else {
                    ser.append(cur.val + 1000).append(",");
                }

                Q.add(cur.left);
                Q.add(cur.right);
            }
        }
        if(ser.length()>0) ser.deleteCharAt(ser.length() - 1);
        return ser.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data==null || data.isEmpty() || data.charAt(0)=='n') return null;
        int ptr = 0;
        int len = data.length();
        // 获取根节点
        int val = 0;
        char c;
        while((c = data.charAt(ptr)) != ','){
            val = val * 10 + c - '0';
            ++ptr;
        }
        val -= 1000;
        ++ptr;

        Queue<TreeNode> Q = new LinkedList<>();
        TreeNode root = new TreeNode(val);
        Q.add(root);

        while(ptr<len){
            int size = Q.size();
            while(size-- > 0){
                TreeNode cur = Q.poll();
                for(int j = 0; j < 2; j++){
                    if(data.charAt(ptr) == 'n'){
                        ptr += 2;
                        if (j == 0) cur.left = null;
                        else cur.right = null;
                        continue;
                    }
                    val = 0;
                    while((c = data.charAt(ptr))!=','){
                        val = val * 10 + c -'0';
                        ++ptr;
                    }
                    val -= 1000;
                    ++ptr;

                    TreeNode child = new TreeNode(val);
                    if (j == 0) cur.left = child;
                    else cur.right = child;
                    
                    Q.add(child);
                }
            }
        }
        return root;
    }
}

