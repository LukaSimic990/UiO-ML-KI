class MilitærResept extends HvitResept {

    public MilitærResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        super(legemiddel, utskrivendeLege, pasient, 3);
    }

    @Override
    public String farge() {
        return super.farge();
    }

    @Override
    public int prisÅBetale() {
        return super.prisÅBetale() * 0;
    }
}