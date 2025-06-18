
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1106
 *
 * dp 유형
 * */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        long [] dp = new long[1100];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int expense = Integer.parseInt(st.nextToken());
            int profit = Integer.parseInt(st.nextToken());
            
            for (int j = profit; j < dp.length; j++) {
                dp[j] = Math.min(dp[j], dp[j-profit] + expense);
            }
        }
        
        long minCost = dp[C];
        for (int i = C; i< dp.length; i++) {
            minCost = Math.min(minCost, dp[i]);
        }
        System.out.println(minCost);

        br.close();


    }


}