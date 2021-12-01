'''
Created on 5 oct. 2021

@author: Carlos Pedraza Antona, Carlos Rodríguez Gómez-Carreño, Alejandro Villamayor Zapero.
'''

import json

print("Tarea 1 ")

vector_estados=[]
   
def leer_fichero(vector_estados):
    with open("Estados.txt","r") as f:
        content = f.readlines()
        linea = 0
        for i in content:
            linea += 1
            try:
                objson = json.loads(i)
                vector_estados.append(f"Estado:{objson}")
                print(f"Estado numero  {linea}:{objson}")
                
            except (json.decoder.JSONDecodeError, Exception) as e:
                    print(f"\n Error en el Estado nº  {linea}: {e}\n")

def test1(vector_estados):
    with open('Estados.json', 'w') as f:
        json.dump(vector_estados, f)
        
    print("\n")
    print(vector_estados[0])
    #print(vector_estados[0][1])
    #print(vector_estados[0][1][0][0])
    
    
leer_fichero(vector_estados)
test1(vector_estados)



