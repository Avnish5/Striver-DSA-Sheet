package DAY_20_BINARY_SEARCH_TREE;

public class Construct_BST_From_Given_Keys {

    /**
     * Time Complexity: O(n)
     *   - Each element in the array is visited exactly once to create a TreeNode.
     *   - n = number of elements in nums.
     *
     * Space Complexity: O(log n) on average (for recursion stack)
     *   - The depth of recursion is proportional to the height of the tree.
     *   - For a balanced BST built from a sorted array, height = O(log n).
     *   - Worst case (if array is skewed, though here mid ensures balance): O(log n).
     */
    public TreeNode sortedArrayToBST(int[] nums) {
      return helper(nums, 0 , nums.length - 1);
    }

    private TreeNode helper(int[] nums, int s, int e) {
        if (s > e) {
            return null;
        }

        int mid = (s + e) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums, s, mid -1);
        root.right = helper(nums, mid + 1, e);

        return root;
    }
}
