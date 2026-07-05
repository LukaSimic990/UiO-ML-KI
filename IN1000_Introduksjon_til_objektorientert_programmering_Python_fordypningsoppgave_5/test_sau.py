from sau import Sau #Importerer klassen Sau fra filen sau.py. 

sau = Sau('whitey', 1) #Oppretter et Sau-objekt.
assert sau.lever() == True, 'Expected: True, but found: False' #Assert for å sjekke om verdien for 'lever' variabelen stemmer. 
sau.blir_spist() #Bruker instansmetoden 'blir_spist' for å endre verdien til 'lever' variabelen.
assert sau.lever() == False, 'Expected: False, but found: True' #Assert for å sjekke verdien til 'lever' variabelen igjen. 

