import java.util.ArrayList;

class Kontroller { // Kontroller klasse for å koordinere samhandlingen mellom modellen (Labyrint-klassen) og GUI-klassen.
    private GUI view;
    private Labyrint modell;
    private String filnavn;

    Kontroller(String filnavn) {
        view = new GUI();
        modell = new Labyrint(filnavn);
        this.filnavn = filnavn;
    }

    public void init() {
        view.init(this);
    }

    public void finnUtveiFra(int r, int k) { // Metoden som bruker modellens finnUtveiFra-metoden.
        ArrayList<Koordinat> optimal = modell.finnUtveiFra(r, k);
        if (optimal != null && !optimal.isEmpty()) { // Koordinerer funksjonene i GUI-klassen i følge resultatet fra finnUtveiFra-metoden.
            view.visFramOptimalUtvei(optimal);
        } else if (optimal == null) {
            view.valgtSortRute();
        }
        
        if (optimal != null && optimal.isEmpty()) {
            view.ingenUtvei();
        }
    }

    // Resten er henter-metodene jeg måtte legge til for å kunne skrive GUI-klassen.
    public String hentFilnavn() {
        return filnavn;
    }

    public int hentAntRader() {
        return modell.r;
    }

    public int hentAntKolonner() {
        return modell.k;
    }

    public Rute[][] hentRuter() {
        return modell.ruter;
    }
}