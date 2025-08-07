# https://www.acmicpc.net/problem/1781
"""

1. 데드라인, 컵라면 수로 오름차순 정렬
2. 순서대로 heap에 넣다가, 만약 deadline을 초과하는 경우가 생기면, heapq 앞을 pop한다

(1, 3), (2, 1), (2, 2), (3, 3) 인 경우
idx 가 3일 때 (3, 3)을 넣어 길이가 4가 되면 (2, 1)을 pop 하여 처리한다
-> 백준 연료채우기 1826와 유사
"""
import sys, heapq

def input(): return sys.stdin.readline().rstrip()

if __name__ == "__main__":
    
    n = int(input())
    
    ramyeon_hq = []
    checked = [False] * (n + 1)
    
    ramyeons = [tuple(map(int, input().split())) for _ in range(n)]
    ramyeons.sort() # 마감일, 라면수 오름차순
    
    for deadline, ramyeon in ramyeons:
        heapq.heappush(ramyeon_hq, ramyeon)
        if len(ramyeon_hq) > deadline:
            heapq.heappop(ramyeon_hq)
            
    print(sum(ramyeon_hq))
