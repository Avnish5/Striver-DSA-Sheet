package DAY_10_Recursion_And_Backtracking;

import java.net.Socket;
import java.util.ArrayList;

public class Rat_In_A_Maze {

    /**
     * Time Complexity: O(4^(n*n))
     * - Each cell has 4 possible directions to explore (D, L, R, U)
     * - In the worst case (all 1s), recursion explores every direction from every cell
     * - Hence, exponential growth in number of recursive calls
     *
     * Space Complexity: O(n*n)
     * - Recursion depth can go up to n*n in the worst case (path through all cells)
     * - The visited[][] matrix adds O(n*n)
     * - Path string uses additional space proportional to path length (≤ n*n)
     */
    public void helper(int[][] maze, ArrayList<String> ans, boolean[][] visited, int n, int i, int j, String s) {
        // Boundary and constraint checks
        if (i >= n || i < 0 || j >= n || j < 0 || visited[i][j] || maze[i][j] == 0) {
            return;
        }

        // Base case: destination reached
        if (i == n - 1 && j == n - 1) {
            ans.add(s);
            return;
        }

        // Mark the current cell as visited
        visited[i][j] = true;

        // Explore all 4 directions
        helper(maze, ans, visited, n, i + 1, j, s + "D"); // Down
        helper(maze, ans, visited, n, i, j - 1, s + "L"); // Left
        helper(maze, ans, visited, n, i, j + 1, s + "R"); // Right
        helper(maze, ans, visited, n, i - 1, j, s + "U"); // Up

        // Backtrack — unmark the current cell
        visited[i][j] = false;
    }

    /**
     * Returns all possible paths from top-left to bottom-right in the maze.
     *
     * @param maze The input maze represented as a 2D grid
     * @return A list of strings, where each string is a valid path
     */
    public ArrayList<String> ratInMaze(int[][] maze) {
        ArrayList<String> ans = new ArrayList<>();
        int n = maze.length;
        boolean[][] visited = new boolean[n][n];

        // Start from top-left corner (0, 0)
        helper(maze, ans, visited, n, 0, 0, "");
        return ans;
    }

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("142.250.190.14", 80); // example IP of google.com


        // Do a quick request
        socket.getOutputStream().write("GET / HTTP/1.1\r\nHost: google.com\r\n\r\n".getBytes());
        socket.getOutputStream().flush();

        // Close the socket (initiates TCP FIN)
        socket.close();

        // The TIME_WAIT is now in kernel for the client side
        System.out.println("Socket closed, but OS may keep it in TIME_WAIT");
        Thread.sleep(10000); // wait so you can observe TIME_WAIT
    }
}
