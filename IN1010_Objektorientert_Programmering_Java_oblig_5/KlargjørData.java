import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.util.concurrent.CountDownLatch;

class KlargjørData {
    private static int ANTALL_TRÅDER = 8;
    public static void main(String[] args) {
        File fil = new File(args[0]);
        Scanner s = null;
        String mappe = fil.getParent() + "/";

        try { // Håndterer muligheten at en fil ikke eksisterer. 
            s = new Scanner(fil);
        } catch (Exception e) {
            System.out.println("Kan ikke lese filen " + args[0] + "!");
            System.exit(1);
        }

        Monitor smittet = new Monitor(); // Oppretter et monitor objekt for hver klasse filer ("smittet", "ikke_smitte").
        Monitor ikkeSmittet = new Monitor();

        ArrayList<Thread> tråder = new ArrayList<Thread>(); // Samler lesetråder i en samling for at det skal være mulig å utføre join() på dem.
        Lesetråd lesetråd = null;
        while (s.hasNextLine()) {
            String linje = s.nextLine();
            String[] splittet = linje.split(",");
            String filnavn = mappe + splittet[0];
            if (splittet[1].equalsIgnoreCase("true")) { // Sorterer tråder iflg om en pasient har blitt smittet med CMV eller ikke.
                lesetråd = new Lesetråd(filnavn ,smittet);
            } else if (splittet[1].equalsIgnoreCase("false")) {
                lesetråd = new Lesetråd(filnavn, ikkeSmittet);
            }
            Thread tråd = new Thread(lesetråd); // Oppretter en lesetråd for hver fil, starter trådene og legger dem hver for seg i samlingen 'tråder'.
            tråd.start();
            tråder.add(tråd);
        }
        s.close();
        try { // InterruptedException kastet av join() må være tatt imot.
            for (Thread t : tråder) {
                t.join(); // Utfører join(). Hovedtråden venter på at alle lesetråder ´blir ferdig, så går den videre.
            }
        } catch (InterruptedException i) {
            Thread.currentThread().interrupt();
        }

        CountDownLatch barriere1 = new CountDownLatch(ANTALL_TRÅDER); // Oppretter en barriere.
        Flettetråd flettetråd = null; // Oppretter en flettetråd. 
        Frekvenstabell ft1 = null; // Frekvenstabellen for de som har vært smittet med CVM.
        for (int i=0;i<ANTALL_TRÅDER;i++) { // Oppretter 8 tråder som fletter sammen frekvenstabellene til dem som har vært smittet.
            flettetråd = new Flettetråd(smittet, barriere1);
            Thread tråd = new Thread(flettetråd);
            tråd.start(); // Starter alle tråder.
        }
        try {
            barriere1.await(); // Barrieren venter på alle ttråder å være ferdig.
            ft1 = smittet.taUt(); // Da alle tråder er ferdig, blir det bare 1 frekvenstabell igjen. Hentes ut med metoden taUt() til monitoren og legges inni variabelen 'ft1'.
        } catch (InterruptedException i) {
            Thread.currentThread().interrupt();
        }

        CountDownLatch barriere2 = new CountDownLatch(ANTALL_TRÅDER); // Oppretter den andre barrieren.
        Frekvenstabell ft2 = null; // Frekvenstabellen for de som ikke har vært smittet med CMV.
        for (int i=0;i<ANTALL_TRÅDER;i++) { // Oppretter 8 tråder som fletter sammen frekvenstabellene til dem som ikke har vært smittet.
            flettetråd = new Flettetråd(ikkeSmittet, barriere2);
            Thread tråd = new Thread(flettetråd);
            tråd.start(); // Starter alle tråder.
        }
        try {
            barriere2.await(); // Den andre barrieren venter på alle tråder å bli ferdig.
            ft2 = ikkeSmittet.taUt(); // Når alle er ferdig tas det ut den siste tabellen til dem som ikke har vært smittet med CMV.
        } catch (InterruptedException i) {
            Thread.currentThread().interrupt();
        }

        ft1.skrivTilFil("smittet.txt"); // Subsekvensene/frekvensene til personer som har vært smittet med CMV skrives til filen "smittet.txt".
        ft2.skrivTilFil("ikkeSmittet.txt"); // Subsekvensene/frekvensene til personer som ikke har vært smittet med CMV skrives til filen "ikkeSmittet.txt".

        System.out.println("Klargjøring av data utført!"); // Programmet gir beskjed nå data klargjøring er utført. 
    }
}