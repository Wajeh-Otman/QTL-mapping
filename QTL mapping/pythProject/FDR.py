# -*- coding: utf-8 -*-
"""
Created on Tue Apr 26 23:16:53 2022

@author: wajeh otman
"""

import statsmodels.stats.multitest 


def FDR_correction(vp):
    fdr_correction=statsmodels.stats.multitest.fdrcorrection(vp)
    return fdr_correction





