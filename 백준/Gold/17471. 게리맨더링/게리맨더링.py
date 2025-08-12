# https://www.acmicpc.net/problem/17471

"""
접근 - bruteforce
2<=n<=10
node갯수 n = 10일 때 부분집합의 갯수 : 2^10

모든 조합(부분 집합 A에)에 대해 B를 A^c( A의 여집합)라고 하면
    1. 부분집합A 내부가 연결되어 있는지
    2. 나머지집합 B가 서로 연결되어 있는지 
    3. 1  &  2를 만족하면 result = max(result, abs(sum_a - sum_b))

    "1 & 2"를 만족하면 선거구가 두개이다. 
        -A와 A의 여집합이 각각 서로 연결되어 있기 때문에

"연결"에 대한 구현
    BFS | DFS 를 통해 노드 A[0] 대해 탐색을 하고, 
    탐색을 마쳤을 때 조합에 대해 미방문 한 노드가 존재하면 연결이 되지 않은 것이다

"""
from collections import deque
from itertools import combinations
import sys
def input(): return sys.stdin.readline().rstrip()
### "연결"에 대한 구현 함수 ### 
def is_connected(graph_comb):
    """
    # 각 조합에 대해 
     -> 연결이 되어 있으면 인구수 총합,
     -> 연결 안되어 있으면 -1
    """
    #graph_comb 예시 = (0, 3, 4) -> 구획을 (0, 3, 4) 노드와 나머지 노드로 나눈다고 가정할 때
    visited = [0]* (n + 1)
    dq = deque([])
    
    # 연결되어있다면 아무 노드에서 시작해도 
    # bfs의 결과 상태에서 sum(visited) == len(graph_comb) 를 만족해야한다
    dq.append(graph_comb[0]) 
    visited[graph_comb[0]] = 1
    while dq:
        node = dq.popleft()

        for neighbor in graph[node]:
            if visited[neighbor] == 0 and neighbor in graph_comb:
                visited[neighbor] = 1
                dq.append(neighbor)


    if sum(visited) == len(graph_comb):
        result = 0
        for g in graph_comb:
            result += population[g]
        return result
    else:
        return -1    

if __name__ == "__main__":
    n = int(input())
    
    population = [0] + [x for x in list(map(int, input().split()))]
    
    graph = [[] for _ in range(n + 1)]
    for start in range(1, n + 1):
        line = list(map(int, input().split()))
        line = line[1:]
        for end in line:
            graph[start].append(end)
            graph[end].append(start)

    population_sub = float("inf")
    
    # nC1부터 nC(n/2) 까지 수행함으로서 모든 부분집합 계산
    for r in range(1, n//2 + 1):
        combs = combinations(range(1, n+1), r)
        
        for comb_a in combs:
            comb_b = [x for x in range(1, n+1) if x not in comb_a]
            
            a_sum = is_connected(comb_a)
            b_sum = is_connected(comb_b)
            
            if a_sum != -1 and b_sum != -1:
                population_sub = min(population_sub, abs(a_sum - b_sum))
    
    if population_sub == float("inf"):
        print(-1)
    else:
        print(population_sub)
