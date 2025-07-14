# https://www.acmicpc.net/problem/21608

"""
N <= 20, 구현 시간초과 X

조건 
1. 비어있는 칸 중 주변에 좋아하는 학생이 가장 많은 칸
2. 그 중에 인접한 칸 중 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
3. 만약 해당 경우 행의 번호, 열의 번호가 가장 작은 칸으로


[흐름]
시작 시 (1,1)에 먼저 첫 학생 배치 (1-2-3 조건에 의해)

이후 학생에 대해, 각 칸을 r, c 순서로 순회하며 빈칸들에 대해
1. 인접칸에 대해 좋아하는 학생의 수
2. 주변의 빈 칸
3. 행, 열 번호
에 대해서 

이전코드에서는 최대힙을 사용했지만, 모든 정렬 대신 상위 1개 값만 필요하므로
max함수를 사용해 불필요한 정렬을 줄일 수 있다

2차원 구조의 경우 max 함수는 리스트의 각 요소를 내림차순하여 가장 큰 값을 반환한다.

"""
import sys
def input(): return sys.stdin.readline().rstrip()

grid = []
grid_favor = []
friends = []
n = 0
dr = [-1, 0, 1, 0]
dc = [0, -1, 0, 1]

def placement(idx):
    """
    빈 칸 중에
    1. 인접칸에 대해 좋아하는 학생의 수가 많은 칸
    2. 같으면 그중에 주변의 빈 칸이 많은 칸
    3. 같으면 그중에 행, 열 번호가 작은 칸
    """
    now = friends[idx][0]
    now_friends = friends[idx][1]
    
    candidates = []

    for r in range(n):
        for c in range(n):
            if grid[r][c] != -1:
                continue
            
            favor_cnt, null_cell_cnt = 0, 0
            
            for i in range(4):
                nr, nc = r + dr[i], c + dc[i]
                if not (0 <= nr < n and 0 <= nc < n):
                    continue
                
                if grid[nr][nc] == -1:
                    null_cell_cnt += 1
                
                elif grid[nr][nc] in now_friends:
                  favor_cnt += 1
            
            # 음수 r, c를 넣어 lexicographical order 유지
            candidates.append((favor_cnt, null_cell_cnt, -r, -c))

    
    favor_cnt, null_cnt, r_neg, c_neg = max(candidates)
    r, c = -r_neg, -c_neg
    grid[r][c] = now
                

def calc():
        
    dict = {}
    for now, now_friends in friends:
        dict[now] = now_friends
    
    result = 0
    for r in range(n):
        for c in range(n):
            now = grid[r][c]
            now_friends = dict[now]
            
            friend_cnt = 0
            for i in range(4):
                nr, nc = r + dr[i], c + dc[i]
                if not (0 <= nr < n and 0 <= nc < n):
                    continue
                if grid[nr][nc] in now_friends:
                    friend_cnt += 1
            if friend_cnt == 1:
                result += 1
            elif friend_cnt == 2:
                result += 10
            elif friend_cnt == 3:
                result += 100
            elif friend_cnt == 4:
                result += 1000

    return result       
                
                
        

def main():
    global grid, grid_favor, friends, n
    
    n = int(input())
    
    for _ in range(n*n):
        lst = list(map(int, input().split()))
        friends.append((lst[0], lst[1:]))
        
    
    grid = [[-1] * n for _ in range(n)]
    
    grid[1][1] = friends[0][0]
    
    for i in range(1, n*n):
        placement(i)
    
    print(calc())
    
if __name__ == "__main__":
    main()
    



