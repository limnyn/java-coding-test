import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/7576
 *
 * */

class Point {
    int r, c;
    Point(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[][] graph;

    static int[]dr = {-1, 0, 1, 0};
    static int[]dc = {0, -1, 0, 1};
    static Queue<Point> q  = new LinkedList<>();
    public static void main(String[] args) throws IOException {

        int m, n;
        st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        graph = new int[n][m];
        int remain = 0;


        for (int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
                if (graph[i][j] == 1) q.offer(new  Point(i, j));
                if (graph[i][j] == 0) remain++;


            }
        }

        int result = 0;

        if (remain == 0) {
            System.out.println(0);
            return;
        }

        while (!q.isEmpty()){
            Point p = q.poll();
            int r = p.r, c = p.c;

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i], nc = c + dc[i];

                if (!(0 <= nr && nr < n && 0 <= nc && nc < m)) continue;

                if (graph[nr][nc] != 0) continue;

                graph[nr][nc] = graph[r][c] + 1;
                result = graph[nr][nc];
                q.offer(new Point(nr, nc));
                remain--;


            }

        }

        System.out.println(remain == 0 ? result - 1 : -1);

    }


}