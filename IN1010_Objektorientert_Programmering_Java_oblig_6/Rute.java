import java.util.ArrayList;

abstract class Rute {
    public int rNr;
    public int kNr;
    public Labyrint lab;
    ArrayList<Rute> naboer = new ArrayList<Rute>();
    public boolean besøkt = false; // Sier om ruten har blitt gått gjennom.
    public int verdi; // Verdien som holder styr på hvilket steg i utvei-stien denne ruten ligger i.
    public static int teller = 1;

    Rute(int r, int k, Labyrint lab) {
        rNr = r;
        kNr = k;
        this.lab = lab;
    }

    public void leggTilNabo(Rute nabo) { // Legger til naboer i 'naboer'-arrayet.
        naboer.add(nabo);
    }

    public abstract void finn(Rute fr, ArrayList<Koordinat> sti);
}