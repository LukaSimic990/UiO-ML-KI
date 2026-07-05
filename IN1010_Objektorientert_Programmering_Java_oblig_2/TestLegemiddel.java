class TestLegemiddel {

    private static void testLegemiddelId(Legemiddel lm, int forventetId) { // Test metoden til å teste ID tildeling. 
        if (lm.id == forventetId) {
            System.out.println("Forventet ID: " + forventetId + ", obektets ID: " +  
                                lm.id + ". Legemidlets ID er riktig!");
        } else {
            System.out.println("ID til legemidlet stemmer ikke!");
        }
    } 

    private static void testHentPris(Legemiddel lm, int forventetPris) { // Test metoden til hentPris(). 
        if (lm.pris == forventetPris) {
            System.out.println("Sjekker pris... Alt riktig!");
        } else {
            System.out.println("Prisen stemmer ikke!");
        }    
    }

    public static void main(String[] args) { // Oppretter et objekt av hver klasse. 
        Narkotisk nl = new Narkotisk("OxyNorm", 130, 10, 3);
        Vanedannende vl = new Vanedannende("Sobril", 102, 25, 4);
        Vanlig vanligL = new Vanlig("Albyl E", 105, 75);
        
        testLegemiddelId(nl, 0); // Tester ID.
        testLegemiddelId(vl, 1);
        testLegemiddelId(vanligL, 2);


        testHentPris(vanligL, 105); // Tester hentPris og settNyPris metodene.
        vanligL.settNyPris(150);
        testHentPris(vanligL, 150);
    }
}