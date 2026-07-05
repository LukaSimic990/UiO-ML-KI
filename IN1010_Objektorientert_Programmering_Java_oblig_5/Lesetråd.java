class Lesetråd implements Runnable { // Tråden som skal lese filer og opprette nye frekvenstabeller. (produsent)
    private String filnavn;
    private Monitor monitor;

    Lesetråd(String f, Monitor m) {
        filnavn = f;
        monitor = m;
    }

    @Override
    public void run() {
        monitor.settInn(monitor.les(filnavn)); // Setter inn en ny frekvenstabell, lest inn fraa en fil, inni registeret.
    }
}