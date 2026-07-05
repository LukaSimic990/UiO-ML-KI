import java.util.Iterator;
import java.util.NoSuchElementException;

abstract class Lenkeliste <E> implements Liste<E> {
    protected Node første;
    protected Node siste;

    protected class Node {
        E data;
        Node neste;
        Node forrige;

        Node(E data) {
            this.data = data;
        }
    }

    @Override
    public String toString () {
        String streng = "";
        if (første == null) {
            return "Listen er tom";
        }
        Node iter = første;
        while (iter != null) {
            streng += iter.data;
            iter = iter.neste;
        }
        return streng;
    }

    @Override
    public int størrelse () {
        int antall = 0;
        Node iter = første;
        while (iter != null) {
            antall++;
            iter = iter.neste;
        }
        return antall;
    }

    @Override
    public void leggTil (E x) {
        Node ny = new Node(x);
        if (første == null) {
            første = ny;
            siste = ny;
            return;
        }
        siste.neste = ny;
        ny.forrige = siste;
        siste = ny;
    }
    
    @Override
    public E hent () {
        if (første == null) {
            return null;
        }
        return første.data;
    }

    @Override
    public E fjern () {
        if (første == null) {
            throw new UgyldigListeindeks(0);
        }
        E ut = første.data;
        første = første.neste;

        if (første != null) {
            første.forrige = null;
        } else {
            siste = null;
        }
        return ut;
    }

    @Override
    public Iterator<E> iterator() {
        return new ListeIterator();
    }

    private class ListeIterator implements Iterator<E> {
        private Node iter = første;

        @Override
        public boolean hasNext() {
            return iter != null;
        }

        @Override
        public E next() {
            if (iter == null) {
                throw new NoSuchElementException();
            }
            E ut = iter.data;
            iter = iter.neste;
            return ut;
        }
    }
}
