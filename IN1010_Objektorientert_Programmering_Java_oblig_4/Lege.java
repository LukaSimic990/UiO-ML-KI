class Lege implements Comparable<Lege> {
    public String navn;
    public IndeksertListe<Resept> utskrevneResepter = new IndeksertListe<Resept>();

    public Lege(String navn) {
        this.navn = navn;
    }

    public String hentNavn() {
        return navn;
    }

    @Override
    public String toString() {
        return "Navn: " + navn;
    }

    @Override
    public int compareTo(Lege annen) {
        return this.navn.compareTo(annen.navn);
    }

    public IndeksertListe<Resept> hentResepter() {
        return utskrevneResepter;
    }

    public HvitResept skrivHvitResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        if (legemiddel instanceof Narkotisk) {
            throw new UlovligUtskrift(this, legemiddel);
        }
        HvitResept rp = new HvitResept(legemiddel, this, pasient, reit);
        utskrevneResepter.leggTil(rp);
        pasient.leggTilResept(rp);
        return rp;
    }

    public MilitærResept skrivMilitærResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        if (legemiddel instanceof Narkotisk) {
            throw new UlovligUtskrift(this, legemiddel);
        }
        MilitærResept rp = new MilitærResept(legemiddel, this, pasient, reit);
        utskrevneResepter.leggTil(rp);
        pasient.leggTilResept(rp);
        return rp;
    }

    public PResept skrivPResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        if (legemiddel instanceof Narkotisk) {
            throw new UlovligUtskrift(this, legemiddel);
        }
        PResept rp = new PResept(legemiddel, this, pasient, reit);
        utskrevneResepter.leggTil(rp);
        pasient.leggTilResept(rp);
        return rp;
    }

    public BlåResept skrivBlåResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        if (legemiddel instanceof Narkotisk && !(this instanceof Spesialist)) {
            throw new UlovligUtskrift(this, legemiddel);
        }
        BlåResept rp = new BlåResept(legemiddel, this, pasient, reit);
        utskrevneResepter.leggTil(rp);
        pasient.leggTilResept(rp);
        return rp;
    }
}