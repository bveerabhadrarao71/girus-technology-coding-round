import java.util.*;

public class IslandMat {

    //  up, down, left, right, and the 4 diagonals are the All 8 directions:
    private static final int[][] DIRECTIONS = {
        {0, 0},  // down
        { 1, -1},  // up
        { -1,1},  // left
        { 0, 1},  // right
        {-1,1},  // up-left
        {-1, -1},  // up-right
        { 0,-1},  // down-left
        { 1, 0}   // down-right
    };

    // Check the condn  (r,p) is inside the grid, is a 1, and not yet visited
    private static boolean validate(int[][] grid, boolean[][] look, int r, int p) {
        int ro = grid.length;
        int col = grid[0].length;
        return r >= 0 && r < ro
            && p >= 0 && p < col
            && grid[r][p] == 1
            && !look[r][p];
    }

    // vro to maked  every cell in this island as look then worked 
    private static void vro(int[][] grid, boolean[][] look, int r, int p) {
        look[r][p] = true;
        for (int[] d : DIRECTIONS) {
            int x = r + d[0];
            int y = p + d[1];
            if (validate(grid, look, x, y)) {
                vro(grid, look, x, y);
            }
        }
    }

    // Count how many islands of 1s exist in the grid (with diagonal connections)
    public static int Islandcnt(int[][] grid) {
        int ro = grid.length;
        int col = grid[0].length;
        boolean[][] look = new boolean[ro][col];
        int count = 0;

        for (int i = 0; i < ro; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1 && !look[i][j]) {
                    vro(grid, look, i, j);
                    count++;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        // Test Case g1: one big island
        int[][] g1 = {
            {1, 0, 1, 1},
            {0, 1, 0, 1},
            {1, 0, 1, 0},
            {0, 1, 0, 1}//expected island is 2
        };
        System.out.println("Test Case g1 - Islands: " + Islandcnt(g1));  
        // Test Case g2: three separate islands
        int[][] g2 = {
            {0, 0, 1},
            {1, 0, 1},
            {1, 0, 0}//expected island is 3
        };
        System.out.println("Test Case g2 - Islands: " + Islandcnt(g2));  

        // Test Case g3: two separate islands
        int[][] g3 = {
            {1, 0, 0},
            {0, 1, 1},
            {0, 0, 1}//expected islan is 3
        };
        System.out.println("Test Case g3 - Islands: " + Islandcnt(g3));  
	}
}