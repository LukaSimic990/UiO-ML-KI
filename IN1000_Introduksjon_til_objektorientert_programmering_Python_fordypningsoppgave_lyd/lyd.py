import numpy as np
import wave

def lag_tone(antall_sekunder, antall_svinginger_i_sekundet):
    lyd = []
    for i in range(int(44100 * antall_sekunder)):
        lyd.append(16000 * (1 + np.sin(antall_svinginger_i_sekundet * i/44100 * np.pi)))
    return lyd


def skriv_lyd_til_fil(data, sample_rate, filnavn):
    # tatt fra https://stackoverflow.com/a/64376061
    audio = np.array([data, data]).T
    audio = audio.astype("<h")

    with wave.open(filnavn, "w") as f:
        f.setnchannels(2)
        f.setsampwidth(2)
        f.setframerate(sample_rate)
        f.writeframes(audio.tobytes())
 
skriv_lyd_til_fil(lag_tone(3, 440), 44100, "Desktop/UiO/1_semester/IN1000/Uke_5/fordypning/filnavn.wav")

#Oppgave 1. Funksjonen som lager nøstede lista med toner og deres varighet i sangen.
path = open('Desktop/UiO/1_semester/IN1000/Uke_5/fordypning/sang.txt', 'r')
def les_sang_fra_fil(fil):
    noter = {
    "A": 440,
    "G": 392,
    "F": 349,
    "E": 330,
    "D": 294,
    "B": 247,
    "C": 261,
    "-": 0
}
    liste_sang = []
    for linje in fil:
        element = []
        linje_biter = linje.split()
        if linje_biter[0] in noter:
            element.append(noter[linje_biter[0]])
            element.append(float(linje_biter[1]))
            liste_sang.append(element)

    return liste_sang
        
#Oppgave 2. Lager en enkel liste fra listen laet av les_sang_fra_fil() som kan sendes til skriv_lyd_fra_fil().
def lag_sang_fra_noter(liste):
   toner_1 = []
   toner = []
   for element in liste:
       toner_1.append(lag_tone(element[1], element[0]))
   for tone in toner_1:
        for i in tone:
            toner.append(i)
   return toner

skriv_lyd_til_fil(lag_sang_fra_noter(les_sang_fra_fil(path)), 44100, 'Desktop/UiO/1_semester/IN1000/Uke_5/fordypning/sangen.wav')

#Oppgave 3. Funksjonen som fader ut sangen.
path = open('Desktop/UiO/1_semester/IN1000/Uke_5/fordypning/sang.txt', 'r')
def fade_ut(liste):
    tall = 1 
    toner = []
    for element in liste:
        toner.append(element*tall)
        tall = tall - (1/564477)
    return toner

skriv_lyd_til_fil(fade_ut(lag_sang_fra_noter(les_sang_fra_fil(path))), 44100, 'Desktop/UiO/1_semester/IN1000/Uke_5/fordypning/sangen_fade.wav')

#Oppgave 4. Funksjonen som forenkeler lyden. 
path = open('Desktop/UiO/1_semester/IN1000/Uke_5/fordypning/sang.txt', 'r')
def forenkle_lyd(liste):
    toner = []
    for element in liste:
        if element < 16000:
            element = 0
            toner.append(element)
        elif element > 16000:
            element = 32000
            toner.append(element)
    return toner

#print(forenkle_lyd(lag_sang_fra_noter(les_sang_fra_fil(path))))
skriv_lyd_til_fil(forenkle_lyd(lag_sang_fra_noter(les_sang_fra_fil(path))), 44100, 'Desktop/UiO/1_semester/IN1000/Uke_5/fordypning/sangen_forenkle.wav')



     
