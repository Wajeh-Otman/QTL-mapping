import numpy as np
from scipy import stats
import Calculation


def f_test(x,y):
    x = np.array(x)
    y = np.array(y)
    xVAr=Calculation.variance(x)
    yVAr=Calculation.variance(y)
    if(xVAr==0 or yVAr==0):
        if(xVAr==0 and yVAr==0):
            return 1
        else:
            return 0.0001
    f = float(np.var(x, ddof=1)) / np.var(y, ddof=1)  # calculate F test statistic
    dfn = x.size - 1  # define degrees of freedom numerator
    dfd = y.size - 1  # define degrees of freedom denominator
    if f==1:
        return 1
    if f>1:
        return 2*(1 - stats.f.cdf(f, dfn, dfd))  # find p-value of F test statistic
    if f<1:
        return 2*(stats.f.cdf(f, dfn, dfd))  # find p-value of F test statistic


