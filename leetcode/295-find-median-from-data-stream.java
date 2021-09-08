import java.util.PriorityQueue;

public class 295-find-median-from-data-stream {
    
}
/*
 * @Author: Wang Naijia
 * @Date: 2021-08-27 09:56:18
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-08-27 10:11:36
 * @Descripttion: 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
例如，
    [2,3,4] 的中位数是 3
    [2,3] 的中位数是 (2 + 3) / 2 = 2.5
设计一个支持以下两种操作的数据结构：
    void addNum(int num) - 从数据流中添加一个整数到数据结构中。
    double findMedian() - 返回目前所有元素的中位数。
示例：
    addNum(1)
    addNum(2)
    findMedian() -> 1.5
    addNum(3) 
    findMedian() -> 2
进阶:
    如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
    如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？
思路：
    大小顶堆，让操作空间一直在数据中间，可以在O(1)复杂度内取得当前中位数，先搞一个大顶堆，这样数据是从小到大（大的是根）的．
    然后弄一个小顶堆，让数据还是从小到大（小的是根）
    这样的话两个根就把中间你需要操作的部分给夹出来了．
    大体就是这样，如果你往里插入数字的话只需要与两个根比较～～
    为了使＂夹出来＂的部分一直在中间，你需要对堆进行调整，保证大顶堆（也就是数比较小的那个）的数字数量一直是等于小顶堆（数字比较大的那个）或者比小顶堆大１．这样能保证两个堆夹着的是中间．

 */
class MedianFinder {
    // 用两个优先队列（堆）维护整个数据流
    PriorityQueue<Integer> l = new PriorityQueue<>((a,b)->b-a);
    PriorityQueue<Integer> r = new PriorityQueue<>((a,b)->a-b);
    /** initialize your data structure here. */
    
    public void addNum(int num) {
        int s1 = l.size(), s2 = r.size();
        //插入前两者大小相同，说明插入前数据流元素个数为偶数，插入后变为奇数。我们期望操作完达到「l 的数量为 r 多一，同时双堆维持有序」
        if(s1 == s2){
            // r为空，说明插入的是首个元素，直接添加到l；
            // r不为空，且num<=r.peek() 说明num的插入位置不在r中
            if(r.isEmpty() || num <= r.peek()){
                l.add(num);
            } else{
                // r不为空，且num>r.peek() 说明num的插入位置在r中，此时将r的堆顶元素放到l中，再将num放到r
                l.add(r.poll());
                r.add(num);
            }
        } else{
            // 插入前两者大小不同，说明前数据流元素个数为奇数，插入后变为偶数。我们期望操作完达到「l 和 r 数量相等，同时双堆维持有序」，进一步分情况讨论（此时 l 必然比 r 元素多一）
            if(l.peek() <= num){
                r.add(num);
            } else {
                r.add(l.poll());
                l.add(num);
            }
        }
    }
    
    public double findMedian() {
        int s1 = l.size(), s2 = r.size();
        if(s1 == s2){
            return (l.peek() + r.peek()) * 1.0 / 2;
        } else {
            return l.peek();
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */