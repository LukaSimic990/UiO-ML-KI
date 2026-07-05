import java.util.concurrent.CountDownLatch; 

class Flettetråd implements Runnable { // Tråd klassen som skal flette sammen alle tabeller inni et register til det bare blir en tabell igjen. (konsument)
    private Monitor monitor;
    private CountDownLatch latch; // Latch-objektet som skal gi signalen til 'barriere'-objektet i hovedprogrammet når tråden er ferdig.

    Flettetråd(Monitor m, CountDownLatch l) {
        monitor = m;
        latch = l;
    }

    @Override
    public void run() { // Metoden må 'catche' InterruptedException som taUtTo() metoden i monitoren kaster. 
        try {
            Frekvenstabell[] tabeller = monitor.taUtTo();
            while (tabeller != null) { // While-løkken som venter på signalen fra taUtTo()-metoden i Monitor-klassen på at det nå bare er den siste frekvenstabellen igjen.
                monitor.settInn(Frekvenstabell.flett(tabeller[0], tabeller[1]));
                tabeller = monitor.taUtTo();
            }
        } catch (InterruptedException i) {
            Thread.currentThread().interrupt();
        } finally {latch.countDown();} // Gir signal til 'barriere' at tråden er ferdig.
    }
}