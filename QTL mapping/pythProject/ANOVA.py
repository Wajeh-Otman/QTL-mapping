from scipy.stats import f_oneway

def doANOVA(x,y):
    p = f_oneway(x,y)
    return p.pvalue