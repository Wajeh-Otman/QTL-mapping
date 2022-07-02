from mlxtend.evaluate import permutation_test
import numpy as np



def permu_test(A,B):
    p_value = permutation_test(A, B, method ='approximate',num_rounds=10000,seed=0)
    return p_value

def permu_test_var(A, B, num_rounds=1000-1):
    n=len(A)
    k=1
    App=[]
    Bpp=[]
    xm=np.mean(A)
    ym=np.mean(B)
    for x in A:
        App.append(x-xm)
    for y in B:
        Bpp.append(y-ym)
    def func1(A,B):
        return abs(np.var(A)-np.var(B))
    def func2(AB,n):
        return func1(AB[:n],AB[n:])
    ABpp = np.concatenate([App, Bpp])
    diff=func2(ABpp,n)
    for j in range(num_rounds):
        np.random.shuffle(ABpp)
        k += (diff <= func2(ABpp,n))
    return float(k) / (num_rounds+1)




