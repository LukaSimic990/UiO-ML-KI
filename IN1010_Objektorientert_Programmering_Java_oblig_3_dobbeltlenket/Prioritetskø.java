class Prioritetskø <E extends Comparable<E>> extends Lenkeliste<E> {
    
    @Override
    public void leggTil(E x) {
        Node ny = new Node(x);
        if (første == null) {
            første = ny;
            siste = ny;
            return;
        }
        if (x.compareTo(første.data) < 0) {
            ny.neste = første;
            første.forrige = ny;
            første = ny;
            return;
        }
        Node iter = første;
        while (iter.neste != null && x.compareTo(iter.neste.data) > 0) {
            iter = iter.neste;
        }
        if (iter.neste == null) {
            ny.forrige = siste;
            siste.neste = ny;
            siste = ny;
            return;
        }
        ny.neste = iter.neste;
        iter.neste.forrige = ny;
        ny.forrige = iter;
        iter.neste = ny;
    }
}
