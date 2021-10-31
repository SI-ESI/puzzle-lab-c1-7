'''
Created on 27 oct 2021

@author: Carlos
'''
import json

lista_color = [
        {
            'color' : 0,
            'cantidad' : 8,
        },
        {
            'color' : 3,
            'cantidad' : 1,
        },
        {
            'color' : 0,
            'cantidad' : 1,
        } 
    ]

lista_botella = [
        {
            'botella' : lista_color,
        }
    ]

lista_estado = [
        {
            'estado' : lista_botella,
        }
    ]

print(lista_color)

# convertir a JSON:

#y = json.dumps(x)

#El resultado es un string de JSON:

#print(y)