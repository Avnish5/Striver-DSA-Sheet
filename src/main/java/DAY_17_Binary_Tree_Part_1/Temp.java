package DAY_17_Binary_Tree_Part_1;

public class Temp {
   static class Custom {
       TreeNode node;
       int depth;

       public Custom(TreeNode node, int depth) {
           this.node = node;
           this.depth = depth;
       }
   }

   public Custom helper(TreeNode root, int depth) {
       if(root == null) {
           return new Custom(null, depth);
       }

       Custom left = helper(root.left, depth + 1);
       Custom right = helper(root.right, depth + 1);

       if(left.depth == right.depth) {
           return new Custom(root, depth + 1);
       } else if(left.depth > right.depth) {
           return new Custom(left.node, left.depth);
       } else {
           return new Custom(right.node, right.depth);
       }

   }
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
       return helper(root, 1).node;
    }
}
