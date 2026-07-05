class Pasient {
    public final String navn;
    public final String fNr;
    private final int ID;
    private static int teller = 0;
    public IndeksertListe<Resept> resepter = new IndeksertListe<Resept>();

    public Pasient(String navn, String fNr) {
        this.navn = navn;
        this.fNr = fNr;
        this.ID = teller;
        teller++;
    }

    public String hentNavn() {
        return navn;
    }

    public String hentFNr() {
        return fNr;
    }

    public int hentID() {
        return ID;
    }

    public void leggTilResept(Resept rp) {
        resepter.leggTil(rp);
    }

    public IndeksertListe<Resept> hentResepter() {
        return resepter;
    }

    @Override
    public String toString() {
        return "Navn: " + navn + "\nFødselsnummer: " + fNr + "\nPasient ID: " + ID;
    }

}