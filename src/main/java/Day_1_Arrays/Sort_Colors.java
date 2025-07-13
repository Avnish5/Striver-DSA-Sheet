package Day_1_Arrays;

import java.util.HashMap;
import java.util.Map;

public class Sort_Colors {

    // 1 - Using frequency hashmap
    public void sortColors1(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        map.put(0,0);
        map.put(1,0);
        map.put(2,0);

        for (int num : nums) {
            map.put(num, map.get(num) + 1);
        }

        int idx = 0;

        for (int color = 0; color <= 2; color++) {
            int freq = map.get(color);

            for (int i = 0; i < freq; i++) {
                nums[idx] = color;
                idx++;
            }
        }
    }

    public void sortColors2(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        int mid = 0;

        while (mid <= end) {
            switch (nums[mid]) {
                case 0 :
                    swap(nums, start, mid);
                    start++;
                    mid++;
                    break;

                case 1 :
                    mid++;
                    break;

                case 2 :
                    swap(nums, mid, end);
                    end--;
                    break;

            }
        }
    }

    private void swap(int[] nums, int pos1, int pos2) {
        int temp = nums[pos1];
        nums[pos1] = nums[pos2];
        nums[pos2] = temp;
    }
}
