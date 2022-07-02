# This is a sample Python script.
import statistics
import scipy.stats as stats

# Press Shift+F10 to execute it or replace it with your code.
# Press Double Shift to search everywhere for classes, files, tool windows, actions, and settings.

# Press the green button in the gutter to run the script.


def average(l):
    avg = statistics.mean(l)
    return avg


def variance(l):
    var = statistics.variance(l)
    return var


def symmetry(A, B):
    sym = A ^ B
    return sym


def calcKurtosis(l):
    kur = stats.kurtosis(l, fisher=True)  # true for fisher , false for Pearson's definition .
    return kur









