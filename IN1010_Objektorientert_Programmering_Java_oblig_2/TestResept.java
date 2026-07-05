public class TestResept {

    private static boolean testReseptId(Resept resept, int forventetId) { // Metoden som skal teste riktig tildelig av resept id. 
        return forventetId == resept.hentId();
    }

    private static void testPasientId(Resept resept, int forventetId) { // Metoden som skal teste hentPasientId-metoden. 
        if (resept.hentPasientId() == forventetId) {
            System.out.println("Sjekker pasient ID ... Riktig!");
        } else {
            System.out.println("Pasient ID stemmer ikke!");
        }
    }

    private static void testReit(Resept resept, int forventetReit) { // Metoden som skal teste hentReit-metoden.
        if (resept.hentReit() == forventetReit) {
            System.out.println("Sjekker reit...Riktig!");
        } else {
            System.out.println("Reit stemmer ikke!");
        }
    }

    private static void testBruk(Resept resept, boolean kanBrukes) { // Metoden som skal teste bruk-metoden.
        if (resept.bruk() == kanBrukes) {
            System.out.println("Sjekker om resepten kan brukes... Riktig!");
        } else {
            System.out.println("Her stemmer ikke noe!");
        }
    }

    public static void main(String[] args) {
        Lege lege1 = new Lege("Luka"); // Oppretter objekter av klassene.
        Spesialist lege2 = new Spesialist("Ivana", "89yX59z6");
        Narkotisk lm1 = new Narkotisk("Morfin", 200, 10, 5);
        Vanedannende lm2 = new Vanedannende("Sobril", 105, 15, 4);
        Vanlig lm3 = new Vanlig("Cerazette", 280, 75);
        Vanlig lm4 = new Vanlig("Metoprolol", 124, 50);

        Resept rp1 = new HvitResept(lm1, lege2, 12345, 1); // Oppretter objekter av Resept-klassene.
        HvitResept rp2 = new MilitærResept(lm2, lege1, 12224, 2);
        PResept rp3 = new PResept(lm3, lege2, 12344, 4);
        BlåResept rp4 = new BlåResept(lm4, lege1, 13342, 4);

        System.out.println("Reseptens ID stemmer - " + testReseptId(rp3, 2)); // Sjekker ID tildelingen.
        System.out.println("Reseptens ID stemmer - " + testReseptId(rp4, 0));

        testPasientId(rp2, 12224); // Sjekker hentPasientID-metoden.
        testPasientId(rp3, 12344);

        testReit(rp1, 1); // Sjekker hentReit- og bruk-metodene.
        rp1.bruk();
        testBruk(rp1, false);
        testReit(rp1, 0);
    }
}