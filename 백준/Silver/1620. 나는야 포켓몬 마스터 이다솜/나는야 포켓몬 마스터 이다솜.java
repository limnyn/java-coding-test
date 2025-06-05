/**
 * https://www.acmicpc.net/problem/1620
 *
 * 10만개에 대해서
 * m개의 입력이 들어온다.
 * 이후 m개의 입력이 들어온다.
 * 입력이 문자열이면 포켓몬 번호 출력
 * 입력이 숫자이면 포켓몬 이름 출력
 *
 * 풀이
 * int[100001] 배열 생성
 * 딕셔너리 생성
 * 각 번호별로 배열에 포켓먼 번호를 넣고
 * 딕셔너리에 포켓몬이름 : 번호 순으로 매핑
 * 문자열이 들어오면 딕셔너리에서 값 추출
 * 숫자가 들어오면 배열에서 값 접근
 *
 *
 * 다음 입력이 문자인지 숫자인지 파악하기 위해서는?
 * */
import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());


        String[] names = new String[n + 1];
        Map<String, Integer> nameToIndex  = new HashMap<>();

        for (int i = 1 ; i <= n; i++) {
            String name = br.readLine();
            names[i] = name;
            nameToIndex.put(name, i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            String query = br.readLine();
            if (Character.isDigit(query.charAt(0))) {
                int index = Integer.parseInt(query);
                sb.append(names[index]).append(("\n"));
            } else {
                sb.append(nameToIndex.get(query)).append(("\n"));
            }
        }

        System.out.println(sb);
    }

}