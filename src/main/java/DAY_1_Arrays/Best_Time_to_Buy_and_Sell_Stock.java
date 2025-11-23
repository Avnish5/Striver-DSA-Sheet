package DAY_1_Arrays;

public class Best_Time_to_Buy_and_Sell_Stock {

    /**
     * Computes the maximum profit from a single buy-sell transaction in a stock price array.
     *
     * Time Complexity: O(n)
     * - We make one pass through the array.
     * - Each price is processed once to update min price or max profit.
     *
     * Space Complexity: O(1)
     * - Only a few integer variables are used (minSoFar, maxSoFar, profit).
     * - No additional data structures are created.
     */
    public int maxProfit(int[] prices) {

        // Minimum stock price found so far
        int minSoFar = Integer.MAX_VALUE;

        // Maximum profit found so far
        int maxSoFar = 0;

        for (int price : prices) {

            // Update minimum price if current price is lower
            if (price < minSoFar) {
                minSoFar = price;

            } else {
                // Calculate profit if the stock is sold at current price
                int profit = price - minSoFar;

                // Update max profit
                maxSoFar = Math.max(maxSoFar, profit);
            }
        }

        return maxSoFar;
    }

}
