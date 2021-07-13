/*
 * @Author: Wang Naijia
 * @Date: 2021-07-10 10:36:05
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-07-11 09:54:24
 * @Description: 创建一个基于时间的键值存储类 TimeMap，它支持下面两个操作：
1. set(string key, string value, int timestamp)
存储键 key、值 value，以及给定的时间戳 timestamp。
2. get(string key, int timestamp)
返回先前调用 set(key, value, timestamp_prev) 所存储的值，其中 timestamp_prev <= timestamp。
如果有多个这样的值，则返回对应最大的  timestamp_prev 的那个值。
如果没有值，则返回空字符串（""）。
示例 1：
    输入：inputs = ["TimeMap","set","get","get","set","get","get"], inputs = [[],["foo","bar",1],["foo",1],["foo",3],["foo","bar2",4],["foo",4],["foo",5]]
    输出：[null,null,"bar","bar",null,"bar2","bar2"]
    解释：  
        TimeMap kv;   
        kv.set("foo", "bar", 1); // 存储键 "foo" 和值 "bar" 以及时间戳 timestamp = 1   
        kv.get("foo", 1);  // 输出 "bar"   
        kv.get("foo", 3); // 输出 "bar" 因为在时间戳 3 和时间戳 2 处没有对应 "foo" 的值，所以唯一的值位于时间戳 1 处（即 "bar"）   
        kv.set("foo", "bar2", 4);   
        kv.get("foo", 4); // 输出 "bar2"   
        kv.get("foo", 5); // 输出 "bar2"   
 */
import java.util.*;
class TimeMap {
    public static void main(String[] args) {
        TimeMap kv = new TimeMap();
        kv.set("foo", "bar", 1); // 存储键 "foo" 和值 "bar" 以及时间戳 timestamp = 1   
        System.out.println(kv.get("foo", 1));  // 输出 "bar"   
        System.out.println(kv.get("foo", 3)); // 输出 "bar" 因为在时间戳 3 和时间戳 2 处没有对应 "foo" 的值，所以唯一的值位于时间戳 1 处（即 "bar"）   
        kv.set("foo", "bar2", 4);
        System.out.println(kv.get("foo", 4)); // 输出 "bar2"   
        System.out.println(kv.get("foo", 5)); // 输出 "bar2"   
    }
    // comparable是类本身继承的接口，可用于定义比较方法，然后用在排序中
    class Pair implements Comparable<Pair>{
        int timestamp;
        String value;
        
        public Pair(int timestamp, String value){
            this.timestamp = timestamp;
            this.value = value;
        }
        // 自己定义的方法
        public boolean equals(Object obj){
            if(obj instanceof Pair) {
                Pair pair2 = (Pair) obj;
                return this.timestamp==pair2.timestamp && this.value.equals(pair2.value);
            }
            return false;
        }
        // 重写
        public int compareTo(Pair pair2){
            if(this.timestamp != pair2.timestamp){
                return this.timestamp - pair2.timestamp; // 为正，则this.timestamp大，为负，则pair2.timestamp大
            } else{
                return this.value.compareTo(pair2.value); // timestamp一致的比较value，为0说明相等
            }
        }
    }

    Map<String, List<Pair>> map;
     /** Initialize your data structure here. */
    public TimeMap() {
        map = new HashMap<String, List<Pair>>();
    }
    
    public void set(String key, String value, int timestamp) {
        List<Pair> pairs = map.getOrDefault(key, new ArrayList<Pair>()); // 取出key对应的List<Pair>，若有则在List后面add一个，若无则new一个
        pairs.add(new Pair(timestamp, value));
        map.put(key, pairs); // 装入hashmap
    }
    /**
     * @description: 返回先前调用 set(key, value, timestamp_prev) 所存储的值，其中 timestamp_prev <= timestamp。
                        如果有多个这样的值，则返回对应最大的 timestamp_prev 的那个值。
                        如果没有值，则返回空字符串（""）。
     * @param {String} key 
     * @param {int} timestamp
     * @return {*}
     */    
    public String get(String key, int timestamp) {
        List<Pair> pairs = map.getOrDefault(key, new ArrayList<Pair>()); // 动态数组
        // 使用一个大于所有value的字符串，以确保在pairs中含有timestamp的情况下也返回大于timestamp的位置
        Pair pair = new Pair(timestamp, String.valueOf((char)127));
        int i = binarySearch(pairs, pair); // 以timestamp为查找标准
        if (i > 0) {
            return pairs.get(i-1).value;
        }
        return "";
    }
    /**
     * @description: 根据 timestamp 在 pairs 中二分查找。我们需要找到最大的不超过 timestamp 的时间戳，
     *                  对此，我们可以二分找到第一个超过 timestamp 的二元组下标 i，若 i>0 则说明目标二元组存在，
     *                  即 pairs[i−1]，否则二元组不存在，返回空字符串。
     * @param {List<Pair>} pairs 数组，Pair类型
     * @param {Pair} target 比较对象，Pair类型
     * @return {int} 二分查找结果的下标
     */    
    private int binarySearch(List<Pair> pairs, Pair target){ //pairs为value, timestamp
        int low = 0, high = pairs.size() - 1;
        if(high < 0 || pairs.get(high).compareTo(target) <= 0){
            // high 的timestamp已经小于target的timestamp，可以直接返回high+1
            return high + 1;
        }
        while(low < high){
            int mid = (high - low) / 2 + low;
            Pair pair = pairs.get(mid);
            if(pair.compareTo(target) <= 0){
                // 当前mid 的timestamp小于target的timestamp，继续向上查找 
                low = mid + 1;
            } else{
                high = mid;
            }
        }
        return low;
    }
}
/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */    