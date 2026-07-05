class Stabel <E> extends Lenkeliste<E> {

    @Override
    public void leggTil(E x) {
        Node ny = new Node(x);
        if (første == null) {
            første = ny;
            siste = ny;
            return;
        }
        ny.neste = første;
        første.forrige = ny;
        første = ny;
    }
}
