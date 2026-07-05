Introduksjon

Denne innleveringen er en innføring i hvordan vi kan bruke objektorientert programmering til å representere og holde orden 
på objekter i en verden. I neste innlevering tar vi dette videre ved å bruke lignende objekter i et spill.

Vi skal her lage klasser for å representere sauer og ulver, og ha en klasse som representerer verdenen disse dyrene befinner seg i. 
For hver klasse er det tester som skal kjøres for å validere at klassen fungerer som den skal.

I neste oblig skal vi arbeide med en 2-dimensjonal verden, men for å gjøre ting litt enklere skal vi her jobbe i en endimensjonal 
verden som består av 10 mulige posisjoner (1-10). Hvert dyr kan altså befinne seg i posisjonen 1 til 10.

Denne uken er det ingen konkurranseoppgave.

Oppgave 1

Filnavn: sau.py

Lag en klasse Sau i filen sau.py.

Klassen skal ha en konstruktør med parameterene navn og posisjon og opprette instansvariablene _navn og _posisjon som holder på 
informasjonen. I tillegg skal klassen ha en instansvariabel _lever som i utgangspunktet er satt til True. Denne variabelen skal 
representere hvorvidt sauen lever eller ikke.

Klassen skal også ha noen metoder:

En metode blir_spist. Denne metoden skal senere bli kalt når en ulv spiser en sau, og metoden skal sørge for å sette _lever til 
False.
En metode lever som returnerer True hvis sauen lever og False hvis ikke.
En metode hent_navn som returnerer navnet til sauen og en metode hent_posisjonsom returnerer posisjonen til sauen.
Oppgave 2

Filnavn: test_sau.py

Importer klassen Sau: from sau import Sau
Lag et objekt av klassen Sau og sjekk at sauen lever. Hvis variabelen sau peker på et Sau-objekt, kan du sjekke at sauen lever 
slik: assert sau.lever(). Hvis du synes assert er vanskelig å bruke, kan du i stedet bruke if /else-setninger for å sjekke om ting 
fungerer som det skal.
Kall metoden blir_spist på sau-objektet og sjekk at sauen nå ikke lenger lever.

Oppgave 3

Filnavn: ulv.py

Lag en klasse Ulv i filen ulv.py.

Klassen skal ha en konstruktør med parametere navn og posisjon og tilsvarende instansvariable som holder på informasjonen. 
I tillegg skal ulven ha en instansvariabel _vekt som representerer vekten til ulven. Vekten er alltid 20 i utgangspunktet.

Klassen skal ha følgende metoder:

spis_sau som tar et parameter sau. Her skal sauen sin blir_spist-metode bli kalt, og vekten til ulven skal økes med 5.
hent_vekt som returnerer vekten til ulven
En metode hent_navn som returnerer navnet til ulven og en metode hent_posisjonsom returnerer posisjonen til ulven.

Oppgave 4:

Filnavn: test_sau_og_ulv.py

I filen test_sau_og_ulv.py skal vi teste at Sau- og Ulv-klassene fungerer som de skal ved å la en sau bli spist av en ulv. 
Gjør følgende:

Importer klassene Sau og Ulv
Lag et sau-objekt og et ulv-objekt
Sjekk at sauen lever
Sjekk at vekten til ulven er 20
La sauen bli spist av ulven ved å kalle riktig metode med riktig parameter
Sjekk at sauen er død
Sjekk at ulven sin vekt nå er 25

Oppgave 5:

Filnavn: verden.py

Vi ønsker nå en klasse Verden som kan holde orden på sauene og ulvene.

Konstruktøren til klassen Verden skal ikke ta noen parametere, men initialisere to variable, _sauer og _ulver, som holder på 
sauene og ulvene i verdenen. Velg en passelig datastruktur.

Klassen skal også ha følgende metoder:

opprett_dyr: Tar parameterene type, navn og posisjon. Type vil alltid være enten teksttsrengen sau eller ulv, og metoden skal 
sørge for å opprette et objekt av dyret, og legge det til verdenen ved å legge det til i self._ulver eller self._sauer.
beskriv: Printer en beskrivelse av verdenen ved å printe ut alle sauene og ulvene med navn og hvor de befinner seg. 
Print gjerne informasjonen om hvert dyr på en ny linje.
antall_sauer: Returnerer antall levende sauer i verdenen.
antall_ulver: Returnerer antall ulver i verdenen (merk at ulver ikke kan dø).

Oppgave 6:

Filnavn: test_verden.py

Test klassen Verden:

Husk å importere klassen først
Lag deretter et objekt av klassen
Opprett to sauer og én ulv og sjekk at antall_sauer og antall_ulver returnerer riktig tall.
Kall metoden beskriv og sjekk selv visuelt ved å se på det som printes (ikke ved å skrive kode) at informasjonen som printes er 
riktig.

Oppgave 7:

Utvide klassen Ulv med to nye metoder:

En metode beveg_hoyre som beveger ulven mot høyre, dvs. øker posisjonen med 1. Hvis posisjonen ender opp med å være større enn 10, 
skal ulven komme ut igjen på starten av verdenen og få posisjonen 1.
En metode beveg_venstre som tilsvarende beveger ulven mot venstre. Hvis posisjonen blir mindre enn 1, skal ulven få posisjon 10.
Utvid klassen Verden med en metode oppdater. Denne metoden skal:

Bevege hver ulv enten mot høyre eller venstre (la det være tilfeldig, f. eks 50% sannsynlighet for høyre og 50% sannsynlighet for 
venstre).
Sjekke om noen av ulvene nå befinner seg på samme posisjon som en levende sau. Hvis det skjer, skal ulven spise sauen og det skal 
printes navnet på ulven som spiser og sauen som blir spist (f. eks Ulven Kåre spiser sauen Bjarne på posisjon 5)
Lag også en metode finn_feiteste_ulv som returnerer ulven (et objekt) som veier mest.

Oppgave 8

Utvid test_verden.py ved å legge til en håndfull sauer og ulver (du velger selv antall) og kall oppdater en del ganger 
(f. eks 10 ganger). Print til slutt navnet på den ulven som er feitest.
