/*
 * @Author: Wang Naijia
 * @Date: 2021-10-08 09:36:08
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-10-08 09:59:45
 * @Descripttion: 有一个二维矩阵 A 其中每个元素的值为 0 或 1 。
移动是指选择任一行或列，并转换该行或列中的每一个值：将所有 0 都更改为 1，将所有 1 都更改为 0。
在做出任意次数的移动后，将该矩阵的每一行都按照二进制数来解释，矩阵的得分就是这些数字的总和。
返回尽可能高的分数。
示例：
    输入：[[0,0,1,1],[1,0,1,0],[1,1,0,0]]
    输出：39
    解释：
    转换为 [[1,1,1,1],[1,0,0,1],[1,1,1,1]]
    0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 = 39
提示：
    1 <= A.length <= 20
    1 <= A[0].length <= 20
    A[i][j] 是 0 或 1
 */
class Solution861{
    public static void main(String[] args) {
        int[][] grid = {{0,0,1,1,},{1,0,1,0},{1,1,0,0}};
        Solution861 solution = new Solution861();
        System.out.println(solution.matrixScore(grid));
    }
    public int matrixScore(int[][] grid){
        int m = grid.length, n = grid[0].length;
        int res = 0;
        for(int i = 0; i < m;i++){
            // 先反转首元素不为1的每一行
            if(grid[i][0] != 1){
                for(int j = 0; j < n;j++){
                    grid[i][j] ^= 1; 
                }
            }
        }
        // 完毕后，再对每一列遍历，若该列中0的数目大于1，则反转该列
        for(int j = 0; j < n; j++){
            // 统计数字
            int count0 = 0, count1 = 0;
            for(int i = 0; i < m; i++){
                if(grid[i][j] == 0) count0++;
                else count1++;
            }
            if(count0 > count1){
                for(int i = 0; i < m; i++){
                    grid[i][j] ^= 1;
                }
            }
        }
        for(int i = 0; i<m;i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j]==1) res+=Math.pow(2, n-j-1);
            }
        }
        return res;
    }
}