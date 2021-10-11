package offer;

import java.util.List;

/*
 * @Author: Wang Naijia
 * @Date: 2021-10-06 22:18:38
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-10-07 21:09:04
 * @Descripttion: 
 */
class ListNode{
    public int val;
    public ListNode next;

    public ListNode(int val){
        this.val = val;
    }
}

class Chapter4{
    // 用哨兵节点简化链表插入操作
    public ListNode append(ListNode head, int value){
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode newNode = new ListNode(value);
        ListNode node = dummy;
        while(node.next != null){
            node = node.next;
        }
        node.next = newNode;
        return dummy.next;
    }

    // 用哨兵节点简化删除操作
    public static ListNode delete(ListNode head, int value){
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode node = dummy;
        while(node.next != null){
            if(node.next.val == value){
                node.next = node.next.next;
                break;
            }
            node = node.next;
        }
        return dummy.next;
    }
    
    // 双指针找出链表倒数第k个节点
    public ListNode removeNthFromEnd(ListNode head, int n){
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode front = head, back = dummy;
        for(int i = 0; i < n; i++){
            front = front.next;
        }
        while(front != null){
            front = front.next;
            back = back.next;
        }
        back.next = back.next.next;
        return dummy.next;
    }
    
    
    // 在有环的链表中找到环的入口节点的步骤
    // 1. 确定是否有环，并返回一个环中元素，绕环一圈就能得出环中节点数目
    private ListNode getNodeInLoop(ListNode head){
        if(head == null || head.next == null){
            return null;
        }
        ListNode slow = head.next;
        ListNode fast = slow.next;
        while(slow != null && fast != null){
            if(slow == fast){
                return slow;
            }
            slow = slow.next;
            fast = fast.next;
            if(fast != null) fast = fast.next;
        }
        return null;
    }

    // 2. 使用双指针找到环的入口节点
    public ListNode detectCycle(ListNode head){
        ListNode inLoop = getNodeInLoop(head);
        if(inLoop == null){
            return null;
        }
        int loopCount = 1;
        for(ListNode n = inLoop; n.next != inLoop; n = n.next){
            loopCount++;
        }
        ListNode fast = head;
        for(int i = 0; i < loopCount; i++){
            fast = fast.next;
        }
        ListNode slow = head;
        while(fast != slow){
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
    
    // 不统计环中节点数目
    public ListNode detectCycleII(ListNode head){
        ListNode inLoop = getNodeInLoop(head);
        if(inLoop == null){
            return null;
        }
        ListNode node = head;
        while(node != inLoop){
            // 经过相同的速度相遇处即为环的入口节点
            node = node.next;
            inLoop = inLoop.next;
        }
        return node;
    }
    
    // 用双指针找出两个单向链表的第一个重合节点
    public ListNode getIntersectionNode(ListNode headA, ListNode headB){
        int count1 = countList(headA);
        int count2 = countList(headB);
        int delta = Math.abs(count1 - count2);
        ListNode longer = count1 > count2 ? headA : headB;
        ListNode shorter = count1 > count2 ? headB : headA;
        ListNode node1 = longer;
        // 长链表的指针先移动delta步数
        for(int i = 0; i < delta; i++){
            node1 = node1.next;
        }
        ListNode node2 = shorter;
        while(node1 != node2){
            node2 = node2.next;
            node1 = node1.next;
        }
        return node1;
    }
    // 分别遍历两个链表得到其长度
    private int countList(ListNode head){
        int count = 0;
        while(head!=null){
            count++;
            head = head.next;
        }
        return count;
    }
    
    // 反转链表需要先保存当前节点的下一节点，否则链表断开
    public ListNode reverseList(ListNode head){
        ListNode prev = null;
        ListNode cur = head;
        while(cur!=null){
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }
    // 用链表表示的整数相加 : 先分别翻转链表，再逐位从个位开始相加
    public ListNode addTwoNumbers(ListNode head1, ListNode head2){
        head1 = reverseList(head1);
        head2 = reverseList(head2);
        ListNode reversedHead = addReversed(head1, head2);
        return reverseList(reversedHead);
    }
    public ListNode addReversed(ListNode head1, ListNode head2){
        ListNode dummy = new ListNode(0);
        ListNode sumNode = dummy;
        int carry = 0;
        while(head1 != null || head2 != null){
            int sum = (head1 == null ? 0 : head1.val) + (head2 == null ? 0 : head2.val) + carry;
            carry = sum >= 10 ? 1 : 0;
            sum = sum >= 10 ? sum - 10 : sum;
            ListNode newNode = new ListNode(sum);

            sumNode.next = newNode;
            sumNode = sumNode.next;
            head1 = head1 == null ? null : head1.next;
            head2 = head2 == null ? null : head2.next;
        }
        if(carry > 0){
            sumNode.next = new ListNode(carry); // 最高位有无进位
        }
        return dummy.next;
    }
    // 重排链表
    // 先把一个链表分成两半，用双指针可以找到中间节点，若链表总长为奇数，则前半段要比后半段多一个节点
    public void reorderList(ListNode head){
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;
        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next;
            // 判断fast右移后还有无后继节点
            if(fast.next != null){
                fast = fast.next;
            }
        }
        // 反转slow的后继链表
        ListNode temp = slow.next;
        slow.next = null;
        link(head, reverseList(temp), dummy);
    }
    // 连接前半段和后半段链表，若为奇数，则前半段链表要多一个
    private void link(ListNode node1, ListNode node2, ListNode head){
        ListNode prev = head;
        while(node1!=null && node2!=null){
            // 先记下来node1的后面节点
            ListNode temp = node1.next;
            prev.next = node1;
            node1.next = node2;
            prev = node2;
            // 移动两个指针
            node1 = temp;
            node2 = node2.next;
        }
        if(node1 != null){
            prev.next = node1;
        }
    }

    // 回文链表
    // 如果把链表分为前后两半，那么前半段反转后于后半段链表是相同的
    public boolean isPalindrome(ListNode head){
        if(head == null || head.next == null){
            return true;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode secondHalf = slow.next;
        if(fast.next != null){
            // 如果是奇数 多移动一个
            secondHalf = slow.next.next;
        }
        slow.next = null; // 前半段链表从中间断开
        return equals(secondHalf, reverseList(head));
    }
    private boolean equals(ListNode head1, ListNode head2){
        while(head1 != null && head2 != null){
            if(head1.val != head2.val){
                return false;
            }
            head1 = head1.next;
            head2 = head2.next;
        }
        return head1==null && head2==null;
    }
    
    
    // 展平多级链表
    public Node flatten(Node head){
        flattenGetTail(head);
        return head;
    }
    // 递归函数
    private Node flattenGetTail(Node head){
        Node node = head;
        Node tail = null;
        while(node!=null){
            Node next = node.next;// 记录下本层的next
            if(node.child != null){
                // 如果节点有子链表
                Node child = node.child;
                Node childTail = flattenGetTail(node.child); // 先获得子链表的尾节点
                node.child = null;
                // 先双向连起来头
                node.next = child;
                child.prev = node;
                childTail.next = next; //再连起来尾
                if(next!=null){
                    // next非空才连起childTail
                    next.prev = childTail;
                }
                tail = childTail; // 返回当前层的尾节点
            } else{
                tail = node; // 继续遍历直到node为空
            }
            node = next;
        }
        return tail;
    }
    // 在循环递增链表里插入新的节点
    public Node insert(Node head, int insertVal){
        Node node = new Node(insertVal);
        if(head == null){
            // 链表为空 插入的节点就是链表中的唯一节点，next只想自己
            head = node;
            head.next = head;
        } else if(head.next == head){
            head.next = node;
            node.next = head;
        } else{
            insertCore(head, node);
        }
        return head;
    }
    private void insertCore(Node head, Node node){
        Node cur = head;
        Node next = head.next;
        Node biggest = head;
        // 当不满足中间插入条件，并且没有遍历到head时（防止无限循环）
        while(!(cur.val <= node.val && next.val >= node.val) && next!=head){
            cur = next;
            next = next.next;
            if(cur.val >= biggest.val){
                biggest = cur; // 找到最大值的位置
            }
        }
        // 当满足cur.val <= node.val <= next.val时，插入
        if(cur.val <= node.val && next.val >= node.val){
            cur.next = node;
            node.next = next;
        } else{
            // 否则，node插入到biggest的后面（无论是最大还是最小）
            node.next = biggest.next;
            biggest.next = node;
        }
    }
}

// 双向链表节点定义
class Node{
    public int val;
    public Node prev;
    public Node next;
    public Node child;
    public Node(int val) {
        this.val = val;
        this.next = null;
        this.prev = null;
        this.child = null;
    }
}