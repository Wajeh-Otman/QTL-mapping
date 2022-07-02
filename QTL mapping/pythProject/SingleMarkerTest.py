# -*- coding: utf-8 -*-
"""
Created on Sat May  7 15:00:17 2022

@author: wajeh otman
"""

import kruskalWalisTest
import PermTest
import StudentTest
import ANOVA
import bigSimulator
import F_test
import FDR
import matplotlib.pyplot as plt
import math


def doSingleMarkerTest(bSimul,Data):
    BigDataSimul = bigSimulator.clDataSimul()
    if (bSimul=="sim"):#run simulator
        subData1=Data[12::]
        BigDataSimul.simul(subData1)
    else:#read data from files
        subData2=Data[1:4:]
        BigDataSimul.readData(subData2)
    im=0
    vp1=[]
    vp2=[]
    vp3=[]
    vp4=[]
    vp5=[]
    vp6=[]
    Pvalue1=[]
    Pvalue2=[]
    Pvalue3=[]
    Pvalue4=[]
    Pvalue5=[]
    Pvalue6=[]
    Qvalue1=[]
    Qvalue2=[]
    Qvalue3=[]
    Qvalue4=[]
    Qvalue5=[]
    Qvalue6=[]
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
            if(Data[5]=="true"):
                p1 = StudentTest.T_test(vx,vy)
            #print(chr.sName+" "+m.sName+" "+str(p1))
                vp1.append(p1)
                Pvalue1.append(math.log10(p1)*(-1))
                
            if(Data[6]=="true"):
                p2 = PermTest.permu_test(vx,vy)
            #print(chr.sName+" "+m.sName+" "+str(p2))
                vp2.append(p2)
                Pvalue2.append(math.log10(p2)*(-1))
            
            if(Data[7]=="true"):
                p3=PermTest.permu_test_var(vx,vy)
                vp3.append(p3)
                Pvalue3.append(math.log10(p3)*(-1))
            
            if(Data[8]=="true"):
                p4 = F_test.f_test(vx,vy)
            #print(chr.sName+" "+m.sName+" "+str(p3))
                vp4.append(p4)
                Pvalue4.append(math.log10(p4)*(-1))
            
            if(Data[9]=="true"):
                p5 = kruskalWalisTest.KruskalWal(vx,vy)
            #print(chr.sName+" "+m.sName+" "+str(p4))
                vp5.append(p5)
                Pvalue5.append(math.log10(p5)*(-1))
            
            if(Data[10]=="true"):
                p6 = ANOVA.doANOVA(vx,vy)
            #print(chr.sName+" "+m.sName+" "+str(p5))
                vp6.append(p6)
                Pvalue6.append(math.log10(p6)*(-1))
            
            im+=1
    
    pos_list=[]
    
    ########################################################correted p value
    if(Data[5]=="true"):
        FDRres1=FDR.FDR_correction(vp1)
        f = open("Results/SMT_Student_test.txt", "w")
        f.write("marker\tchr\t\tpos\t\tp-val\t\t\tcorrected\t\tsignif\n")
        i=0
        for chr in BigDataSimul.vChr:
            for m in chr.vMarker:
                s=m.sName+"\t"+chr.sName+"\t"+str(m.pos)+"\t"+str(vp1[i])+"\t"+str(FDRres1[1][i])+"\t"+str(FDRres1[0][i])+"\n"
                f.write(s)
                pos_list.append(str(m.pos))
                i+=1
        f.close()
        for temp in FDRres1[1]:
            Qvalue1.append(math.log10(temp)*(-1))
        plt.plot(pos_list, Pvalue1, label ="Student test")
        plt.plot(pos_list, Qvalue1, label ="Student test", linestyle='dashed')
        # plt.plot(pos_list, log_list)
        # plt.xlabel('Chromosome postion')
        # plt.ylabel('P-value')
        # plt.title('Student test graph')
        # plt.savefig('C:/pythProject/StudentTest.png')
        # plt.show()
        
        
    if(Data[6]=="true"):
        pos_list.clear()
        FDRres2=FDR.FDR_correction(vp2)
        f = open("Results/SMT_Permutation_test.txt", "w")
        f.write("marker\tchr\t\tpos\t\tp-val\t\t\tcorrected\t\tsignif\n")
        i=0
        for chr in BigDataSimul.vChr:
            for m in chr.vMarker:
                s=m.sName+"\t"+chr.sName+"\t"+str(m.pos)+"\t"+str(vp2[i])+"\t"+str(FDRres2[1][i])+"\t"+str(FDRres2[0][i])+"\n"
                f.write(s)
                pos_list.append(str(m.pos))
                i+=1
        f.close()
        for temp in FDRres2[1]:
            Qvalue2.append(math.log10(temp)*(-1))
        plt.plot(pos_list, Pvalue2, label ="Permu test")
        plt.plot(pos_list, Qvalue2, label ="Permu test", linestyle="dashed")
        # print(pos_list)
        # print(log_list)
        # plt.plot(pos_list, log_list)
        # plt.xlabel('Chromosome postion')
        # plt.ylabel('P-value')
        # plt.title('Permutation test graph')
        # plt.savefig('C:/pythProject/PermutationTest.png')
        # plt.show()
        
    if(Data[7]=="true"):
        pos_list.clear()
        FDRres3=FDR.FDR_correction(vp3)
        f = open("Results/SMT_Permutation_VAR_test.txt", "w")
        f.write("marker\tchr\t\tpos\t\tp-val\t\t\tcorrected\t\tsignif\n")
        i=0
        for chr in BigDataSimul.vChr:
            for m in chr.vMarker:
                s=m.sName+"\t"+chr.sName+"\t"+str(m.pos)+"\t"+str(vp3[i])+"\t"+str(FDRres3[1][i])+"\t"+str(FDRres3[0][i])+"\n"
                f.write(s)
                pos_list.append(str(m.pos))
                i+=1
        f.close()
        for temp in FDRres3[1]:
            Qvalue3.append(math.log10(temp)*(-1))
        plt.plot(pos_list, Pvalue3, label ="Permu Var test")
        plt.plot(pos_list, Qvalue3, label ="Permu Var test", linestyle="dashed")
        
    if(Data[8]=="true"):
        pos_list.clear()
        FDRres4=FDR.FDR_correction(vp4)
        f = open("Results/SMT_f_test.txt", "w")
        f.write("marker\tchr\t\tpos\t\tp-val\t\t\tcorrected\t\tsignif\n")
        i=0
        for chr in BigDataSimul.vChr:
            for m in chr.vMarker:
                s=m.sName+"\t"+chr.sName+"\t"+str(m.pos)+"\t"+str(vp4[i])+"\t"+str(FDRres4[1][i])+"\t"+str(FDRres4[0][i])+"\n"
                f.write(s)
                pos_list.append(str(m.pos))
                i+=1
        f.close()
        for temp in FDRres4[1]:
            Qvalue4.append(math.log10(temp)*(-1))
        plt.plot(pos_list, Pvalue4, label ="f test")
        plt.plot(pos_list, Qvalue4, label ="f test", linestyle="dashed")
        # plt.plot(pos_list, log_list)
        # plt.xlabel('Chromosome postion')
        # plt.ylabel('P-value')
        # plt.title('f test graph')
        # plt.savefig('C:/pythProject/F_Test.png')
        # plt.show()
        
        
    if(Data[9]=="true"):
        pos_list.clear()
        FDRres5=FDR.FDR_correction(vp5)
        f = open("Results/SMT_Kruskal_Walis_test.txt", "w")
        f.write("marker\tchr\t\tpos\t\tp-val\t\t\tcorrected\t\tsignif\n")
        i=0
        for chr in BigDataSimul.vChr:
            for m in chr.vMarker:
                s=m.sName+"\t"+chr.sName+"\t"+str(m.pos)+"\t"+str(vp5[i])+"\t"+str(FDRres5[1][i])+"\t"+str(FDRres5[0][i])+"\n"
                f.write(s)
                pos_list.append(str(m.pos))
                i+=1
        f.close()
        for temp in FDRres5[1]:
            Qvalue5.append(math.log10(temp)*(-1))
        plt.plot(pos_list, Pvalue5, label ="KruWal test")
        plt.plot(pos_list, Qvalue5, label ="KriWal test", linestyle="dashed")
        # plt.plot(pos_list, log_list)
        # plt.xlabel('Chromosome postion')
        # plt.ylabel('P-value')
        # plt.title('Kruskal Walis test graph')
        # plt.savefig('C:/pythProject/KruskalWalisTest.png')
        # plt.show()
        
        
    if(Data[10]=="true"):
        pos_list.clear()
        FDRres6=FDR.FDR_correction(vp6)
        f = open("Results/SMT_ANOVA_test.txt", "w")
        f.write("marker\tchr\t\tpos\t\tp-val\t\t\tcorrected\t\tsignif\n")
        i=0
        for chr in BigDataSimul.vChr:
            for m in chr.vMarker:
                s=m.sName+"\t"+chr.sName+"\t"+str(m.pos)+"\t"+str(vp6[i])+"\t"+str(FDRres6[1][i])+"\t"+str(FDRres6[0][i])+"\n"
                f.write(s)
                pos_list.append(str(m.pos))
                i+=1
        f.close()
        for temp in FDRres6[1]:
            Qvalue6.append(math.log10(temp)*(-1))
        plt.plot(pos_list, Pvalue6, label ="ANOVA test")
        plt.plot(pos_list, Qvalue6, label ="ANOVA test", linestyle="dashed")
        # plt.plot(pos_list, log_list)
        # plt.xlabel('Chromosome postion')
        # plt.ylabel('P-value')
        # plt.title('ANOVA test graph')
        # plt.savefig('C:/pythProject/ANOVA_Test.png')
        # plt.show()
        
    # threshold=[]
    # for i in range(len(pos_list)):
    #     threshold[i]=4
    # plt.plot(pos_list,threshold, linestyle="loosely dotted")
    plt.axhline(y=2, color='r', linestyle='dotted')

    plt.xlabel('Chromosome postion')
    plt.ylabel('-log(p-value),-log(q-value)')
    plt.title('Tests results')
    plt.legend(loc='best')
    plt.savefig('Results/tests result.png')
    
    #plt.show()
    print("end")

    
