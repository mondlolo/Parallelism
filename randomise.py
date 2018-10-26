from random import *

def makeTree():
    x = randint(1,10000)
    y = randint(1,10000)
    r = randint(3,15)
    tree = str(x) +" " +str(y) +" "+ str(r)
    return tree

def makeSunlight():
    return uniform(5, 30)


def main():
    f = open("newFile.txt","a+")
    j = ""
    for k in range(0,10000*10000):
        j += str(makeSunlight()) +" "

    f.append("20 20")
    print(j)

    for i in range(1000):
        print(makeTree())

main()
