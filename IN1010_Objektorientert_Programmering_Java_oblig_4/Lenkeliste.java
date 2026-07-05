import java.util.Iterator;
import java.util.NoSuchElementException;

abstract class Lenkeliste <E> implements Liste<E> {
    
    protected class Node {
        Node neste;
        E element;
        
        Node(E element, Node neste) {
            this.neste = neste;
            this.element = element;
        }
    }

    protected Node første;

    protected class LenkelisteIterator implements Iterator<E> {
        Node iter = første;

        @Override
        public boolean hasNext() {
            return iter != null;
        }

        @Override
        public E next() {
            if (iter == null) {throw new NoSuchElementException();}
            E ut = iter.element;
            iter = iter.neste;
            return ut;
        } 
    }

    @Override
    public String toString () {
        String streng = "Listen inneholder følgende elementer: ";
        if (første == null) {
            return "Listen er tom!";
        }
        Node iter = første;
        while (iter.neste != null) {
            streng = streng + iter.element;
            iter = iter.neste;
        }
        return streng;
    }

    @Override
    public int størrelse () {
        int i = 0;
        Node iter = første;
        while (iter != null) {
            i++;
            iter = iter.neste;
        }
	    return i;
    }

    @Override
    public void leggTil (E x) {
        Node ny = new Node(x, null);
        if (første == null) {
            første = ny;
            toString();
            return;
        }
        Node iter = første;
        while (iter.neste != null) {
            iter = iter.neste;
        }
        iter.neste = ny;
        toString();
    }
    
    @Override
    public E hent () {
        if (første == null) {return null;}
        return første.element;
    }

    @Override
    public E fjern () {
        if (første != null) {
            E elem = første.element;
            første = første.neste;
            toString();
            return elem;
        } else {
            throw new UgyldigListeindeks(-1);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new LenkelisteIterator();
    }
}
