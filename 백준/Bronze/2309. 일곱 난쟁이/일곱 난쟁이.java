

/**
 * https://www.acmicpc.net/problem/2309
 * 7개를 뽑아서 총 합이 100이 되는 경우
 *
 * 9C7의 경우의 수 -> 9C2 -> 9 * 8 /2 = 36가지
 *
 * 1. Combinations을 만든다. (DFS)
 * 2. 합이 100과 일치하는 경우 멈춘다.
 *
 * 출력 : 오름차순으로 정렬 후 출력
 * */
import java.util.*;
import java.io.*;

public class Main {
    static int[] small = new int[9];
    static boolean found = false;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 9; i++) {
            small[i] = Integer.parseInt(br.readLine());
        }

        dfs(0, 0, new int[7], 0);
        System.out.println(sb);
    }

    static void dfs(int x, int depth, int[] result, int sum) {
        if (found) return;

        if (depth == 7) {
            if (sum == 100) {
                Arrays.sort(result);

                for (int i = 0; i < 7; i++) {
                    sb.append(result[i]).append("\n");
                }
                found = true;
            }
            return;
        }
        for (int i = x; i < 9; i++) {
            result[depth] = small[i];
            dfs(i + 1, depth + 1, result, sum + small[i]);
        }
    }
}