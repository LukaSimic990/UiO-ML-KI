abstract class Lenkeliste <E> implements Liste<E> {
    protected class Node { // Oppretter en indre klasse 'Node'.
        E element;
        Node neste;

        Node(E element, Node neste) {
            this.element = element;
            this.neste = neste;
        }
    }
    protected Node første; // 'Første peker' - peker på første instansen av Node-klassen som skal inneholde det første elementet i listen.

    @Override
    public String toString () {
        String streng = new String("Listen inneholder følgende elementer:"); // Deklarerer strengen som skal returneres.
        if (første == null) { // Hvis 'første' peker på et null-objekt så er listen tom og da skrives det passende beskjed til brukeren.
            return " Listen er tom!";
        }
        Node iter = første; // Itererer gjennom listen og legger til elementene i hver posisjon til strengen.
        while (iter.neste != null) {
            streng += iter.element;
            iter = iter.neste;
        }
        return streng; // Strengen returneres nå metoden blir kalt.
    }

    @Override
    public int størrelse () {
        int størrelse = 0; // Oppretter variabelen som skal lagre størrelsen til listen.
        Node iter = første;
        while (iter != null) { // Itererer gjennom listen og inkrementerer variablen 'størrelse' for hver 'neste' peker i listen som ikke peker på 
            størrelse++;      // et null-objekt.
            iter = iter.neste;
        }
        return størrelse; // Returnerer størrelsen til listen.
    }

    @Override
    public void leggTil (E x) {
        Node ny = new Node(x,null); // Oppretter det nye elementet til listen; pekeren 'neste' peker på et null-objekt - elementet legges til i slutten av listen
        if (første == null) {       // Sjekker om 'første' peker på et null-objekt (hvis listen er tom).
            første = ny; // Legger det nye elementet i starten av listen hvis listen er tom.
            return;
        }
        Node iter = første; // Hvis listen ikke er tom, itererer gjennom listen til pekeren 'neste' peker til null (iter peker da på det siste elementet i listen).
        while (iter.neste != null) {
            iter = iter.neste;
        }
        iter.neste = ny; // Setter det nye elementet etter det siste elementet i listen.
        toString(); // Skriver ut listen etter å ha lagt til et nytt element.
    }
    
    @Override
    public E hent () {
        if (første == null) { // Hvis 'første' peker på et null-objekt. 
            return null;
        }
        return første.element; // Ellers returenerer elementet til objektet 'første' peker på.
    }

    @Override
    public E fjern () {
        if (første == null) {
            throw new UgyldigListeindeks(0); // Hvis første er null så er listen tom og det ikke kan fjernes noen elementer.
        }
        E elem = første.element; // Eller oppretter pekeren som skal peke på elementet til objektet 'første' peker på.
        første = første.neste; // Endrer objektet 'første' peker på til å være neste i listen.
        return elem; // Returenerer elementet til Node-objektet 'første' peket på før metoden ble kalt. 
    }
}
