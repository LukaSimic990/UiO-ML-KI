abstract class Resept { // Abstarkt superklassen Resept. 
    public final int ID;
    public final Legemiddel legemiddel; // Deklarerer instansvariablene, samt privat, statisk variabel 'teller' for ID tildeling til Resept-objektene.
    public final Lege utskrivendeLege;
    public final int pasientId;
    private int reit;
    private static int teller;

    public Resept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit) { // Konstruktøren til superklassen.
        this.legemiddel = legemiddel;
        this.utskrivendeLege = utskrivendeLege;
        this.pasientId = pasientId;
        this.reit = reit;
        ID = teller;
        teller++;
    }

    public int hentId() { // 'getter' metoden som returnerer objektets ID nummer.
        return ID;
    }

    public Legemiddel hentLegemiddel() { // 'getter' metoden som returnerer objektets Legemiddel-objekt 
        return legemiddel;              // (kaller på toString() metoden i Legemiddel-objektet knyttet til Resept-objektet).
    }

    public Lege hentLege() { // 'getter' metoden som returnerer objektets Lege-objekt 
        return utskrivendeLege; // (kaller på toString() metoden i Lege-objektet knyttet til Resept objektet).
    }

    public int hentPasientId() { // 'getter' metoden som returnerer Resept-objektets pasientId.
        return pasientId;
    }

    public int hentReit() { // 'getter' metoden som returnerer verdien til 'reit' instansvariabelen. 
        return reit;
    }

    public boolean bruk() { // Instansmetoden som simulerer bruk av resept. Reduserer antall 'reit', hvis 'reit' ikke er 0. 
        if (hentReit() > 0) {     // Hvis 'reit' er 0, er resepten ikke brukbar. Metoden returnerer true/false - resepten er brukbar/ikke brukbar.
            reit -= 1;
            return true;
        }
        return false;
    }

    public abstract String farge(); // To abstrakte metoder som implementers i subklassene til Resept.

    public abstract int prisÅBetale();

    // Overstyret toString metoden fra Objekt-klassen som skriver ut all tilgjengelig info om et Resept-objekt.
    @Override
    public String toString() {
        return "ReseptID: " + hentId() + ";\n" +
                "PasientID: " + hentPasientId() + ";\n" +
                "Legemiddel:\n{" + hentLegemiddel() + "}\n" +
                "Utskrivende lege:\n{" + hentLege() + "}\n" +
                "Reit: " + hentReit() +";";
    }
}

class HvitResept extends Resept { // HvitResept - subklassen til klassen Resept.
    public HvitResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit) {
        super(legemiddel, utskrivendeLege, pasientId, reit); // Konstruktøren til subklassen tar inn samme parametere som superklassen.
    }

    public String farge() { // Abstrakte metodene til superklassen implementeres.
        return "Hvit";
    }

    public int prisÅBetale() {
        return legemiddel.hentPris();
    }

    // Subklassens toString metode som overstyrer superklassens toString metode.
    @Override
    public String toString() {
        return super.toString() + "\n" + // Returnerer først superklassens info. 
                "Type resept: " + farge() + ";\n" + // Legger til info karakteristisk for subklassen.
                "Pris å betale: " + prisÅBetale() + ";"; 
    }
}

class MilitærResept extends HvitResept { // MilitærResept - subklassen til klassen HvitResept.
    public MilitærResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit) {
        super(legemiddel, utskrivendeLege, pasientId, 3); // Konstruktøren tar inn samme parametere som superklassen HvitResept;
    }                                                     // instansvariabelen 'reit' har verdien 3 for alle objekter. 

    @Override
    public int prisÅBetale() { // Overstyrer HvitResept sin metode prisÅBetale slik at prisen blir alltid 0.
        return legemiddel.hentPris() * 0; 
    }
} // Abstrakt metoden 'farge' returnerer samme streng 'Hvit' som superklassen, siden MilitærResept er en type HvitResept.
// Samme gjelder metoden toString.

class PResept extends HvitResept { // PResept - subklassen til klassen HvitResept. 
    public PResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit) {
        super(legemiddel, utskrivendeLege, pasientId, reit); // Konstruktøren til subklassen tar inn akkurat samme parametere som superklassen.
    }

    // Overstyret prisÅBetale metoden som alltid returnerer prisen til legemidlet redusert med 108kr. Metoden reduserer prisen bare om 
    // legemidlets pris er høyere eller lik 108 kr, siden en pasient ikke kan betale minder enn 0 kr for et legemiddel.
    @Override
    public int prisÅBetale() {
        if (legemiddel-hentPris() >= 108) {
            return legemiddel.hentPris() - 108;
        }
        return legemiddel.hetPris(); 
    }
} // Abstrakt metoden 'farge' returnerer samme streng 'Hvit' som superklassen, siden MilitærResept er en type HvitResept.
// Samme gjelder metoden toString.

class BlåResept extends Resept { // BlåResept - subklassen til Resept.
    public BlåResept(Legemiddel legemiddel, Lege lege, int pasientId, int reit) {
        super(legemiddel, lege, pasientId, reit); // Konstruktøren tar inn samme parametere som superklassen.
    }

    public String farge() { // Implementerer den apstrakte metoden 'farge' slik at den nå returnerer strengen "Blå".
        return "Blå";
    }

    public int prisÅBetale() { // Implementerer den abstrakte metoden 'prisÅBetale' til superklassen slik at det returneres kun 25% av den opprinelige prisen.  
        return (int) Math.round(legemiddel.hentPris() * 0.25);
    }

    // Overstyret toString metoden for å returnere en streng med all tilgjengelig info til en instans av BlåResept objekt.
    @Override
    public String toString() {
        return super.toString() + "\n" + // Returnerer først generell info om resepten, ved å kalle på toString metoden i superklassen.
            "Type resept: " + farge() + ";\n" + // Legger til info karakteristisk for BlåResept objekter.   
            "Pris å betale: " + prisÅBetale() + ";";
    }
}