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

def find_farthest_node(start, graph, n):
    dq = deque([(start, 0)])
    visited = [False] * (n + 1)
    visited[start] = True
    far_node, far_dist = start, 0
    
    while dq:
        node, acc = dq.popleft()
        
        if acc > far_dist:
            far_node, far_dist = node, acc
        
        for next, w in graph[node]:
            if not visited[next]:
                visited[next] = True
                dq.append((next, acc + w))
    
    return far_node, far_dist
                
            

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

    # 1번에서 가장 먼 노드 X 찾기
    farthest_node, _ = find_farthest_node(1, graph, n)
    # X에서 다시 가장 먼 노드까지의 거리가 트리의 지름
    _, diameter = find_farthest_node(farthest_node, graph, n)
        
    print(diameter)