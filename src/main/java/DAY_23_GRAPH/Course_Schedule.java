package DAY_23_GRAPH;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Course_Schedule {

    public boolean canFinish(int numCourses, int[][] prerequisites) {

        // Step 1: Create adjacency list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        // Step 2: Indegree array
        int[] indegree = new int[numCourses];

        // Step 3: Build graph
        for (int[] pre : prerequisites) {
            int course = pre[0];
            int prereq = pre[1];
            adj.get(prereq).add(course);
            indegree[course]++;
        }

        // Step 4: Queue for BFS
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        // Step 5: BFS traversal
        int completed = 0;

        while (!q.isEmpty()) {
            int curr = q.poll();
            completed++;

            for (int next : adj.get(curr)) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    q.add(next);
                }
            }
        }

        // Step 6: Check if all courses completed
        return completed == numCourses;
    }
}
