/**
 * https://www.acmicpc.net/problem/2750
 * n개의 수가 주어졌을 때, 이를 오름차순으로 정렬하는 프로그램
 * */
import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        
        Arrays.sort(arr);
        
        for (int i = 0; i < n; i++) {
            sb.append(arr[i] + "\n");
        }
        System.out.println(sb);
    }

}