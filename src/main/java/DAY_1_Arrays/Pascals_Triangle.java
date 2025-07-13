package DAY_1_Arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pascals_Triangle {

    public List<List<Integer>> generate(int numRows) {

        List<List<Integer>> ans = new ArrayList<>();
        if (numRows >= 1) ans.add(Arrays.asList(1));
        if (numRows >= 2) ans.add(Arrays.asList(1, 1));

        for (int i = 2; i < numRows; i++) {
            List<Integer> prevList = ans.get(i-1);
            List<Integer> list = new ArrayList<>();
            list.add(1);

            for (int j = 1; j < i; j++) {
                list.add(prevList.get(j-1) + prevList.get(j));
            }
            list.add(1);
            ans.add(list);
        }

        return ans;

    }

}
