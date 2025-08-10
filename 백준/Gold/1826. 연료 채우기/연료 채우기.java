import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/1826
 *
 * 1. 현재 가스로 다음 정류장 까지 갈 수 없으\면, 최대 힙에서 poll 한다 (count++)
 *  -> 이전 정류장 중 가스를 가장 많이 주던 정류소를 들른것으로 친다.
 * 2. 가스를 최대 힙에 넣는다.
 * */


public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        int[][] gasStation = new int[N + 1][2]; //주유소 위치, 연료 양

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            gasStation[i][0] = Integer.parseInt(st.nextToken());
            gasStation[i][1] = Integer.parseInt(st.nextToken());
        }

        // 마지막 줄 입력
        st = new StringTokenizer(br.readLine());
        gasStation[N][0] = Integer.parseInt(st.nextToken()); // 마지막 목적지도 지나가는 경로에 포함
        int remainGas = Integer.parseInt(st.nextToken());

        // 입력이 주유소 가까운 순 정렬이 아닐수 도 있기 때문에 정렬 처리
        Arrays.sort(gasStation, Comparator.comparingInt(o -> o[0]));

        int count = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < N + 1; i++) {
            int gasStationDistance = gasStation[i][0];
            int gas = gasStation[i][1];

            while (remainGas < gasStationDistance) {
                if (pq.isEmpty()){
                    System.out.println(-1);
                    return;
                }
                remainGas += pq.poll();
                count += 1;
            }
            pq.offer(gas);
        }

        System.out.println(count);
    }
}