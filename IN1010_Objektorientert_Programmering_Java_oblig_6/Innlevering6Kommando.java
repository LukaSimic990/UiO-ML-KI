import java.util.Scanner;

class Innlevering6 {
    public static void main(String[] args) {
        Labyrint lab = new Labyrint(args[0]);

        System.out.println("Slik ser labyrinten ut: \n" + lab);

        Scanner tast = null;
        boolean fortsett = true;
        while (fortsett) {
            tast = new Scanner(System.in);
            System.out.println("Skriv inn startkoordinatene <rad> <kolonne> ('-1' for å avslutte)");
            String brukerInp = tast.nextLine().trim();
            String[] splittet = brukerInp.split(" ");
            int r = 0;
            int k = 0;
            if (splittet.length == 2) {
                try {
                    r = Integer.parseInt(splittet[0]);
                    k = Integer.parseInt(splittet[1]);
                } catch (Exception e) {
                    System.out.println("Ugyldig input! Input må være tall!");
                    continue;
                }
            } else if (splittet.length != 2) {
                if (splittet[0].equals("-1")) {
                    fortsett = false;
                    System.out.println("Programmet avsluttet!");
                    continue;
                } else {
                    System.out.println("Ugyldig input! Prøv igjen!");
                    continue;
                }
            }
            if (r != 0 && k != 0 && !(lab.hentRute(r,k) instanceof SortRute)) {
                lab.finnUtveiFra(r, k);
                System.out.println();
            } else if (lab.hentRute(r,k) instanceof SortRute) {
                System.out.println("\nOoops... Der er det en vegg! Kan ikke starte fra en veg! Prøv igjen!");
                continue;
            }
        }
        tast.close(); 
    }
}