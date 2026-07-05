class Integrasjonstest {
    public static void main(String[] args) {
        Legemiddel lm1 = new Vanlig("Paracet", 96, 1000);   // Oppretter objektene.
        Narkotisk lm2 = new Narkotisk("OxyContin", 130, 10, 4);
        Vanedannende lm3 = new Vanedannende("Valium", 125, 5, 3);
        Vanlig lm4 = new Vanlig("Cerazette", 212, 75);

        Lege lege1 = new Lege("Luka");
        Spesialist lege2 = new Spesialist("Ivana", "gb345m7z");

        HvitResept rp1 = new HvitResept(lm1, lege1, 12345, 4);
        MilitærResept rp2 = new MilitærResept(lm1, lege2, 12233, 2);
        PResept rp3 = new PResept(lm4, lege1, 22335, 4);
        BlåResept rp4 = new BlåResept(lm2, lege2, 13346, 3);

        // Sjekker utskriften til hver eneste klasse også når andre klassers objekter inneholder referanser til andre klasser.
        System.out.println(lm1.toString());
        System.out.println("\n" + lm2.toString());
        System.out.println("\n" + lm3.toString());
        System.out.println("\n" + lm4.toString());

        System.out.println("\n" + lege1.toString());
        System.out.println("\n" + lege2.toString());

        System.out.println("\n" + rp1.toString());
        System.out.println("\n" + rp2.toString());
        System.out.println("\n" + rp3.toString());
        System.out.println("\n" + rp4.toString());
    }
}