# -*- coding: utf-8 -*-
"""
Created on Fri May  6 20:57:05 2022

@author: wajeh otman
"""
#import bigSimulator
import SingleMarkerTest
import ChromosomeWiseTest
import sys
import timeit

start = timeit.default_timer()


#sim
#inputData=['pythProject/MAIN.py', 'null', 'null', 'null', 'Single', 'false', 'true', 'true', 'false', 'false', 'true', 'sim', '120', '13', '170', '400', '1', '10', '-300']
#uploadFile
 # inputData=['C:\\Users\\Public\\a1.py', 'C:\\pythProject\\geneticMap.txt', 'C:\\pythProject\\genotypesExample.txt', 'C:\\pythProject\\traitsExample.txt', 'Single', 'true', 'fasle', 'fasle', 'fasle', 'fasle', 'fasle', 'file']

print ('Number of Arguments:', len(sys.argv), 'arguments.')
print ('Argument List:', str(sys.argv))
inputData=sys.argv
#"Single marker test","Chromosome wise test"

TestName=inputData[4]


if (TestName=="Single"):
    
    SingleMarkerTest.doSingleMarkerTest(inputData[11],inputData)
else:
    ChromosomeWiseTest.DoChromWise(inputData[11],inputData)


print('This is Python Code')
print('Executing Python')
print('From Java')
stop = timeit.default_timer()
print('Time: ', stop - start)