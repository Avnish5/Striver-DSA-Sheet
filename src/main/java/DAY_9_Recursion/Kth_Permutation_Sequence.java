package DAY_9_Recursion;

public class Kth_Permutation_Sequence {

    public  int changeToNumber(int n) {
        int val = 0;
        for (int i = 1, k = n - 1; i <= n && k >= 0; i++, k--) {
            val = val * 10 + i;
        }

        return val;
    }

    public String getPermutation(int n, int k) {
         int val = changeToNumber(n);

    }

    public static void main(String[] args) {
        System.out.println(changeToNumber(9));
    }
}
