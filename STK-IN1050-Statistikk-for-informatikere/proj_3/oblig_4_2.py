import pandas as pd
import numpy as np
import statsmodels.api as sm
from statsmodels.stats.weightstats import ttest_ind
import seaborn as sns
import matplotlib.pyplot as plt

# Metoden 1 - To-utvalgs t-test

# H0: Gjennomsnittlig månedstemperatur i juli har ikke endret seg fra perioden 1986–1995 til perioden 2016–2025.
# Ha: Gjennomsnittlig månedstemperatur i juli for periodene 1986-1995 og 2016-2025 er forskjellig.
# alpha: 0.05 (standard verdi pga at dette er en vanlig analyse (ikke kritisk medisinsk analyse eller lignende)).

p1 = pd.read_csv("blindern_daily1986-1995.txt", sep=";")

means_p1 = []
for _ in range(1986, 1996):
    mean = p1[p1["year"] == _]["temp"].mean() # Trekker ut gjennomsnitt til hvert år i perioden.
    means_p1.append(mean)

means_p1 = np.array(means_p1)

print(f"Gjennomsnittet for perioden 1986-1995: {means_p1.mean(): .3f}; variansen for perioden 1986-1995: {means_p1.var(): .3f}.")

p2 = pd.read_csv("blindern_daily2016-2025.txt", sep=";")

means_p2 = []
for _ in range(2016, 2026):
    mean = p2[p2["year"] == _]["temp"].mean()
    means_p2.append(mean)

means_p2 = np.array(means_p2)

print(f"Gjennomsnittet for perioden 2016-2025: {means_p2.mean(): .3f}; variansen for perioden 2016-2025: {means_p2.var(): .3f}.")

# Konklusjon: variansen i disse to periodene er ikke like - må bruke Welch t-test.

t_stat, p_verdi, df = ttest_ind(means_p1, means_p2, usevar='unequal', alternative='two-sided') # usevar='unequal' - Welch t-test; tosidig t-test - vi ønsker å se på forskjellen
                                                                                               # mellom to gjennomsnitteneg.

print(f"t-statistikk: {t_stat: .3f}; p-verdi: {p_verdi: .3f}; frihetsgrader: {df: .3f}.")

# Konklusjon: p-verdien er 0.178 > 0.05 - det er ikke nok grunn til å forkaste nullhypotesen. Det er ingen forskjell i månedlige gjennomsnittene for juli måneden 
# i disse to periodene. P-verdien på 0.178 vil si at det er stor nok sjanse for å observere forskjellen mellom gjennonsnittene vi har observert her under nullhypotesen, derfor
# er det ikke nok grunn her for å forkaste den.

# Antakelsene om denne metoden er allerede diskutert i oppgaven 1. 
# Her har jeg sett direkte på variansene mellom disse to datasettene og det er lett å se forskjellen: Var(means_p1) = 1.989 og Var(means_p2) = 4.302. 
# I dette oppsettet er det mer begrunnet å anta uavhengighet i forhold til oppsettet i oppgaven 1 (vi ser på juli måneden isolert for hvert år i hver av periodene - 
# mindre sannsynlighet for autokorrelasjon).
# Det som gjenstår er å se på om dataene våre er normalfordelt - veldig viktig her pga veldig liten utvalgsstørrelse (10 punkter i hvert datasett).

# Lager histogrammene:

sns.set_style("whitegrid")
sns.histplot(means_p1, bins=4, kde=True)
plt.title("Fordelingen av gjennomsnittene for perioden 1986-1995")
plt.show()

sns.set_style("whitegrid")
sns.histplot(means_p2, bins=4, kde=True)
plt.title("Fordelingen av gjennomsnittene for 2016-2025")
plt.show()

# Setter opp QQ-plottene:

sns.set_style("whitegrid")
sm.qqplot(means_p1, line='s')
plt.title("QQ-plott for gjennomsnittene i perioden 1986-1995")
plt.show()

sns.set_style("whitegrid")
sm.qqplot(means_p2, line='s')
plt.title("QQ-plott for gjennomsnittene i perioden 2016-2025")
plt.show()

# Kommentar: Det er lett å se ut fra disse grafene at dataene våre ikke er normalfordelt (lett å se at gjennomsnittene her er høyreskjev; vi kunne også sett på den 
# deskriptive statistikken og sjekket om det gjelder at mean > median > modus - høyreskjevhet). Derfor er den viktigste antakelsen for at denne modellen skulle gi 
# signifikante resultater på disse datasettene ikke oppfylt.

# Metoden 2 - Permutasjonstest: 

# Hypotesene for permutasjonstesten skal være like som til to-utvalgs t-testen her:

# H0: Gjennomsnittene til døgntemperatur i juli måneden er like for periodene: 1986-1995 og 2016-2025.
# Ha: Gjennomsnittene er forskjellige.
# Setter opp signifikasnnivået til 0.05 her lik som for to-utvalgs t-testen.

def perm_test(a, b, n_perms=10000):
    observert = b.mean() - a.mean() # Den observerte forskjellen mellom gjennomsnittene.
    komb = np.concatenate([a, b]) # Slår sammen datasettene.
    n_a = len(a)

    differanser = []
    
    for _ in range(n_perms):
        perm = np.random.permutation(komb) # Permuterer, lager nye nullfordelinger.
        diff = np.mean(perm[n_a:]) - np.mean(perm[:n_a]) # Beregner differanser i gjennomsnittene på permuterte data.
        differanser.append(diff) 

    differanser = np.array(differanser)
    p_verdi = np.mean(np.abs(differanser) >= abs(observert)) # Sannsynligheten for å observere minst like stor forskjell om ikke enda større enn den vi har observert.
    
    return observert, p_verdi

observert, p_verdi = perm_test(means_p1, means_p2)

print(f"Den observerte forskjellen: {observert: .3f}; p_verdi: {p_verdi: .3f}.")

# Konlusjon: p-verdien fra permutasjonstesten ligger veldig nærme p-verdien fra t-testen. Den er 0.18 som er større enn 0.05 og derfor er det ikke nok grunn for å forkaste 
# nullhypotesen, som vil si at vi kommer til akkurat den samme konklusjonen som med to-utvalgs t-testen ovenfor - gjennomsnittene i døgntemperatur for juli måneden i de 
# gitte periodene er ikke forskjellige.

# Permutasjonstest har ingen krav til normaliteten og gir en "empirisk" p-verdi som gjør den mer robust for og tryggere når data ikke er normalfordelt eller når vi ikke er
# sikre på normaliteten i våre data. Her kan vi anta uavhengighet, som forklart ovenfor og derfor kan vi si at vi kan stole på resultatet fra permutasjonstesten i denne 
# situasjonen noe mer enn på resultatet til to-utvalgs t-test.



