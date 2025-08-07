import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/17135
 *
 * 궁수 조합을 뽑는다.
 *
 * 각 조합에 대해 라운드를 반복한다.
 * 1. 라운드 별 각 궁수별로 bfs 탐색을 돌아 적을 찾는다.
 * 2. 모든 궁수 탐색 이후 찾은 적을 제거한다.
 * 3. 라운드 종료 시 궁수의 row를 한 칸 씩 내린다
 *
 * */


public class Main {

    static class Point {

        int r;
        int c;
        int dist;

        Point (int r, int c, int dist) {
            this.r = r;
            this.c = c;
            this.dist = dist;
        }
    }

    static int[][] grid;
    static int N;
    static int M;
    static int D;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        grid = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int maxKill = 0;
        for (int i = 0; i < M - 2; i++) {
            for (int j = i + 1; j < M - 1; j++) {
                for (int k = j + 1; k < M; k++) {
                    maxKill = Math.max(game(new int[]{i, j, k}), maxKill);
                }
            }
        }

        System.out.println(maxKill);
        br.close();
    }

    public static int game(int[] archerCols) {
        int[] dr = {0, -1, 0};
        int[] dc = {-1, 0, 1};

        int[][] gameGrid = new int[N][M];
        for (int i = 0; i < N; i++)
            gameGrid[i] = grid[i].clone();

        int killCount = 0;

        // 라운드 단위
        for (int round = N; round > 0; round--) {
            Set<Integer> targetPoints = new HashSet<>();

            //각 궁수별 탐색 수행
            for (int archerCol: archerCols) {

                boolean[][] visited = new boolean[round][M];

                Queue<Point> q = new ArrayDeque<>();
                q.offer(new Point(round, archerCol, 1));

                boolean found = false;
                // 궁수의 BFS 탐색
                while (!q.isEmpty() && !found) {
                    Point now = q.poll();

                    // 각 방향에 대해
                    for (int i = 0; i < 3; i++) {
                        int nr = now.r + dr[i];
                        int nc = now.c + dc[i];

                        //범위 이탈이나 방문한 경우
                        if (!(0 <= nr && nr < round && 0 <= nc && nc < M) || visited[nr][nc])
                            continue;;

                        visited[nr][nc] = true;

                        //타겟 발견 시 해당 궁수는 탐색 중단.
                        if (gameGrid[nr][nc] == 1) {

                            targetPoints.add(nr*M + nc);
                            found = true;
                            break;
                        }

                        if (now.dist + 1 > D)
                            continue;

                        q.offer(new Point(nr, nc, now.dist + 1));
                    }
                }
            }
            //라운드 탐색 종료 후 제거 처리
            for (int s: targetPoints) {
                int r = s / M;
                int c = s % M;
                gameGrid[r][c] = 0;
                killCount += 1;
            }
        }
        return killCount;
    }
}