package DAY_7_LINKED_LIST_And_Array;

public class Trapping_Rain_Water {

    public int[] leftArrayMax(int[] height, int n) {
        int[] leftMaxArray = new int[n];
        leftMaxArray[0] = height[0];

        for (int  i =1; i < n; i++) {
            leftMaxArray[i] = Math.max(leftMaxArray[i-1], height[i]);
        }

        return leftMaxArray;
    }

    public int[] rightArrayMax(int[] height, int n) {
        int[] rightMaxArray = new int[n];
        rightMaxArray[n-1] = height[n-1];

        for (int  i = n-2; i >= 0; i--) {
            rightMaxArray[i] = Math.max(rightMaxArray[i+1], height[i]);
        }

        return rightMaxArray;
    }

    public int trap(int[] height) {
        int n = height.length;

        int[] leftMaxArray = leftArrayMax(height, n);
        int[] rightMaxArray = rightArrayMax(height, n);

        int ans = 0;

        for (int i = 0; i < n; i++) {
            ans += Math.min(leftMaxArray[i], rightMaxArray[i]) - height[i];
        }

        return ans;

    }

    public static int removeDuplicates(int[] nums) {

        int currIndex = 0;
        int prev = nums[0];
        int ans = 0;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == prev) continue;

            nums[currIndex] = prev;
            currIndex += 1;
            prev = nums[i];
            ans++;
        }

        nums[currIndex] = nums[nums.length-1];

        for (int i : nums) {
            System.out.print(i + " ");
        }
        System.out.println();

        return ans;

    }
   public static char get(char c) {
        return (char) ('a' + (c - 'a' + 1) % 26);
   }
    public static  char kthCharacter(int k) {
        StringBuilder sb = new StringBuilder();
        sb.append('a');

        while(sb.length() < k) {
            for (char c : sb.toString().toCharArray()) {
                sb.append(get(c));
            }
        }

        return sb.charAt(k-1);
    }

    public static void main(String[] args) {
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        System.out.println(kthCharacter(10));
    }
}
