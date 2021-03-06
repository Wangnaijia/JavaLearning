/*
 * @Author: Wang Naijia
 * @Date: 2021-07-06 17:39:08
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-07-14 08:11:02
 * @Descripttion: 给你一个数组 orders，表示客户在餐厅中完成的订单，确切地说， orders[i]=[customerNamei,tableNumberi,foodItemi] ，其中 customerNamei 是客户的姓名，tableNumberi 是客户所在餐桌的桌号，而 foodItemi 是客户点的餐品名称。
请你返回该餐厅的 点菜展示表 。在这张表中，表中第一行为标题，其第一列为餐桌桌号 “Table” ，后面每一列都是按字母顺序排列的餐品名称。接下来每一行中的项则表示每张餐桌订购的相应餐品数量，第一列应当填对应的桌号，后面依次填写下单的餐品数量。
注意：客户姓名不是点菜展示表的一部分。此外，表中的数据行应该按餐桌桌号升序排列。
示例 1：
    输入：orders = [["David","3","Ceviche"],["Corina","10","Beef Burrito"],["David","3","Fried Chicken"],["Carla","5","Water"],["Carla","5","Ceviche"],["Rous","3","Ceviche"]]
    输出：[["Table","Beef Burrito","Ceviche","Fried Chicken","Water"],["3","0","2","1","0"],["5","0","1","0","1"],["10","1","0","0","0"]] 
    解释：
    点菜展示表如下所示：
    Table,Beef Burrito,Ceviche,Fried Chicken,Water
    3    ,0           ,2      ,1            ,0
    5    ,0           ,1      ,0            ,1
    10   ,1           ,0      ,0            ,0
    对于餐桌 3：David 点了 "Ceviche" 和 "Fried Chicken"，而 Rous 点了 "Ceviche"
    而餐桌 5：Carla 点了 "Water" 和 "Ceviche"
    餐桌 10：Corina 点了 "Beef Burrito" 
示例 2：
    输入：orders = [["James","12","Fried Chicken"],["Ratesh","12","Fried Chicken"],["Amadeus","12","Fried Chicken"],["Adam","1","Canadian Waffles"],["Brianna","1","Canadian Waffles"]]
    输出：[["Table","Canadian Waffles","Fried Chicken"],["1","2","0"],["12","0","3"]] 
    解释：
    对于餐桌 1：Adam 和 Brianna 都点了 "Canadian Waffles"
    而餐桌 12：James, Ratesh 和 Amadeus 都点了 "Fried Chicken"
示例 3：
    输入：orders = [["Laura","2","Bean Burrito"],["Jhon","2","Beef Burrito"],["Melissa","2","Soda"]]
    输出：[["Table","Bean Burrito","Beef Burrito","Soda"],["2","1","1","1"]]
提示：
    1 <= orders.length <= 5 * 10^4
    orders[i].length == 3
    1 <= customerNamei.length, foodItemi.length <= 20
    customerNamei 和 foodItemi 由大小写英文字母及空格字符 ' ' 组成。
    tableNumberi 是 1 到 500 范围内的整数。
 */

import java.util.*;

class Solution1418 {
    public static void main(String[] args) {
        String[][] orders = {{"James","12","Fried Chicken"},{"Ratesh","12","Fried Chicken"},{"Amadeus","12","Fried Chicken"},{"Adam","1","Canadian Waffles"},{"Brianna","1","Canadian Waffles"}};
        List<List<String>> myOrders = new ArrayList<>();
        for(String[] order : orders){
            myOrders.add(Arrays.asList(order)); // 数组转化为list
        }
        Solution1418 solution = new Solution1418();
        List<List<String>> res = solution.displayTable(myOrders);
        System.out.println(res);
    }
    public List<List<String>> displayTable(List<List<String>> orders) {
        List<List<String>> ans = new ArrayList<>();
        Map<Integer, Map<String, Integer>> tm = new HashMap<>();// 桌号：{餐品：个数}
        Set<String> ts = new HashSet<>(); // 餐品
        for(List<String> o: orders){
            String c = o.get(0), t = o.get(1), f = o.get(2);// c:顾客名，t:桌号，f:菜品
            Integer tidx = Integer.parseInt(t); // str 转 int
            ts.add(f); // 用集合存储菜品
            Map<String, Integer> map = tm.getOrDefault(tidx, new HashMap<>()); // 当前桌号的点菜（若无先申请）
            map.put(f, map.getOrDefault(f, 0) + 1); // 当前桌号的点菜（菜品名字，当前桌号当前菜品的数目+1（若无为0））
            tm.put(tidx, map); // 将当前桌的点菜放入tm的表中
        }
        int n = tm.size() + 1, m = ts.size() + 1;
        // 构造title & 手动排序
        List<String> foods = new ArrayList<>(ts); // 去过重的set转为list
        Collections.sort(foods);
        List<String> title = new ArrayList<>(); // 构造title
        title.add("Table");
        title.addAll(foods);
        ans.add(title); // ans的第一个元素为title
        // 构造内容， 手动排序
        List<Integer> tables = new ArrayList<>(tm.keySet());  // tm表的键值为桌号
        Collections.sort(tables); // 排序
        for(int tidx : tables){ // 取每张桌子tidx
            Map<String, Integer> map = tm.get(tidx); //tidx的{菜品：数量} 
            List<String> cur = new ArrayList<>(); // 当前桌号的所有统计
            cur.add(tidx + ""); // ans的第tidx行，第1列为桌号
            for(String food : foods){ // 取foods集合中的每个菜品
                cur.add(map.getOrDefault(food, 0) + ""); // cur行添加（food菜的出现次数）
            }
            ans.add(cur); // 添加行
        }
        return ans;
    }   
}
