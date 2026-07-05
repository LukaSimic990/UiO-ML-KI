public class Verden { //Deklarasjon av klassen Verden samt instansmetodene.
    public int antRader;
    public int antKolonner;
    public Rutenett rutenett;
    public int genNr;

    public Verden(int r, int k) { //Konstruktøren tar inn antall rader/kolonner til rutenettet man ønsker å opprette i instansen av klassen Verden. 
        antRader = r;
        antKolonner = k;
        rutenett = new Rutenett(r, k); //Parametrene brukes for å opprette en instans av Rutenett-klassen i Verden-klassen.
        genNr = 0;
        rutenett.fyllMedTilfeldigeCeller(); //Konstruktøren fyller Verden med tilfeldige Celle-objekter (levende/døde).
        rutenett.kobleAlleCeller(); //Konstruktøren setter opp naboene til hver celle i rutenettet.
    }

    public void tegn() { //Metoden skriver ut rutenettet sammen med hver celle sit status tegn.
        rutenett.tegnRutenett();
        System.out.println("Generasjon nr: " + genNr); //Metoden også skriver ut hvilken generasjon det er på rad og hvor mange levende celler det er i generasjonen.
        System.out.println("Antall levende celler i generasjonen: " + rutenett.antallLevende());
    }

    public void oppdatering() { //Metoden itererer gjennom alle Celle-objekter i Verdenen (rutenettet) og teller antall levende naboer til hver Celle-objekt.
        for (int i=0;i<antRader;i++) {
            for (int j=0;j<antKolonner;j++) {
                rutenett.hentCelle(i, j).tellLevendeNaboer();
                rutenett.hentCelle(i, j).oppdaterStatus(); //Etter å ha telt alle levende naboer, så oppdatere det status til hver Celle-objekt iflg spillets regler.
            }
        }
        genNr++; //Metoden inkrementerer generasjonsnummer i tillegg.
    }
}