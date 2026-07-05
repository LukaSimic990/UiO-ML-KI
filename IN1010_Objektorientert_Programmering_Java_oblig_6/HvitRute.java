import java.util.ArrayList;

class HvitRute extends Rute {

    HvitRute(int r, int k, Labyrint lab) {
        super(r,k,lab);
    }

    @Override
    public void finn(Rute fra, ArrayList<Koordinat> sti) { // HvitRute-klassens rekursjonsmetoden - finn().
        if (besøkt) {return;} // Hvis ruten har blitt forbigått skal programmet ikke gå gjennom den igjen (hindrer syklisk oppførsel av programmet).
        besøkt = true; // Når ruten har blitt forbigått ()

        ArrayList<Koordinat> nySti = new ArrayList<Koordinat>(sti); // Det legges til koordinater til hver rute inni stien.
        nySti.add(new Koordinat(this));

        for (Rute nabo : naboer) {
            if (nabo != null & nabo != fra) { // Nabo skal ikke være den ruten programmet har kommet fra og heller ikke et null-objekt.
                nabo.finn(this, nySti); // Rekursjonskall.
            }
        }
        besøkt = false; // Ruten skal ikke være sperret i evig etter å ha blitt gjennomgått en gang.
    }

    @Override
    public String toString() {
        if (besøkt) {
            return Integer.toString(verdi);
        }
        return ".";
    }
    
}