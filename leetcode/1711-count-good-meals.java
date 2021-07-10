/*
 * @Author: Wang Naijia
 * @Date: 2021-07-07 12:23:39
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-07-07 12:33:03
 * @Descripttion: 
 */
import java.util.*;
class Solution1711 {
    int mod = (int)1e9+7;
    public int countPairs(int[] deliciousness) {
        int N = deliciousness.length;
        long ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < N; i++){
            int x = deliciousness[i];
            for(int other:map.keySet()){
                if(isPowerOfTwo( other + x )) ans += map.get(other);
            }
            map.put(x, map.getOrDefault(x, 0) + 1);
        }
        return (int)(ans % mod);
    }
    public boolean isPowerOfTwo(int n){
        return n>0 && (n&(n-1)) == 0;
    }
}
