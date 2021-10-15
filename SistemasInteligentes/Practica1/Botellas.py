'''
Created on 5 oct. 2021

@author: Carlos
'''

import json


"""JSON"""


f = open('Estados.txt', 'r')
content = f.read()
objetoJson = json.dumps(content, indent=4)

print(objetoJson)

f = open('Estados.txt', 'w')
f.write(objetoJson)
f.close


'''
acciones = open('Acciones.txt', 'r')
c = acciones.read()
accionesJson = json.dumps(c)
'''

""" Leer fichero .txt 

fichero = open('Estados.txt')

print(fichero.read())

"""

'''JSON a Python

datosJSON ='{"name": "Carlos", "edad":39}'
objetoJson = json.loads(datosJSON)
print(objetoJson)

Python a JSON

datosPython = {'name': 'Carlos', 'edad':44, 'isEmployed':True}
diccionariotoJSON = json.dumps(datosPython)
print(diccionariotoJSON)

'''
