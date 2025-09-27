package DAY_20_BINARY_SEARCH_TREE;

import java.util.HashMap;
import java.util.Map;

public class TreeNode {

    int val;
      TreeNode left;
      TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
        this.left = left;
          this.right = right;
      }

    public int maxFrequencyElements(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(0, num) + 1);
        }
        int max = 0;

        for (int freqVal : freq.values()) {
            max = Math.max(max, freqVal);
        }
        int ans = 0;

        for (int freqVal : freq.values()) {
            if (max == freqVal) ans += max;
        }

        return ans;


    }
}

