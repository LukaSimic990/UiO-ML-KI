from verden import Verden

start = input("**** Hei og velkommen til THE GAME OF LIFE ****\n(trykk 'Enter' for å begynne; trykk 'q' for å avslutte)")

if start == '':
    ant_rad = int(input("Oppgi antall rader til rutenettet: "))
    ant_kol = int(input("Oppgi antall kolonner til rutenettet: "))

    verden = Verden(ant_rad, ant_kol)

    verden.tegn()

    melding = "\nHvis du ønsker å se neste generasjon trykk 'Enter' (trykk 'q' for å avslutte)"
    fortsett = input(melding)

    if fortsett.lower() == 'q':
        print("**** Takk for at du spilte THE GAME OF LIFE! Vi sees!")
        
    while fortsett == '':
        verden.oppdatering()
        verden.tegn()
        fortsett = input(melding)

        if fortsett.lower() == 'q':
            print("**** Takk for at du spilte THE GAME OF LIFE! Vi sees! ****")
            break
elif start.lower() == 'q':
    print("**** THE GAME OF LIFE er avsluttet! Vi sees! ****")
