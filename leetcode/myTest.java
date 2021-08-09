/*
 * @Author: Wang Naijia
 * @Date: 2021-06-20 10:33:28
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-07-22 02:44:05
 * @Descripttion: 
 */

import java.util.*;

public class myTest {
    public static void main(String[] args){
        List<String> s = Arrays.asList("ab","ba","cd","dc","ef","fe","gh","hg","ij","ji","kl","lk","mn","nm","op","po");
        myTest solution = new myTest();
        int result = solution.maxLength(s);
        System.out.println("result::::"+result);
    }

    public int maxLength(List<String> arr){
        // 局部的letterOld变量
        int[] letterSet = new int[26];
        //没有赋值，那么就会使用默认值，作为int类型的数组，默认值是0
        int ans = dfs(arr,0,letterSet);
        return ans;
    }
    
    int dfs(List<String> arr, int pos, int[] letterSet){
        /**
         * @description: 
         * @param {*} pos: 当前访问到的arr下标
         * @return {*} 返回字符数组长度
         */        
        if(pos == arr.size()){
            for(int i:letterSet){
                System.out.print(i);
            }
            System.out.print("\n");
            return 0;
        }
        
        int [] letterOld = new int[26]; 
        // 先将目前的letterSet存成old，注意避坑：不能直接赋letterset给letterold，二者指向的同一个地址，不会改变
        for(int i = 0; i < letterSet.length; i++){
            letterOld[i] = letterSet[i];
        }

        if(isUnique(arr.get(pos), letterOld)){
            // 如果执行了isUnique,letterOld对应的会被改写，增加相应的字符出现次数
            int curLen = arr.get(pos).length();
            int len1 = curLen + dfs(arr, pos+1, letterOld); // 取当前字符串
            int len2 = dfs(arr, pos+1, letterSet);// 不取当前字符串
            int ans = Math.max(len1, len2);
            return ans;
        }
        int ans = dfs(arr, pos+1, letterSet);
        return ans;
    }
    
    boolean isUnique(String string, int[] letterSet){
    /**
     * @description: 
     * @param {*} string: 检查string字符串中的每个字母是否唯一出现；注意：同时也要确保string内部没有重复字符
     * @return {*} 返回boolean型
     */        
        for(int i = 0; i < string.length(); i++){
            char ch = string.charAt(i);
            letterSet[ch - 'a']++;
        }
        for(int i = 0; i < 26; i++){
            if(letterSet[i] > 1){
                return false;
            }
        }
        return true;
    }
}
