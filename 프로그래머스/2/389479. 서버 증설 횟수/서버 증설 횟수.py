from collections import deque
def solution(players, m, k):
    
    dq = deque([])
    dq.append((0,0)) # (증설한 서버를 반납하는 시간, 해당 시점에 수거할 서버 개수)
    server_add_count = 0
    now_capable = m
    current_server = 1
    for time, user in enumerate(players):
        
        if dq and dq[0][0] == time:
            t, server_num = dq.popleft()
            current_server -= server_num
            
        # 현재 수용가능한 최대 인원 = server갯수 * m - 1
        now_capable = current_server * m - 1
        
        if user > now_capable:
            need_server = user//m - (current_server - 1)
            dq.append((time + k, need_server))
            server_add_count += need_server
            current_server +=  need_server

    return server_add_count