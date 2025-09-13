package DAY_17_Binary_Tree_Part_1;

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
}
