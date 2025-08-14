from collections import deque
def solution(players, m, k):
    """
    깡구현
    시각에 따라 deque 구조로 밀어내기 방식으로 서버를 유지한다. 
    """
    dq = deque([])
    dq.append((0,0))
    server_add_count = 0
    now_capable = m
    for time, user in enumerate(players):
        
        if dq and dq[0][0] == time:
            t, server_num = dq.popleft()
            now_capable -= (server_num * m)
            
        if user > now_capable - 1:
            need_server = user//m - (now_capable-1)//m
            dq.append((time + k, need_server))
            print(time + k, need_server)
            server_add_count += need_server
            now_capable += (need_server * m)

    return server_add_count