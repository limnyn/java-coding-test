# https://www.acmicpc.net/problem/1106
'''
dp = min(dp[i - 얻을 수 있는 사람 수] + 비용, dp[i])
즉, 현재 도시에서 사람을 선택했을 때와 아닐때를 비교해서 갱신한다.

여기서 C명을 넘기는 경우의 최소 비용이기 때문에
min(dp[C:])로 계산한다.
'''
import sys
def input(): return sys.stdin.readline().rstrip()


if __name__ == "__main__":
    C, N = map(int, input().split())
    
    pairs = []
    
    dp = [float("inf")] * 1100
    dp[0] = 0
    
    for _ in range(N):
        expense, profit = map(int, input().split())
        for i in range(profit, len(dp)):
            dp[i] = min(dp[i-profit] + expense, dp[i])
        

    print(min(dp[C:]))
