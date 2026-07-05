from verden import Verden #Importerer klassen Verden fra filen verden.py. 

verden = Verden() #Oppretter instans av Verden-klasse. 
verden.opprett_dyr('sau', 'whitey', 2) #Oppretter dyr til Verdenen. 
verden.opprett_dyr('sau', 'lilly', 4)
verden.opprett_dyr('ulv', 'blackey', 6) #Her kunne jeg evt. brukt en liste med navn og for-løkke, det er jeg helt klar over, men jeg gjorde det 
verden.opprett_dyr('ulv', 'crazy', 3) #sånn først og dessverre hadde en jobb-helg nå i helgen så hadde ikke mulighet til å fikse det. 
verden.opprett_dyr('sau', 'poly', 8)
verden.opprett_dyr('sau', 'pedro', 1)
verden.opprett_dyr('sau', 'millie', 5)
verden.opprett_dyr('ulv', 'bad one', 9)
verden.opprett_dyr('sau', 'bella', 7)
verden.opprett_dyr('sau', 'molly', 3)
verden.opprett_dyr('sau', 'dana', 4)

result_antall_sauer = verden.antall_sauer() #Sjekker om metodene som henter antal dy returnerer riktige verdier. 
assert verden.antall_sauer() == 8, 'Expected: 8, but found:'+str(result_antall_sauer)

result_antall_ulver = verden.antall_ulver()
assert verden.antall_ulver() == 3, 'Expected: 3, but found:'+str(result_antall_ulver)

#Sjekker om Verdenen er sånn som vi har opprettet den ved bruk av instansmetoden 'beskriv' på den.
verden.beskriv()

#Oppdaterer VErdenen 10 ganger.
verden.oppdater()
verden.oppdater()
verden.oppdater()
verden.oppdater()
verden.oppdater()
verden.oppdater()
verden.oppdater()
verden.oppdater()
verden.oppdater()
verden.oppdater()

#Sjekker hvilke dyr det er igjen og på hvilke posisjoner nå.
verden.beskriv()
verden.feiteste_ulven() #Sjekker å se hvilken ulv er den feiteste. 








