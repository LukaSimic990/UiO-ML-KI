from sau import Sau #Importerer klassen Sau. 

class Ulv: #Definerer klassen Ulv.
    def __init__(self, navn, posisjon): ##Konstruktør med parametre navn og posisjon.
        self._navn = navn.title() #Instansvariablene navn og posisjon som kan bli oppgitt som argumenter.
        self._posisjon = posisjon
        self._vekt = 20 #Instansvariabelen 'vekt' settes til 20 som default for hver nytt Ulv-objekt.

    def spis_sau(self, sau_objekt): #Instansmetoden 'spiser en sau'. Metoden bruker 'blir_spist' metode fra Sau-klassen.  
        sau_objekt.blir_spist()
        self._vekt += 5 #Metoden øker vekten til Ulv-objekt med 5 når objektet spiser et Sau-objekt.

    def hent_vekt(self): #Henter vekten til et Ulv-objekt.
        return self._vekt 
    
    def hent_navn(self): #Henter navnet til et Ulv-objekt.
        return self._navn
    
    def hent_posisjon(self): #Henter posisjonen til et Ulv-objekt.
        return self._posisjon
    
    def beveg_høyre(self): #Instansmetoden som beveger et Ulv-objekt til høyre.
        self._posisjon += 1 #Posisjonsverdien øker med 1 når et Ulv-objekt beveger seg mot høyre. 
        if self._posisjon > 10: #Hvis posisjonen øker over verdien 10, får Ulv-objekt den start posisjonen 1. 
            self._posisjon = 1

    def beveg_venstre(self): #Instansmetoden som beveger et Ulv-objekt til venstre. 
        self._posisjon -= 1 #Posisjonsverdien minker med 1 når et Ulv-objekt beveger seg mot venstre. 
        if self._posisjon < 1: #Hvis posisjonen minker under verdien 1, får Ulv-objekt posisjonen 10, den maksimumsposisjonen. 
            self._posisjon = 10
    
    

