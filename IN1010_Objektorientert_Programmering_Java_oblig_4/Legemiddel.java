abstract class Legemiddel {
    public final String navn;
    public final int ID;
    private int pris;
    public double mengdeVS;
    private static int teller = 0;

    public Legemiddel(String navn, int pris, double mengdeVS) {
        this.navn = navn;
        this.pris = pris;
        this.mengdeVS = mengdeVS;
        this.ID = teller;
        teller++;
    }

    public String hentType() {
        return this.getClass().getSimpleName();
    }

    public String hentNavn() {
        return navn;
    }   

    public int hentID() {
        return ID;
    }

    public double hentMengdeVS() {
        return mengdeVS;
    }

    public int hentPris() {
        return pris;
    }

    public void settNyPris(int nyPris) {
        this.pris = nyPris;
    }

    @Override
    public String toString() {
        return "ID: " + ID + "\n" + "Navn: " + navn + "\n" + 
                "Pris: " + pris + "\n" + "Mengde virkastof: " + mengdeVS;
    }
}