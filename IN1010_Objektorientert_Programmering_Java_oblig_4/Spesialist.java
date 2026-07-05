class Spesialist extends Lege implements Godkjenningsfritak {
    public final String kontrollKode;

    public Spesialist(String navn, String kontrollKode) {
        super(navn);
        this.kontrollKode = kontrollKode;
    }

    @Override
    public String hentKontrollKode() {
        return kontrollKode;
    }

    @Override
    public String toString() {
        return super.toString() + "\nKontrollkode: " + kontrollKode;
    }
}