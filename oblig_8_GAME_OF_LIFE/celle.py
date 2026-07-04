class Celle:
    def __init__(self):
        self._status = 'død'
        self._naboer = list()
        self._ant_levende_naboer = 0

    def sett_død(self):
        self._status = 'død'

    def sett_levende(self):
        self._status = 'levende'

    def er_levende(self):
        if self._status == 'levende':
            return True
        return False
    
    def hent_status_tegn(self):
        if self._status == 'levende':
            return 'O'
        return '.'
    
    def legg_til_nabo(self, nabo):
        self._naboer.append(nabo)

    def tell_levende_naboer(self):
        self._ant_levende_naboer = 0
        for nabo in self._naboer:
            if nabo.er_levende():
                self._ant_levende_naboer += 1

    def oppdater_status(self):
        if self.er_levende() and self._ant_levende_naboer < 2 or self.er_levende() and self._ant_levende_naboer > 3:
            self._status = 'død'
        elif not self.er_levende() and self._ant_levende_naboer == 3:
            self._status = 'levende'
