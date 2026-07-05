class PResept extends HvitResept {

    public PResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        super(legemiddel, utskrivendeLege, pasient, reit);
    }

    @Override
    public String farge() {
        return super.farge();
    }

    @Override
    public int prisÅBetale() {
        if (super.prisÅBetale() >= 108) {
            return super.prisÅBetale() - 108;
        } 
        return 0;
    }
}