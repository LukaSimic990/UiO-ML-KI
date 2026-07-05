class IndeksertListe <E> extends Lenkeliste<E> {
    public void leggTil (int pos, E x) {
        if (pos < 0 || pos > størrelse()) { // Sjekker om den angitte indeksen er innafor.
            throw new UgyldigListeindeks(pos); // Hvis det ikke er tilfellet, så kaster den et unntak.
        }
        if (pos == 0) { // Hvis 'pos' er 0, så skal det nye elementet legges inn i den første posisjonen.
            Node ny = new Node(x,første);
            første = ny;
            return;
        }
        Node iter = første; // Ellers iterer gjennom listen til den kommer til elementet i posisjonen før den angitte (indeksene går til posisjonen før den som er angitt 
        for (int i=0;i<pos-1;i++) { // med indeksen).
            iter = iter.neste;
        }
        iter.neste = new Node(x,iter.neste); // Setter elementet inni posisjonen angitt av indeksen - iter.neste peker på Node-objektet i den posisjonen da.
    }

    public void sett (int pos, E x) {
        if (pos < 0 || pos >= størrelse()) {
            throw new UgyldigListeindeks(pos);
        }
        if (pos == 0) {
            første.element = x; // Hvis den ønskede indeksen er 0, så ønsker vi å endre elementet i den første posisjonen.
            return;
        }
        Node iter = første; // Hvis ikke iterer den på samme måten som ovenfor, men til slutt endres det elementet fra posisjonen fra den angitte indeksen.
        for (int i=0;i<pos-1;i++) {
            iter = iter.neste;
        }
        iter.neste.element = x;
    }

    public E hent (int pos) {
        if (pos < 0 || pos >= størrelse()) {
            throw new UgyldigListeindeks(pos);
        }
        if (pos == 0) {
            return første.element;
        }
        Node iter = første; // Sammen koden som ovenfor, med forskjellen at det returneres elementet fra den ønskede posisjonen.
        for (int i=0;i<pos-1;i++) {
            iter = iter.neste;
        }
        return iter.neste.element;
    }

    public E fjern (int pos) {
        if (pos < 0 || pos >= størrelse()) {
            throw new UgyldigListeindeks(pos);
        }
        if (pos == 0) {
            E elem = første.element; // Her fjernes det det første elementet og deretter returneres det, hvis den ønskede posisjonen er 0.
            første = første.neste;
            return elem;
        }
        Node iter = første;
        for (int i=0;i<pos-1;i++) { // Ellers itererer koden gjennom listen til den kommer til elementet i posisjonen før den ønskede.
            iter = iter.neste;
        }
        E elem = iter.neste.element; // Deretter fjernes det ønskede elementet og til slutt returneres det elementet.
        iter.neste = iter.neste.neste;
        return elem;
    }
}
