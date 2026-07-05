import java.util.concurrent.locks.*;

class Monitor { // Klasse koordinerer operasjoner på et subsekvensregister, og sørger for at disse utføres trådsikkert.
    private Subsekvensregister ssr = new Subsekvensregister();
    private Lock lås = new ReentrantLock(); // Klassen trenger et lås-objekt.
    private Condition ikkeTom = lås.newCondition(); // Trenger bare et Condition.-objekt, til når 'ssr' ikke lenger er tom.

    public void settInn(Frekvenstabell f) {
        lås.lock();
        try {
            ssr.settInn(f); // Det er nok å signalisere når registeret ikke lenger er tom. 
            ikkeTom.signalAll();
        } finally {lås.unlock();}
    } 

    public Frekvenstabell taUt() throws InterruptedException {
        lås.lock();
        try {
            while (ssr.antall() == 0) {ikkeTom.await();} // En tråd må vente til registeret er fylt på med frekvenstabeller for å ta en ut.
            return ssr.taUt();
        } finally {lås.unlock();}
    }

    public int antall() {
        return ssr.antall();
    }

    public Frekvenstabell les(String filnavn) { 
        lås.lock();
        try {
            return ssr.les(filnavn);
        } finally {lås.unlock();}
    }

    public Frekvenstabell[] taUtTo() throws InterruptedException { // Metoden kaster 'InterruptedException' pga at den bruker taUt() metodne fra denne Klassen.
        lås.lock();
        try {
            if (ssr.antall() < 2) {return null;} // Flettingen ska terminere når det bare blir 1 frekvenstabell igjen i registeret. Koden sender signal til 
                                                // flettetråden  til å terminere når det bare blir den siste frekvenstabellen igjen i registeret.
            Frekvenstabell[] tabeller = new Frekvenstabell[2];
            tabeller[0] = ssr.taUt(); // Tar ut to tilfeldige frekvenstabeller fra registeret.
            tabeller[1] = ssr.taUt();
            return tabeller;
        } finally {lås.unlock();}
    }
}