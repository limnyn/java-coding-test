import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/15661
 *
 * 팀을 구성해 가며, 각 조합에 대해 점수를 계산한다.
 * N/2 를 초과하는 팀 조합은 여집합으로 완성되기 때문에 생략한다.
 *
 *
 * */


public class Main {


    static int[][] grid;
    static boolean[] picked;
    static int result;
    static int N;



    public static int teamDiff() {
        int sumA = 0;
        int sumB = 0;

        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                int s = grid[i][j] + grid[j][i];
                if (picked[i] && picked[j]) sumA += s;
                if (!picked[i] && !picked[j]) sumB += s;

            }
        }
        return Math.abs(sumA - sumB);
    }

    public static void dfs(int idx, int cntA) {

        if (cntA > N/2) return;

        if (idx == N) {
            // 계산 수행
            result = Math.min(result, teamDiff());
            return;
        }


        //idx를 선택한 경우
        picked[idx] = true;
        dfs(idx + 1, cntA + 1);

        //idx를 선택하지 않은 경우
        picked[idx] = false;
        dfs(idx + 1, cntA);


    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        grid = new int[N][N];
        picked = new boolean[N];
        result = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0,0);
        System.out.println(result);

    }
}

