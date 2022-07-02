from scipy import stats


def T_test(x,y):
    result = stats.ttest_ind(x, y)
    return result.pvalue


