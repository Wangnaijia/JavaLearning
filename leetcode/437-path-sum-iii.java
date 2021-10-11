/*
 * @Author: Wang Naijia
 * @Date: 2021-09-28 11:13:48
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-09-28 14:34:30
 * @Descripttion: 
 */
import java.util.*;
class Solution{
    public int pathSum(TreeNode root, int targetSum){
        // key是前缀和，value是大小为key的前缀和出现的次数
        Map<Integer, Integer> prefixSumCount = new HashMap<>();
        // 前缀和为0 的一条路径 可能性为1
        prefixSumCount.put(0, 1);
        // 前缀和的递归回溯思路，初始sum为0
        return recursionPathSum(root, prefixSumCount, targetSum, 0);
    }
    /**
     * @description: 前缀和的递归回溯：从当前节点反推到根节点，有且只有一条路径，如果此前有和为currSum - target，而当前的和为currSum，两者的差即为target
     * 所以前缀和对当前路径来说是唯一的，当前记录的前缀和，在回溯结束回到本层时需要去除
     * @param {TreeNode} node 树结点
     * @param {Map<Integer,Integer>} prefix 前缀和
     * @param {int} targetSum 目标值
     * @param {int} start 当前路径和
     * @return {*} 满足题意的解
     */    
    private int recursionPathSum(TreeNode node, Map<Integer, Integer> prefixSumCount, int targetSum, int currSum){
        // 1. 递归终止条件
        if(node == null){
            return 0;
        }
        // 2. 本层要做的事情
        int res = 0;
        // 当前路径上的和
        currSum += node.val;
        // 核心：看root到当前节点这条路上是否存在节点前缀和加target为currSum的路径
        // 当前节点>root反推，有且只有一条路径，如果此前有和为currSum - target，而当前和为currSum，两者之差就是target
        // currSum - target相当于找路径的起点，起点的sum+target=currSum，当前点到起点的距离为target
        res += prefixSumCount.getOrDefault(currSum - targetSum, 0);
        // 更新路径上当前节点前缀和的个数
        prefixSumCount.put(currSum, prefixSumCount.getOrDefault(currSum, 0) + 1);

        // 3. 进入下一层
        res += recursionPathSum(node.left, prefixSumCount, targetSum, currSum);
        res += recursionPathSum(node.right, prefixSumCount, targetSum, currSum);
        // 4. 回到本层，恢复状态，去除当前节点前缀和数量
        prefixSumCount.put(currSum, prefixSumCount.get(currSum) - 1);
        return res;

    }
}