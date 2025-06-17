# https://www.acmicpc.net/problem/1106
'''
최소 C명 이상을 도달하기 위해 가장 작은 고객수

dp 냅색?
'''
import sys
def input(): return sys.stdin.readline().rstrip()


if __name__ == "__main__":
    C, N = map(int, input().split())
    
    ad_cost = [-1]
    attracted_people = [-1]
    for _ in range(N):
        c, p = map(int, input().split())
        ad_cost.append(c)
        attracted_people.append(p)
    
    # idx명을 유치하기 위해 들어가는 최소 비용 -> dp[idx]
    dp = [float("inf")] * 1101
    dp[0] = 0
    for i in range(1, N + 1):
        # dp 초기화, 
        ad_c, a_p = ad_cost[i], attracted_people[i]

    
    for r in range(1, N + 1):
        
        ad_c, a_p = ad_cost[r], attracted_people[r]
        
        for currendt_a_p in range(a_p, 1101):
            if dp[currendt_a_p - a_p] != float("inf"):
                dp[currendt_a_p] = min(dp[currendt_a_p - a_p] + ad_c, dp[currendt_a_p])
            
    
    print(min(dp[C:]))
            
    
        
    
    