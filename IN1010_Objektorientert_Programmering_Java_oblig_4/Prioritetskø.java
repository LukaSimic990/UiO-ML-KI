class Prioritetskø <E extends Comparable<E>> extends Lenkeliste<E> {
    
    @Override
    public void leggTil(E x) {
        if (første == null || x.compareTo(første.element) < 0) {
            Node ny = new Node(x, første);
            første = ny;
            return;
        }
        Node iter = første;
        while (iter.neste != null && x.compareTo(iter.neste.element) > 0) {
            iter = iter.neste;
        }
        iter.neste = new Node(x, iter.neste);
    }
}
