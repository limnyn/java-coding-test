import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/17090
 *
 * dfs + dp로 접근
 *
 * 시작점에서 탈출 또는 loop를 발견할 때 까지 탐색
 *
 * 발견 시 재귀를 탈출
 *
 * 탐색 시 이미 방문한 칸에 도달하면 해당 칸의 결과를 채용
 * */


public class Main {

    static int N, M;
    static int[][] grid;
    static boolean[][] visited, escaped;

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static boolean dfs(int r, int c) {

        visited[r][c] = true;

        int nr = r + dr[grid[r][c]];
        int nc = c + dc[grid[r][c]];

        // 다음칸이 범위를 벗어나면 해당칸은 탈출 가능한 칸
        if (!(0 <= nr && nr < N && 0 <= nc && nc < M)) {
            escaped[r][c] = true;
            return true;
        }

        // 만약 이미 방문 한 칸이라면
        if (visited[nr][nc]) {
            escaped[r][c] = escaped[nr][nc];
            return  escaped[r][c];

        }

        // 첫 방문하는 칸이라면 계속 탐색
        escaped[r][c] = dfs(nr, nc);
        return escaped[r][c];

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        grid = new int[N][M];
        visited = new boolean[N][M];
        escaped = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine().trim(); //한줄 전체
            for (int j = 0; j < M; j++) {
                char ch = line.charAt(j);
                if (ch == 'U') {
                    grid[i][j] = 0;
                } else if (ch == 'R') {
                    grid[i][j] = 1;
                } else if (ch == 'D') {
                    grid[i][j] = 2;
                } else if (ch == 'L') {
                    grid[i][j] = 3;
                }
            }
        }

        int count = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j])
                    dfs(i, j);

                if (escaped[i][j]) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}