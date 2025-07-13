package Day_1_Arrays;

public class Best_Time_to_Buy_and_Sell_Stock {

    public int maxProfit(int[] prices) {
        int minSoFar = Integer.MAX_VALUE;
        int maxSoFar = 0;

        for (int price : prices) {
            if (price < minSoFar) {
                minSoFar = price;
            } else {
                int profit = price - minSoFar;
                maxSoFar = Math.max(maxSoFar, profit);
            }
        }

        return maxSoFar;
    }
}
