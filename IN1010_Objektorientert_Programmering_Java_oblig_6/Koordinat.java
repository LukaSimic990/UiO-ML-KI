class Koordinat {
    public int r;
    public int k;

    Koordinat(Rute rute) { // Klassen tar imot et Rute objekt og tar dets koordinater.
        r = rute.rNr;
        k = rute.kNr;
    }

    @Override
    public String toString() { // Formatert utskrift av en rutes koordinater.
        return "(" + Integer.toString(r) + ", " + Integer.toString(k) + ")";
    }
}