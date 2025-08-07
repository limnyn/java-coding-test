# https://www.acmicpc.net/problem/20366
'''
4개의 점을 뽑아서 2쌍, 2쌍 차이 적은것 뽑기

적게 연산하는 접근 해 보기

N = 최대값 600 일 때 
1. sort 후 2쌍 끼리 접근해서 값을 넣으면?
    599, 대략 600번 연산하면 dict에 값을 넣을 수 있다.

    문제점
        sort 해서 앞뒤값만 비교하는 로직은 반례가 존재할 수 있을 것 같다.
    


2. 600c2 를 연산한다면?
    대략 18만번 연산.
    18만번 연산해서 dict에 값을 넣고. 
    dict를 리스트로 변경해서 list인덱스를 이동하면서 값 차이가 가장 적은 것을 찾으면 된다?

    문제점
        같은 눈을 사용한 경우가 문제가 될 수 있다.

    
3. 같은 눈을 사용한 경우를 해결하기 위해서는?
    600 c 2로 눈을 뽑고
    나머지 598개에 대해서 투 포인터를 이동하면서 가장 차이가 적은 눈사람 조합을 탐색한다.
    그러면 18만 * 600, 대략 1억번?


  
    def snowman_finder(x, y):
        elsa_snow = snows[x] + snows[y]
        # 엘사가 선택한 눈덩이를 제외한 나머지 눈덩이를 사용해야한다.
        anna_snow = snows[:x] + snows[x+1:y] + snows[y+1:]
        result = float('inf')

        anna_snow_1, anna_snow_2 = -1, -1
        left = 0
        right = n-3

        while left < right or result != 0:
            gap = elsa_snow - (left + right)
            if result > abs(gap):
                anna_snow_1, anna_snow_2 = anna_snow[left], anna_snow[right]
                result = abs(gap)
            
            if gap < 0:
                left += 1
            elif gap > 0:
                right -= 1

        return result, anna_snow_1, anna_snow_2
            
        
        


    n = int(input())
    snows = sorted(list(map(int,input().split())))
    combination = combinations(snows, 2)
    min_gap = float('inf')
    r_elsa = [-1,-1]
    r_anna = [-1,-1]
    for comb in combination:
    elsa_1, elsa_2 = comb

    gap, anna_1, anna_2 = snowman_finder(elsa_1, elsa_2)
    if min_gap > gap:
        min_gap = gap
        r_elsa = [snows[elsa_1], snows[elsa_2]]
        r_anna = [anna_1, anna_2]

    result = r_elsa + r_anna
    print(result)

    
'''


def snowman_finder(x, y):
    elsa_snow = snows[x] + snows[y]
    # 엘사가 선택한 눈덩이를 제외한 나머지 눈덩이를 사용해야한다.
    anna_snow = snows[:x] + snows[x+1:y] + snows[y+1:] 
    result = float('inf')
    anna_snow_1, anna_snow_2 = -1, -1
    left = 0
    right = n-3
    #이전 gap과 비교해서 포인터를 이동해야 한다. X  합에 대해 비교하며 찾아보자
    while left < right:
        gap = elsa_snow - (anna_snow[left] + anna_snow[right])
        if result > abs(gap):
            anna_snow_1, anna_snow_2 = anna_snow[left], anna_snow[right]
            result_bf = result
            result = abs(gap)
        
        if gap < 0:
            right -= 1
        elif gap > 0:
            left += 1
        elif gap == 0:
            return result
    return result
        
    
    

from itertools import combinations
import sys
input = sys.stdin.readline

if __name__ == "__main__":
    n = int(input())
    snows = sorted(list(map(int,input().split())))
    combination = combinations([x for x in range(n)], 2)
    min_gap = float('inf')
    r_elsa = [-1,-1]
    r_anna = [-1,-1]
    for comb in combination:
        elsa_1, elsa_2 = comb
        gap = snowman_finder(elsa_1, elsa_2)
        if min_gap > gap:
            min_gap = gap
            if min_gap == 0:
                break


    print(min_gap)
