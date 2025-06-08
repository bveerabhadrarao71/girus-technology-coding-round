import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SudokuValidator {

    public static boolean validateSudoku(int[][] board, List<List<int[]>> customZones) {
        // Check if board is 9x9
        if (board.length != 9) {
            return false;
        }
        for (int[] row : board) {
            if (row.length != 9) {
                return false;
            }
        }

        // Check all cells contain digits 1-9
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] < 1 || board[i][j] > 9) {
                    return false;
                }
            }
        }

        // Check rows
        for (int i = 0; i < 9; i++) {
            Set<Integer> rowSet = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                if (!rowSet.add(board[i][j])) {
                    return false;
                }
            }
        }

        // Check columns
        for (int j = 0; j < 9; j++) {
            Set<Integer> colSet = new HashSet<>();
            for (int i = 0; i < 9; i++) {
                if (!colSet.add(board[i][j])) {
                    return false;
                }
            }
        }

        // Check standard 3x3 subgrids
        for (int boxRow = 0; boxRow < 9; boxRow += 3) {
            for (int boxCol = 0; boxCol < 9; boxCol += 3) {
                Set<Integer> subgridSet = new HashSet<>();
                for (int i = boxRow; i < boxRow + 3; i++) {
                    for (int j = boxCol; j < boxCol + 3; j++) {
                        if (!subgridSet.add(board[i][j])) {
                            return false;
                        }
                    }
                }
            }
        }

        // Check custom zones if provided
        if (customZones != null) {
            for (List<int[]> zone : customZones) {
                if (zone.size() != 9) {
                    return false;
                }
                Set<Integer> zoneSet = new HashSet<>();
                for (int[] cell : zone) {
                    int row = cell[0];
                    int col = cell[1];
                    if (row < 0 || row >= 9 || col < 0 || col >= 9) {
                        return false;
                    }
                    if (!zoneSet.add(board[row][col])) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        // Valid standard Sudoku
        int[][] validStandard = {
            {5, 3, 4, 6, 7, 8, 9, 1, 2},
            {6, 7, 2, 1, 9, 5, 3, 4, 8},
            {1, 9, 8, 3, 4, 2, 5, 6, 7},
            {8, 5, 9, 7, 6, 1, 4, 2, 3},
            {4, 2, 6, 8, 5, 3, 7, 9, 1},
            {7, 1, 3, 9, 2, 4, 8, 5, 6},
            {9, 6, 1, 5, 3, 7, 2, 8, 4},
            {2, 8, 7, 4, 1, 9, 6, 3, 5},
            {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };

        // Valid Sudoku with custom zones
        int[][] validWithZones = {
            {5, 3, 4, 6, 7, 8, 9, 1, 2},
            {6, 7, 2, 1, 9, 5, 3, 4, 8},
            {1, 9, 8, 3, 4, 2, 5, 6, 7},
            {8, 5, 9, 7, 6, 1, 4, 2, 3},
            {4, 2, 6, 8, 5, 3, 7, 9, 1},
            {7, 1, 3, 9, 2, 4, 8, 5, 6},
            {9, 6, 1, 5, 3, 7, 2, 8, 4},
            {2, 8, 7, 4, 1, 9, 6, 3, 5},
            {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };

        // Define custom zones (example: diagonal and anti-diagonal)
        List<List<int[]>> customZones = List.of(
            List.of(new int[]{0,0}, new int[]{1,1}, new int[]{2,2}, new int[]{3,3}, new int[]{4,4}, new int[]{5,5}, new int[]{6,6}, new int[]{7,7}, new int[]{8,8}),
            List.of(new int[]{0,8}, new int[]{1,7}, new int[]{2,6}, new int[]{3,5}, new int[]{4,4}, new int[]{5,3}, new int[]{6,2}, new int[]{7,1}, new int[]{8,0})
        );

        // Adjust validWithZones to satisfy custom zones
        validWithZones[0][0] = 1;
        validWithZones[1][1] = 2;
        validWithZones[2][2] = 3;
        validWithZones[3][3] = 4;
        validWithZones[4][4] = 5;
        validWithZones[5][5] = 6;
        validWithZones[6][6] = 7;
        validWithZones[7][7] = 8;
        validWithZones[8][8] = 9;
        
        validWithZones[0][8] = 9;
        validWithZones[1][7] = 8;
        validWithZones[2][6] = 7;
        validWithZones[3][5] = 6;
        validWithZones[4][4] = 5;
        validWithZones[5][3] = 4;
        validWithZones[6][2] = 3;
        validWithZones[7][1] = 2;
        validWithZones[8][0] = 1;

        // Invalid Sudoku (duplicate in first row)
        int[][] invalidRow = {
            {5, 5, 4, 6, 7, 8, 9, 1, 2},
            {6, 7, 2, 1, 9, 5, 3, 4, 8},
            {1, 9, 8, 3, 4, 2, 5, 6, 7},
            {8, 5, 9, 7, 6, 1, 4, 2, 3},
            {4, 2, 6, 8, 5, 3, 7, 9, 1},
            {7, 1, 3, 9, 2, 4, 8, 5, 6},
            {9, 6, 1, 5, 3, 7, 2, 8, 4},
            {2, 8, 7, 4, 1, 9, 6, 3, 5},
            {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };

        // Invalid Sudoku (duplicate in column)
        int[][] invalidCol = {
            {5, 3, 4, 6, 7, 8, 9, 1, 2},
            {5, 7, 2, 1, 9, 5, 3, 4, 8},
            {1, 9, 8, 3, 4, 2, 5, 6, 7},
            {8, 5, 9, 7, 6, 1, 4, 2, 3},
            {4, 2, 6, 8, 5, 3, 7, 9, 1},
            {7, 1, 3, 9, 2, 4, 8, 5, 6},
            {9, 6, 1, 5, 3, 7, 2, 8, 4},
            {2, 8, 7, 4, 1, 9, 6, 3, 5},
            {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };

        // Invalid Sudoku (duplicate in 3x3 subgrid)
        int[][] invalidSubgrid = {
            {5, 3, 4, 6, 7, 8, 9, 1, 2},
            {6, 7, 2, 1, 9, 5, 3, 4, 8},
            {5, 9, 8, 3, 4, 2, 5, 6, 7},
            {8, 5, 9, 7, 6, 1, 4, 2, 3},
            {4, 2, 6, 8, 5, 3, 7, 9, 1},
            {7, 1, 3, 9, 2, 4, 8, 5, 6},
            {9, 6, 1, 5, 3, 7, 2, 8, 4},
            {2, 8, 7, 4, 1, 9, 6, 3, 5},
            {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };

        // Invalid Sudoku for custom zones
        int[][] invalidCustom = {
            {1, 3, 4, 6, 7, 8, 9, 1, 9},
            {6, 2, 2, 1, 9, 5, 3, 8, 8},
            {1, 9, 3, 3, 4, 2, 7, 6, 7},
            {8, 5, 9, 4, 6, 6, 4, 2, 3},
            {4, 2, 6, 8, 5, 3, 7, 9, 1},
            {7, 1, 3, 9, 2, 4, 8, 5, 6},
            {9, 6, 1, 5, 3, 7, 7, 8, 4},
            {2, 8, 7, 4, 1, 9, 6, 2, 5},
            {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };

        // Wrong dimensions
        int[][] wrongDimensions = new int[8][9];

        // Invalid numbers
        int[][] invalidNumbers = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                invalidNumbers[i][j] = 0;
            }
        }

        // Run test cases
        System.out.println("Valid standard Sudoku: " + validateSudoku(validStandard, null));
        System.out.println("Valid with custom zones: " + validateSudoku(validWithZones, customZones));
        System.out.println("Invalid row: " + validateSudoku(invalidRow, null));
        System.out.println("Invalid column: " + validateSudoku(invalidCol, null));
        System.out.println("Invalid subgrid: " + validateSudoku(invalidSubgrid, null));
        System.out.println("Invalid custom zone: " + validateSudoku(invalidCustom, customZones));
        System.out.println("Wrong dimensions: " + validateSudoku(wrongDimensions, null));
        System.out.println("Invalid numbers: " + validateSudoku(invalidNumbers, null));
    }
}