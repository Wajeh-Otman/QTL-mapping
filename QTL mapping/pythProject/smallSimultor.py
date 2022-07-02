import math  # for calculation
import random  # to simulate random values etc.
import StudentTest
import kruskalWalisTest
import PermTest
import os  # to work with files
import sys  # to get parameters from command line


class clDataSimul():
    def __init__(self):
        self.vChr = []
        self.vQTL = []
        self.vInd = []

    def runSimul(self):
        Chr = self.clChr("chr01", 120, 13)
        self.vChr.append(Chr)
        self.printToFileGeneticMap()

        for i in range(100):
            sName = "ind_" + str(i)
            Ind = self.clIndivid(sName, self.vChr)
            self.vInd.append(Ind)
        self.printToFileGenotypes()

        self.vQTL.append(self.clQTL(0, 10, -300))
        a0 = 170
        v0 = 400
        for Ind in self.vInd:
            Ind.calculateTrait(a0, v0, self.vQTL)

        self.printToFileTraits()

    class clChr():
        class clMarker():
            def __init__(self, sName, pos):
                self.sName = sName
                self.pos = pos

        def __init__(self, sName, len, nMarkers):
            self.sName = sName
            self.len = len
            self.nMarkers = 0
            self.vMarker = []
            self.setMarkersEvenly(nMarkers)

        def setMarkersEvenly(self, nMarkers):
            self.nMarkers = 0
            self.vMarker = []
            for i in range(nMarkers):
                sMarker = self.sName + "_" + str(i)
                pos = (float(self.len) / (nMarkers - 1)) * i
                Marker = self.clMarker(sMarker, pos)
                self.vMarker.append(Marker)
                self.nMarkers += 1

    class clIndivid():
        def __init__(self, sName, vChr):
            self.sName = sName
            self.vGenotype = []
            self.trait = 0
            for Chr in vChr:
                a = random.randrange(0, 2)  # 0 or 1
                posPrev = -1
                for Marker in Chr.vMarker:
                    pos = Marker.pos
                    if posPrev >= 0:
                        d_cM = pos - posPrev
                        r = 0.5 * (1 - math.exp(-float(d_cM) * 2 / 100))
                        if random.random() <= r:
                            a = 1 - a  # 0<->1
                    self.vGenotype.append(a)
                    posPrev = pos

        def calculateTrait(self, a0, v0, vQTL):
            a = a0
            v = v0
            for QTL in vQTL:
                g = self.vGenotype[QTL.iMarker]
                if g > 0:
                    a += QTL.a
                    v += QTL.v
            self.trait = random.gauss(a, v)  # not v^2

    def printToFileGeneticMap(self):
        sFileName = "geneticMap.txt"
        f = open(sFileName, 'w')
        s = "marker" + "\t" + "chr" + "\t" + "pos"
        f.write(s + "\n")
        for Chr in self.vChr:
            for Marker in Chr.vMarker:
                s = Marker.sName + "\t" + Chr.sName + "\t" + str(Marker.pos)
                f.write(s + "\n")
        f.close()

    def printToFileGenotypes(self):
        sFileName = "genotypesExample.txt"
        f = open(sFileName, 'w')
        s = "marker"
        for Ind in self.vInd:
            s += "\t" + Ind.sName
        f.write(s + "\n")
        iMarker = 0
        for Chr in self.vChr:
            for Marker in Chr.vMarker:
                s = Marker.sName
                for Ind in self.vInd:
                    s += "\t" + str(Ind.vGenotype[iMarker])
                f.write(s + "\n")
                iMarker += 1
        f.close()

    def printToFileTraits(self):
        sFileName = "traitsExample.txt"
        f = open(sFileName, 'w')
        s = "trait"
        for Ind in self.vInd:
            s += "\t" + Ind.sName
        f.write(s + "\n")
        s = "trait"
        for Ind in self.vInd:
            s += "\t" + str(Ind.trait)
        f.write(s + "\n")
        f.close()

    class clQTL():
        def __init__(self, iMarker, a, v):
            self.iMarker = iMarker
            self.a = a
            self.v = v

    class clSimpleTest():
        def __init__(self):
            self.vx = []
            self.vy = []
            self.simulParam_x = []
            self.simulParam_y = []

        def simul(self, simulParam):
            x = 0
            if simulParam[0] == "gauss":  # ["gauss",a,STDV,"a","sigma"]
                a = simulParam[1]
                v = simulParam[2]
                x = random.gauss(a, v)  # not v^2
            return x

        def vssimul(self, simulParam, bPrint):
            vs = []
            s = "distr" + "\t" + simulParam[0]
            vs.append(s)
            if bPrint:
                print(s)
            if simulParam[0] == "gauss":  # ["gauss",a,STDV,"a","sigma"]
                s = simulParam[3] + "\t" + str(simulParam[1])
                vs.append(s)
                if bPrint:
                    print(s)
                s = simulParam[4] + "\t" + str(simulParam[2])
                vs.append(s)
                if bPrint:
                    print(s)
            return vs

        def vsimul(self, simulParam, n):
            vx = []
            for i in range(n):
                x = self.simul(simulParam)
                vx.append(x)
            return vx

        def v2simul(self, simulParam_x, n_x, simulParam_y, n_y):
            self.vx = self.vsimul(simulParam_x, n_x)
            self.vy = self.vsimul(simulParam_y, n_y)
            self.simulParam_x = simulParam_x
            self.simulParam_y = simulParam_y

    def runSimpleTest(self):
        SimpleTest = self.clSimpleTest()
        SimpleTest.v2simul(["gauss", 0, 0.1, "a", "sigma"], 10, ["gauss", 1, 0.3, "a", "sigma"], 15)
        print("parameters of x:" + "\t" + str(len(SimpleTest.vx)))
        SimpleTest.vssimul(SimpleTest.simulParam_x, True)
        print()
        print("parameters of y:" + "\t" + str(len(SimpleTest.vy)))
        SimpleTest.vssimul(SimpleTest.simulParam_y, True)
        print()
        #print("x:" + "\t" + str(SimpleTest.vx))
        #print("y:" + "\t" + str(SimpleTest.vy))
        return SimpleTest.vx,SimpleTest.vy


DataSimul = clDataSimul()
DataSimul.runSimul()
vx,vy= DataSimul.runSimpleTest()

#p1=StudentTest.T_test(vx,vy)
#p2=KruskalWal

#print("p value of t-test = "+str(p))
# cd C:\Frenkel\Braude\2021b\QTL_varEffect\progVova\
# python C:\Frenkel\Braude\2021b\QTL_varEffect\progVova\varQTLsimul.py
# stop: ctrl+c
#
# pip install numpy
# pip install sklearn
