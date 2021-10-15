import json
from textwrap import indent

f = open('Estados.txt', 'r')
content = f.read()
objetoJson = json.dumps(content)

estadosF = json.loads(objetoJson)

print(estadosF)


    







