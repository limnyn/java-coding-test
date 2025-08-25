# https://www.acmicpc.net/problem/1167
"""
트리의 지름
트리의 지름은 
1. 특정 노드에서 bfs로 가장 먼 노드를 선택하고
2. 해당 노드에서 다시 bfs로 가장 먼 노드를 탐색하면
해당 두 노드의 거리가 트리의 지름이다
"""
from collections import deque
import sys
def input(): return sys.stdin.readline().rstrip()

if __name__ == "__main__":
    
    n = int(input())
    
    graph = [[] for _ in range(n + 1)]
    
    for _ in range(n):
        lst = list(map(int, input().split()))
        src = lst[0]
        lst = lst[1:-1]
        for i in range(0, len(lst), 2):
            dst, weight = lst[i], lst[i+1]
            graph[src].append((dst, weight))
            
    dq = deque([])
    dq.append((1, 0))
    visited = [False] * (n + 1)
    visited[1] = True
    max_node, max_dist = 1, 0
    while dq:
        node, acc = dq.popleft()
        
        for next, dist in graph[node]:
            if not visited[next]:
                visited[next] = True
                dq.append((next, acc + dist))
                if max_dist <= acc + dist:
                    max_dist = acc + dist
                    max_node = next

    
    
    start_node = max_node
    dq = deque([])
    dq.append((start_node, 0))
    visited = [False] * (n + 1)
    visited[start_node] = True
    max_node, max_dist = 0, 0
    while dq:
        node, acc = dq.popleft()
        
        for next, dist in graph[node]:
            if not visited[next]:
                visited[next] = True
                dq.append((next, acc + dist))
                if max_dist <= acc + dist:
                    max_dist = acc + dist
                    max_node = next            
    
    print(max_dist)