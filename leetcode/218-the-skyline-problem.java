import java.util.ArrayList;

/*
 * @Author: Wang Naijia
 * @Date: 2021-07-13 10:58:38
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-07-13 21:01:57
 * @Descripttion: 
 */
import java.util.*;
class Solution218 {
    public static void main(String[] args) {
        // int[][] buildings = {{2,9,10},{3,7,15},{5,12,12},{15,20,10},{19,24,8}};
        int[][] buildings = {{0,2147483647,2147483647}};
        Solution218 solution = new Solution218();
        List<List<Integer>> skyline = solution.getSkyline(buildings);
        System.out.println(skyline);
        }
    
    public List<List<Integer>> getSkyline(int[][] buildings){
        // 第 1 步：预处理
        List<int[]> buildingPoints = new ArrayList<>();
        for (int[] b : buildings) {
            // 负号表示左边高度的转折点
            buildingPoints.add(new int[]{b[0], -b[2]});
            buildingPoints.add(new int[]{b[1], b[2]});
        }

        // 第 2 步：按照横坐标排序，横坐标相同的时候，高度高的在前面
        buildingPoints.sort((a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            } else {
                // 注意：这里因为左端点传进去的时候，数值是负的，在比较的时候还按照升序排序
                return a[1] - b[1];
            }
        });

        // 第 3 步：扫描一遍动态计算出结果
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        // 哈希表，记录「延迟删除」的元素，key 为元素，value 为需要删除的次数
        Map<Integer, Integer> delayed = new HashMap<>();

        // 最开始的时候，需要产生高度差，所以需要加上一个高度为 0，宽度为 0 的矩形
        maxHeap.offer(0);
        // 为了计算高度差，需要保存之前最高的高度
        int lastHeight = 0;
        List<List<Integer>> res = new ArrayList<>();
        for (int[] buildingPoint : buildingPoints) {
            if (buildingPoint[1] < 0) {
                // 说明此时是「从下到上」，纵坐标参与选拔最大值，请见「规则 1」
                maxHeap.offer(-buildingPoint[1]);
            } else {
                // 不是真的删除 buildingPoint[1]，把它放进 delayed，等到堆顶元素是 buildingPoint[1] 的时候，才真的删除
                delayed.put(buildingPoint[1], delayed.getOrDefault(buildingPoint[1], 0) + 1);
            }

            // 如果堆顶元素在延迟删除集合中，才真正删除，这一步可能执行多次，所以放在 while 中
            // while (true) 都是可以的，因为 maxHeap 一定不会为空
            while (!maxHeap.isEmpty()) {
                int curHeight = maxHeap.peek();
                if (delayed.containsKey(curHeight)) {
                    delayed.put(curHeight, delayed.get(curHeight) - 1);
                    if (delayed.get(curHeight) == 0) {
                        delayed.remove(curHeight);
                    }
                    maxHeap.poll();
                } else {
                    break;
                }
            }

            int curHeight = maxHeap.peek();
            // 有高度差，才有关键点出现
            if (curHeight != lastHeight) {
                // 正在扫过的左端点的值
                res.add(Arrays.asList(buildingPoint[0], curHeight));
                // 当前高度成为计算高度差的标准
                lastHeight = curHeight;
            }
        }
        return res;
    }
    
    public List<List<Integer>> getSkylineMine(int[][] buildings) {
        List<List<Integer>> res = new ArrayList<>();
        
        int N = buildings.length;
        long Xmax = buildings[N-1][1];
        List<Integer> horizental = new  ArrayList<>();
        // long[][] horizental = new long[Xmax + 1L][2];
        for(int[] building : buildings){
            for(int i = building[0]; i < building[1] + 1;i++ ){
                horizental.set(i, Math.max(horizental.get(i), building[2]));
                // horizental[i][1] = Math.max(horizental[i][1], building[2]);
            }
        }
        List<Integer> height = new ArrayList<>(2);
        if(buildings[0][0] == 0){
            height.add(buildings[0][0]);
            height.add(buildings[0][2]);
            res.add(height);
        } 
        for(int i = 1; i < Xmax + 1;i++){
            height = new ArrayList<>(2);
            if( horizental.get(i) > horizental.get(i-1)){
                height.add(i);
                height.add(horizental.get(i));
                res.add(height);
            } else if(horizental.get(i) < horizental.get(i-1)){
                height.add(i-1);
                height.add(horizental.get(i));
                res.add(height);
            }
            if (i==Xmax) {
                height.add(i); // 横坐标
                height.add(0);
                res.add(height);
            }
        }
        return res;
    }
}
