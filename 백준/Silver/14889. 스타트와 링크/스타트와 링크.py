# https://www.acmicpc.net/problem/14889

"""
N명을 두 팀으로 나눠서 두 팀의 능력치 차이가 적게 나도록 결과를 만들어야 한다

N <= 20
두 팀으로 나누는 경우의 수
    20명 중 10명을 뽑는 조합의 경우 -> 20C10
        -> 20만을 넘지 않는다. 완탐가능
"""

n = int(input())
grid = []
result = 1e9
def ability_sum(comb):
    abil_sum = 0
    for i in range(n//2):
        for j in range(n//2):
            if i != j:
                abil_sum += grid[comb[i]][comb[j]]
    return abil_sum

for _ in range(n):
    grid.append(list(map(int, input().split())))

from itertools import combinations
combination = combinations([x for x in range(n)], n//2)

for start in combination:
    #link팀 조합을 구한다.
    start_sum = ability_sum(start)
    link = [x for x in range(n) if x not in start]
    link_sum = ability_sum(link)
    result = min(result, abs(start_sum-link_sum))

print(result)




