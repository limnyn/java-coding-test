
/**
 * https://www.acmicpc.net/problem/1260
 *
 * */
import java.util.*;
import java.util.stream.*; //자바는 재귀 import 하지 않음
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static boolean[] isVisited;
    static List<List<Integer>> graph = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();

    static List<Integer> dfsResult = new ArrayList<>();
    static List<Integer> bfsResult = new ArrayList<>();



    public static void main(String[] args) throws Exception {


        int n, m, v;

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        int src, dst;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            src = Integer.parseInt(st.nextToken());
            dst = Integer.parseInt(st.nextToken());
            graph.get(src).add(dst);
            graph.get(dst).add(src);
        }

        for (int i = 1; i <= n; i++) {
            Collections.sort(graph.get(i));
        }

        // DFS 실행
        isVisited = new boolean[n + 1];
        dfs(v);


        // BFS 실행
        isVisited = new boolean[n + 1];
        bfs(v);

        sb.append(
                dfsResult.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(" "))
        ).append("\n");
        sb.append(
                bfsResult.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(" "))
        ).append("\n");

        System.out.println(sb);
    }

    public static void dfs(int now) {
        isVisited[now] = true;
        dfsResult.add(now);
        for (int next : graph.get(now)) {
            if (!isVisited[next]) {
                dfs(next);
            }
        }
    }

    public static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        isVisited[start] = true;

        while (!q.isEmpty()) {
            int now = q.poll();
            bfsResult.add(now);

            for (int next: graph.get(now)) {
                if (!isVisited[next]) {
                    isVisited[next] = true;
                    q.offer(next);
                }
            }
        }
    }
}