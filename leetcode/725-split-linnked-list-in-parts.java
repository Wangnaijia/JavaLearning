/*
 * @Author: Wang Naijia
 * @Date: 2021-09-22 13:09:12
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-09-22 14:09:54
 * @Descripttion: 
 */

import java.util.*;
// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}
class Solution725{
    public ListNode[] splitListToParts(ListNode head, int k){
        ListNode[] res = new ListNode[k];
        if(head==null) return res;
        int len = 0;
        ListNode curr = head;
        while(curr != null){
            curr = curr.next;
            len+=1;
        }
        curr = head;
        // perNode 表示平均的个数，mod表示多出来的个数
        // 由于mod是不能均摊的，所以只要有多的mod，就多走一步
        int perNode = len / k, mod = len % k;
        for(int i = 0; i < k; i++){
            // dummy Node
            ListNode dummy = new ListNode(0);
            dummy.next = curr;
            ListNode pre = dummy;
            for(int j = 0; j < perNode && curr != null; j++){
                // 平均的部分
                curr = curr.next;
                pre = pre.next;
            }
            if(mod > 0){
                // 多加的部分
                curr = curr.next;
                pre = pre.next;
                mod--;
            }
            pre.next = null;
            res[i] = dummy.next;
        }
        return res;
    }
}
