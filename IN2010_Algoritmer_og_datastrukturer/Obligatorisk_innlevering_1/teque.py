from sys import stdin


#a) Her har jeg valgt implementasjon med en dobbeltlenket liste.

# Klassen nod må ha pekere til den neste og den forrige noden, samt en instansvariabel som 
# peker til verdien lagret i noden.
class Node:
    def __init__(self, value):
        self.value = value
        self.next = None
        self.prev = None

class Teque:
    # Klassen 'Teque' er implementert som en dobbeltlenket liste, derfor må den ha pekere til den 
    # første og den siste noden i listen. Listen har også pekeren til noden som ligger i midten ('self.mid') og det hjelper 
    # med at prosedyren 'pushmiddle' skal også være i O(1), slik som de andre to push-prosedyrene.
    # Instansvariabel 'n' lagrer størrelsen til listen og hjelper med å finne noden i midten av lista, samt med 
    # å holder styr med når 'mid'-pekeren må flyttes bak-/fremover ved bruk av de push-prosedyrene.
    def __init__(self):
        self.n = 0
        self.first = None
        self.mid= None
        self.last = None

    #b) Kjøretidskompleksitet: O(1), konstant kjøretid. Prosedyren har konstant kjøretidskompleksitet.
    # Prosedyren bruker bare primitive oprasjoner - sammenligninger og flyttinger av 
    # pekere til nodene, både i instansvariablene og i den noden som legges til, samt
    # matematisk operasjon self.n = self.n + 1 og tilordninger. 
    # Kjøretiden øker ikke når inputstørrelsen øker.
    # VERSTE TILFELLET: O(n), lineær kompleksitet. Hvis man f.eks. velger implementasjon med
    # enkeltlenket liste og da må iterere fra 'first'-pekeren frem til slutten av listen. 
    # Hvis man velger implementasjon med dynamiske arrayer, så blir 'pushback' også 
    # O(n) i det verste tilfellet, hvis arrayet må utvides når det ikke er nok plass i arrayet. Da må alle elementer
    # kopieres og flyttes i det nye utvidede arrayet og den prosedyren øker sammen med inputstørrelsen.
    # Det skjer ikke hver gang man kaller på prosedyren, slik at i dette tilfellet sier vi at prosedyren kjører i O(1) - amortisert. 
    def pushback(self, x):
        v = Node(x)
        if self.n == 0: # Hvis tequen er tom. 
            self.first = v
            self.mid = v
            self.last = v
            self.n += 1
            return
        if self.n == 1: # Når det bare er ett element i tequen fra før, må det opprettes riktige forbindelser mellom nodene.
            self.last = v
            self.mid = v
            self.last.prev = self.first
            self.mid.prev = self.first
            self.first.next = v
            self.n += 1
            return
        v.prev = self.last # Setter noden på den siste posisjonen i listen. 
        self.last.next = v
        self.last = v
        if self.n % 2 != 0: # Ved bruk av 'push_back' flyttes 'mid' pekeren framover bare når antall elementer før innsetting er et oddetall.
            self.mid = self.mid.next
        self.n += 1

    #b) Kjøretidskompleksitet: O(1), konstant kjøretid. 
    # Samme grunnen til det som med 'pushback'.
    # VERSTE TILFELLET: Prosedyren kjører i O(n). Av samme grunner som med 'pushback', hvis vi har implementasjon med dynamisk array og
    # listen ikke har nok plass, så må arrayet utvides, alle elementer må kopieres og flyttes en posisjon framover. Her skjer det heller ikke 
    # hver gang vi kjører prosedyren at vi må øke størrelsen til arrayet, slik at det her også blir en - O(1) amortisert kompleksitet.
    # Hvis man bruker enkeltlenket liste blir kompleksiteten O(1) da det bare er nok med å flytte 'first'-pekeren og lage riktige forbindelser 
    # mellom den nye noden og den forrige første noden i listen.
    def pushfront(self, x):
        v = Node(x)
        if self.n == 0: # Håndterer innsetting i en tom liste.
            self.first = v
            self.mid = v
            self.last = v
            self.n += 1
            return
        if self.n == 1: # Når det bare er ett element i listen - riktige forbindelser mellom nodene.
            self.first = v
            self.first.next = self.mid
            self.mid.prev = v
            self.n += 1
            return
        v.next = self.first # Setter inn den nye noden på den første posisjonen i listen ellers.
        self.first.prev = v
        self.first = v
        if self.n % 2 == 0: # Ved bruk av 'push_front' flyttes 'mid' pekeren bakover bare når antall elementer før innsetting er et partall.
            self.mid = self.mid.prev
        self.n += 1

    #b) Kjøretidskompleksitet: O(1), konstant kjøretid.
    # Samme grunnen til det som med 'pushback' og 'pushfront'. 
    # VERSTE TILFELLET: Av de samme grunnene som med de andre to push-operasjonene - O(n), lineær kjøretid, men O(1) amortisert hvis man bruker 
    # dynamisk array implementasjon. O(n) hvis man bruker vanlig enkeltlenket/dobbeltlenket liste, uten en 'mid'-peker.
    def pushmiddle(self, x):
        v = Node(x)
        if self.n == 0: # Hvis listen er tom.
            self.first = v
            self.mid = v
            self.last = v
            self.n += 1
            return
        if self.n == 1: # Når det bare er ett element i tequen.
            v.prev = self.first
            self.first.next = v
            self.last = v
            self.mid = v
            self.n += 1
            return
        if self.n % 2 == 0: # Hvis partall elementer i tequen før innsetting - det nye elementet settes inn før det nåværende 'mid'-elementet.
            v.next = self.mid
            v.prev = self.mid.prev
            self.mid.prev.next = v
            self.mid.prev = v
            self.mid = v # Det nye elementet blir det nye elementet som ligger i midten.
        elif self.n % 2 != 0: # Hvis oddetall elementer i tequen før innsetting - det nye elementet settes inn etter det nåværende 'mid'-elementet.
            v.prev = self.mid
            v.next = self.mid.next
            self.mid.next.prev = v
            self.mid.next = v
            self.mid = v
        self.n += 1

    # Kjøretidskompleksitet: O(n), lineær kjøretid. 
    # Her har jeg klart å optimisere algoritmen, siden implementasjonen bruker pekere til første, midtre og siste noden i tequen: 
    # - hvis 'i' er større enn indeksen til elementet i midten og er nærmere midten enn slutten av listen - iterer fra midten
    # - hvis 'i' er nærmere slutten av listen - iterer fra slutten
    # - hvis 'i' er nærmere midten enn starten av listen - iterer fra midten
    # - eller iterer fra starten til 'i'.
    # Dette deler opp input i fire, men kjøretiden fortsatt øker sammen med økningen til størrelsen til input, derfor O(n).
    # VERSTE TILFELLET: Her må man iterere frem til den valgte indeksen. Prosedyren blir O(1) hvis indeksen treffer på 
    # 'first', 'mid' eller 'last', men det skjer ikke så ofte, så det fortsatt blir O(n). Hvis man bruker dynamisk array kan
    # prosedyren bli i O(1) - return list[i] - men i så fall, slik det er nevnt ovenfor, blir alle andre prosedyreene i O(n). 
    # Ved bruk av lenkede lister blir prosedyren i O(n), med mulige optimaliseringer, slik jeg har forsøkt å implementere her.
    def get(self, i):
        if i < 0 or i >= self.n:
            raise IndexError("Not a valid index!")
        if i == 0: return self.first.value
        if i == (self.n-1): return self.last.value
        mid = self.n//2
        if i == mid: return self.mid.value
        if i > mid:
            if (i-mid) <= ((self.n-1)-i):
                it = self.mid
                for j in range((i-mid)):
                    it = it.next
                return it.value
            else:
                it = self.last
                for j in range(((self.n-1)-i)):
                    it = it.prev
                return it.value
        else:
            if (mid-i) <= i:
                it = self.mid
                for j in range((mid-i)):
                    it = it.prev
                return it.value
            else:
                it = self.first
                for j in range(i):
                    it = it.next
                return it.value


if __name__ == '__main__':
    teque = Teque()
    N = int(stdin.readline())
    for _ in range(N):
        line = stdin.readline().split()
        cmd = line[0]
        num = int(line[1])
        if cmd == "push_back":
            teque.pushback(num)
        elif cmd == "push_front":
            teque.pushfront(num)
        elif cmd == "push_middle":
            teque.pushmiddle(num)
        else:
            print(teque.get(num))
