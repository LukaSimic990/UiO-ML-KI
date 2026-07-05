from sau import Sau #Importerer klassene Sau og Ulv.
from ulv import Ulv 

sau = Sau('whitey', 1) #Oppretter ett objekt av hver klasse.
ulv = Ulv('blackey', 1)

assert sau.lever() == True, 'Expected: True, but found: False' #Sjekker startverdien til 'lever' variabelen til Sau-objektet.
result_ulv_vekt = ulv.hent_vekt() #Sjekker startverdien til 'vekt' variabelen til Ulv-objektet.
assert ulv.hent_vekt() == 20, 'Expected: 20, but found:'+str(result_ulv_vekt) 

ulv.spis_sau(sau) #Bruker instansmetoden 'spis_sau' på Ulv-objektet med Sau-objektet som argument.
#Sjekker om metoden har gjort det den skulle. 
result_ulv_vekt_2 = ulv.hent_vekt() 
riktig = ulv.hent_vekt()
assert riktig == 25, 'Expected: 25, but found:'+str(result_ulv_vekt_2)
assert sau.lever() == False, 'Expected: False, but found: True'

