class Stabel <E> extends Lenkeliste<E> {
    
    @Override
    public void leggTil(E x) { // Metoden leggTil overskrives. 'første' peker nå på det nye elementet og forrige elementet 'første' har peket på 
        Node ny = new Node(x,første); // pekes nå på av 'ny' sin 'neste' peker.
        første = ny;
    }
}
