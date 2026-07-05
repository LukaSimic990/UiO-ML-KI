abstract class Resept {
    public final int ID;
    public final Legemiddel legemiddel;
    public final Lege utskrivendeLege;
    public final Pasient pasient;
    public int reit;
    private static int teller = 0;

    public Resept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        this.legemiddel = legemiddel;
        this.utskrivendeLege = utskrivendeLege;
        this.pasient = pasient;
        this.reit = reit;
        this.ID = teller;
        teller++;
    }

    public String hentType() {
        return this.getClass().getSimpleName().replaceFirst("Resept","").trim();
    }

    public int hentID() {
        return ID;
    }

    public Legemiddel hentLegemiddel() {
        return legemiddel;
    }

    public Lege hentLege() {
        return utskrivendeLege;
    }

    public Pasient hentPasient() {
        return pasient;
    }

    public int hentReit() {
        return reit;
    }

    public boolean bruk() {
        if (reit > 0) {reit--; return true;}
        return false;
    }

    // Returnerer reseptens farge, enten hvit eller blå.
    abstract public String farge();

    // Returnerer prisen pasienten må betale.
    abstract public int prisÅBetale();

    @Override
    public String toString() {
        return "Resept ID: " + ID + "\nLegemiddel: {\n" + legemiddel + "}\n" + 
            "Utskrivende lege: {" + utskrivendeLege + ";}\n" + "Pasient: " + pasient +
            "\nReit: " + reit + "\nFarge: " + farge() + "\nÅ betale: " + prisÅBetale();
    }
}