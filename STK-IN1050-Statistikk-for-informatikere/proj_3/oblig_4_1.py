import pandas as pd
import numpy as np
from statsmodels.stats.weightstats import ttest_ind

# Metoden 1 - To-utvalgs t-test: En to-utvalgs t-test er en statistisk metode brukt til å undersøke om to uavhengige grupper har forskjellig
# gjennomsnitt. Testen vurderer om forskjellen er reell eller kan forklares av tilfeldig variasjon.

# to-utvalgs t-test kan være et godt valg for denne situasjonen på grunn av:
# - vi sammenligner gjennomsnittene i to uavhengige grupper med observasjoner
# - utvalgsstørrelsen er 310 observasjoner per gruppe, så vi kan si at det ikke er så viktig her å bevise normaliteten til dataene (t-fordeling tilnærmet lik normalfordeling).

# H0: Gjennomsnittlig døgntemperatur har ikke forandret seg fra perioden 1986-1995 til perioden 2016-2025.
# Ha: Gjennomsnittlig døgntemperatur for periodene er forskjellig.
# alpha: 0.05 (her handler det om vanlig dataanalyse, så vi trenger ikke alpha mindre enn denne verdien).

p1 = pd.read_csv("blindern_daily1986-1995.txt", sep=";")
p2 = pd.read_csv("blindern_daily2016-2025.txt", sep=";")

# Sjekker om variansene i disse to periodene er like:

print(f"Variansen til perioden 1986-1995: {p1["temp"].var(): .3f}.")
print(f"Variansen til perioden 2016-2025: {p2["temp"].var(): .3f}.")

# Konlusjon: Vi bruker Welch’s t-test som ikke forutsetter like varians, uavhengig av om variansene er like eller ikke.

p1_temp = np.array(p1["temp"], dtype=float)
p2_temp = np.array(p2["temp"], dtype=float)

t_stat, p_verdi, df = ttest_ind(p1_temp, p2_temp, usevar='unequal', alternative='two-sided') # Tosidig Welch t-test for å teste om det er forskjellen i gjennomsnittene.

print(f"t-statistikk: {t_stat: .3f}; p-verdi: {p_verdi: .7f}; frihetsgrader: {df: .3f}.")

# Konklusjon: p-verdien av to-utvalgs t-testen er betydelig mindre enn alpha verdien vår og ut ifra det kan vi konkludere at det ikke er nok grunn til å beholde nullhypotesen 
# som betyr at det er statistisk signifikant forskjell i gjennomsnittlig døgntemperatur i juli måneden i disse to periodene.

# Metoden bygger på antakelsene om normaliteten i våre observasjoner (her ikke så viktig pga at utvalgsstørrelsene våre er nokså høye), om at disse to periodene har lik
# varians, ellers så bruker vi Welchs t-test (i vårt tilfelle) og om at observasjonene våre er uavhengige. Siden de første to antakelsene er det tatt høyde for i modellen vår
# så blir det det eneste vi må bry oss om her - uavhengighet i observasjonene, som vi ikke kan så lett si er tilfellet her. Det er mye autokorrelasjon i observasjonene våre - 
# varme dager følger varme dager og omvendt. Derfor mangler metoden den siste antakelsen for at dens resultater skal være signifikante - uavhengighet. Derfor kav vi  
# konkludere at den lave p-verdien mest sannsynlig oppstår pga denne høye autokorrelasjonen her og at vår forkastning av nullhypotesen her ikke er godt nok begrunnet i dataene.

# Metoden 2 - Permutasjonstest: Permutasjonstest svarer på spørsmålet: "Hvor sannsynlig er det at forskjellen i gjennomsnittene av døgntemperatur i juli måneden på 
# Blindern skyldes bare tilfeldigheter?". Vi permuterer de gjennomsnittlige døgntemperatur verdiene mellom dagene for hver periode (lager nye nullfordelinger) så 
# trekker ut nye gjennomsnitter og til slutt sjekker vi hvor mange ganger vi har fått den samme forskjellen (eller forskjellen som er større enn) den som vi egentlug har 
# observert.

# Metoden bryr seg ikke om normaliteten i dataene og er veldig generell. Den er kan være nyttig i tilfeller når vi ikke er sikre på at dataene er normalfordelt for å 
# eventuelt sjekke om resultatene fra t-test gir mening.

# Hypotesene er like ved bruk av denne metoden som ved bruk av to-utvalgs t-test:
# H0: Gjennomsnittlig døgntemperatur har ikke forandret seg fra perioden 1986-1995 til perioden 2016-2025.
# Ha: Gjennomsnittlig døgntemperatur for periodene er forskjellig.
# alpha: 0.05 (her handler det om vanlig dataanalyse, så vi trenger ikke alpha mindre enn denne verdien).

def perm_test(a, b, n_perm=10000):
    observert = np.mean(b) - np.mean(a)
    komb = np.concatenate([a, b])
    n_a = len(a)

    differanser = []
    for _ in range(n_perm):
        perm = np.random.permutation(komb)
        diff = np.mean(perm[n_a:]) - np.mean(perm[:n_a])
        differanser.append(diff)

    differanser = np.array(differanser)
    p_verdi = np.mean(np.abs(differanser) >= np.abs(observert))

    return observert, p_verdi

observert, p_verdi_perm = perm_test(p1_temp, p2_temp)
print(f"Den observerte differansen i gjennomsnittene: {observert: .3f}; p-verdi: {p_verdi_perm: .7f}.") 

# Konklusjon: p-verdien for permutasjonstesten er veldig lave, som samsvarer med resultatet fått av metoden 1. Det vil si at det ikke er nok grunn for å beholde nullhypotesen 
# og vi skal derfor konkludere at det er en statistisk signifikant forskjell i gjennomsnittlig døgntemperatur i juli måneden mellom disse to periodene.

# Permutasjonstest er mer robust for underliggende fordelinger i dataene våre og deres varianser. Den eneste signifikante antakelsen som må være oppfylt her er at observasjonene 
# må være uavhengige og derfor lett permuterbare, som vi ikke kan si er tilfellet her. Som beskrevet i tilfellet av to-utvalgs t-test ovenfor, så er det mye avhengighet i dataene 
# (autokorrelasjon) og pga det er det ikke mulig å fullstendig konkludere at vår nullhypotese forkastning er godt nok begrunnet i dataene her. Det må lagges en bedre modell for 
# å sjekke denne temperatur forskjellen her.




