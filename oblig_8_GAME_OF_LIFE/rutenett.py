from random import random # Her bestemte jeg meg om å bruke random.
from celle import Celle

class Rutenett:
    def __init__(self, ant_rader, ant_kolonner):
        self._ant_rader = ant_rader
        self._ant_kolonner = ant_kolonner
        self._rutenett = self._lag_tomt_rutenett()

    def _lag_tom_rad(self):
        return [None for _ in range(self._ant_kolonner)]

    def _lag_tomt_rutenett(self):
        return [self._lag_tom_rad() for _ in range(self._ant_rader)]
    
    def lag_celle(self):
        celle = Celle()
        if random() <= 0.33:
            celle.sett_levende()
        return celle
    
    def fyll_med_tilfeldige_celler(self):
        self._rutenett = [[self.lag_celle() for j in range(len(self._rutenett[i]))] for i in range(len(self._rutenett))]

    def hent_celle(self, r, k):
        return None if r < 0 or r >= len(self._rutenett) or k < 0 or k >= len(self._rutenett[r]) else self._rutenett[r][k]
    
    def tegn_rutenett(self):
        for i in range(len(self._rutenett)):
            for j in range(len(self._rutenett[i])):
                print(self._rutenett[i][j].hent_status_tegn(), end="")
            print()

    def _sett_naboer(self, r, k):
        celle = self.hent_celle(r, k)
        for i in range(-1,2):
            for j in range(-1,2):
                if (i == 0 and j == 0): continue # Treffer selve cellen vi ønsker å sette opp naboene til.
                nr = r + i
                nk = k + j
                if nr >= 0 and nr < len(self._rutenett) and nk >= 0 and nk < len(self._rutenett[nr]):
                    celle.legg_til_nabo(self.hent_celle(nr, nk))

    def koble_celler(self):
        [[self._sett_naboer(i, j) for j in range(self._ant_kolonner)] for i in range(self._ant_rader)]

    def hent_alle_celler(self):
        return [self._rutenett[i][j] for j in range(self._ant_kolonner) for i in range(self._ant_rader)]
    
    def antall_levende(self):
        return sum(1 for x in self.hent_alle_celler() if x.er_levende())