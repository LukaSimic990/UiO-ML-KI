import java.io.*;
import java.util.Scanner;

class Legesystem {
    private static IndeksertListe<Legemiddel> legemidler = new IndeksertListe<Legemiddel>();
    private static IndeksertListe<Resept> resepter = new IndeksertListe<Resept>();
    private static Prioritetskø<Lege> leger = new Prioritetskø<Lege>();
    private static IndeksertListe<Pasient> pasienter = new IndeksertListe<Pasient>();

    public static void lesFraFil(String filnavn) {
        Scanner s = null;
        try {
            s = new Scanner(new File(filnavn));
        } catch (Exception e) {
            System.out.println("Kan ikke lese fra filen " + filnavn + "!");
            System.exit(1);
        }
        String seksjon = "";
        while (s.hasNextLine()) {
            String linje  = s.nextLine().trim();
            if (linje.startsWith("#")) {
                linje = linje.replaceFirst("#","").trim();
                seksjon = linje.split(" ")[0];
                continue;
            }
            if (seksjon.equalsIgnoreCase("pasienter")) {
                String[] linjeBiter = linje.split(",");
                Pasient pas = new Pasient(linjeBiter[0], linjeBiter[1]);
                pasienter.leggTil(pas);
            } else if (seksjon.equalsIgnoreCase("legemidler")) {
                String[] linjeBiter = linje.split(",");
                int pris = Integer.parseInt(linjeBiter[2]);
                double mengdeVS = Double.parseDouble(linjeBiter[3]);
                if (linjeBiter[1].equalsIgnoreCase("vanlig")) {
                    Vanlig lm = new Vanlig(linjeBiter[0], pris, mengdeVS);
                    legemidler.leggTil(lm);
                } else if (linjeBiter[1].equalsIgnoreCase("vanedannende")) {
                    int styrke = Integer.parseInt(linjeBiter[4]);
                    Vanedannende lm = new Vanedannende(linjeBiter[0], pris, mengdeVS, styrke);
                    legemidler.leggTil(lm); 
                } else if (linjeBiter[1].equalsIgnoreCase("narkotisk")) {
                    int styrke = Integer.parseInt(linjeBiter[4]);
                    Narkotisk lm = new Narkotisk(linjeBiter[0], pris, mengdeVS, styrke);
                    legemidler.leggTil(lm);
                }
            } else if (seksjon.equalsIgnoreCase("leger")) {
                String[] linjeBiter = linje.split(",");
                int kode = Integer.parseInt(linjeBiter[1]);
                Lege lege = null;
                if (kode != 0) {
                    lege = new Spesialist(linjeBiter[0], linjeBiter[1]);
                } else {
                    lege = new Lege(linjeBiter[0]);
                }
                leger.leggTil(lege);
            } else if (seksjon.equalsIgnoreCase("resepter")) {
                String[] linjeBiter = linje.split(",");
                int legemiddelId = Integer.parseInt(linjeBiter[0]);
                String legeNavn = linjeBiter[1];
                int pasId = Integer.parseInt(linjeBiter[2]);
                String type = linjeBiter[3];
                Legemiddel lm = null;
                for (Legemiddel l : legemidler) {
                    if (l.hentID() == legemiddelId) {
                        lm = l;
                    }
                }
                Lege lege = null;
                for (Lege lg : leger) {
                    if (lg.hentNavn().equals(legeNavn)) {
                        lege = lg;
                    }
                }
                Pasient pas = null;
                for (Pasient p : pasienter) {
                    if (p.hentID() == pasId) {
                        pas = p;
                    }
                }
                Resept rp = null;
                if (type.equalsIgnoreCase("hvit")) {
                    try {
                        int reit = Integer.parseInt(linjeBiter[4]);
                        rp = lege.skrivHvitResept(lm, pas, reit);
                    } catch (UlovligUtskrift e) {
                        System.out.println(e.getMessage());
                    }
                } else if (type.equalsIgnoreCase("blaa")) {
                    try {
                        int reit = Integer.parseInt(linjeBiter[4]);
                        rp = lege.skrivBlåResept(lm, pas, reit);
                    } catch (UlovligUtskrift e) {
                        System.out.println(e.getMessage());
                    }
                } else if (type.equalsIgnoreCase("militaer")) {
                    try {
                        rp = lege.skrivMilitærResept(lm, pas, 0);
                    } catch (UlovligUtskrift e) {
                        System.out.println(e.getMessage());
                    }
                } else if (type.equalsIgnoreCase("p")) {
                    try {
                        int reit = Integer.parseInt(linjeBiter[4]);
                        rp = lege.skrivPResept(lm, pas, reit);
                    } catch (UlovligUtskrift e) {
                        System.out.println(e.getMessage());
                    }
                }
                resepter.leggTil(rp);
            }
        }
    }
    public static void main(String[] args) {
        Scanner tast = new Scanner(System.in);

        lesFraFil(args[0]);

        System.out.println("** VELKOMMEN TIL LEGESYSTEMET **");

        while (true) {
            System.out.println("\nAlternativer:\n0. Avslutt\n1. Skriv ut alle data\n2. Skriv ut statistikk\n3. Legg til lege\n" +
                            "4. Legg til legemiddel\n5. Legg til pasient\n6. Legg til resept\n7. Bruk resept\n8. Skriv ut alle data på fil");
            int brukerInp = Integer.parseInt(tast.nextLine());
            if (brukerInp == 1) {
                System.out.println("# Pasienter (navn, fnr)");
                for (Pasient p : pasienter) {
                    System.out.println(p.hentNavn()+ "," + p.hentFNr());
                }
                System.out.println("# Legemidler (navn,type,pris,virkestoff,[styrke])");
                for (Legemiddel lm : legemidler) {
                    if (lm instanceof Vanlig) {
                        System.out.println(lm.hentNavn() + "," + lm.hentType() + "," + lm.hentPris() + "," + Double.toString(lm.hentMengdeVS()));
                    } else if (lm instanceof Vanedannende) {
                        Vanedannende lgm = (Vanedannende)lm;
                        System.out.println(lgm.hentNavn() + "," + lgm.hentType() + "," + lgm.hentPris() + "," + Double.toString(lgm.hentMengdeVS()) + 
                        "," + lgm.hentStyrke());
                    } else if (lm instanceof Narkotisk) {
                        Narkotisk lgm = (Narkotisk)lm;
                        System.out.println(lgm.hentNavn() + "," + lgm.hentType() + "," + lgm.hentPris() + "," + Double.toString(lgm.hentMengdeVS()) + 
                        "," + lgm.hentStyrke());
                    }
                }
                System.out.println("# Leger (navn,kontrollkode / 0 hvis vanlig lege)");
                for (Lege lege : leger) {
                    if (lege instanceof Spesialist) {
                        Spesialist lg = (Spesialist)lege;
                        System.out.println(lg.hentNavn() + "," + lg.hentKontrollKode());
                    } else {
                        System.out.println(lege.hentNavn() + "," + 0);
                    }
                }
                System.out.println("# Resepter (legemiddelNummer,legeNavn,pasientID,type,[reit])");
                for (Resept rp : resepter) {
                    if (!(rp instanceof MilitærResept)) {
                        System.out.println(rp.hentLegemiddel().hentID() + "," + rp.hentLege().hentNavn() + "," + 
                        rp.hentPasient().hentID() + "," + rp.hentType() + "," + rp.hentReit());
                    } else {
                        System.out.println(rp.hentLegemiddel().hentID() + "," + rp.hentLege().hentNavn() + "," + 
                        rp.hentPasient().hentID() + "," + rp.hentType());
                    }
                }
            } else if (brukerInp == 2) {
                System.out.println("Alternativer:\n1. Totalt antall utskrevne resepter på vanedannende legemidler\n" +
                    "2. Totalt antall utskrevne resepter på narkotiske legemidler\n" + 
                    "3. Statistikk om mulig misbruk av narkotika");
                brukerInp = Integer.parseInt(tast.nextLine());
                if (brukerInp == 1) {
                    int teller = 0;
                    for (Legemiddel lm : legemidler) {
                        if (lm instanceof Vanedannende) {
                            teller++;
                        }
                    }
                    System.out.println("Totalt antall utskrevne resepter på vanedannende legemidler: " + teller);
                } else if (brukerInp == 2) {
                    int teller = 0;
                    for (Legemiddel lm : legemidler) {
                        if (lm instanceof Narkotisk) {
                            teller++;
                        }
                    }
                    System.out.println("Totalt antall utskrevne resepter på narkotiske legemidler: " + teller);
                } else if (brukerInp == 3) {
                    System.out.println("Antall narkotiske resepter skrevet ut per lege:");
                    for (Lege lege : leger) {
                        int tellerLege = 0;
                        for (Resept rp : lege.hentResepter()) {
                            if (rp.hentLegemiddel() instanceof Narkotisk) {
                                tellerLege++;
                            }
                        }
                        if (tellerLege != 0) {
                            System.out.println("Lege: " + lege.hentNavn() + "; antall utskrevne narkotiske legemidler: " + tellerLege);
                        }
                    }
                    System.out.println();
                    System.out.println("Antall utskrevne narkotiske legemidler per pasient: ");
                    for (Pasient p : pasienter) {
                        int tellerPas = 0;
                        for (Resept rp : p.hentResepter()) {
                            if (rp.hentLegemiddel() instanceof Narkotisk) {
                                tellerPas++;
                            }
                        }
                        if (tellerPas != 0) {
                            System.out.println("Pasient: " + p.hentNavn() + "; antall utskrevne narkotiske legemidler: " + tellerPas);
                        }
                    }
                }
            } else if (brukerInp == 3) {
                System.out.println("Vennligst oppgi legens navn:");
                String navn = "Dr. " + tast.nextLine();
                System.out.println("Er legen spesialist? (tast inn 'ja' eller 'nei')");
                String spes = tast.nextLine();
                if (spes.equalsIgnoreCase("ja")) {
                    System.out.println("Vennligst oppgi legens kontrollkode:");
                    String kode = tast.nextLine();
                    Spesialist lege = new Spesialist(navn,kode);
                    leger.leggTil(lege);
                } else if (spes.equalsIgnoreCase("nei")) {
                    Lege lege = new Lege(navn);
                    leger.leggTil(lege);
                } else {
                    System.out.println("Ugyldig input!");
                    continue;
                }
            } else if (brukerInp == 4) {
                Legemiddel lm = null;
                System.out.println("Vennligst oppgi legemidlets navn:");
                String navn = tast.nextLine();
                System.out.println("Vennligst oppgi legemidlets pris (heltall):");
                int pris = 0;
                try {
                    pris = Integer.parseInt(tast.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Ugyldig input!");
                }
                System.out.println("Vennligst oppgi mengde virkestoff:");
                double mengdeVS = 0;
                try {
                    mengdeVS = Double.parseDouble(tast.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Ugyldig input!");
                }
                System.out.println("Er legemidlet:\n1. Vanlig\n2. Vanedannende\n3. Narkotisk");
                int type = 0;
                try {
                    type = Integer.parseInt(tast.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Ugyldig input!");
                }
                if (type == 1) {
                    lm = new Vanlig(navn, pris, mengdeVS);
                } else if (type == 2) {
                    System.out.println("Vennligst oppgi legemidlets styrke:");
                    int styrke = Integer.parseInt(tast.nextLine());
                    lm = new Vanedannende(navn, pris, mengdeVS, styrke);
                } else if (type == 3) {
                    System.out.println("Venneligst oppgi legemidlets styrke:");
                    int styrke = Integer.parseInt(tast.nextLine());
                    lm = new Narkotisk(navn, pris, mengdeVS, styrke);
                }
                legemidler.leggTil(lm);
            } else if (brukerInp == 5) {
                System.out.println("Vennligst oppgi pasientens navn:");
                String navn = tast.nextLine();
                System.out.println("Vennligst oppgi pasientens fødselsnummer:");
                String fNr = tast.nextLine();
                Pasient pas = new Pasient(navn, fNr);
                pasienter.leggTil(pas);
            } else if (brukerInp == 6) {
                Lege lege = null;
                Legemiddel lgm = null;
                Pasient pas = null;
                System.out.println("Vennligst oppgi den utskrevende legens navn:");
                String navn = tast.nextLine();
                for (Lege l : leger) {
                    String legeNavn = l.hentNavn().replace("Dr.","").trim();
                    if (legeNavn.equalsIgnoreCase(navn)) {
                        lege = l;
                    }
                } 
                if (lege == null) {
                    System.out.println("Lege med det oppgitte navnet finnes ikke i systemet. Vennligst prøv igjen!");
                }
                
                if (lege != null) {
                    System.out.println("Vennligst oppgi legemidlets navn:");
                    String navnLm = tast.nextLine();
                    for (Legemiddel lm : legemidler) {
                        if (lm.hentNavn().equalsIgnoreCase(navnLm)) {
                            lgm = lm;
                        } 
                    }
                    if (lgm == null) {
                        System.out.println("Legemiddel med det oppgitte navnet finnes ikke i systemet. Vennligst prøv igjen!");
                    }
                }
                if (lege != null && lgm != null) {
                    System.out.println("Vennligst oppgi pasientens navn:");
                    String navnPas = tast.nextLine();
                    for (Pasient p : pasienter) {
                        if (p.hentNavn().equalsIgnoreCase(navnPas)) {
                            pas = p;
                        }
                    } 
                    if (pas == null) {
                        System.out.println("Pasient med det oppgitte navnet finnes ikke i systemet. Vennligst prøv igjen!");
                    }
                }
                if (lege != null && lgm != null && pas != null) {
                    System.out.println("Oppgi reit til resepten:");
                    int reit = 0;
                    try {
                        reit = Integer.parseInt(tast.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Ugyldig input!");
                    }
                    System.out.println("Ønsker du å skrive:\n1. HvitResept\n2. Blå resept\n3. Militær resept\n4. PResept");
                    int valg = 0;
                    try {
                        valg = Integer.parseInt(tast.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Ugyldig input!");
                    }
                    Resept rp = null;
                    if (valg == 1) {
                        try {
                            rp = lege.skrivHvitResept(lgm, pas, reit);
                        } catch (UlovligUtskrift e) {
                            System.out.println(e.getMessage());
                        }
                    } else if (valg == 2) {
                        try {
                            rp = lege.skrivBlåResept(lgm, pas, reit);
                        } catch (UlovligUtskrift e) {
                            System.out.println(e.getMessage());
                        }
                    } else if (valg == 3) {
                        try {
                            rp = lege.skrivMilitærResept(lgm, pas, reit);
                        } catch (UlovligUtskrift e) {
                            System.out.println(e.getMessage());
                        }
                    } else if (valg == 4) {
                        try {
                            rp = lege.skrivPResept(lgm, pas, reit);
                        } catch (UlovligUtskrift e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        System.out.println("Ugyldig input!");
                    }
                    if (rp != null) {
                        resepter.leggTil(rp);
                    }
                }
            } else if (brukerInp == 7) {
                System.out.println("Vennligst oppgi pasientens navn:");
                String navn = tast.nextLine();
                Pasient pas = null;
                for (Pasient p : pasienter) {
                    if (p.hentNavn().equalsIgnoreCase(navn)) {
                        pas = p;
                    } 
                }
                if (pas == null) {
                    System.out.println("Pasient med det oppgitte navnet finnes ikke i systemet. Vennligst prøv igjen!");
                }
                if (pas != null) {
                    System.out.println("Pasientens reseptliste:");
                    for (Resept rp : pas.hentResepter()) {
                        System.out.println(rp.hentID() + " " + rp.hentLegemiddel().hentNavn());
                    }
                    System.out.println("Vennligst oppgi nummeret til resepten du ønsker å bruke:");
                    int valg = 0;
                    try {
                        valg = Integer.parseInt(tast.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Ugyldig input!");
                    }
                    Resept rp = resepter.hent(valg);
                    if (rp.bruk()) {
                        System.out.println("Resepten brukt en gang!");
                    } else {
                        System.out.println("Det er ikke flere utleveringer igjen på resepten! Kontakt din lege for å fornye den!");
                    }
                }
            } else if (brukerInp == 8) {
                PrintWriter fil = null;
                System.out.println("Vennligst oppgi navnet til filen du ønsker å skrive til:");
                String filnavn = tast.nextLine();
                try {
                    fil = new PrintWriter(filnavn);
                } catch (Exception e) {
                    System.out.println("Kan ikke skrive til filen " + filnavn + "!");
                    System.exit(1);
                }
                fil.println("# Pasienter (navn, fnr)");
                for (Pasient pas : pasienter) {
                    fil.println(pas.hentNavn() + "," + pas.hentFNr());
                }
                fil.println("# Legemidler (navn,type,pris,virkestoff,[styrke])");
                for (Legemiddel lm : legemidler) {
                    if (lm instanceof Vanlig) {
                        fil.println(lm.hentNavn() + "," + lm.hentType() + "," + lm.hentPris() + "," + lm.hentMengdeVS());
                    } else if (lm instanceof Vanedannende) {
                        Vanedannende vane = (Vanedannende)lm;
                        fil.println(vane.hentNavn() + "," + vane.hentType() + "," + vane.hentPris() + "," + vane.hentMengdeVS() + "," + vane.hentStyrke());
                    } else if (lm instanceof Narkotisk) {
                        Narkotisk nark = (Narkotisk)lm;
                        fil.println(nark.hentNavn() + "," + nark.hentType() + "," + nark.hentPris() + "," + nark.hentMengdeVS() + "," + nark.hentStyrke());
                    }
                }
                fil.println("# Leger (navn,kontrollid / 0 hvis vanlig lege)");
                for (Lege lege : leger) {
                    if (lege instanceof Spesialist) {
                        Spesialist spes = (Spesialist)lege;
                        fil.println(spes.hentNavn() + "," + spes.hentKontrollKode());
                    } else {
                        fil.println(lege.hentNavn() + "," + 0);
                    }
                }
                fil.println("# Resepter (legemiddelNummer,legeNavn,pasientID,type,[reit])");
                for (Resept rp : resepter) {
                    if (!(rp instanceof MilitærResept)) {
                        fil.println(rp.hentLegemiddel().hentID() + "," + rp.hentLege().hentNavn() + "," + 
                        rp.hentPasient().hentID() + "," + rp.hentType() + "," + rp.hentReit());
                    } else {
                        fil.println(rp.hentLegemiddel().hentNavn() + "," + rp.hentLege().hentNavn() + "," +
                        rp.hentPasient().hentID() + "," + rp.hentType());
                    }
                }
                fil.close();
            }
            if (brukerInp == 0) {
                System.out.println("Legesystemet avsluttet. Ha en fin dag!");
                break;
            }
        }
    }
}