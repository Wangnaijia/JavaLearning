package offer;
import java.util.*;


/*
 * @Author: Wang Naijia
 * @Date: 2021-10-05 12:27:08
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-10-06 14:40:02
 * @Descripttion: 
 */
class SolutionOffer11{
    public int findMaxLength(int[] nums){
        Map<Integer,Integer> sumToIndex = new HashMap<>();
        // key为sum值，value为下标
        sumToIndex.put(0,-1);
        int sum = 0;
        int maxLength = 0;
        for(int i = 0; i < nums.length; i++){
            sum += nums[i] == 0 ? -1 : 1; // 把0变成-1
            // 如果哈希表中存在sum，则求出i-j的长度并与之前的长度最大值比较，取两者中的最大值
            if(sumToIndex.containsKey(sum)){
                maxLength = Math.max(maxLength, i - sumToIndex.get(sum));
            } else {
                sumToIndex.put(sum, i);
            }
        }
        return maxLength;
    }
    public int pivotIndex(int[] nums){
        int total = 0;
        for(int num : nums){
            total += num;
        }
        int sum = 0;
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
            if(sum - nums[i] == total - sum){
                return i;
            }
        }
        return -1;
    }
    // 检查s1是否为s2的子串
    public boolean checkInclution(String s1, String s2){
        // 如果s1的长度超过s2，则必不可能
        if(s2.length() < s1.length()){
            return false;
        }
        int[] counts = new int[26];
        // 滑动窗的长度为s1的length
        for(int i = 0; i < s1.length(); i++){
            counts[s1.charAt(i) - 'a']++;
            counts[s2.charAt(i) - 'a']--;
        }
        if(areAllZero(counts)){
            return true;
        }
        for(int i = s1.length(); i < s2.length(); i++){
            counts[s2.charAt(i) - 'a']--;
            counts[s2.charAt(i - s1.length()) - 'a']++;
            if(areAllZero(counts)){
                return true;
            }
        }
        return false;
    }
    private boolean areAllZero(int[] counts){
        for(int count : counts){
            if(count != 0){
                return false;
            }
        }
        return true;
    }
    // 检查s1中s2所有变位词的下标
    public List<Integer> findAnagrams(String s1, String s2){
        List<Integer> indices = new LinkedList<>();
        // 如果s2的长度超过s1，则必不可能
        if(s1.length() < s2.length()){
            return indices;
        }
        int[] counts = new int[26];
        int i = 0;
        // 滑动窗的长度为s1的length
        for(; i < s2.length(); i++){
            counts[s2.charAt(i) - 'a']++;
            counts[s2.charAt(i) - 'a']--;
        }
        if(areAllZero(counts)){
            indices.add(0);
        }
        for(; i < s1.length(); i++){
            counts[s1.charAt(i) - 'a']--;
            counts[s1.charAt(i - s2.length()) - 'a']++;
            if(areAllZero(counts)){
                indices.add(i - s2.length() + 1);
            }
        }
        return indices;
    }
    
    public int lengthOfLongestSubstring(String s){
        if(s.length() == 0){
            return 0;
        }
        int[] counts = new int[256];// ASCII码总共有256个
        int i = 0, j = -1, longest = 1;
        for(;i < s.length(); i++){ // 右指针右移
            counts[s.charAt(i)]++;
            while(hasGreaterThan1(counts)){
                ++j; // 左指针右移
                counts[s.charAt(j)]--; // 把左指针的字符数量减去1
            }
            longest = Math.max(i - j, longest);
        }
        return longest;
    }
    private boolean hasGreaterThan1(int[] counts){
        for(int count : counts){
            if(count > 1){
                return true;
            }
        }
        return false;
    }
    
    public String minWindow(String s, String t){
        // 哈希表大小不会超过256
        HashMap<Character, Integer> charToCount = new HashMap<>();
        for(char ch : t.toCharArray()){
            charToCount.put(ch, charToCount.getOrDefault(ch, 0) + 1);
        }

        int count  = charToCount.size();// count是出现在字符串t中但还没出现在字符串s中的子字符串中的字符个数
        // start为左指针，end为右指针
        int start = 0, end = 0, minStart = 0, minEnd = 0;
        int minLength = Integer.MAX_VALUE;
        // 右指针移动到0 且count = 0时，继续调整左指针
        while(end < s.length() || (count == 0 && end == s.length())){
            if(count > 0){
                char endCh = s.charAt(end); // 右指针遇到了t中出现过的字符
                if(charToCount.containsKey(endCh)){
                    // 右指针扫描时先将哈希表值减去
                    charToCount.put(endCh, charToCount.get(endCh) - 1);
                    if(charToCount.get(endCh) == 0){
                        count--;
                    }
                }
                end++; // 右指针主动右移
            } else{
                // 当count等于0时，两个指针之间的子字符串就包含字符串t中所有字符
                if(end - start < minLength){
                    // 维护minLength, minStart, minEnd
                    minLength = end - start;
                    minStart = start;
                    minEnd = end;
                }
                char startCh = s.charAt(start);
                // 向右移动左指针以判断删除子字符串最左字符后能否仍然包含t的所有字符
                if(charToCount.containsKey(startCh)){
                    // 左指针扫描时再将扫过的值加上
                    charToCount.put(startCh, charToCount.get(startCh) + 1);
                    if(charToCount.get(startCh) == 1){
                        // 如果左字符是唯一字符，说明移动左指针已经无法包含t的所有字符了，需要重新调整右指针
                        count++;
                    }
                }
                start++; // 左指针右移
            }
        } 
        return minLength < Integer.MAX_VALUE ? s.substring(minStart, minEnd) : "";
    }
}
class NumMatrix{
    private int[][] sums;
    // NumMatrix的构造函数用来做预处理，根据输入矩阵生成辅助矩阵sums，用两个嵌套循环，时间复杂度和空间复杂度为O(mn)
    public NumMatrix(int[][] matrix){
        if(matrix.length == 0 || matrix[0].length == 0){
            return;
        }
        // 为了防止下标为-1的情况，所以矩阵行列多加一维
        sums = new int[matrix.length + 1][matrix[0].length+1];
        for(int i = 0; i < matrix.length; i++){
            int rowSum = 0;
            for(int j = 0; j < matrix[0].length; j++){
                rowSum += matrix[i][j];
                sums[i+1][j+1] = sums[i][j+1] + rowSum;
            }
        }
    }
    // 求子矩阵的数字之和
    public int sumRegion(int row1, int col1, int row2, int col2){
        return sums[row2+1][col2+1] - sums[row1][col2+1] - sums[row2+1][col1] + sums[row1][col1];
    }
}