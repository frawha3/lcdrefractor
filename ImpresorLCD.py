# -*- coding: utf-8 -*-
"""
Created on Mon Jan  1 

@author: Franklin E. Whaite G.
@email: fe.whaite10@uniandes.edu.co
"""


import sys
import numpy as np

"""
#--------------------------------------------------------------
# Classes
#--------------------------------------------------------------
"""
#Class that represents a segement of an LCD digit
class segment():
       def __init__(self,indexFp,position):
           self.indexFp=indexFp
           self.position=position

"""
#--------------------------------------------------------------
# Functions for validation
#--------------------------------------------------------------
"""
#Validatea that the givenspace is numeric and between (0,5)
def validateSpace(space):
    try:
        space=int(space)
    except ValueError:
        sys.exit("Cadena " + space + " no es un entero")
    if((space <0) | (space>5)):
        sys.exit("El espacio entre digitos debe estar entre 0 y 5")
        
#Validates that a command has th character "," 
#and the correct amount of paremeters
#and that the digit size is numeric and between (1,10)
def validateCommand(command):
    if(not ("," in command)):
        sys.exit("Cadena " + command + " no contiene caracter ,")
    
    parameters=command.split(",")
    if(len(parameters)>2):
        sys.exit("Cadena " + command+ " contiene mas caracter ,")
        
    
    if(parameters[1]==''):
        sys.exit("Cadena " + command+ " no contiene los parametros requeridos")
    
    size=parameters[0]
    try:
        size=int(size)
    except ValueError:
        sys.exit("Parametro Size [" + size+ "] no es un numero")
        
    if((size <1) | (size>10)):
        sys.exit("El parametro size ["+str(size) + "] debe estar entre 1 y 10")

#Valdates that a digit is numeric
def validateDigit(digit):
    try:
        digit=int(digit)
    except ValueError:
        sys.exit("Caracter " + digit+ " no es un digito")

"""      
#--------------------------------------------------------------
# General Functions
#--------------------------------------------------------------
"""
#Adds a digit to outputMatrix
def addDigit(digit, containerDigits, outputMatrix, size):
    for segment in  containerDigits[int(digit)]:
        fp=containerFp[segment.indexFp]
        position=segment.position
        
        if (position==POSITION_X):
            for y in range(1,(size+1)):
                val = fp[1] + y;
                outputMatrix[fp[0]][val] = HORIZONTAL_CHAR;
        else:
            for y in range(1,(size+1)):
                val = fp[0] + y;
                outputMatrix[val][fp[1]] = VERTICAL_CHAR;

#Prints the outputMatrix         
def printMatrix(outputMatrix):
    for i in range(outputMatrix.shape[0]):
        for j in range(outputMatrix.shape[1]):
            print(outputMatrix[i,j],end='')
        print('')

"""
#--------------------------------------------------------------
# Variables
#--------------------------------------------------------------
"""
#Constant characters that make up a digit
VERTICAL_CHAR= "|"
HORIZONTAL_CHAR = "-"

#Constant that indicates the position of a segment
POSITION_X = "X"
POSITION_Y = "Y"

#Contains the fixed points of a digit
containerFp=np.zeros(shape=[5, 2]).astype(int)

#Initialize segements
seg1=segment(0,POSITION_Y)
seg2=segment(1,POSITION_Y)
seg3=segment(4,POSITION_Y)
seg4=segment(3,POSITION_Y)
seg5=segment(0,POSITION_X)
seg6=segment(1,POSITION_X)
seg7=segment(2,POSITION_X)

#Each element of this lists represents the digit correspoding to its index
containerDigits=[None]*10

#Saves each digit's segments
containerDigits[0]=[seg1,seg2,seg3,seg4,seg5,seg7]
containerDigits[1]=[seg3,seg4]
containerDigits[2]=[seg2,seg3,seg5,seg6,seg7]
containerDigits[3]=[seg3,seg4,seg5,seg6,seg7]
containerDigits[4]=[seg1,seg6,seg3,seg4]
containerDigits[5]=[seg5,seg1,seg6,seg4,seg7]
containerDigits[6]=[seg5,seg1,seg6,seg2,seg7,seg4]
containerDigits[7]=[seg5,seg3,seg4]
containerDigits[8]=[seg1,seg2,seg3,seg4,seg5,seg6,seg7]
containerDigits[9]=[seg1,seg3,seg4,seg5,seg6,seg7]


"""
#--------------------------------------------------------------
# main
#--------------------------------------------------------------
"""
def main():
    #reads space between digits
    space=input("Espacio entre Digitos (0 a 5):")
    
    validateSpace(space)
    space=int(space)
    
    #saves input lines
    commandLines=[]
    
    #reads input lines
    command=input("Entrada:")
    
    #reads input and saves it to commandLines
    while (command!="0,0"):
        commandLines.append(command)
        command=input("Entrada:")
        
    #processes each entry of commandLines
    for command in commandLines:
        validateCommand(command)
    
        #extracts the size and number
        parameters=command.split(",")
        size=int(parameters[0])
        number=parameters[1] 
            
        #rows of the digit    
        rowDig = (2 * size) + 3;
    
        #columns of the digit 
        colDig = size + 2;
    
        #total rows of the number
        totalRows = rowDig;
    
        #total columns of the number
        totalCols = (colDig * len(number))+ (space * len(number));
        
        #Initializes and fills the matrix were the number being print is saved 
        outputMatrix=(np.empty(shape=[totalRows,totalCols])).astype('str')
        outputMatrix.fill(" ")
            
        #Calculate coordinates of the first digit's fixed points
        #all other coordinates of fixed points are intially cero
        containerFp[1,0] = (rowDig / 2)
        containerFp[2,0] = (rowDig - 1)
        containerFp[3,0] = (rowDig / 2)
        containerFp[3,1] = (colDig - 1) 
        containerFp[4,1] = (colDig - 1) 
        
        pivotX = colDig + space;
            
        for digit in number:
            validateDigit(digit)
            addDigit(digit, containerDigits, outputMatrix, size)
            
            #updates second coordinate of fixed points
            containerFp[:,1]=containerFp[:,1]+pivotX
            
        #prints matrix
        printMatrix(outputMatrix)
        
        #resets fixed points        
        containerFp.fill(0)
        
if __name__== "__main__":
  main()

    
    
    
    
    
    
    
    
    
