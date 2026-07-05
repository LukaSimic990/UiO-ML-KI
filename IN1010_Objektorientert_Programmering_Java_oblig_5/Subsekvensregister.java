import java.util.*;
import java.io.File;


class Subsekvensregister { // Klassen representerer et register som holder rede på, og lar en behandle, en samling av frekvenstabeller.
    private ArrayList<Frekvenstabell> register = new ArrayList<Frekvenstabell>();
    private static int SUBSEKVENSLENGDE = 3;

    public void settInn(Frekvenstabell f) {
        register.add(f);
    }

    public Frekvenstabell taUt() { // Bruker Random for å hente opp en random index til fjerning fra register, siden metoden skal fjerne en tilfeldig tabell.
        Random rand = new Random();
        return register.remove(rand.nextInt(register.size())); // Metoden reomve() fjerner og returnerer element fra en gitt index. 
    }

    public int antall() {
        return register.size();
    }

    public Frekvenstabell les(String filnavn) {
        Scanner s = null;
        Frekvenstabell f = new Frekvenstabell();
        try { // Håndterer muligheten at filen med 'filnavn' ikke kan leses (f.eks. eksisterer ikke).
            s = new Scanner(new File(filnavn));
        } catch (Exception e) {
            System.out.println("Kan ikke lese filen " + filnavn + "!");
            System.exit(1);
        }
        while (s.hasNextLine()) {
            String linje = s.nextLine();
            for (int i=0;i<linje.length() - SUBSEKVENSLENGDE + 1;i++) { // i kan ikke gå opptil den siste indeksen i en linje pga lengde 3 til subsekvensen.
                String sek = ""; // Restarter sekvensen for hver i - leser linje for hver bokstav frem til bokstaven som er posisjonert som 3. fra slutten (siste sekvensen).
                sek = sek + linje.substring(i, i+SUBSEKVENSLENGDE);
                if (f.containsKey(sek)) {continue;} // Tar bare unike subsekvenser.
                else {f.put(sek,1);} // Hver unik subsekvens skal ha frekvens 1 i starten.
            }
        }
        return f;
    } 

}