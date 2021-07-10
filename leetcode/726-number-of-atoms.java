/*
 * @Author: Wang Naijia
 * @Date: 2021-07-10 14:59:21
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-07-10 15:25:53
 * @Descripttion: 
 */
import java.util.*;
class Solution726 {
    public static void main(String[] args) {
        String formula = "Mg(OH)2";
        Solution726 solution = new Solution726();
        String ans = solution.countOfAtoms(formula);
        System.out.println(ans);

    }
    
    int i,n;
    String formula;
    public String countOfAtoms(String formula) {
        this.i = 0;
        this.n = formula.length();
        this.formula = formula;
        
        Deque<Map<String, Integer>> stack = new LinkedList<Map<String, Integer>>();
        stack.push(new HashMap<String, Integer>());
        while(i < n){
            char ch = formula.charAt(i);
            if(ch=='('){
                i++;
                // 将一个空的哈希表压入栈中，准备统计括号中的字符
                stack.push(new HashMap<String, Integer>());
            } else if(ch==')'){
                i++;
                int num = parseNum(); //括号右侧数字
                Map<String, Integer> popMap = stack.pop(); // 弹出括号中的原子数量
                Map<String, Integer> topMap = stack.peek();
                for(Map.Entry<String, Integer> entry: popMap.entrySet()){
                    String atom = entry.getKey();
                    int v = entry.getValue();
                    topMap.put(atom, topMap.getOrDefault(atom, 0) + v * num); // 将括号内的原子数量乘上num, 加到上一层的原子数量中
                }
            } else {
                String atom = parseAtom();
                int num = parseNum();
                Map<String, Integer> topMap = stack.peek();
                topMap.put(atom, topMap.getOrDefault(atom, 0) + num); // 统计原子数量
            }
        }
        
        Map<String, Integer> map = stack.pop();
        TreeMap<String, Integer> treeMap = new TreeMap<String, Integer>(map);

        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String,Integer> entry: treeMap.entrySet()){
            String atom = entry.getKey();
            int count = entry.getValue();
            sb.append(atom);
            if(count>1){
                sb.append(count);
            }
        }
        return sb.toString();
    }
    
    public String parseAtom(){
        StringBuffer sb = new StringBuffer();
        sb.append(formula.charAt(i++)); // 扫描首字母
        while(i<n && Character.isLowerCase(formula.charAt(i))){
            // 扫描首字母后的小写字母
            sb.append(formula.charAt(i++));
        }
        return sb.toString();
    }

    public int parseNum(){
        if(i==n || !Character.isDigit(formula.charAt(i))){
            return 1; // 不是数字，视作1
        }
        int num = 0;
        while(i<n && Character.isDigit(formula.charAt(i))){
            num = num*10 + formula.charAt(i++) - '0';//扫描数字
        }
        return num;
    }
}