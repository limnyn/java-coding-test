# https://www.acmicpc.net/problem/9663
"""
N-Queen
dfs로 조합을 만들며 불가능 한 경우 가지치기

놓을 수 있는 경우
1. 같은 열에 이미 퀸이 있으면 불가능
2. 같은 대각선에 이미 퀸이 있으면 불가능
그 외에 해당 행 열에 놓을 수 있음

대각 "/" == r+c
/ 1 2 3 4
1 2 3 4 5
2 3 4 5 6
3 4 5 6 7
4 5 6 7 8

(r, c)에 대해서 / 방향 대각선은 r+c가 같은 값들이다, 
즉 /방향 대각선은 (2n+1) boolean 배열로 값을 파악할 수 있다.

대각 "\" == r-c
\ 1 2 3 4
1 0 -1 -2 -3
2 1 0 -1 -2
3 2 1 0 -1
4 3 2 1 0

-(n-1)부터 n-1까지 값으로 처리 가능
이를 n을 더해서
1~2n-1 값으로 처리 가능
"""

import sys
def input(): return sys.stdin.readline().rstrip()



def dfs(r):
    global count
    
    if r == n:
        count += 1
        return
    
    for c in range(n):
        d1 = r + c
        d2 = r - c + n
        
        if not diags1[d1] and not diags2[d2] and not col[c]:
            col[c] = diags1[d1] = diags2[d2] = True
            dfs(r + 1)
            col[c] = diags1[d1] = diags2[d2] = False
            
        

if __name__== "__main__":
    n = int(input())
    count = 0
    col = [False] * n
    diags1 = [False] * (2*n + 1) # /
    diags2 = [False] * (2*n + 1) # \
    
    dfs(0)
    print(count)
