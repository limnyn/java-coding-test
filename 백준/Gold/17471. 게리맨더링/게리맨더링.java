import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/17471
 *
 * 2 <= N <= 10
 *
 * 모든 부분집합 2^10
 *
 * nC1부터 nC(n/2)까지 A집합을 구하고 나머지 여집합을 B라고 할 때
 *
 * A 와 B가 각각 연결되어있는지 확인하고, 차이를 갱신해서 계산
 *
 * */


public class Main {

    static int N;
    static ArrayList<ArrayList<Integer>> graph;
    static int[] population;

    static boolean[] picked;
    static int result;

    public static int getPopulation(ArrayList<Integer> sector) {
        // 구역이 연결되어있으면 인구수 합 반환, 연결이 안되어있으면 -1 반환
        if (sector.isEmpty()) return -1;

        boolean[] inSector = new boolean[N + 1];
        for (int v : sector) inSector[v] = true;

        boolean[] visited = new boolean[N + 1];
        ArrayDeque<Integer> q = new ArrayDeque<>();

        q.offer(sector.get(0));
        visited[sector.get(0)] = true; // enqueue 시 바로 방문 처리
        int populationSum = 0;
        int visitCount = 0;

        while (!q.isEmpty()) {
            int node = q.poll();
            populationSum += population[node];
            visitCount++;

            for (int nei : graph.get(node)) {
                if (!inSector[nei]) continue;       // ★ 같은 구역만
                if (visited[nei]) continue;
                visited[nei] = true;
                q.offer(nei);
            }
        }

        return (visitCount == sector.size()) ? populationSum : -1;
    }


    public static void dfs(int idx, int pickedCount, int maxPick) {

        // 선택 완료 시
        if (pickedCount == maxPick) {
            //선택 종료 후 계산
            ArrayList<Integer> combA = new ArrayList<>();
            ArrayList<Integer> combB = new ArrayList<>();
            for (int i = 1; i <= N; i++) {
                if (picked[i]) combA.add(i);
                else combB.add(i);
            }
            int populationA = getPopulation(combA);
            if (populationA == -1) return;
            int populationB = getPopulation(combB);
            if (populationB == -1) return;

            result = Math.min(result, Math.abs(populationA - populationB));
            return;
        }

        if (idx > N) return;

        // 조합 선택
        picked[idx] = true;
        dfs(idx + 1, pickedCount + 1, maxPick);

        picked[idx] = false;
        dfs(idx + 1, pickedCount, maxPick);

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        population = new int[N + 1];
        graph = new ArrayList<>();
        graph.add(new ArrayList<>());
        picked = new boolean[N + 1];
        result = Integer.MAX_VALUE;
        //입력
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            population[i] = Integer.parseInt(st.nextToken());
            graph.add(new ArrayList<>());
        }

        for (int start = 1; start <= N; start++) {
            st = new StringTokenizer(br.readLine());
            int neighborCount = Integer.parseInt(st.nextToken());

            for (int j = 0; j < neighborCount; j++) {
                int end = Integer.parseInt(st.nextToken());
                graph.get(start).add(end);
                graph.get(end).add(start);
            }
        }

        for (int i = 1; i <= N/2; i++) {
            dfs(1, 0, i);
        }

        if (result == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(result);

    }
}