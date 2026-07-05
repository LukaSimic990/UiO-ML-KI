class Sau: #Definerer klassen Sau.
    def __init__(self, navn, posisjon): #Konstruktør med parametre navn og posisjon. 
        self._navn = navn.title() #Instansvariablene navn og posisjon som kan bli oppgitt som argumenter.
        self._posisjon = posisjon
        self._lever = True #Instansvariabelen 'lever' som er satt opp til default verdi True for alle Sau-objekt instanser. 

    def blir_spist(self): #Instansmetoden som endrer 'lever' variablen til False når eb sau objekt blir spist. 
        self._lever = False

    def lever(self): #Instansvariabelen som returenrer True/False iflg verdien av 'lever' instansvariabelen. 
        if self._lever == True:
            return True
        elif self._lever == False:
            return False
    
    def hent_navn(self): #Instansmetoden som henter navnert til Sau-objekt.
        return self._navn 
    
    def hent_posisjon(self): #Instansmetoden som henter posisjonen til Sau-objekt.
        return self._posisjon
