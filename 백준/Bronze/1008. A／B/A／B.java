
import java.util.*;
import java.io.*;

//https://www.acmicpc.net/problem/1008
/**
 * 두 정수 A와 B를 입력받은 다음, A/B를 출력하는 프로그램을 작성하시오.
 * */
public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        
        double a = Double.parseDouble(st.nextToken());
        double b = Double.parseDouble(st.nextToken());
        
        System.out.println(a/b);

    }
}