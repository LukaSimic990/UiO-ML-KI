import java.util.TreeMap;
import java.io.PrintWriter;

class Frekvenstabell extends TreeMap<String, Integer> { // En frekvenstabell holder rede på en samling subsekvenser, og deres frekvenser (altså, hvor ofte de forekommer).
    
    @Override
    public String toString() {
        String streng = ""; 
        for (String n : this.keySet()) { // Går gjennom hvert nøkkel-verdi par og legger dem inni strengen i hvar sin linje.
            streng = streng + n + " " + this.get(n) + "\n";
        }
        return streng;
    }

    public static Frekvenstabell flett(Frekvenstabell f1, Frekvenstabell f2) {
        Frekvenstabell flettet = new Frekvenstabell();
        for (String n : f1.keySet()) { // Flytter første 'f1' til 'flettet'.
            flettet.put(n, f1.get(n));
        }
        for (String n : f2.keySet()) { // Så flytter 'f2' til 'flettet'.
            if (flettet.containsKey(n)) { // Hvis en subsekvens i 'f2' finns også i 'f1' summeres verdiene, så oppdateres da subsekvensens frekvens i 'flettet'.
                int sum = flettet.get(n) + f2.get(n); 
                flettet.put(n, sum);
            } else {
                flettet.put(n, f2.get(n)); // Ellers blir bar subsekvens/frekvens par flyttet til 'flettet'.
            }
        }
        return flettet;
    }

    public void skrivTilFil(String filnavn) {
        PrintWriter fil = null;
        try {
            fil = new PrintWriter(filnavn); // Håndterer muligheten at filen ikke kan nås.
        } catch (Exception e) {
            System.out.println("Kan ikke skrive  til filen " + filnavn + "!");
            System.exit(1);
        }
        String[] linjer = this.toString().split("\n"); // Splitter opp strengen i linjer; skriver hver linje for seg til filen.
        for (String l : linjer) {
            fil.println(l);
        }
        fil.close();
    }   
}