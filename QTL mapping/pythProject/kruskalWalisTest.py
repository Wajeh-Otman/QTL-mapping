from scipy import stats

def KruskalWal(A,B):
    result = stats.kruskal(A,B)
    return result.pvalue


