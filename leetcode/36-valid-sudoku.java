import java.util.HashSet;

/*
 * @Author: Wang Naijia
 * @Date: 2021-09-17 09:44:12
 * @LastEditors: Wang Naijia
 * @LastEditTime: 2021-09-17 10:13:45
 * @Descripttion: 
 */
class Solution36{
    public static void main(String[] args) {
        char[][] board = {{'.','.','.','.','5','.','.','1','.'},
                        {'.','4','.','3','.','.','.','.','.'},
                        {'.','.','.','.','.','3','.','.','1'},
                        {'8','.','.','.','.','.','.','2','.'},
                        {'.','.','2','.','7','.','.','.','.'},
                        {'.','1','5','.','.','.','.','.','.'},
                        {'.','.','.','.','.','2','.','.','.'},
                        {'.','2','.','9','.','.','.','.','.'},
                        {'.','.','4','.','.','.','.','.','.'}};
        Solution36 solution = new Solution36();
        System.out.println(solution.isValidSudoku(board));
        
    }
    public boolean isValidSudoku(char[][] board){
        Map<Integer, Set<Integer>> row  = new HashMap<>(), col = new HashMap<>(), area = new HashMap<>();
        for (int i = 0; i < 9; i++) {
            row.put(i, new HashSet<>());
            col.put(i, new HashSet<>());
            area.put(i, new HashSet<>());
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.') continue;
                int u = c - '0';
                int idx = i / 3 * 3 + j / 3;
                if (row.get(i).contains(u) || col.get(j).contains(u) || area.get(idx).contains(u)) return false;
                row.get(i).add(u);
                col.get(j).add(u);
                area.get(idx).add(u);
            }
        }
        return true;
    }
    public boolean myisValidSudoku(char[][] board){
        for(int i = 0; i < 9; i++){
            HashSet<Character> set = new HashSet<>();
            for(int j = 0; j < 9; j++){
                if(set.contains(board[i][j]) && board[i][j] != '.') return false;
                else set.add(board[i][j]);
            }
        }
        for(int i = 0; i < 9; i++){
            HashSet<Character> set = new HashSet<>();
            for(int j = 0; j < 9; j++){
                if(set.contains(board[j][i]) && board[j][i] != '.') return false;
                else set.add(board[j][i]);
            }
        }
        for(int bias = 0; bias < 9; bias++){
            HashSet<Character> set = new HashSet<>();
            for(int x = (bias / 3) * 3; x < (bias / 3) * 3 + 3; x++){
                for(int y = (bias % 3) * 3; y < (bias % 3) * 3 + 3; y++){
                    if(set.contains(board[x][y]) && board[x][y] != '.') return false;
                    else set.add(board[x][y]);
                }
            }
        }
        return true;
    }
}
