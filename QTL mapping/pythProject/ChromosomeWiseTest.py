# -*- coding: utf-8 -*-
"""
Created on Sun Apr 24 13:39:22 2022

@author: wajeh otman
"""



import PermTest

import bigSimulator
import FDR
import math
import numpy as np
import kruskalWalisTest
import PermTest
import StudentTest
import ANOVA
import bigSimulator
import F_test


def DoChromWise(bSimul,Data):
    BigDataSimul = bigSimulator.clDataSimul()
    if (bSimul=="sim"):#run simulator
        subData=Data[12::]
        BigDataSimul.simul(subData)
    else:#read data from files
        subData2=Data[1:4:]
        BigDataSimul.readData(subData2)
    chr=BigDataSimul.vChr[0]
    nInd=len(BigDataSimul.vInd)
    viPermutated=list(range(nInd))
    #print(str(viPermutated))
    val0=chromozomeWiseCriterion(BigDataSimul,chr,viPermutated,0)
    vval1=[]

    vval4=[]

    vval6=[]
    for i in range(1000-1):
        viPermutated=np.random.permutation(nInd)
        #print(str(viPermutated))
        val=chromozomeWiseCriterion(BigDataSimul,chr,viPermutated,0)
        vval1.append(val[0])
        # vval2.append(val[1])
        # vval3.append(val[1])
        vval4.append(val[1])
        # vval5.append(val[4])
        vval6.append(val[2])
    
    permutationRes1=PermTest.permu_test(vval1, [val0[0]])
    # permutationRes2=PermTest.permu_test(vval2, [val0[1]])
    # permutationRes3=PermTest.permu_test(vval3, [val0[1]])
    permutationRes4=PermTest.permu_test(vval4, [val0[1]])
    # permutationRes5=PermTest.permu_test(vval5, [val0[4]])
    permutationRes6=PermTest.permu_test(vval6, [val0[2]])
    li=[permutationRes1,permutationRes4,permutationRes6]#,permutationRes2,permutationRes3,permutationRes5
    print("Chromosomal Permutation tests result:")
    for i in li:
        print(str(i))

def chromozomeWiseCriterion(BigDataSimul,chr,viPermutated,index=0):
    def funMarker(vx,vy,index):
        if index==0:
            p=StudentTest.T_test(vx,vy)
            return p
        # if index==1:
        #     p=PermTest.permu_test(vx,vy)
        #     return p
        # if index==2:
        #     p=PermTest.permu_test_var(vx,vy)
        #     return p
        if index==3:
            p=F_test.f_test(vx,vy)
            return p
        # if index==4:
        #     p=kruskalWalisTest.KruskalWal(vx,vy)
        #     return p
        if index==5:
            p=ANOVA.doANOVA(vx,vy)
            return p
    def chrWiseCriterion(vp,index):
        if index==1:
            val=0
            for p in vp:
                if p>0:
                    val-=p*math.log(p)
            return val
        if index==0:
            val=0
            for p in vp:
                if p>0:
                    val-=math.log(p)
                #print(str(vp))
            return val
    vp1=[]
    vp2=[]
    vp3=[]
    vp4=[]
    vp5=[]
    vp6=[]
    for m in chr.vMarker:
        vi1=[]
        vi2=[]
        vx=[]
        vy=[]
        i=0
        nInd=len(BigDataSimul.vInd)
        for iInd in range(nInd):
            iIndPerm=viPermutated[iInd]
            ind=BigDataSimul.vInd[iInd]
            indPerm=BigDataSimul.vInd[iIndPerm]
            a=ind.vGenotype[m.id]
            if a==0:
                vi1.append(i)
                vx.append(indPerm.trait)
            if a==1:
                vi2.append(i)
                vy.append(indPerm.trait)
            i+=1
        p=funMarker(vx,vy,0)
        vp1.append(p)
        # p=funMarker(vx,vy,0)
        # vp2.append(p)
        # p=funMarker(vx,vy,0)
        # vp3.append(p)
        p=funMarker(vx,vy,3)
        vp4.append(p)
        # p=funMarker(vx,vy,4)
        # vp5.append(p)
        p=funMarker(vx,vy,5)
        vp6.append(p)
    val1=chrWiseCriterion(vp1,index)
    # val2=chrWiseCriterion(vp2,index)
    # val3=chrWiseCriterion(vp3,index)
    val4=chrWiseCriterion(vp4,index)
    # val5=chrWiseCriterion(vp5,index)
    val6=chrWiseCriterion(vp6,index)
    li=[val1,val4,val6]#,val2,val3,val5
    return li


# data=['C:\\Users\\Public\\a1.py', 'path1', 'path2', 'path3', 'testname', 'true', 'false', 'true', 'true', 'true']

# DoChromWise(data)




