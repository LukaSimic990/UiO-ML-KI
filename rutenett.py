from celle import Celle
from random import randint

class Rutenett:
    def __init__(self, ant_rader, ant_kolonner):
        self._ant_rader = int(ant_rader)
        self._ant_kolonner = int(ant_kolonner)
        self._rutenett = self._lag_tomt_rutenett()

    def _lag_tom_rad(self):
        liste = list()
        for i in range(0, self._ant_kolonner):
            liste.append(None)
        return liste
    
    def _lag_tomt_rutenett(self):
        liste = list()
        for i in range(0, self._ant_rader):
            liste.append(self._lag_tom_rad())
        return liste

    def lag_celle(self):
        celle = Celle()
        bestemmer = randint(0,2)
        if bestemmer == 1:
            celle.sett_levende()
        else:
            celle.sett_død()
        return celle

    def fyll_med_tilfeldige_celler(self):
        for rad in self._rutenett:
            for kolonne in rad:
                rad.remove(None)
                rad.append(self.lag_celle())
            
    def hent_celle(self, rad, kol):
        if rad < 0 or rad > self._ant_rader:
            return None
        elif kol < 0 or kol > self._ant_kolonner:
            return None
        else:
            return self._rutenett[rad][kol]
        
    def tegn_rutenett(self):
        for i in range(0,10):
            print('')
        for rad in self._rutenett:
            for kol in rad:
                print(kol.hent_status_tegn(), end='')
            print()

    def _sett_naboer(self, rad, kolonne): #Jeg klarte å løse dette på denne måten. Det er mye kode. Jeg vet ikke om dette kan gjøres på en annen måte 
        celle = self.hent_celle(rad, kolonne)#men hvis det er mulig så takker jeg hjertelig hvis jeg kan få koden til det :)
        if kolonne != 0 and kolonne != self._ant_kolonner-1 and rad != 0 and rad != self._ant_rader-1:
            celle.legg_til_nabo(self._rutenett[rad][kolonne-1])
            celle.legg_til_nabo(self._rutenett[rad][kolonne+1])
            celle.legg_til_nabo(self._rutenett[rad-1][kolonne])
            celle.legg_til_nabo(self._rutenett[rad-1][kolonne-1])
            celle.legg_til_nabo(self._rutenett[rad-1][kolonne+1])
            celle.legg_til_nabo(self._rutenett[rad+1][kolonne])
            celle.legg_til_nabo(self._rutenett[rad+1][kolonne-1])
            celle.legg_til_nabo(self._rutenett[rad+1][kolonne+1])
        elif kolonne == 0 and rad != 0 and rad != self._ant_rader-1:
            celle.legg_til_nabo(self._rutenett[rad][kolonne+1])
            celle.legg_til_nabo(self._rutenett[rad-1][kolonne])
            celle.legg_til_nabo(self._rutenett[rad-1][kolonne+1])
            celle.legg_til_nabo(self._rutenett[rad+1][kolonne])
            celle.legg_til_nabo(self._rutenett[rad+1][kolonne+1])
        elif kolonne == self._ant_kolonner-1 and rad != 0 and rad != self._ant_rader-1:
            celle.legg_til_nabo(self._rutenett[rad][kolonne-1])
            celle.legg_til_nabo(self._rutenett[rad-1][kolonne])
            celle.legg_til_nabo(self._rutenett[rad-1][kolonne-1])
            celle.legg_til_nabo(self._rutenett[rad+1][kolonne])
            celle.legg_til_nabo(self._rutenett[rad+1][kolonne-1])
        elif kolonne == 0 and rad == 0:
            celle.legg_til_nabo(self._rutenett[rad][kolonne+1])
            celle.legg_til_nabo(self._rutenett[rad+1][kolonne])
            celle.legg_til_nabo(self._rutenett[rad+1][kolonne+1])
        elif kolonne == self._ant_kolonner-1 and rad == 0:
            celle.legg_til_nabo(self._rutenett[rad][kolonne-1])
            celle.legg_til_nabo(self._rutenett[rad+1][kolonne])
            celle.legg_til_nabo(self._rutenett[rad+1][kolonne-1])
        elif kolonne == 0 and rad == self._ant_rader-1:
            celle.legg_til_nabo(self._rutenett[rad][kolonne+1])
            celle.legg_til_nabo(self._rutenett[rad-1][kolonne])
            celle.legg_til_nabo(self._rutenett[rad-1][kolonne+1])
        elif kolonne == self._ant_kolonner-1 and rad == self._ant_rader-1:
            celle.legg_til_nabo(self._rutenett[rad][kolonne-1])
            celle.legg_til_nabo(self._rutenett[rad-1][kolonne])
            celle.legg_til_nabo(self._rutenett[rad-1][kolonne-1])
        elif kolonne != 0 and kolonne != self._ant_kolonner-1 and rad == 0:
            celle.legg_til_nabo(self._rutenett[rad][kolonne-1])
            celle.legg_til_nabo(self._rutenett[rad][kolonne+1])
            celle.legg_til_nabo(self._rutenett[rad+1][kolonne])
            celle.legg_til_nabo(self._rutenett[rad+1][kolonne-1])
            celle.legg_til_nabo(self._rutenett[rad+1][kolonne+1])
        elif kolonne != 0 and kolonne != self._ant_kolonner-1 and rad == self._ant_rader-1:
            celle.legg_til_nabo(self._rutenett[rad][kolonne-1])
            celle.legg_til_nabo(self._rutenett[rad][kolonne+1])
            celle.legg_til_nabo(self._rutenett[rad-1][kolonne])
            celle.legg_til_nabo(self._rutenett[rad-1][kolonne-1])
            celle.legg_til_nabo(self._rutenett[rad-1][kolonne+1])

    def koble_celler(self):
        for rad in self._rutenett:
            for kolonne in rad:
                self._sett_naboer(self._rutenett.index(rad), rad.index(kolonne))

    def hent_alle_celler(self):
        liste = list()
        for rad in self._rutenett:
            for kolonne in rad:
                liste.append(kolonne)
        return liste

    def antall_levende(self):
        teller = 0
        for celle in self.hent_alle_celler():
            if celle.er_levende() == True:
                teller += 1
            else:
                teller = teller
        return teller 
                
       
        




        

            



        
            

    


