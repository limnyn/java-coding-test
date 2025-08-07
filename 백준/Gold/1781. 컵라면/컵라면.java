
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1781
 *
 * 데드라인, 컵라면을 오름차순 정렬 한다
 * 이후 하나씩 큐에 넣으면서, 만약 큐에 현재 deadline보다 많이 존재하면, pq에서 poll 한다
 * -> 이는 이전값 중 가장 적은라면을 얻는 시점을 포기하기 때문에, 큐에는 항상 가장 최선의 선택이 남아 있다
 * 연료채우기 문제와 유사
 *
 * */


public class Main {

    static class Pair {

        int deadline;
        int ramyeon;

        Pair(int deadline, int ramyeon) {
            this.deadline = deadline;
            this.ramyeon = ramyeon;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        ArrayList<Pair> problems = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int deadline = Integer.parseInt(st.nextToken()), ramyeon = Integer.parseInt(st.nextToken());
            problems.add(new Pair(deadline, ramyeon));
        }

        problems.sort(Comparator.comparingInt((Pair p) -> p.deadline).thenComparingInt(p -> p.ramyeon));

        for (Pair problem: problems) {
            pq.offer(problem.ramyeon);

            if (pq.size() > problem.deadline)
                pq.poll();

        }

        int result = pq.stream().mapToInt(Integer::intValue).sum();

        System.out.println(result);
        br.close();
    }
}