import sys
input = sys.stdin.readline

n = int(input())
FULL = (1 << n) - 1   # n개의 열 비트가 1
ans = 0

def dfs(row: int, cols: int, d1: int, d2: int):
    # cols: 사용중인 열, d1: \ 대각(왼쪽으로 쉬프트), d2: / 대각(오른쪽으로 쉬프트)
    global ans
    if row == n:
        ans += 1
        return
    avail = FULL & ~(cols | d1 | d2)   # 이번 행에서 놓을 수 있는 자리들
    while avail:
        bit = avail & -avail           # 최하위 1비트 뽑기
        avail -= bit
        dfs(row + 1,
            cols | bit,
            (d1 | bit) << 1 & FULL,
            (d2 | bit) >> 1)

dfs(0, 0, 0, 0)
print(ans)
