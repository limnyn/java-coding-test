
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/17135

/**
3<=n<=15
15개중 3자리 뽑는 경우 15C3 -> 455
최대 455번 game(positions_of_archers)을 수행시켜 최대값 출력
-> 완탐 수행

def game
    -> 각 archer에 대해 bfs를 통해 
        조건을 만족하기 위해서 (가장가까운 거리, 동일한 거리는 왼쪽것 부터)
        [0,-1], [-1,0] ,[0,1], 우 순서대로 탐색 하다가 제일 처음 만나는 적을 
        target으로 지정

        성벽에 배치된 궁수에 대해 stage(또는 round) 마다 
        궁수들의 좌표 중 r 값을 n부터 0까지 한 줄 씩 올리면서 궁수들의 거리 계산 및 bfs를 수행한다
        이후 표적들에 대해 list에 넣어서 stage가 끝날 때 마다 표적들을 0으로 만들어 주고 count를 한다

        stage가 끝나면 처리한 전체 적을 반환한다.
        

 */
public class Main {
    static int n, m, d;
    static int grid[][];
    static int result;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        grid = new int[n][m];
        for(int r = 0; r < n; r++){
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < m; c++){
                if (st.nextToken().equals("1")){
                    grid[r][c] = 1;
                } else {
                    grid[r][c] = 0;
                }
            }
        }
        int [] arr = new int[m];
        for (int i = 0; i < m; i++){
            arr[i] = i;
        }
        result = Integer.MIN_VALUE;
        combination(arr, new boolean[m], 0, m, 3);

        System.out.println(result);
    }

    static int[] comb;
    static void combination(int[] arr, boolean[] visited, int start, int n, int r) {
        if(r == 0) {
            comb = new int[3];
            int idx = 0;
            for (int i = 0; i < m; i++){
                if (visited[i] == true){
                    comb[idx] = i;
                    idx++;
                }
            }
            int gameResult = game(comb);
            result = Math.max(gameResult, result);
            // System.out.printf("%d, %d, %d\n",comb[0],comb[1],comb[2] );
            return;
        }
            
        for(int i = start; i < n; i++) {
            visited[i] = true;
            combination(arr, visited, i + 1, n, r - 1);
            visited[i] = false;
        }
    }

    public static int game(int[] positionsOfArchers){
        int [] dr = {0, -1, 0};
        int [] dc = {-1, 0, 1};
        int[][] gridForComb = new int[n][m];

        for (int i = 0; i < grid.length; i++){
            System.arraycopy(grid[i],0, gridForComb[i], 0, grid[0].length);
        }

        int enemyKillCount = 0;
        for(int stage = n; stage > -1; stage--){

            ArrayList<int[]> enemySpot = new ArrayList<int[]>();
            
            for (int archer : positionsOfArchers) {
                boolean[][] visited = new boolean[n][m];
                Queue<int[]> q = new LinkedList<>();
                int[] first_start = {stage, archer, 0};
                q.add(first_start);
                //0으로 초기화된 2차원 방문배열
                
                while(!q.isEmpty()){
                    int[] qPop = q.poll();
                    int r = qPop[0];
                    int c = qPop[1];
                    int dis = qPop[2];
                    
                    for (int i = 0; i < 3; i++){
                        int nr = r + dr[i];
                        int nc = c + dc[i];

                        if (0 <= nr && nr < n && 0 <= nc && nc < m){
                            if (visited[nr][nc] == false){
                                visited[nr][nc] = true;
                                if (gridForComb[nr][nc] == 1){
                                    if (dis + 1 <= d){
                                        // int[] next = {nr, nc, dis + 1};
                                        enemySpot.add(new int[]{nr, nc, dis + 1});
                                        q = new LinkedList<>();
                                        break;
                                    }
                                } else {
                                    if (dis + 1 <= d){
                                        q.add(new int[] {nr, nc, dis + 1});
                                    }
                                }


                            }
                            
                        }
                    }
                }


                
            }
            
            for (int[] e_rcdis : enemySpot) {
                int eR = e_rcdis[0];
                int eC = e_rcdis[1];
                int eDis = e_rcdis[2];

                if (gridForComb[eR][eC] == 1){
                    enemyKillCount += 1;
                    gridForComb[eR][eC] = 0;
                }
                
            }
            if (stage > 0)
                gridForComb[stage-1] = new int[m];
        }
        return enemyKillCount;
    }
}
