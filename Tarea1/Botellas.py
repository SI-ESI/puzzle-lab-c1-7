'''
Created on 5 oct. 2021

@author: Carlos Pedraza Antona, Carlos Rodríguez Gómez-Carreño, Alejandro Villamayor Zapero.
'''

import json

class Json:
    def __init__(self,estado):
        self.estado = estado

def leer_fichero(vector_estados):
    
    with open("Estados.txt","r") as f:
        content = f.readlines()
        linea = 0
        for i in content:
            linea += 1
            try:
                objson = json.loads(i)
                vector_estados.append(objson)
                #vector_estados_json.append(Json(objson).__dict__)
                
            except (json.decoder.JSONDecodeError, Exception) as e:
                    print(f"\n Error en el Estado nº  {linea}: {e}\n")

def imprime_json(vector_estados):
    print(json.dumps(vector_estados, indent=2))
    
def ES_AccionPosible (Botella_origen, Botella_destino, Cantidad):
    max = 9
    posible = False
    if total_cantidad(Botella_origen) >= Cantidad:
        if (total_cantidad(Botella_destino)+Cantidad) <= max:
            posible = True
        else:
            posible = False

    return posible

def total_cantidad (Botella):
    suma = 0
    for i in range(0, len(Botella)):
        suma = Botella[i][0]

    return suma

def escribir_json(vector_estados):
    with open('Estados.json', 'w') as f:
        s=json.dumps(vector_estados, indent=2)
        f=open("Estados.json","w")
        f.write(s)
        f.close()
        
def main():
    print("Tarea 1 ")
    
    vector_estados=[]
    vector_estados_json=[]    
    leer_fichero(vector_estados)
    
    
    imprime_json(vector_estados)
    posible=ES_AccionPosible (vector_estados[4][0], vector_estados[4][1], 7)
    
    print(posible)
    
    escribir_json(vector_estados)


try:
    main()
except KeyboardInterrupt:
    print("exit")





