import java.util.Scanner; //Importerer Scanner-klassen for lesing av fra terminalen.

public class GameOfLife { //Hoved programmet med klassen Game Of Life.
    public static void main(String[] args) { //Main-metoden som skal kjøre programmet.
        Scanner scanner = new Scanner(System.in);

        System.out.println("*** Velkommen til GAME OF LIFE ***"); //Metoden tar input fra brukeren om hvor mange rader/kolonner rutenettet skal ha.
        System.out.println("\n\nVennligst velg antall rad i Verdenen:");
        int antRad = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Vennligst velg antall kolonner i Verdenen:");
        int antKol = scanner.nextInt();
        scanner.nextLine();
        Verden verden = new Verden(antRad, antKol);
        System.out.println("Her er Verdenen i starten - - - ");
        verden.tegn(); //Tegner 0-te generasjon med tilfeldige celler.

        while (true) { //Brukeren får selv besteme om hen ønsker å se neste iterasjon av Verdenen eller å avsluttet spillet.
            System.out.println("\nHvis du ønsker å se neste generasjon trykk 'Enter' " +
            "\n(trykk hvilken som helst annen knapp for å avslutte)");
            String brukerInp = scanner.nextLine();
            if (brukerInp == "") {
                verden.oppdatering();
                verden.tegn();
            } else {
                System.out.println("Game Of Life er avsluttet. Ha en fin dag!");
                break;
            }
        }
    }
}