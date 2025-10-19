package DAY_27_TRIE;

import java.util.Arrays;

public class Longest_String_With_All_Prefixes {

    static class TrieNode {
        TrieNode[] childrens = new TrieNode[26];
        boolean isTerminated = false;

        public TrieNode get(char ch) {
            return childrens[ch - 'a'];
        }

        public void put(char ch, TrieNode node) {
            childrens[ch - 'a'] = node;
        }

        public boolean contains(char ch) {
            return childrens[ch - 'a'] != null;
        }

        public void setTerminated() {
            isTerminated = true;
        }

        public boolean getIsTerminated() {
            return isTerminated;
        }

    }

    public void insert(TrieNode root, String word) {
        TrieNode curr = root;



        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!curr.contains(ch)) {
                TrieNode node = new TrieNode();
                curr.put(ch, node);
            }

            curr = curr.get(ch);
        }

        curr.setTerminated();
    }

    public boolean isValidWord(TrieNode root, String word) {

        TrieNode curr = root;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = curr.get(ch);
            if (node == null || !node.getIsTerminated()) return false;
            curr = node;
        }

        return true;

    }

    /**
     * 1. Using Trie
     *
     * Let:
     *    N = number of words
     *    L = maximum length of a word
     *
     * 1. Inserting all words into Trie:
     *    - For each word, traverse all characters → O(L)
     *    - For N words → O(N * L)
     *
     * 2. Sorting words using Arrays.sort(words):
     *    - Sorting takes O(N log N) comparisons
     *    - Each string comparison (compareTo) takes O(L) in worst case
     *    - Total → O(N log N * L)   <-- ✅ Dominant term
     *
     * 3. Checking each word using isValidWord():
     *    - For one word → O(L)
     *    - For N words → O(N * L)
     *
     * 4. Comparing and updating result string:
     *    - word.length() = O(1)
     *    - word.compareTo(result) = O(L)
     *    - Done N times → O(N * L)
     *
     * ✅ Final Time Complexity = O(N log N * L)
     *
     * -----------------------------------------------------
     *
     * ✅ Space Complexity:
     *
     * 1. Trie data structure:
     *    - Worst case: No common prefixes → N * L nodes
     *    - Each node has fixed 26 pointers → O(1) space per node
     *    - Total → O(N * L)
     *
     * 2. Additional space used:
     *    - String result → O(L)
     *    - Sorting is in-place → O(1) extra
     *
     * ✅ Final Space Complexity = O(N * L)
     */

    public String longestValidWord1(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            insert(root,word);
        }

        String result = "";
        Arrays.sort(words);

        for (String word : words) {
            if(isValidWord(root, word)) {
                if (word.length() > result.length() ||
                        word.compareTo(result) < 0) {
                    result = word;
                }
            }
        }

        return result;

    }

   public int compareTo(String s1, String s2) {
        int n1 = s1.length(), n2 = s2.length();
        int i = 0, j= 0;

        while (i <= n1 && j <= n2) {
            if (s1.charAt(i) != s2.charAt(j)) {
                return s1.charAt(i) - s2.charAt(j);
            }
            i++;
            j++;
        }

        return n1-n2;
   }

   public int binarySearch(String[] arr, String word) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int compare = compareTo(arr[mid], word);
            if (compare == 0) return mid;
            if (compare < 0) low = mid + 1;
            else high = mid - 1;

        }

        return -1;
   }

   public void merge(String[] arr, int left, int mid, int right) {
        String[] temp = new String[right - left + 1];
        int i = left,  j = mid +1;
        int k = 0;

        while (i <= mid && j <= right) {
            if (compareTo(arr[i] , arr[j]) <= 0) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= mid)  temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];

        for (int p = 0; p < temp.length; p ++) {
            arr[left + p] = temp[p];
        }

   }

   public void mergeSort(String[] arr, int left, int right) {
        if (left >= right) return;

        int mid = (left + right) /2;
        mergeSort(arr, left, mid );
        mergeSort(arr, mid +1, right);
        merge(arr, left, mid, right);

   }

   public void customMergeSort(String[] words) {
         mergeSort(words, 0 , words.length - 1);
    }

    /**
     * 2. Using Binary search
     *
     * Time Complexity Analysis:
     *
     * Step 1: customMergeSort(words)
     *      - Merge Sort takes: O(N log N)
     *      - Each comparison between two strings takes O(K) in worst case
     *      => Sorting Cost = O(N log N × K)
     *
     * Step 2: Outer loop iterates over all N words → O(N)
     * Step 3: For each word, we generate all prefixes → at most K iterations per word → O(K)
     * Step 4: For each prefix, we perform binarySearch(words, prefix)
     *      - Binary search takes O(log N) comparisons
     *      - Each comparison between two strings takes O(K)
     *      => Binary Search Cost per prefix = O(K × log N)
     *
     * => Total for prefix checking = N × K × (K × log N) = O(N × K² × log N)
     *
     * Final Time Complexity:
     *      O(N log N × K) + O(N × K² × log N)
     *      ≈ O(N × K² × log N)
     *
     * Space Complexity Analysis:
     *      - Merge sort uses extra temp array: O(N)
     *      - Recursion stack in merge sort: O(log N)
     *      - Prefix string building: O(K) (reused each time)
     *
     * Final Space Complexity:
     *      O(N)
     */

    public String longestValidWord2(String[] words) {
       customMergeSort(words);
       String result = "";

       for (String word : words) {
           boolean isValid = true;
           String prefix = "";

           for (char c :word.toCharArray()) {
               prefix += c;

               if (binarySearch(words, prefix) < 0) {
                   isValid = false;
                   break;
               }

           }

           if (isValid &&
                   (word.length() > result.length() ||
                           (word.length() == result.length() && compareTo(word, result) < 0))) {
               result = word;
           }
       }

       return result;
    }
}
