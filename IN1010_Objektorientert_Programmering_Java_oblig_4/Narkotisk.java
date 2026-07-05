class Narkotisk extends Legemiddel {
    public final int styrke;

    public Narkotisk(String navn, int pris, double mengdeVS, int styrke) {
        super(navn, pris, mengdeVS);
        this.styrke = styrke;
    }

    public int hentStyrke() {
        return styrke;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" + "Styrke (narkotisk): " + styrke;
    }
}