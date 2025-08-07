
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/20366
 *
 * N에서 두개를 임의로 선택하고, 나머지 중에서 정렬을 해서 투포인터로 키 차이의 최소값을 구하는 과정을 반복한다.
 *
 * */


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        long[] arr = new long[N];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) arr[i] = Long.parseLong(st.nextToken());

        Arrays.sort(arr);

        long minDiff = Long.MAX_VALUE;

        for (int i = 0; i < N - 3; i++) {
            for (int j = i + 3; j < N; j++) {
                long sum1 = arr[i] + arr[j];
                int left = i + 1;
                int right = j - 1;

                while (left < right) {
                    long sum2 = arr[left] + arr[right];
                    long diff = Math.abs(sum1 - sum2);

                    minDiff = Math.min(minDiff, diff);

                    if (sum2 < sum1) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        System.out.println(minDiff);

        br.close();
    }
}