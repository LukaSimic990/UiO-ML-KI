import java.util.ArrayList;

class Åpning extends HvitRute {
    
    Åpning(int r, int k, Labyrint lab) {
        super(r,k,lab);
    }

    @Override
    public void finn(Rute fra, ArrayList<Koordinat> sti) {
        if (besøkt) {return;}
        besøkt = true;

        ArrayList<Koordinat> nySti = new ArrayList<Koordinat>(sti);
        nySti.add(new Koordinat(this));

        lab.leggTilKoordinater(nySti); // finn() her bruker metodene i Labyrint-objektet de hører til for å legge til utvei-sti koordinatene i listen over 
        lab.leggTilUtvei(this);       // utvei-stier og så sine egne koordinater til (Åpning-rute-objekt) til listen over koordinatene over alle utganger i 
                                    // labyrinten.
        besøkt = false;
    }

    @Override
    public String toString() {
        if (besøkt) {
            return Integer.toString(verdi);
        }
        return ".";
    }
}