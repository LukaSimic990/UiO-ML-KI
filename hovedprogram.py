from celle import Celle
from rutenett import Rutenett
from verden import Verden

rad = input('Oppgi hvor mange rader spillebrettet skal ha - ')
kol = input('Oppgi hvor mange kolonner spillebrettet skal ha - ')

verden = Verden(rad, kol)
verden.tegn()

videre = input('Trykk "Enter" for å fortsette eller "q" for å avslutte ')
while videre == '':
    verden.oppdatering()
    verden.tegn()
    videre = input('Trykk "Enter" for å fortsette eller "q" for å avslutte ')
    if videre == 'q':
        break
    else:
        continue