'''
Created on 5 oct. 2021

@author: Carlos Pedraza Antona, Carlos Rodríguez Gómez-Carreño, Alejandro Villamayor Zapero.
'''

import json
from ntpath import split
from math import radians
from textwrap import indent
from itertools import count



def main():
    print("Tarea 1 ")
    vector_estados=[]
    leer_fichero(vector_estados)
    test1(vector_estados)

   
def leer_fichero(vector_estados):
    with open("Estados.txt","r") as f:
        content = f.readlines()
         
        indice = 0
        for linea in content:
            indice += 1
            try:
                objson = json.loads(linea)
                vector_estados.append(objson)
                #print(f"Estado nº  {indice}: {objson}")
                
            except (json.decoder.JSONDecodeError, Exception) as e:
                    print(f"\n Error en el Estado nº  {indice}: {e}\n")


def test1(vector_estados):
    with open('Estados.json', 'w') as f:
        json.dump(vector_estados, f)
    """"    
    print("\n")
    print(vector_estados[0])
    print(vector_estados[0][1])
    print(vector_estados[0][1][0])
    """
    esposible = (ES_AccionPosible(vector_estados[0][0], vector_estados[0][1], 8))
    print(esposible)
    if esposible:
        Accion(vector_estados[0][0], vector_estados[0][1], 9)
    print(vector_estados[0])

    


#Comprobar si se puede hacer el movimiento que queremos
def ES_AccionPosible (Botella_origen, Botella_destino, cantidad):
    max = 10
    posible = False
    if total_cantidad(Botella_origen) >= cantidad:
        if (total_cantidad(Botella_destino)+cantidad) <= max:
            posible = True
    return posible
#Cantidad total en una botella independientemente del color
def total_cantidad (Botella):
    suma = 0
    for i in range(0, len(Botella)):
        print(i)
        suma += Botella[i][1]
        print(Botella[i][1])
    return suma

def Accion(Botella_origen, Botella_destino, cantidad):
    acumulado = 0
    nColores = 0
    i = 0
    insertar_liquido(cantidad, Botella_origen, Botella_destino)
    eliminar_liquido(cantidad, Botella_origen)
    i += 1
    
def eliminar_liquido(cantidad, Botella_origen):
    
    for i in range(0, len(Botella_origen)):
        if cantidad >= Botella_origen[0][1]:
            cantidad -= Botella_origen[0][1]
            Botella_origen.pop(0)  
        else:
            Botella_origen[0][1] -= cantidad
            break
def insertar_liquido (cantidad, Botella_origen, Botella_destino):
    for i in range(0, len(Botella_origen)):
        if Botella_origen[i][0] == Botella_destino[0][0]:#tenemos que mezclar 
            if cantidad > Botella_origen[i][1]:#tenemos que echar todo el color porque hay mas colores que echar
                Botella_destino[0][1] += Botella_origen[i][1]
                cantidad -= Botella_origen[i][1]
            else: 
                Botella_destino[0][1] += cantidad #solo hay q echar de este color asique echamos todos y se acabo
                break
        else:
            if cantidad > Botella_origen[i][1]:
                Botella_destino.insert(0, Botella_origen[i])
                cantidad -= Botella_origen[i][1]
            else:
                Botella_destino.insert(0, Botella_origen[i])
                Botella_destino[i][1] = cantidad
                break
              


            


#try:
main()
#except KeyboardInterrupt:
#    print("exit")
