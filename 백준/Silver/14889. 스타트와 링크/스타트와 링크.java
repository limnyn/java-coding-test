

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/15661
 *
 * 두 팀을 뽑는다. 팀의 능력치 합의 차이 값이 최소가 될때 그 최소값을 출력한다.
 *
 *
 * 1. 조합을 짠다. (N명중 N/2 명을 뽑는다.)
 * 2. 각 조합에 대해 합의 차이를 계산하고 갱신한다.
 *
 * */


public class Main {


    static int[][] grid;
    static boolean[] picked;
    static int result;
    static int N;


    public static int teamSynergy(List<Integer> team) {

        int sum = 0;
        for (int i = 0; i < team.size() - 1; i++) {
            for (int j = i + 1; j < team.size(); j++) {
                sum += grid[team.get(i)][team.get(j)];
                sum += grid[team.get(j)][team.get(i)];
            }
        }
        return sum;
    }

    public static void dfs(int[] comb, int level, int startIdx) {

        if (level == comb.length) {
            // comb 조합 완성
            List<Integer> start = new ArrayList<>();
            List<Integer> link = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                if (picked[i])
                    start.add(i);
                else
                    link.add(i);
            }
            int a = teamSynergy(start);
            int b = teamSynergy(link);

            result = Math.min(Math.abs(a - b), result);
            return;
        }

        for (int i = startIdx; i <= N - comb.length + level; i++) {
            picked[i] = true;
            comb[level] = i;
            dfs(comb, level + 1, i + 1);
            picked[i] = false;
        }

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

        int[] comb = new int[N / 2];
        dfs(comb, 0, 0);
        System.out.println(result);

    }
}