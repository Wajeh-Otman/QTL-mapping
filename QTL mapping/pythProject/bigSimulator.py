import math  # for calculation
import random  # to simulate random values etc.
import os  # to work with files
import sys  # to get parameters from command line


class clDataSimul():
    def readDataFromFile__geneticMap(self,sFileName):
        a=[]
        with open(sFileName) as inp:
            a.append(list(zip(*(line.strip().split('\t') for line in inp))) )
        nm=len(a[0][0])-1
        self.vChr = []
        #iChr=-1
        chrom=self.clChr("", 0, 0)
        for m in range(nm):
            sMarker=a[0][0][m+1]
            sChr=a[0][1][m+1]
            sCoor=a[0][2][m+1]
            coor=float(sCoor)
            marker=chrom.clMarker(sMarker, coor)
            marker.id=m
            bNewChr=False
            if (chrom.sName==""):
                bNewChr=True
            else:
                if chrom.sName!=sChr:
                    bNewChr=True
            if (bNewChr):
                chrom=self.clChr("", 0, 0)
                chrom.sName=sChr
                self.vChr.append(chrom)
            chrom.vMarker.append(marker)
            chrom.nMarkers+=1
            chrom.length=max(chrom.length,coor)
    def readDataFromFile__genetypes(self,sFileName):
        a=[]
        with open(sFileName) as inp:
            a.append(list(zip(*(line.strip().split('\t') for line in inp))) )
        nm=len(a[0][0])-1
        nInd=len(a[0])-1
        
        #self.sName = sName
        #self.vGenotype = []
        #self.trait
        self.vInd = []
        for i in range(nInd):
            sName=a[0][i+1][0]
            ind=self.clIndivid(sName,self.vChr)
            self.vInd.append(ind)
        for m in range(nm):
            #sMarker=a[0][0][m+1]
            for i in range(nInd):
                allele=int(a[0][i+1][m+1])
                self.vInd[i].vGenotype.append(allele)
    def readDataFromFile__traits(self,sFileName):
        a=[]
        with open(sFileName) as inp:
            a.append(list(zip(*(line.strip().split('\t') for line in inp))) )
        #nTraits=len(a[0][0])-1
        nInd=len(a[0])-1
        for i in range(nInd):
            self.vInd[i].trait=float(a[0][i+1][1])
    def readData(self,Data):
        self.readDataFromFile__geneticMap(Data[0])
        self.readDataFromFile__genetypes(Data[1])
        self.readDataFromFile__traits(Data[2])
    def __init__(self):
        
        self.vChr = []
        self.vQTL = []
        self.vInd = []
    def simul(self,Data):
        #Data[4]  Number of QTL
        nMarkers=int(Data[1])
        Chr = self.clChr("chr01", float(Data[0]), nMarkers)
        Chr.setMarkersEvenly(nMarkers)
        self.vChr.append(Chr)
        self.printToFileGeneticMap()

        for i in range(100):
            sName = "ind_" + str(i)
            Ind = self.clIndivid(sName, self.vChr)
            self.vInd.append(Ind)
        self.printToFileGenotypes()
        
        #(self, iMarker, a, v)
        #self.vQTL.append(self.clQTL(0, 0, 20))#var
        self.vQTL.append(self.clQTL(int(Data[4])-1, float(Data[5]), float(Data[6])))#addetive  
        a0 = float(Data[2]) #int(Data[5]) allele mean
        v0 = float(Data[3])  #int(Data[6])  allele var
        for Ind in self.vInd:
            Ind.calculateTrait(a0, v0, self.vQTL)

        self.printToFileTraits()

    class clChr():
        class clMarker():
            def __init__(self, sName, pos):
                self.sName = sName
                self.pos = pos
                self.id=-1

        def __init__(self, sName, length, nMarkers):
            self.sName = sName
            self.length = length
            self.nMarkers = 0
            self.vMarker = []
            

        def setMarkersEvenly(self, nMarkers):
            self.nMarkers = 0
            self.vMarker = []
            for i in range(nMarkers):
                sMarker = self.sName + "_" + str(i)
                pos = (float(self.length) / (nMarkers - 1)) * i
                Marker = self.clMarker(sMarker, pos)
                Marker.id=len(self.vMarker)
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


a = clDataSimul()
#a.simul()
#a.readData()