# https://www.acmicpc.net/problem/17135


"""
3<=n<=15
15개중 3자리 뽑는 경우 15C3 -> 455
최대 455번 game(positions_of_archers)을 수행시켜 최대값 출력
-> 완탐 수행

각 조합에 대해서 궁수를 N + 1번째 행에서 시작하여
라운드마다 아래를 반복
1. 왼쪽 궁수부터 bfs 탐색하며 첫 적 탐색 후 제거, 이를 3회 반복
2. 라운드가 끝나면 궁수를 한칸 아래 행으로 위치시킨다

        

"""
import sys, itertools, copy

from collections import deque
def input(): return sys.stdin.readline().rstrip()


dr = [0, -1, 0]
dc = [-1, 0, 1]
grid = []

def game(positions_of_archers):
    
    # 궁수 배열 복사
    game_grid = copy.deepcopy(grid)

    
    dq = deque([])
 
    kill_count = 0        
    
    for archer_row in range(n, 0, -1): # n~1번까지 이동
    
        # 라운드 시작
        target_enemy = set() # 적 중복 방지
        
        for archer_col in positions_of_archers:
            dq = deque([])
            dq.append((archer_row, archer_col, 1)) #시작 사거리
            visited = [[False] * m for _ in range(archer_row)]
            
            found = False
            
            while dq and not found:
                r, c, dist = dq.popleft()
                
                for i in range(3):
                    nr, nc = r + dr[i], c + dc[i]
                    
                    # 범위 벗어나거나 방문한 경우
                    if not (0 <= nr < archer_row and 0 <= nc < m) or visited[nr][nc] == True:
                        continue
                    
                    visited[nr][nc] = True
                    
                    # 적 발견 시 이번 라운드 해당 궁수 탐색 중지
                    if game_grid[nr][nc] == 1:
                        found = True
                        target_enemy.add((nr, nc))
                        break

                    if dist + 1 > d: # 사거리 넘어서 이동 방지
                        continue
                    
                    dq.append((nr, nc, dist + 1))
        
        # 라운드동안 찾은 적 제거
        for r, c in target_enemy:
            game_grid[r][c] = 0
            kill_count += 1
        
        # 라운드 종료 시 궁수 행 한 칸 이동 -> archer_row 값 - 1
    
    return kill_count


if __name__ == "__main__":
    n, m, d = map(int, input().split())
    
    grid = [list(map(int, input().split())) for _ in range(n)]
    
    
    combs = itertools.combinations(range(m),3)
    
    max_kill_count = 0
    # 각 조합에 대해
    for comb in combs:
        max_kill_count = max(max_kill_count, game(comb))
    
    
    print(max_kill_count)