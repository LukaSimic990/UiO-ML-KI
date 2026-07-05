abstract class Legemiddel { // Abstrakt klasse Legemiddel (det lages ikke objekter av denne klassen selv).
    public final String navn; // Deklarerer instasvariablene.
    public final int id;
    protected int pris;
    public final double mvs; // mvs - mengde virkestoff.
    private static int teller; // Privat, statisk variabel som inkrementerer variabelen id, slik at hvert legemiddel objekt 
                              // får sit eget, unikt id nummer.
    public Legemiddel(String navn, int pris, double mvs) {
        this.navn = navn;
        this.pris = pris;
        this.mvs = mvs;
        id = teller;
        teller++;
    }

    public int hentPris() { // 'getter' metode som returnerer legemidlets pris.
        return pris;
    }

    public void settNyPris(int nyPris) { // 'setter' metode som endrer prisen til et Legemiddel-objekt.
        pris = nyPris;
    }

    // Overstyret toString() metoden fra Objekt-klassen som returnerer en streng med all tilgjengelig info om legemidlet. 
    @Override
    public String toString() {
        return "Navn: " + navn + " " + mvs + "mg;\n" +
                "ID: " + id + ";\n" +
                "Pris: " + pris + ";";
    } 
}

class Narkotisk extends Legemiddel { // Narkotisk - subklassen til klassen Legemiddel.
    protected int styrke;

    public Narkotisk(String navn, int pris, double mvs, int styrke) {
        super(navn, pris, mvs); // Tar inn samme parametere som Legemiddel-klassen. 
        this.styrke = styrke; // Tar in en parameter til - narkotisk styrke.
    }

    // toString metoden til Narkotisk-subklassen. Overstyrer toString() metoden til superklassen Legemiddel.
    @Override
    public String toString() {
        return super.toString() + "\n" + // Kaller først på metoden toString fra superklassen,
                "Narkotisk styrke: " + styrke + ";"; // så legger til info som er spesifikk til subklassen Narkotisk.           
    }
}

class Vanedannende extends Legemiddel { // Vanedannende - subklassen til klassen Legemiddel.
    protected int vd; // vd - hvor vanedannende legemidlet er.

    public Vanedannende(String navn, int pris, double mvs, int vd) {
        super(navn, pris, mvs); // Tar inn først parametere til superklassen Legemiddel.
        this.vd = vd; // Legger til parameteren som er kaarakteristisk til denne subklassen.
    }

    @Override
    public String toString() {
        return super.toString() + "\n" + // I utgangspunktet samme kode som til metoden toString i Narkotisk.
                "Styrke vanedannende: " + vd + ";";          
    }
}

class Vanlig extends Legemiddel { // Vanlig - sublassen til klassen Legemiddel.
    
    public Vanlig(String navn, int pris, double mvs) { // Subklassen har akkurat samme parametere og akkurat samme metodene som superklassen, 
        super(navn, pris, mvs);                        // derfor implementeres ingen nye metoder/instansvariabler her.
    }
}
