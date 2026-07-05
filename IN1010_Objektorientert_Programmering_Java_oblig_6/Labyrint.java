import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;

class Labyrint {
    public Rute[][] ruter;
    public int r;
    public int k;
    public ArrayList<ArrayList<Koordinat>> koordinater = new ArrayList<ArrayList<Koordinat>>(); // Koordinater til ruter i stien programmet går gjennom.
    public ArrayList<Rute> utveier = new ArrayList<Rute>(); // Variabelen 'utveier' - lagrer koordinater til alle utveier i en labyrint.

    Labyrint(String filnavn) {
        if (!filnavn.endsWith(".labyrint")) { // Sjekker extension til filen som sendes til 'args'. Skriver ut en melding hvis filen ikke er av riktig type.
            System.out.println("Kan ikke lese filen " + filnavn + "! Feil fil-type!");
            System.exit(1);
        }

        Scanner s = null;
        try {
            s = new Scanner(new File(filnavn));
        } catch (Exception e) {
            System.out.println("Kan ikke lese filen " + filnavn + "!");
            System.exit(1);
        }

        // Bygger opp rutene i labyrinten fra filen.
        String førsteLinje = s.nextLine().trim();
        String[] splittet = førsteLinje.split(" ");
        r = Integer.parseInt(splittet[0]);
        k = Integer.parseInt(splittet[1]);
        ruter = new Rute[r][k];

        Rute rut = null;
        for (int i=0;i<r;i++) {
            String linje = s.nextLine();
            for (int j=0;j<k;j++) {
                char c = linje.charAt(j);
                if (c == '.') {
                    if (i == 0 || i == (r-1) || j == 0 || j == (k-1)) {
                        rut = new Åpning(i, j, this);
                    } else {
                        rut = new HvitRute(i, j, this);
                    }
                } else if (c == '#') {
                    rut = new SortRute(i, j, this);
                }
                ruter[i][j] = rut;
            }
        }
        for (int i=0;i<ruter.length;i++) { // Setter opp naboer til hver rute.
            for (int j=0;j<ruter[i].length;j++) {
                settOppNaboer(i, j);
            }
        }
    }

    public Rute hentRute(int r, int k) { // Metoden som returnerer arrayen med rutene i labyrinten.
        return ruter[r][k];
    }

    public void settOppNaboer(int r, int k) { // Metoden for å sette opp naboer til hver rute i labyrinten.
        Rute rut = ruter[r][k];
        int[][] retninger = {{0,-1}, {0,1}, {1,0}, {-1,0}}; // Mulige posisjoner til naboene (venstre, høyre, opp, ned).
        for (int[] ret : retninger) {
            int naboR = r + ret[0];
            int naboK = k + ret[1];
            if (naboR >= 0 && naboR < ruter.length && naboK >= 0 && naboK < ruter[naboR].length) { // Sjekker om indeksen til naboen er riktig.
                Rute nabo = ruter[naboR][naboK];
                rut.leggTilNabo(nabo); // Legger til naboen til ruten.
            }
        }
    }

    public ArrayList<Koordinat> finnUtveiFra(int r, int k) {
        this.nullstill(); // Nullstiller parametrene i hver rute i labyrinten før hver kjøring av metoden.
        koordinater.clear(); // Tømmer listene med koordinater.
        utveier.clear();

        ArrayList<Koordinat> sti = new ArrayList<Koordinat>();
        if (!(ruter[r][k] instanceof SortRute)) {
            ruter[r][k].finn(null, sti); // Oppstart av rekursjonsdelen. Første ruten (valgt av brukeren starter med Rute fra = null).
        } else {
            return null; // Hvis den valgte ruten er en SortRute returneres det null - ikke mulig å starte fra en vegg.
        }

        System.out.println("\nÅpninger: ");
        for (Rute ut : utveier) { // Skriver ut de oppdagede utveiene i labyrinten.
            System.out.println("(" + ut.rNr + ", " + ut.kNr + ")");
        }
        
        int minLengde = this.r * this.k; // Kodeblokken som finner den korteste stien til en utvei fra den valgte posisjonen i labyrinten.
        ArrayList<Koordinat> optimal = new ArrayList<Koordinat>();
        for (ArrayList<Koordinat> vei : koordinater) {
            if (vei.size() < minLengde) {
                minLengde = vei.size();
                optimal = vei;
            }
        }

        System.out.println("\n" + utveier.size() + " utveier funnet.\n"); // Skriver ut hvor mange utveier det har blitt funnet i labyrinten.

        if (optimal != null) {
            System.out.println("Den optimale utveien:");
            for (Koordinat koo :optimal) { // Skriver ut stien til den opptimale stien.
                System.out.print(koo);
            }
            System.out.println();
        }
        return optimal; // Returnerer den optimale stien.
    }

    public void leggTilKoordinater(ArrayList<Koordinat> sti) { // Legger til koordinater til hver utvei i listen med Koordinat-objekter.
        koordinater.add(sti); 
    }

    public void leggTilUtvei(Rute ut) { // Legger til koordinatene til hver av de oppdagede utveiene i labyrinten til listen.
        if (!utveier.contains(ut)) {
            utveier.add(ut); 
        }
    }

    public void nullstill() { // Nullstiller parametrene i hver av de rutene i labyrinten.
        for (int i=0;i<ruter.length;i++) {
            for (int j=0;j<ruter[i].length;j++) {
                ruter[i][j].besøkt = false;
                ruter[i][j].teller = 1;
            }
        }
    }

    @Override
    public String toString() { 
        String streng = "";
        for (int i=0;i<ruter.length;i++) {
            for (int j=0;j<ruter[i].length;j++) { // Koden tilpasset slik at det skrives ut hver av stegene programmet tok til å finne hver av utveiene i labyrinten.
                if (ruter[i][j].toString().length() == 2) { // Meningen er å tilpasse utskriften slikt at den tar hensyn til to-siffrede stegnummer.
                    streng += ruter[i][j].toString() + " ";
                } else if (ruter[i][j].toString().length() == 1) {
                    streng += ruter[i][j].toString() + "  ";
                }
            }
            streng += "\n";
        }
        return streng;
    }
}