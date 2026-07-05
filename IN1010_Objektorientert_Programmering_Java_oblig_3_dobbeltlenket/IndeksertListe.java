class IndeksertListe <E> extends Lenkeliste<E> {
    
    public void leggTil (int pos, E x) {
        Node ny = new Node(x);
        if (pos < 0 || pos > størrelse()) {
            throw new UgyldigListeindeks(pos);
        }
        if (første == null) {
            første = ny;
            siste = ny;
            return;
        }
        if (pos == 0) {
            ny.neste = første;
            første.forrige = ny;
            første = ny;
            return;
        }
        Node iter = første;
        for (int i=0;i<pos-1;i++) {
            iter = iter.neste;
        }
        if (iter == siste) {
            ny.forrige = siste;
            siste.neste = ny;
            siste = ny;
            return;
        }
        ny.forrige = iter;
        ny.neste = iter.neste;
        iter.neste = ny;
    }

    public void sett (int pos, E x) {
        if (pos < 0 || pos >= størrelse()) {
            throw new UgyldigListeindeks(pos);
        }
        if (pos == 0) {
            første.data = x;
            return;
        }
        Node iter = første;
        for (int i=0;i<pos-1;i++) {
            iter = iter.neste;
        }
        iter.neste.data = x;
    }

    public E hent (int pos) {
        if (pos < 0 || pos >= størrelse()) {
            throw new UgyldigListeindeks(pos);
        }
        if (pos == 0) {
            return første.data;
        }
        Node iter = første;
        for (int i=0;i<pos-1;i++) {
            iter = iter.neste;
        }
        return iter.neste.data;
    }

    public E fjern (int pos) {
        if (pos < 0 || pos >= størrelse()) {
            throw new UgyldigListeindeks(pos);
        }
        if (pos == 0 && første == siste) {
            E ut = første.data;
            første = null;
            siste = null;
            return ut;
        } else if (pos == 0 && første != siste) {
            E ut = første.data;
            første = første.neste;
            første.forrige = null;
            return ut;
        }
        Node iter = første;
        for (int i=0;i<pos-1;i++) {
            iter = iter.neste;
        }
        if (iter.neste == siste) {
            E ut = siste.data;
            siste = siste.forrige;
            siste.neste = null;
            return ut;
        }
        E ut = iter.neste.data;
        iter.neste = iter.neste.neste;
        iter.neste.forrige = iter;
        return ut;
    }
}
