public class Rutenett { //Oppretter klassen med deklarasjonen av instansvariablene.
    Celle[][] rutene;
    public int antRader;
    public int antKolonner;

    public Rutenett(int r, int k) { //Konstruktøren til klassen som tar inn antall rader/kolonner i rutenettet som parametere.
        rutene = new Celle[r][k];
        antRader = r;
        antKolonner = k;
    }

    public void lagCelle(int r, int k) { //Lager en celle objekt i ønsket posisjon i rutenettet (parametere r,k(rad/kolonne)) med 1/3 sjanse å være levende.
        Celle celle = new Celle();
        if (Math.random() <= 0.3333) {
            celle.settLevende();
        }
        rutene[r][k] = celle;
    }       

    public void fyllMedTilfeldigeCeller() { //Fyller ut rutenettet med tilfeldige celler (levende/døde). 
        for (int i=0;i<rutene.length;i++) { //Dobbel for-løkke som itererer gjennom rader og kolonner.
            for (int j=0;j<rutene[i].length;j++) {
                lagCelle(i, j); //Bruker lagCelle-metoden for å opprette en tilfedig Celle og plassere det i en og en posisjon i rutenettet.
            }
        }
    }

    public Celle hentCelle(int r, int k) { //Returnerer et Celle-objekt ut ifra koordinatene (parametrene r og k (rad/kolonne)).
        if (r >= 0 && r < antRader && k >= 0 && k < antKolonner) { //Sørger for at indekser som er utenfor omfanget av rutenettet returnerer null. 
            return rutene[r][k];
        }
        return null;
    }

    public void tegnRutenett() { //Tegner rutenett i terminalen. 
        for(int i=0;i<3;i++) { //3 linjer mellomrom mellom hver iterasjon av rutenettet.
            System.out.println();
        }
        for (int i=0;i<rutene.length;i++) { 
            for (int j=0;j<rutene[i].length;j++) {
                System.out.print(rutene[i][j].hentStatusTegn()); //Printer cellene som ligger i hver rad etter hverandre.
            }
            System.out.println(); //Flytter kursoren til neste linje. 
        }
    }

    public void settNaboer(int r, int k) { //Setter opp naboer til hver celle i rutenettet. 
        Celle celle = hentCelle(r,k); //Henter opp cellen vi ønsker å legge naboer til  med metoden hentCelle.
        for (int i=-1;i<=1;i++) { //Dobbel for-løkke som itererer mellom verdiene -1 og 1 for hver rad/kolonne. Det er posisjonene over/under og til venstre/høyre fra 
            for (int j=-1;j<=1;j++) {                                                                           //Celle-objektet vi ønsker å legge naboer til.
                if (i == 0 && j == 0) { //Hvis variablene i og j begge blir like 0, så treffer løkken akkurat på Celle-objektet vi ønsker å legge naboer til.
                    continue;
                }
                int naboRad = r + i; //Oppretter indekser for nabo sine rad/kolonne posisjon. 
                int naboKol = k + j;

                if (naboRad >= 0 && naboRad < rutene.length && naboKol >= 0 && naboKol < rutene[naboRad].length) { //Sjekker om at indeksene til naboen er gyldige. 
                    Celle naboCelle = hentCelle(naboRad, naboKol); //Hvis indeksene er gyldige hentes det naboen til cellen med hentCelle metoden.
                    celle.leggTilNabo(naboCelle); //Naboen legges til ved bruk av leggTilNabo metoden fra Celle-klassen.
                }
            }
        }
    }

    public void kobleAlleCeller() { //Metoden itererer gjennom hver posisjon i rutenettet og setter opp naboer for hver Celle-objekt i rutenettet.
        for (int i=0;i<rutene.length;i++) {
            for (int j=0;j<rutene[i].length;j++) {
                settNaboer(i, j);
            }
        }
    }

    public int antallLevende() { //Metoden som itererer gjennom rutenettet og returnerer totalt antall levende celler i rutenettet.
        int teller = 0;
        for (int i=0;i<rutene.length;i++) {
            for (int j=0;j<rutene[i].length;j++) {
                if (rutene[i][j].erLevende()) {
                    teller++;
                }
            }
        } return teller;
    }

}