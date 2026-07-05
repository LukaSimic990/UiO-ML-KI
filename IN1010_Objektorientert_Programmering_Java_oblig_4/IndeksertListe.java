class IndeksertListe <E> extends Lenkeliste<E> {
    public void leggTil (int pos, E x) {
        if (pos < 0 || pos > størrelse()) {
            throw new UgyldigListeindeks(pos);
        }
        if (pos == 0) {
            Node ny = new Node(x, første);
            første = ny;
            return;
        }
        Node iter = første;
        for (int i=0;i<pos-1;i++) {
            iter = iter.neste;
        }
        iter.neste = new Node(x, iter.neste);
    }

    public void sett (int pos, E x) {
        if (pos < 0 || pos >= størrelse()) {
            throw new UgyldigListeindeks(pos);
        }
        if (pos == 0) {
            første.element = x;
        }
        Node iter = første;
        for (int i=0;i<pos-1;i++) {
            iter = iter.neste;
        }
        iter.neste.element = x;
    }

    public E hent (int pos) {
        if (pos < 0 || pos >= størrelse()) {
            throw new UgyldigListeindeks(pos);
        }
        if (første == null) {return null;}
        if (pos == 0) {
            return første.element;
        }
        Node iter = første;
        for (int i=0;i<pos;i++) {
            iter = iter.neste;
        }
        return iter.element;
    }

    public E fjern (int pos) {
        if (pos < 0 || pos >= størrelse()) {
            throw new UgyldigListeindeks(pos);
        }
        if (pos == 0 && første != null) {
            E elem = første.element;
            første = første.neste;
            return elem;
        } 
        if (første == null) {return null;}
        Node iter = første;
        for (int i=0;i<pos-1;i++) {
            iter = iter.neste;
        }
        E elem = iter.neste.element;
        iter.neste = iter.neste.neste;
        return elem;
    }
}
