import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/16498
 *
 * 작은 벌점
 *
 * A, B 한쌍에 대해 가장 가까운 C값을 이분탐색으로 찾는다.
 * */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int aCardCount = Integer.parseInt(st.nextToken());
        int bCardCount = Integer.parseInt(st.nextToken());
        int cCardCount = Integer.parseInt(st.nextToken());

        int[] aCards = new int[aCardCount];
        int[] bCards = new int[bCardCount];
        int[] cCards = new int[cCardCount];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < aCardCount; i++) aCards[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < bCardCount; i++) bCards[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < cCardCount; i++) cCards[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(aCards);
        Arrays.sort(bCards);
        Arrays.sort(cCards);

        int result = Integer.MAX_VALUE;

        for (int a : aCards) {
            for (int b : bCards) {
                int avg = (a + b) / 2;
                int idx = findClosestIndex(cCards, avg);

                // 인접한 3개 위치 (idx-1, idx, idx+1)를 모두 비교
                for (int i = idx - 1; i <= idx + 1; i++) {
                    if (i < 0 || i >= cCards.length) continue;
                    int c = cCards[i];

                    int max = Math.max(a, Math.max(b, c));
                    int min = Math.min(a, Math.min(b, c));
                    result = Math.min(result, max - min);
                }
            }
        }

        System.out.println(result);
    }

    // C 배열에서 target과 가장 가까운 값의 인덱스를 반환
    static int findClosestIndex(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        int bestIdx = 0;
        int bestDiff = Integer.MAX_VALUE;

        while (left <= right) {
            int mid = (left + right) / 2;
            int diff = Math.abs(arr[mid] - target);

            if (diff < bestDiff || (diff == bestDiff && arr[mid] < arr[bestIdx])) {
                bestIdx = mid;
                bestDiff = diff;
            }

            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return bestIdx;
    }
}
