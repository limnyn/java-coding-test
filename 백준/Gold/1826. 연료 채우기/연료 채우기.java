

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    //https://www.acmicpc.net/problem/1826

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int [][] gasStations = new int[n+1][2];
    
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int loc = Integer.parseInt(st.nextToken());
            int gas = Integer.parseInt(st.nextToken());
            gasStations[i][0] = loc;
            gasStations[i][1] = gas;

        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int end = Integer.parseInt(st.nextToken());
        int left_gas = Integer.parseInt(st.nextToken());
        gasStations[n][0] = end;
        Arrays.sort(gasStations, (o1, o2) -> {
            return o1[0]-o2[0];            
        });


        PriorityQueue<Integer> pQ = new PriorityQueue<>(Collections.reverseOrder());
        int gas_visit_count = 0;
        for (int i = 0; i <= n; i++){
            
            if (left_gas < gasStations[i][0]){
                
                while (left_gas < gasStations[i][0]) {
                    if (pQ.peek() == null){
                        System.out.println(-1);
                        return;
                    }
                    left_gas += pQ.poll();
                    gas_visit_count += 1;
                }
            }
            pQ.add(gasStations[i][1]);
        }
        System.out.println(gas_visit_count);
        
    }
    
    
}
