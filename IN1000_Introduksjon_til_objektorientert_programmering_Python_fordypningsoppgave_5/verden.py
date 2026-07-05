from sau import Sau #Importerer alle nødvendige klasser for å opprette Verden-klassen.
from ulv import Ulv
from random import randint  

class Verden: #Definerer Verden-klassen. 
    def __init__(self): #Konstruktør uten parametre.
        self._sauer = []   #Variablene sauer og ulver som er lister som skal holde alle Sau- og Ulv-objekter som blir opprettet i Verden. 
        self._ulver = []

    def opprett_dyr(self, type, navn, posisjon): #Instansmetoden som oppretter dyr i Verden. Den tar type, navn og posisjon so argumenter.
        if type == 'sau':
            sau_objekt = Sau(navn, posisjon)
            self._sauer.append(sau_objekt)
        elif type == 'ulv':
            ulv_objekt = Ulv(navn, posisjon)
            self._ulver.append(ulv_objekt)
        else:
            print('Dyrets type kan enten være sau eller ulv!')

    def beskriv(self): #Instansmetoden som skriver ut hvor mange dyr av hver art det er i hverden med posisjon og navn.
        if self._sauer: #Sjekker om det er noen Sau-objekter igjen i Verden og skriver dem ut.
            print('\nDet er følgende sauer i verden:')
            for objekt in self._sauer:
                print(f'Navn: {objekt._navn} (posisjon: {objekt._posisjon})')
        else: #Hvis alle Sau-objekter har blitt spist av Ulv-objektene, skriver metoden denne beskjeden.
            print('\nDet er ingen levende sauer lenger!')
        print('\nDet er følgende ulver i verden:')
        for objekt in self._ulver: #Ulv-objeketene kan ikke bli spist. 
            print(f'Navn: {objekt._navn} (posisjon: {objekt._posisjon})')

    def antall_sauer(self): #Instansmetoden som henter hvor mange Sau-objekter det er i Verden.
        return len(self._sauer)
    
    def antall_ulver(self):#Instansmetoden som henter hvor mange Ulv-objekter det er i Verden.
        return len(self._ulver)
    
    def oppdater(self): #Instansmetoden som oppdaterer Verdenen. 
        bevegelse = randint(0, 1) #Randint for å gi tilfeldig tall mellom 0 og 1. 0 - beveger Ulv-objekt til høyre, 1 - beveger Ulv-objekt til venstre.
        for objekt in self._ulver:
            if bevegelse == 0:
                objekt.beveg_høyre()
            elif bevegelse == 1:
                objekt.beveg_venstre()
        for ulv in self._ulver: #Sjekker om noen Sau-objekter blir spist av Ulv-objektene. 
            for sau in self._sauer:
                if ulv._posisjon == sau._posisjon: #Hvis posisjonene til et Ulv- og et Sau-objekt blir like - Ulv-objektet spiser Sau-objektet.
                    ulv.spis_sau(sau)
                    print(f'\nUlven {ulv._navn} har spist sauen {sau._navn}!') #Printer ut beskjed om hvilken ulv har spist hvilken sau. 
                else:
                    continue
            for sau in self._sauer: #Fjerener alle 'spiste' sauer fra listen av Sau-objektene. 
                if sau.lever() == False:
                    self._sauer.remove(sau)
    
    def feiteste_ulven(self): #Instansmetoden som sjekker hvilken ulv fra listen av Ulv-objeketen har spist flest av sauene (er feitest).
        max = 0
        feiteste_ulven = ''
        for ulv in self._ulver:
            if ulv._vekt > max:
                max = ulv._vekt
                feiteste_ulven = ulv._navn
        print(f'Den feiteste ulven er {feiteste_ulven} og den veier {max} kg!')
            

