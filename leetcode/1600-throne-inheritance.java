/*
 * @Author: Wang Naijia
 * @Date: 2021-06-20 13:54:25
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-06-21 11:48:31
 * @Descripttion: 
 */
package leetcode;
import java.util.*;

class ThroneInheritance {
    String kingName;
    Map<String, List<String>> map = new HashMap<>();
    Set<String> deathSet = new HashSet<>();
    
    public ThroneInheritance(String kingName) {
        this.kingName = kingName;
        map.put(kingName, new LinkedList<>());
    }
    
    public void birth(String parentName, String childName) {
        map.get(parentName).add(childName);
        map.put(childName, new LinkedList<>());
    }
    
    public void death(String name) {
        deathSet.add(name);

    }
    
    public List<String> getInheritanceOrder() {
        List<String> s = new LinkedList<>();
        dfs(s, kingName);
        return s;
    }
    
    private void dfs(List<String> result, String name){
        if(!deathSet.contains(name)){
            result.add(name);
        }
        for(String subName: map.get(name)){
            dfs(result, subName);
        }
    }
    
    
    public static void main(String[] args){
        String kingName = "king";

        ThroneInheritance obj = new ThroneInheritance(kingName);
        obj.birth("king", "andy");
        obj.birth("king", "bob");
        obj.birth("king", "catherine");
        obj.birth("andy", "matthew");
        obj.birth("bob", "alex");
        
        List<String> result = obj.getInheritanceOrder();
        System.out.println("result::::"+result);
        
        obj.death("bob");
        result = obj.getInheritanceOrder();
        System.out.println("result_after_death::::"+result);
    }
}
    /**
     * Your ThroneInheritance object will be instantiated and called as such:
     * ThroneInheritance obj = new ThroneInheritance(kingName);
     * obj.birth(parentName,childName);
     * obj.death(name);
     * List<String> param_3 = obj.getInheritanceOrder();
     */
   

