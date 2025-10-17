package DAY_27_TRIE;


public class Trie {

   static class TrieNode {
       TrieNode[] childrens = new TrieNode[26];
       boolean isTerminated = false;
       int childCount = 0;

       boolean containsKey(char ch) {
           return childrens[ch - 'a'] != null;
       }

       void put(char ch, TrieNode trieNode) {
           childrens[ch - 'a'] = trieNode;
           childCount++;
       }

       TrieNode get(char ch) {
           return childrens[ch - 'a'];
       }

       void setTerminated() {
           isTerminated = true;
       }

       boolean isTerminated() {
           return isTerminated;
       }
   }

   private TrieNode root;

   public Trie() {
       root = new TrieNode();
   }

   public void insert(String word) {
       TrieNode node = root;

       for (int i = 0; i < word.length(); i++) {
           char ch = word.charAt(i);

           if (!node.containsKey(ch)) {
               node.put(ch, new TrieNode());
           }

           node = node.get(ch);
       }

       node.setTerminated();
   }

   public boolean search(String word) {
       TrieNode node = root;
       for (int i = 0; i < word.length(); i++) {
           char ch = word.charAt(i);

           if(!node.containsKey(ch)) {
               return false;
           }

           node = node.get(ch);
       }

       return node.isTerminated();
   }

   public boolean startWith(String prefix) {

       TrieNode node = root;
       for (int i = 0; i < prefix.length(); i++) {
           char ch = prefix.charAt(i);

           if(!node.containsKey(ch)) {
               return false;
           }

           node = node.get(ch);
       }

       return true;
   }

   public boolean remove(String word) {
       return removeHelper(root, word, 0);
   }

    private boolean removeHelper(TrieNode node, String word, int i) {

       if (i == word.length()) {
           if (!node.isTerminated()) {
               return false;
           }

           node.isTerminated = false;
           return true;
       }

       int childIndex = word.charAt(i) - 'a';
       TrieNode child = node.childrens[childIndex];
       if (child == null) return false;

       boolean shouldDelete = removeHelper(child, word, i + 1);

       if (!child.isTerminated() && child.childCount == 0) {
           node.childrens[childIndex] = null;
           node.childCount--;
       }

       return shouldDelete;
    }
}
