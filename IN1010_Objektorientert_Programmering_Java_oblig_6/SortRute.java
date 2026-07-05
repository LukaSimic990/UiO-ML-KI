import java.util.ArrayList;

class SortRute extends Rute {

    SortRute(int r, int k, Labyrint lab) {
        super(r,k,lab);
    }

    @Override
    public void finn(Rute fra, ArrayList<Koordinat> sti) { // Metoden gjør ingenting - SortRute-objekt = vegg.
        return;
    }
    
    @Override
    public String toString() {
        return "#";
    }
}