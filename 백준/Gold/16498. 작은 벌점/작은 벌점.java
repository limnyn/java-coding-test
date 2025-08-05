

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
 * 3값에 대해 가장 작은 값을 증가시켜 차이를 줄인다.
 * */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력
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

        // 정렬
        Arrays.sort(aCards);
        Arrays.sort(bCards);
        Arrays.sort(cCards);

        // 3포인터 시작
        int i = 0, j = 0, k = 0;
        int result = Integer.MAX_VALUE;

        while (i < aCardCount && j < bCardCount && k < cCardCount) {
            int a = aCards[i];
            int b = bCards[j];
            int c = cCards[k];

            int max = Math.max(a, Math.max(b, c));
            int min = Math.min(a, Math.min(b, c));

            result = Math.min(result, max - min);

            // 가장 작은 값을 증가시켜서 범위를 좁힘
            if (min == a) {
                i++;
            } else if (min == b) {
                j++;
            } else {
                k++;
            }
        }

        System.out.println(result);
    }
}
