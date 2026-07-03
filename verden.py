from celle import Celle
from rutenett import Rutenett

class Verden:
    def __init__(self, rad, kol):
        self._rutenett = Rutenett(rad, kol)
        self._generasjonsnummer = 0
        self._rutenett.fyll_med_tilfeldige_celler()
        self._rutenett.koble_celler()

    def tegn(self):
        self._rutenett.tegn_rutenett()
        print('Generasjon:', self._generasjonsnummer)
        print('Antall levende celler:', self._rutenett.antall_levende())

    def oppdatering(self):
        for element in self._rutenett.hent_alle_celler():
            element.tell_levende_naboer()
        for element in self._rutenett.hent_alle_celler():
            element.oppdater_status()
        self._generasjonsnummer += 1


