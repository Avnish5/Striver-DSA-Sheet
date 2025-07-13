package DAY_7_LINKED_LIST_And_Array;

public class Max_Consecutive_Ones {

    public int findMaxConsecutiveOnes(int[] nums) {

        int innerMax = 0;
        int ans = 0;

        for (int i : nums) {
            if (i == 1) {
                innerMax++;
            } else {
                ans = Math.max(innerMax, ans);
                innerMax = 0;
            }
        }
        return ans;
    }
}
