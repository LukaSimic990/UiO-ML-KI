class Lege { // Superklassen Lege. 
    protected final String navn; // Kun en instansvariabel - navn (til lege).

    public Lege(String navn) {
        this.navn = navn;
        
    }

    // Overstyrer Objekt-klassens toString metode for å returnere ne stren med all tilgjengelig info om et Lege-objekt.
    @Override 
    public String toString() {
        return "Navn: " + navn +";";
    }
}

class Spesialist extends Lege implements Godkjenningsfritak { // Spesialist - subklassen til klassen Lege. Subklassen implementerer 'Godkjenningsfritak'-interface.
    protected final String kontrollkode;
    public Spesialist(String navn, String kontrollkode) {
        super(navn); // Tar inn samme parameteren som superklassen Lege. 
        this.kontrollkode = kontrollkode; // Tar inn en ekstra parameter - kontrollkode.
    }

    public String hentKontrollkode() { // 'getter' metoden som returnerer objektets kontrollkode. 
        return kontrollkode;
    }

    // Overstyret toString metode fra superklassen Lege, for å returnere en streng med all tilgjengelig info om Spesialist-objektet.
    @Override
    public String toString() {
        return super.toString() + "\n" + // returnerer først 'generell' info, tilgjengelig for alle Lege-objekter og dens subklasser (instansvariabelen 'navn').
                "Kontrollkode: " + hentKontrollkode() + ";"; // Returnerer tillaggsinfo karakteristisk for Spesialist-subklassen (kontrollkode).
    }
}

