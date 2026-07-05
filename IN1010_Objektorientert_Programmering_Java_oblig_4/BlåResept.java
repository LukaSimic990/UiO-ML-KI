class BlåResept extends Resept {

    public BlåResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        super(legemiddel, utskrivendeLege, pasient, reit);
    }

    @Override
    public String farge() {
        return "Blå";
    }

    @Override
    public int prisÅBetale() {
        return (int) Math.round(legemiddel.hentPris() * 0.25);
    }
}