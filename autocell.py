import random
import copy
import os
import time

size_row = 20
size_column = 20
key = 0.5
global matrix

def initState():
    if random.random() > key:
        return 1
    else:
        return 0
    
def init():
    global matrix
    matrix = [[initState() for j in range(size_column)] for i in range(size_row)]
 
def show():
    for list in matrix:
        print list
 
def next():
    snapshot = copy.deepcopy(matrix)
    for i in range(0, len(matrix)):
        for j in range(0, len(matrix[i])):
            nextState(i, j, snapshot)
  
def nextState(row, column, snapshot):
    global matrix
    sum = 0
    for i in range(row - 1, row + 2):
        for j in range(column - 1, column + 2):
            if(i >= 0 and j >= 0 and i < len(matrix) and j < len(matrix[column]) and not(i == row and j == column)):
                sum += snapshot[i][j]
                #print 'sum (', row, ',', column, ')=', sum      
    if matrix[row][column] == 1:
        if sum < 2 or sum > 3:
            matrix[row][column] = 0
    else:
        if sum == 3:
            matrix[row][column] = 1        
   
   
def begin():
    init()
    for i in range(1, 1000):
        next()
        show()
        time.sleep(1)
        os.system('cls')
    
    
begin()    
    
    
