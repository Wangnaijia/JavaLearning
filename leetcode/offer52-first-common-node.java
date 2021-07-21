/*
 * @Author: Wang Naijia
 * @Date: 2021-07-22 01:21:30
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-07-22 01:24:07
 * @Descripttion: 
 */

// Definition for singly-linked list.
public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
 }
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode curA = headA;
        ListNode curB = headB;
        int lenA = 0;
        int lenB = 0;
        while(curA != null){
            lenA++;
            curA = curA.next;
        }
        while(curB != null){
            lenB++;
            curB = curB.next;
        }
        curA = headA;
        curB = headB;
        if(lenB > lenA){
            int tmplen = lenA;
            lenA = lenB;
            lenB = tmplen;
            ListNode tmp = curA;
            curA = curB;
            curB = tmp;
        }
        int diff = lenA - lenB;
        while(diff-- > 0){
            curA = curA.next;
        }
        while(curA!=null){
            if(curA == curB){
                return curA;
            }
            curA = curA.next;
            curB = curB.next;
        }
        return null;

    }
}
