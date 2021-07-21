/*
 * @Author: Wang Naijia
 * @Date: 2021-07-22 01:22:22
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-07-22 02:32:54
 * @Descripttion: 
 */

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}


class Solution138 {
    public Node copyRandomList(Node head) {
        // 关键是复制random指针的关系
        if(head==null){
            return null;
        }
        Node res = head;
        // 第1步，在每个原节点后面创建一个新节点
        // 1->1'->2->2'->3->3'
        while(res != null){
            Node newNode = new Node(res.val);
            newNode.next = res.next;
            res.next = newNode;
            res = newNode.next;
        }
        res = head;
        // 第2步，设置新节点的随机节点
        while(res != null){
            if(res.random != null){
                res.next.random = res.random.next; // 新节点的随机节点为原节点的随机节点的新节点
            }
            res = res.next.next;
        }
        Node dummy = new Node(-1);
        res = head;
        Node cur = dummy;
        // 第3步，把两个链表分离
        while(res != null){
            cur.next = res.next; // dummy的next指向res的新节点
            cur = cur.next; // cur后移一位
            res.next = cur.next; // 原节点指向，后移两位
            res = res.next; // 下一个res为当前res的next新节点
        }
        return dummy.next;
    }
}
