import numpy as np
import matplotlib.pyplot as plt
import pickle
import wave

def skriv_lyd_til_fil(data, sample_rate, filnavn):
    audio = np.array([data, data]).T
    audio = audio.astype("<h")

    with wave.open(filnavn, "w") as f:
        f.setnchannels(2)
        f.setsampwidth(2)
        f.setframerate(sample_rate)
        f.writeframes(audio.tobytes())

def les_lyd_fra_fil():
    lyd = pickle.load(open("Desktop/UiO/1_semester/IN1000/Uke_5/fordypning/kode.pickle", "rb"))
    return lyd

hemmelig_kode = les_lyd_fra_fil()

skriv_lyd_til_fil(hemmelig_kode, 44100, 'Desktop/UiO/1_semester/IN1000/Uke_5/fordypning/kode.wav')

#Jeg starter med å splitte lista med lydbølger. Det er fordi da jeg første gangen 'renset' lyden så det ut som ome det var 
#noen lyder i andre halvdelen av opptaket som var tregere, og derfor forkorter jeg den andre halvdelen først, for å få lydbølgene så nærme
#hverandre som mulig. Deretter lager jeg nøstede lister av hver av delene og appender dem til den hoved nøstede lista. For hvert element i den hoved 
#nøstede lista lager jeg elementer som er 3 plasser før og 3 plasser etter den og jeg regner ut medianen. Hvis elementet har større/mindre verdi en 
#enn noen av de andre elementene, så byttes det ut med medianen. Deretter lager jeg en enkel liste av den nøstede og til slutt forsterker lyden ved 
#gjøre det omvendte til fade_ut funksjonen i lyd.py. Til slutt lager jeg en fil til å lagre den rensede lyden. Koden er: 73649. Jeg skal ikke 
#være til stede på gruppetimen til Python fordypning fredag den 3.10. så gjerne si fra hvis du/dere får sjanse om hvordan jeg kunne har gjort det 
#bedre, om koden er riktig og hvem som vant konkurransen! :)
def fiks_lyd(lyd):
    første_delen = lyd[:len(lyd)//2]
    andre_delen = lyd[len(lyd)//2:]
    fix_andre_delen = andre_delen[::10]
    nested = []
    nested_1 = []
    nested_2 = []
    ikke_nested = []
    forsterket_lyd = []
    tall = 1
    for i in range(0, len(første_delen), 100000):
       liste = første_delen[i:i+100000]
       nested_1.append(liste)
    for i in range(0, len(fix_andre_delen),100000):
        liste = andre_delen[i:i+100000]
        nested_2.append(liste)
    for element in nested_1:
        nested.append(element)
    for element in nested_2:
        nested.append(element)
    for element in nested: 
        for i in range(3, len(element)-3):
            venstre = element[i-1] 
            venstre_2 = element[i-2] 
            venstre_3 = element[i-3]
            mid = element[i]
            høyre = element[i+1]
            høyre_2 = element[i+2]
            høyre_3 = element[i+3]
            lst = [venstre_3, venstre_2, venstre, mid, høyre, høyre_2, høyre_3]
            median = sorted(lst)[3]
            if mid > høyre or mid < høyre:
                element[i] = median
            elif mid < venstre or mid > venstre:
                element[i] = median
            elif mid > høyre_2 or mid < høyre_2:
                element[i] = median
            elif mid > venstre_2 or mid < venstre_2:
                element[i] = median
            elif mid > høyre_3 or mid < høyre_3:
                element[i] = median
            elif mid > venstre_3 or mid < venstre_3:
                element[i] = median
    for element in nested:
        for verdi in element:
            ikke_nested.append(verdi)
    for tone in ikke_nested:
        forsterket_lyd.append(tone*tall)
        tall = tall + (1/140000)
    return forsterket_lyd
   

skriv_lyd_til_fil(fiks_lyd(hemmelig_kode), 44100, 'Desktop/UiO/1_semester/IN1000/Uke_5/fordypning/kode_fikset.wav')

#Det var veldig nyttig å plotte ut lyden. 
#plt.plot(fiks_lyd(hemmelig_kode))
#plt.show()