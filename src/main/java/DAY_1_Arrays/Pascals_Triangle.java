package DAY_1_Arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pascals_Triangle {

    /**
     *
     * Time Complexity: O(numRows^2)
     * - For each row, we iterate through elements to compute values.
     *
     * Space Complexity: O(numRows^2)
     * - We store all rows of the triangle in a List<List<Integer>>.
     *   Total elements = 1 + 2 + 3 + ... + numRows = numRows * (numRows + 1) / 2 → O(numRows^2)
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(Arrays.asList(1)); // First row is always [1]

        if (numRows == 1) return ans;

        ans.add(Arrays.asList(1, 1)); // Second row is always [1, 1]

        if (numRows == 2) return ans;

        for (int i = 2; i < numRows; i++) {
            List<Integer> list = new ArrayList<>(i);
            List<Integer> prevList = ans.get(i - 1);

            list.addFirst(prevList.getFirst()); // First element is always 1

            for (int j = 1; j < i; j++) {
                list.add(j, prevList.get(j) + prevList.get(j - 1)); // Sum of two above elements
            }

            list.add(prevList.getLast()); // Last element is always 1
            ans.add(list);
        }

        return ans;
    }


}
