package DAY_3_Arrays_Part_3;

public class Pow_X_N {


    public double solve(double x, long n) {

        // Base Case:
        // x^0 = 1
        if(n == 0) return 1;

        /**
         * -------------------------------
         * Handle Negative Power
         * -------------------------------
         * x^(-n) = (1/x)^n
         */
        if(n < 0) {
            return solve(1 / x, -n);
        }

        /**
         * -------------------------------
         * Even Power Case
         * -------------------------------
         * x^n = (x * x)^(n/2)
         */
        if(n % 2 == 0) {
            return solve(x * x, n / 2);
        }

        /**
         * -------------------------------
         * Odd Power Case
         * -------------------------------
         * x^n = x * (x * x)^((n - 1)/2)
         */
        return x * solve(x * x, (n - 1) / 2);
    }

    /**
     * Time Complexity: O(log n)
     *   - At each step, we divide n by 2 → logarithmic reduction
     *
     * Space Complexity: O(log n)
     *   - Due to recursion stack (height ≈ log n)
     */
    public double myPow(double x, int n) {
        return solve(x, n);
    }

}
