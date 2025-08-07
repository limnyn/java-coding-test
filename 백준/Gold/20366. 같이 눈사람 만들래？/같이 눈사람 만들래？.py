# https://www.acmicpc.net/problem/20366
'''

1. 두 값 쌍의 합을 구하고 정렬한다.
2. 인접한 두 값들에 대해 탐색해서 인덱스가 중복되지 않는 두 쌍을 찾는다.
    
'''

import sys
def input(): return sys.stdin.readline()

if __name__ == "__main__":
    n = int(input())
    arr = sorted(list(map(int, input().split())))
    
    twin_lst = []
    for i in range(n-1):
        for j in range(i+1, n):
            twin_lst.append([arr[i] + arr[j], i, j])
            
    twin_lst.sort(key=lambda x: x[0])
    
    min_diff = float("inf")
    
    for i in range(len(twin_lst) - 1):
        h1, x1, y1 = twin_lst[i]
        
        for j in range(i + 1, len(twin_lst)):
            h2, x2, y2 = twin_lst[j]
            
            diff = h2-h1
            
            if min_diff <= diff: # 이후 j가 증가할수록 diff는 더 크거나 같기 때문에 생략
                break
            
            if len({x1, x2, y1, y2}) == 4: # 4가지 값중 중복된 값이 있으면 set 시 4가 될 수 없음
                min_diff = diff
                
    print(min_diff)