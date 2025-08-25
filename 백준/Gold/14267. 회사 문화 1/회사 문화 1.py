# https://www.acmicpc.net/problem/14267

"""
직속 상사의 점수를 기록하고, 자신의 점수와 직속 상사의 점수를 더한다
직속 상사의 번호가 자신의 번호보다 작기 때문에, 작은 순서대로 한번 탐색하여 해결할 수 있다.
"""

import sys
def input(): return sys.stdin.readline().rstrip()

if __name__ == "__main__":
    n, m = map(int, input().split())
    
    manager = [0] + list(map(int, input().split()))
    
    dp = [0] * (n + 1)
    
    commend = [0] * (n + 1)
    for _ in range(m):
        worker, score = map(int, input().split())
        commend[worker] += score
    
    for i in range(2, n+1):
        worker = i
        dp[worker] = dp[manager[worker]] + commend[worker]
    
    for i in range(1, n + 1):
        print(dp[i], end=" ")
        
        
