# https://www.acmicpc.net/problem/17090

"""

특정 칸에서 깊이 탐색으로 탐색
루프 또는 탈출 경우 지나온 경로들에 대해 결과 처리

탐색 도중 방문한 칸이 있다면 해당 칸의 결과를 그대로 사용 후 깊이탐색 중단

"""

import sys
sys.setrecursionlimit(10**6)
def input(): return sys.stdin.readline().rstrip()


dr = [-1, 0, 1, 0]
dc = [0, 1, 0, -1]

visited = []
escaped = []

def dfs(r, c):
    visited[r][c] = True
    
    oper = grid[r][c]
    
    idx = -1
    if oper == 'U':
        idx = 0
    elif oper == 'R':
        idx = 1
    elif oper == 'D':
        idx = 2
    elif oper == 'L':
        idx = 3
        
    nr, nc = r + dr[idx], c + dc[idx]
    
    # 범위 벗어난 경우 우선 처리
    if not (0 <= nr < n and 0 <= nc < m):
        escaped[r][c] = True
        return True
    
    # 다음 칸이 존재하는 경우 이미 방문한 칸일 때
    if visited[nr][nc] == True:
        
        # 탈출한 경우탐색 중단.
        if escaped[nr][nc]:
            escaped[r][c] = True
            return True
        
        # 루프인 경우 탈출 불가한 경우
        else:
            return False
    else:
    # 다음칸이 존재하지 않는 경우 계속 탐색
        escaped[r][c] = dfs(nr, nc)
        return escaped[r][c]
            
    
        

if __name__ == "__main__":
    
    n, m = map(int, input().split())
    
    grid = [list(input()) for _ in range(n)]
    
    visited = [[False] * m for _ in range(n)]
    escaped = [[False] * m for _ in range(n)]

    count = 0
    
    for r in range(n):
        for c in range(m):
            if not visited[r][c]:
                dfs(r, c)
            
            if escaped[r][c]:
                count += 1

            

                
    print(count)


        
    