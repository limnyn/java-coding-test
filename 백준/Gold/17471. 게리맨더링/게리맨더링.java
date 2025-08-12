import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/17471
/**
 * 
접근 - bruteforce
2<=n<=10
node갯수 n = 10일 때 부분집합의 갯수 : 2^10

모든 조합(부분 집합 A에)에 대해 B를 A^c( A의 여집합)라고 하면
    1. 부분집합A 내부가 연결되어 있는지
    2. 나머지집합 B가 서로 연결되어 있는지 
    3. 1  &  2를 만족하면 result = max(result, abs(sum_a - sum_b))

    "1 & 2"를 만족하면 선거구가 두개이다. 
        -A와 A의 여집합이 각각 서로 연결되어 있기 때문에

"연결"에 대한 구현
    BFS | DFS 를 통해 노드 A[0] 대해 탐색을 하고, 
    탐색을 마쳤을 때 조합에 대해 미방문 한 노드가 존재하면 연결이 되지 않은 것이다

*/
public class Main {

    /**
     
     * 
     * 
     * @param graphComb
     * 예시 -> (0, 3, 4) -> 구획을 (0,3,4) 와 나머지 노드들로 나눈다고 가정
     * @return
     * 각 조합에 대해 (한 선거구에 대해)
     * if 연결이 되어 있으면  return 인구수 총합,
     * else if 연결이 되어 있지 않으면 return -1
     */
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
    static int[] costs;
    static int[] subset;
    static int result = Integer.MAX_VALUE; 
    static int max_cnt;
    static int isConnected(List<Integer> graphComb) {
        Queue<Integer> q = new LinkedList<>();
        int[] visited = new int[graph.size()];
        
        int visitedSum = 0;

        int start = graphComb.get(0);
        q.add(start);
        visited[start] = 1;
        visitedSum = 1;

        while(!q.isEmpty()){
            int node = q.poll();

            for (int neighbor : graph.get(node)) {

                if (visited[neighbor] == 0 && graphComb.contains(neighbor)){
                    visited[neighbor] = 1;
                    visitedSum += 1;
                    q.add(neighbor);
                }
            }
        }
        
        if (visitedSum == graphComb.size()){
            int result = 0;
            for (int g : graphComb) {
                result += costs[g];
            }
            return result;
        } else{
            return -1;
        }
    }
    
    // 각 조합에 따른 선거구가 연결되어있는지 확인하고, 두 선거구에 대한 인구 차이 반환
    static void combinationIntegers(int cnt, int k){
        ArrayList<Integer> Compliment = new ArrayList<>();
        if(cnt == max_cnt){
            ArrayList<Integer> list = new ArrayList<Integer>();
            for (int s : subset) {
                list.add(s);
            }

            for(int i = 0; i < graph.size(); i++){
                if (!list.contains(i))
                    Compliment.add(i);
            }
            int resultA = isConnected(list);
            if (resultA == -1)
                return;
            int resultB = isConnected(Compliment);
            if (resultB == -1)
                return;
            result = Math.min(result, Math.abs(resultA-resultB));
            return;
        }
        for(int i = k; i < graph.size(); i++){
            subset[cnt] = i;
            combinationIntegers(cnt + 1, i + 1);
        }
        
       
        
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        costs = new int[n];

        //입력단
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++){
            costs[i] = Integer.parseInt(st.nextToken());
        }
        
        for (int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int edge_count = Integer.parseInt(st.nextToken());
            ArrayList<Integer> node_input = new ArrayList<>();
            for(int j = 0; j < edge_count; j++){
                node_input.add(Integer.parseInt(st.nextToken())-1);
            }
            graph.add(node_input);
        }
        
        

        // 부분집합 만들기
        for(int i = 1; i < n; i++){
            max_cnt = i;
            subset = new int[i];
            combinationIntegers(0,0);
        }
        if (result == Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(result);
    }


}