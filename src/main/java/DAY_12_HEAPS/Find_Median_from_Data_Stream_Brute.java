package DAY_12_HEAPS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Find_Median_from_Data_Stream_Brute {

    /**
     *Time complexity: O(n * log n)
     * Space complexity: O(n)
     */
    private List<Integer> nums;

    public Find_Median_from_Data_Stream_Brute() {
        nums = new ArrayList<>();
    }

    public void addNum(int num) {
        nums.add(num);
    }

    public double findMedian() {
        // Sort every time we need the median
        Collections.sort(nums);

        int n = nums.size();
        if (n % 2 == 1) {
            return nums.get(n / 2);  // Odd -> middle element
        } else {
            return (nums.get(n / 2 - 1) + nums.get(n / 2)) / 2.0;  // Even -> mean of two middle
        }
    }
}
