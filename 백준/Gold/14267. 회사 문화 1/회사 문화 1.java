import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/14267
 *
 * 상사의 번호가 부하보다 앞서기 때문에 순차 스캔으로 계산 가능
 * 
 * 
 * */


public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[] manager;
        int[] commend;
        int[] dp;
        int N, M;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        manager = new int[N + 1];
        commend = new int[N + 1];
        dp = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            manager[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M ; i++) {
            st = new StringTokenizer(br.readLine());
            int worker = Integer.parseInt(st.nextToken());
            int score = Integer.parseInt(st.nextToken());
            commend[worker] += score;
        }

        for (int worker = 2; worker <= N; worker++) {
            dp[worker] = dp[manager[worker]] + commend[worker];
        }

        for (int i = 1; i <= N; i++) {
            System.out.print(dp[i] + " ");
        }
        
    }
}