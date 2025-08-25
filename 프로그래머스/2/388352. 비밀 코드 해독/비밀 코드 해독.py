from itertools import combinations


def check_intersection(comb_set, tries_sets, answers):
    for s, need in zip(tries_sets, answers):
        if len(comb_set & s) != need:
            return False
    return True

    

def solution(n, q, ans):
    """
    10 <= n <= 30
    최악의 경우 모든 조합의 갯수
    30c5 -> 142506
    14만가지 조합에 대해 교집합 갯수가 일치하는지 최대 10번씩 탐색
    -> 140만번 연산
    """
    
    combs = combinations(range(1, n+1), 5)
    answer = 0
    
    tries_set = [set(try_comb) for try_comb in q]

    for comb in combs:
        comb_set = set(comb)
        if check_intersection(comb_set, tries_set, ans):
            answer += 1
            
    return answer