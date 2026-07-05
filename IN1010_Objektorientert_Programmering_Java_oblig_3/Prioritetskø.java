class Prioritetskø <E extends Comparable<E>> extends Lenkeliste<E> {
    
    @Override 
    public void leggTil(E x) {
        if (første == null || x.compareTo(første.element) < 0) { // Hvis det første elementet eksisterer og er større enn 'x',
            Node ny = new Node(x,første);                        // det settes elementet i 'x' i den første posisjonen.
            første = ny;
            return;
        } 
        Node iter = første; // Ellers itererer koden til den kommer til et ikke-null element som er større en elementet i 'x'.
        while (iter.neste != null && x.compareTo(iter.neste.element) > 0) { 
            iter = iter.neste;
        }
        iter.neste = new Node(x,iter.neste); // Elementet i 'x' settes inn den posisjonen og resten av elementene skyves fremover.
    }
}
