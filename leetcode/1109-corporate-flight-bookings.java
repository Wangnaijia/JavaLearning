/*
 * @Author: Wang Naijia
 * @Date: 2021-08-31 09:35:41
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-08-31 09:52:12
 * @Descripttion: 这里有 n 个航班，它们分别从 1 到 n 进行编号。

有一份航班预订表 bookings ，表中第 i 条预订记录 bookings[i] = [firsti, lasti, seatsi] 意味着在从 firsti 到 lasti （包含 firsti 和 lasti ）的 每个航班 上预订了 seatsi 个座位。
请你返回一个长度为 n 的数组 answer，其中 answer[i] 是航班 i 上预订的座位总数。
示例 1：
    输入：bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
    输出：[10,55,45,25,25]
    解释：
    航班编号        1   2   3   4   5
    预订记录 1 ：   10  10
    预订记录 2 ：       20  20
    预订记录 3 ：       25  25  25  25
    总座位数：      10  55  45  25  25
    因此，answer = [10,55,45,25,25]
示例 2：
    输入：bookings = [[1,2,10],[2,2,15]], n = 2
    输出：[10,25]
    解释：
    航班编号        1   2
    预订记录 1 ：   10  10
    预订记录 2 ：       15
    总座位数：      10  25
    因此，answer = [10,25]
提示：
    1 <= n <= 2 * 104
    1 <= bookings.length <= 2 * 104
    bookings[i].length == 3
    1 <= firsti <= lasti <= n
    1 <= seatsi <= 104
 */
class Solution1109{
    public static void main(String[] args) {
        int[][] bookings = {{1,2,10},{2,3,20},{2,5,25}};
        Solution1109 solution = new Solution1109();
        int n = 5;
        System.out.println(solution.corpFlightBookings(bookings, n));
    }
    public int[] corpFlightBookings(int[][] bookings, int n){
        int[] counter = new int[n];
        for(int i = 0; i < bookings.length; i++){
            int first = bookings[i][0] - 1, last = bookings[i][1] - 1, num = bookings[i][2];
            for(int j = first - 1; j < last + 1; j++){
                counter[j] += num;
            }
        }
        return counter;
    }
    /**
     * @description: 超出内存限制
     * @param {int[][]} bookings
     * @param {int} n
     * @return {*}
     */    
    public int[] myCorpFlightBookings(int[][] bookings, int n){
        int[][] flights = new int[bookings.length][n];
        for(int i = 0; i < bookings.length; i++){
            int first = bookings[i][0] - 1, last = bookings[i][1] - 1, num = bookings[i][2];
            for(int j = first; j < last + 1; j++){
                flights[i][j] = num;
            }
        }
        int[] answer = new int[n];
        for(int i = 0; i < n; i++){
            for(int row = 0; row < bookings.length; row++){
                answer[i] += flights[row][i];
            }
        }
        return answer;
    }
}
