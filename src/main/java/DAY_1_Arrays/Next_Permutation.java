package DAY_1_Arrays;

public class Next_Permutation {

    private void swap(int[] nums, int pos1, int pos2) {
        int temp = nums[pos1];
        nums[pos1] = nums[pos2];
        nums[pos2] = temp;
    }

    private void reverseRange(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
    public void nextPermutation(int[] nums) {

        int  n = nums.length;
        int gola_index = -1;

        for (int i = n-1; i > 0; i--) {
            if (nums[i-1] < nums[i]) {
                gola_index = i-1;
                break;

            }
        }

        if (gola_index != -1) {
            int swap_index = gola_index;

            for (int  j = n-1; j >= gola_index +1; j--) {
                if (nums[j] > nums[gola_index]) {
                    swap_index = j;
                    break;
                }
            }

            swap(nums, gola_index, swap_index);
        }

        reverseRange(nums, gola_index+1, n-1);
    }
}
