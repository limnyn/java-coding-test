import sys
input = sys.stdin.readline

N = int(input())
cols = [0] * (N + 1)  # 1-indexed
ans = 0

def promising(level):
    # 방금 둔 level행과 이전 행들 비교
    for i in range(1, level):
        if cols[i] == cols[level]:
            return False
        if level - i == abs(cols[level] - cols[i]):
            return False
    return True

def dfs(level):
    global ans
    if level == N:
        ans += 1
        return
    nxt = level + 1
    for c in range(1, N + 1):
        cols[nxt] = c
        if promising(nxt):
            dfs(nxt)

dfs(0)
print(ans)
