import Calculation
import kruskalWalisTest
import PermTest
import StudentTest
import ANOVA
import smallSimultor
import bigSimulator
import F_test
import kruskalWalisTest
import FDR
import math
import numpy as np

def creatDataSamllSim():
    smallDataSimul = smallSimultor.clDataSimul()
    vx,vy= smallDataSimul.runSimpleTest()
    return vx,vy


def chromozomeWiseCriterion(BigDataSimul,chr,viPermutated,index=0):
    def funMarker(vx,vy,index):
        if index==0:
            p=StudentTest.T_test(vx,vy)
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
    vp=[]
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
        p=funMarker(vx,vy,index)
        vp.append(p)
    val=chrWiseCriterion(vp,index)
    return val


def testforDataBigSim(bSimul=False):
    BigDataSimul = bigSimulator.clDataSimul()
    if (bSimul):
        BigDataSimul.simul()
    else:
        BigDataSimul.readData()
    im=0
    vp1=[]
    vp2=[]
    vp3=[]
    vp4=[]
    vp5=[]
    for chr in BigDataSimul.vChr:
        for m in chr.vMarker:
            vi1=[]
            vi2=[]
            vx=[]
            vy=[]
            i=0
            for ind in BigDataSimul.vInd:
                a=ind.vGenotype[im]
                if a==0:
                    vi1.append(i)
                    vx.append(ind.trait)
                if a==1:
                    vi2.append(i)
                    vy.append(ind.trait)
                i+=1
            
            p1 = StudentTest.T_test(vx,vy)
            #print(chr.sName+" "+m.sName+" "+str(p1))
            vp1.append(p1)
            
            p2 = ANOVA.doANOVA(vx,vy)
            #print(chr.sName+" "+m.sName+" "+str(p2))
            vp2.append(p2)
            
            p3 = PermTest.permu_test(vx,vy)
            #print(chr.sName+" "+m.sName+" "+str(p3))
            vp3.append(p3)
            
            p4 = F_test.f_test(vx,vy)
            #print(chr.sName+" "+m.sName+" "+str(p4))
            vp4.append(p4)
            
            p5 = kruskalWalisTest.KruskalWal(vx,vy)
            #print(chr.sName+" "+m.sName+" "+str(p5))
            vp5.append(p5)
            
            im+=1
        ##############################################print p value for all the test
    
    
    # print("************ANOVA")
    # for temp in vp2:
    #     print(str(temp))
    # print()
    
    # print("************Permutation test")
    # for temp in vp3:
    #     print(str(temp))
    # print()
    
    # print("************f-test")

    # for temp in vp4:
    #     print(str(temp))
    # print()
    
    # print("************kruskal Walis Test")
    # for temp in vp5:
    #     print(str(temp))
    # print()
    ########################################################correted p value
    FDRres=FDR.FDR_correction(vp1)
    #print("corrected p-value for t-test\n"+str(vp1_corrected)+"\n")
    print("marker\tchr\tpos\tp-val\tcorrected\tsignif")
    i=0
    for chr in BigDataSimul.vChr:
        for m in chr.vMarker:
            s=m.sName+"\t"+chr.sName+"\t"+str(m.pos)+"\t"+str(vp1[i])+"\t"+str(FDRres[1][i])+"\t"+str(FDRres[0][i])
            print(s)
            i+=1
    
    # vp2_corrected=FDR.FDR_correction(vp2)
    # print("corrected p-value for ANOVA\n"+str(vp2_corrected)+"\n")
    
    # vp3_corrected=FDR.FDR_correction(vp3)
    # print("corrected p-value for Permutation test\n"+str(vp3_corrected)+"\n")
    
    # vp4_corrected=FDR.FDR_correction(vp4)
    # print("corrected p-value for f-test\n"+str(vp4_corrected)+"\n")
    
    # vp5_corrected=FDR.FDR_correction(vp5)
    # print("corrected p-value for kruskal Walis Test\n"+str(vp5_corrected)+"\n")
    
    #chromosome-wise permutation test
    
    chr=BigDataSimul.vChr[0]
    nInd=len(BigDataSimul.vInd)
    viPermutated=list(range(nInd))
    #print(str(viPermutated))
    val0=chromozomeWiseCriterion(BigDataSimul,chr,viPermutated,0)
    vval=[]
    for i in range(1000-1):
        viPermutated=np.random.permutation(nInd)
        #print(str(viPermutated))
        val=chromozomeWiseCriterion(BigDataSimul,chr,viPermutated,0)
        vval.append(val)
    
    permutationRes=PermTest.permu_test(vval, [val0])
    print("Chromosomal Permutation test result:")
    print(str(permutationRes))
    #print(str(val0))
    #print(str(vval))
def DoSingleMarkerTest(vx,vy):
#create data
    vx,vy=creatDataSamllSim()
    #f_geneMap,f_genotype,f_trait=readDataBigSim()
    
#tests
    p1 = StudentTest.T_test(vx,vy)
    p2 = ANOVA.doANOVA(vx,vy)
    p3 = PermTest.permu_test(vx,vy)
    p4 = F_test.f_test(vx,vy)
    p5 = kruskalWalisTest.KruskalWal(vx,vy)

    return p1, p2, p3, p4, p5


def testforDataSMALLSim():
    vx,vy=creatDataSamllSim()
    #list_geneMap,list_genotype,list_trait=readDataBigSim()
    p1,p2,p3,p4,p5=DoSingleMarkerTest(vx,vy)
    print("p value of t-test = "+str(p1))
    print("p value of ANOVA-test = "+str(p2))
    print("p value of permutation-test = "+str(p3))
    print("p value of f-test = "+str(p4))
    print("p value of kruskal Walis Test = "+str(p5))
    print("*****************************************")
 




#testforDataSMALLSim()
testforDataBigSim(False)












