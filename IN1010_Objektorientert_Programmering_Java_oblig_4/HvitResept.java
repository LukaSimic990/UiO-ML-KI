class HvitResept extends Resept {

    public HvitResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        super(legemiddel, utskrivendeLege, pasient, reit);
    }

    @Override
    public String farge() {
        return "Hvit";
    }

    @Override
    public int prisÅBetale() {
        return legemiddel.hentPris();
    }
}