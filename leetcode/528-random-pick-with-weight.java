import java.util.Arrays;

/*
 * @Author: Wang Naijia
 * @Date: 2021-08-30 09:31:55
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-08-30 09:42:15
 * @Descripttion: 
 */
class Solution528{
    int[] pre;
    int total;

    public Solution(int[] w){
        pre = new int[w.length];
        pre[0] = w[0];
        for(int i = 0; i < w.length; i++){
            pre[i] = pre[i-1] + w[i];
        }
        total = Arrays.stream(w).sum();
    }

    public int pickIndex(){
        int x = (int) (Math.random() * total) + 1;
        return binarySearch(x);
    }

    private int binarySearch(int x){
        int low = 0, high = pre.length - 1;
        while(low <= high){
            int mid = (high + low + 1) >> 1;
            if(pre[mid] < x){
                low = mid + 1;
            } else{
                high = mid;
            }
        }
        return low;
    }
}