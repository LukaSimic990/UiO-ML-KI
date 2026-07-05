class Vanedannende extends Legemiddel {
    public final int styrke;

    public Vanedannende(String navn, int pris, double mengdeVS, int styrke) {
        super(navn, pris, mengdeVS);
        this.styrke = styrke;
    }

    public int hentStyrke() {
        return styrke;
    }

    public String toString() {
        return super.toString() + "\n" + "Styrke (vanedannende): " + styrke;
    }
}