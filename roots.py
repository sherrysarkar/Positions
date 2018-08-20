import numpy as np
from scipy import stats
import matplotlib.pyplot as plt

def straddle_biggest_root(degree):
    coeff = [1, -1]
    coeff += [0] * (degree - 3)
    coeff += [-1]
    roots = np.roots(coeff)
    first_root = roots[0]
    second_root = roots[1]
    distance = np.absolute(first_root) - np.absolute(second_root)
    #print(first_root)
    #print(second_root)
    print("Degree ", degree, "Distance ", distance)
    straddle_pont = first_root - (distance/2.0)
    #print("Straddle Point ", straddle_pont)
    #if np.absolute(second_root) >= 1:
        #print("Second root is greater than 1")
    return distance

def second_root_magnitude(degree):
    coeff = [1, -1]
    coeff += [0] * (degree - 3)
    coeff += [-1]
    roots = np.roots(coeff)
    first_root = roots[0]
    second_root = roots[1]
    ans = np.absolute(second_root)

    return ans

def execute_roots():
    x = []
    y = []
    for i in range(5, 100):
        distance = straddle_biggest_root(i)
        x += [i]
        y += [distance]
        print()

    plt.scatter(x, y, c="g")
    plt.show()

def distance_between_cutoffs():
    cutoffs = []
    file_cutoffs = open("cutoffs.txt", "r")
    for number in file_cutoffs.read().split():
        cutoffs.append(float(number))

    differences = []

    for i in range(len(cutoffs) - 1):
        differences.append(cutoffs[i + 1] - cutoffs[i])

    differences = [0.1] + differences
    order = [x for x in range(101)]

    print(min(differences))

    plt.scatter(cutoffs, differences, c="g")
    plt.show()

def number_of_cutoffs():
    x = [0.5 * i for i in range(5,152)]

    cutoffs = []
    file_cutoffs = open("cutoffs.txt", "r")
    for number in file_cutoffs.read().split():
        cutoffs.append(float(number))
    y = []
    for i in x:
        j = 0
        while cutoffs[j] <= i and j < len(cutoffs) - 1:
            j += 1
        y.append(j)

    for k in range(0, len(x)):
        print(x[k], "&", y[k])
        print("\\\ \hline")
    print(np.polyfit(x, y, 2))
    #print(y)
    plt.scatter(x, y, c="black", marker='.')
    plt.xlabel("n")
    plt.ylabel("$\gamma(n)$")
    plt.show()

number_of_cutoffs()
