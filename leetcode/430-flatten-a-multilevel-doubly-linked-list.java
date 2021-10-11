/*
 * @Author: Wang Naijia
 * @Date: 2021-09-24 10:40:38
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-09-24 11:07:35
 * @Descripttion: 
 */
class BiNode{
    public int val;
    public BiNode prev;
    public BiNode next;
    public BiNode child;
    public BiNode(int val) {
        this.val = val;
        this.next = null;
        this.prev = null;
        this.child = null;
    }
};

class Solution430{
    public BiNode myflatten(BiNode head){
        BiNode dummy = new BiNode(0);
        BiNode pre = new BiNode(0);
        dummy.next = head;
        pre = head;
        while(pre != null){
            if(pre.child != null){
                BiNode cur = flatten(pre.child);
                BiNode end = cur;
                while(end.next != null){
                    end = end.next;
                }
                end.next = pre.next;
                pre.next.prev = end;
                pre.next = cur;
                cur.prev = pre;
            }else{
                pre = pre.next;
            }
        }
        return dummy.next;
    }
    public BiNode flatten(BiNode head){
        BiNode dummy = new BiNode(0);
        dummy.next = head;
        while(head != null){
            if(head.child == null){
                head = head.next;
            } else {
                BiNode tmp = head.next;
                BiNode chead = flatten(head.child);
                head.next = chead;
                chead.prev = head;
                head.child = null;
                // 还要去找一遍尾节点
                while(head.next != null) head = head.next;
                head.next = tmp;
                if(tmp != null) tmp.prev = head;
                head = tmp;
            }
        }
        return dummy.next;
    }
    // 优化： 额外设计递归函数dfs用于返回扁平化后的链表尾节点
    public BiNode DFSflatten(BiNode head){
        dfs(head);
        return head;
    }
    BiNode dfs(BiNode head){
        BiNode last = head;
        while(head != null){
            if(head.child == null){
                last = head;
                head = head.next;
            } else{
                BiNode tmp = head.next;
                BiNode childLast = dfs(head.child);
                head.next = head.child;
                head.child.prev = head;
                head.child = null;
                if(childLast != null) childLast.next = tmp;
                if(tmp != null) tmp.prev = childLast;
                last = head;
                head = childLast;
            }
        }
        return last;
    }
}