import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/14890
 *
 * 단방향 진행으로(왼->오, 아래, 위) 종 횡 수행
 * 1. 높이가 올라갈 때는
 * L만큼 이전에 평평한지, 범위를 벗어나지 않는지, L만큼 이전동안 경사로가 설치된적 있는지 검사
 * 2. 높이가 내려갈 때는
 * L만큼 이전에 평평한지, 범위를 벗어나지 않는지 확인 후 경사로 설치
 *
 * 구분해서 처리한다.
 *
 *
 * */

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[][] grid = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int way = 0;


        // 종 방향
        for (int r = 0; r < N; r++) {
            boolean possible = true;
            boolean[] visited = new boolean[N];

            for (int now = 0; now < N - 1; now++) {
                int now_h = grid[r][now];
                int next_h = grid[r][now + 1];
                int diff = now_h - next_h;

                // 평지일 때
                if (diff == 0) continue;

                // 올라갈 때
                else if (diff == -1) {

                    
                    for (int i = now; i >= now - L + 1; i--) {
                        // 범위를 벗어나거나, 현재칸과 높이가 다르거나, 내리막길이 설치된 경우 실패
                        if (i < 0 || now_h != grid[r][i] || visited[i]) {
                            possible = false;
                            break;
                        }
                    }

                }

                // 내려갈 때
                else if (diff == 1) {
                    // 범위 벗어나지 않는지
                    if (now + L  >= N) {
                        possible = false;
                        break;
                    }

                    // L만큼 이후가 평평한지 확인 후 visited 처리(경사로 설치)
                    for (int i = now + 1; i <= now + L; i++) {

                        if (i >= N || next_h != grid[r][i] || visited[i]) {
                            possible = false;
                            break;
                        }
                        visited[i] = true;
                        

                    }
                    now = now + L - 1; // 내려간 경사로는 스킵
                }
                // 그외 높이차이가 2 이상
                else {
                    possible = false;
                    break;
                }
            }
            if (possible) way += 1;
        }

        // 횡 방향
        for (int c = 0; c < N; c++) {
            boolean possible = true;
            boolean[] visited = new boolean[N];

            for (int now = 0; now < N - 1; now++) {
                int now_h = grid[now][c];
                int next_h = grid[now + 1][c];
                int diff = now_h - next_h;

                // 평지일 때
                if (diff == 0) continue;

                    // 올라갈 때
                else if (diff == -1) {


                    for (int i = now; i >= now - L + 1; i--) {
                        // 범위를 벗어나거나, 현재칸과 높이가 다르거나, 내리막길이 설치된 경우 실패
                        if (i < 0 || now_h != grid[i][c] || visited[i]) {
                            possible = false;
                            break;
                        }
                    }

                }

                // 내려갈 때
                else if (diff == 1) {
                    // 범위 벗어나지 않는지
                    if (now + L  >= N) {
                        possible = false;
                        break;
                    }

                    // L만큼 이후가 평평한지 확인 후 visited 처리(경사로 설치)
                    for (int i = now + 1; i <= now + L; i++) {

                        if (i >= N || next_h != grid[i][c] || visited[i]) {
                            possible = false;
                            break;
                        }
                        visited[i] = true;


                    }
                    now = now + L - 1; // 내려간 경사로는 스킵
                }
                // 그외 높이차이가 2 이상
                else {
                    possible = false;
                    break;
                }
            }
            if (possible) way += 1;
        }
        System.out.println(way);
    }
}
