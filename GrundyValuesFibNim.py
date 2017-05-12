def fibnim(n):
    gvalues = [[0]]
    for i in range(1,n+1):
        gvalues.append([0])
        for j in range(1,i+1):
            S = [gvalues[i-k][min(2*k,i-k)] for k in range(1,j+1)]
            gvalues[i].append(mex(S))
    return gvalues

def fibnim(alpha,n):
    gvalues = [[0]]
    for i in range(1,n+1):
        gvalues.append([0])
        for j in range(1,i+1):
            alphamin = int(alpha*k)
            S = [gvalues[i-k][min(alphamin,i-k)] for k in range(1,j+1)]
            gvalues[i].append(mex(S))
    return gvalues

def mex(S):
    i = 0
    while True:
        if i not in S:
            return i
        i = i+1

def grundy(n):
    values = fibnim(n)
    S = [0] + [values[i][i-1] for i in range(1,n+1)]
    return S

def fibonaccis(n):
    fibs = [0,1,2]
    while True:
        if fibs[-1] >= n:
            return fibs
        fibs.append(fibs[-1]+fibs[-2])

def zeckendorf(n):
    if n == 0:
        return []
    zeck = [largestfib(n)] + zeckendorf(n-largestfib(n))
    return zeck

def largestfib(n):
    fibs = fibonaccis(n)
    while True:
        if fibs[-1] <= n:
            return fibs[-1]
        del fibs[-1]