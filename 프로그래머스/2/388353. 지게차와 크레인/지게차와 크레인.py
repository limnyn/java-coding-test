from collections import deque
dr = [-1, 0, 1, 0]
dc = [0, -1, 0, 1]
n = 0
m = 0
def workable(start_r, start_c, grid):
    
    dq = deque([])
    dq.append((start_r, start_c))
    
    visited = [[False] * m for _ in range(n)]
    visited[start_r][start_c] = True
    
    dr = [-1, 0, 1, 0]
    dc = [0, -1, 0, 1]
    
    while dq:
        r, c = dq.popleft()
        for i in range(4):
            nr, nc = r + dr[i], c + dc[i]
            if not (0 <= nr < n and 0 <= nc < m):
                return True
            
            if grid[nr][nc] == '0' and not visited[nr][nc]:
                visited[nr][nc] = True
                dq.append((nr, nc))

    return False
                
                    
                

def solution(storage, requests):
    global connected, n, m
    n = len(storage)
    m = len(storage[0])
    
    grid = []
    for i in range(n):
        grid.append(list(storage[i]))

    
    for request in requests:

        work = '크레인' if len(request) == 2 else '지게차'
        dq = deque([])
        for r in range(n):
            for c in range(m):
                if grid[r][c] == request[0]:
                    if work == '크레인' or workable(r, c, grid):
                        dq.append((r, c))
        while dq:
            r, c = dq.popleft()
            grid[r][c] = '0'
                
    answer = 0
    for r in range(n):
        for c in range(m):
            if grid[r][c] != '0':
                answer += 1
                
    return answer
            
    
    

