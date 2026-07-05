import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

class GUI implements GrafiskBrukergrensesnitt {
    public Kontroller kontroller;
    public JFrame vindu;
    public JPanel panel, konsoll, rutenett;
    public JButton[][] ruter;
    public JLabel status;
    public JButton stoppKnapp;
    public Timer t; // Gir bedre kontroll over beskjedene som JLabel-objektet skriver ut i GUI.
    public ArrayList<Koordinat> aktivSti;

    @Override
    public void init(Kontroller k) {
        kontroller = k;

        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            System.exit(1);
        }

        vindu = new JFrame(kontroller.hentFilnavn()); // Setter opp vinduet.
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel(); // Setter opp det ytre panelet.
        panel.setLayout(new BorderLayout());
        vindu.add(panel);

        konsoll = new JPanel(); // Konsoll-panelet: Skrive ut beskjeder til brukeren, 'Avslutt'-knappen.
        konsoll.setLayout(new BorderLayout());
        panel.add(konsoll, BorderLayout.NORTH); // Posisjonert øverst i det ytre panelet.

        status = new JLabel("Velg en start rute"); // JLabel-objektet for å skrive ut beskjeder til brukeren.
        konsoll.add(status, BorderLayout.NORTH);

        stoppKnapp = new JButton("Avslutt"); // Oppretter 'Avslutt'-knappen. 
        class stoppHendelse implements ActionListener { // ActionListener-klassen som avslutter programmet ved 'klik' på 'stoppKnappen'.
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(1);
            }
        }
        stoppKnapp.addActionListener(new stoppHendelse());
        konsoll.add(stoppKnapp, BorderLayout.SOUTH);

        rutenett = new JPanel(); // Oppretter rutenettet av knapper (rutene i labyrinten) som et til panel som posisjoneres sentralt i det ytre panelet.
        rutenett.setLayout(new GridLayout(kontroller.hentAntRader(), kontroller.hentAntKolonner())); // Layout: GridLayout.
        ruter = new JButton[kontroller.hentAntRader()][kontroller.hentAntKolonner()]; // Oppretter ruter-arayet for å holde styr på knappene.
        panel.add(rutenett, BorderLayout.CENTER);
        for (int i=0;i<kontroller.hentAntRader();i++) { // Oppreter knappene i labyrinten avhengig av hvilken klasse de hører til og legger dem til i rutenett-panelet og 
            for (int j=0;j<kontroller.hentAntKolonner();j++) { // ruter-arayet.
                JButton knapp = new JButton();
                if (kontroller.hentRuter()[i][j] instanceof HvitRute) {
                    knapp.setBackground(Color.WHITE); // Setter opp fargene til knappene.
                } else if (kontroller.hentRuter()[i][j] instanceof SortRute) {
                    knapp.setBackground(Color.BLACK);
                }
                knapp.addActionListener(new klikk(i, j));
                rutenett.add(knapp);
                ruter[i][j] = knapp;
            }
        }

        vindu.pack(); // Setter opp visningen til vinduet når programmet startes.
        vindu.setSize(400, 400);
        vindu.setLocationRelativeTo(null);
        vindu.setVisible(true);
    } 

    public void visFramOptimalUtvei(ArrayList<Koordinat> koo) { // Metoden som skriver den optimale stien i GUI.
        if (t != null) { // Alle Timer-objektene som allerede løper når metoden starter stoppes. Det for å kunne synkronisere GUI-et.
            t.stop();
        }
        if (aktivSti != null) { // aktivSti-arrayet holder styr på hvilken sti vises på skjermen. Ved gjentatt trykk mens en utvei sti vises i labyrinten 
            nullstill(aktivSti); // faller GUI-et uta av synkroniseringen. aktivSti fjerner den gamle stien fra visningen og med engang viser den nye stien 
        }                        // fra den andre valgte posisjonen i labyrinten.

        aktivSti = new ArrayList<Koordinat>(koo);

        if (aktivSti != null) { // aktivSti nullstilles ved hver ny kjøring av metoden.
            for (Koordinat k : aktivSti) {
                ruter[k.r][k.k].setBackground(Color.RED); // Fargen til hver av de knappene i utvei-stien endres til rød for å vise stien.
            }
        }
        status.setText("Utvei funnet!"); // Timer-objekt som styrer visning av teksten i JLabel.
        t = new Timer(3000, new nullUt(new ArrayList<>(koo))); // Beskjeden tilbakestilles etter 3 sekunder.
        t.start();
    }

    public void valgtSortRute() { // Metoden som håndterer tilfellet hvor en sort rute velges av brukeren som start-rute.
        if (t != null) {
            t.stop();
        }
        status.setText("Du kan ikke starte fra en vegg! Prøv igjen!");
        t = new Timer(3000, new venter()); // Timer-objektet som håndterer beskjeden gitt til brukeren.
        t.start();
    }

    public void ingenUtvei() { // Metoden som håndterer tilfellet hvor programmet ikke klarer å finne en utvei fra en valgt posisjon i labyrinten.
        if (t != null) {
            t.stop();
        }
        status.setText("Det finnes ingen utvei fra den valgte ruten!");
        t = new Timer(3000, new venter());
        t.start();
    }

    public void nullstill(ArrayList<Koordinat> koo) { // Nullstiller rutene i utvei-stien tilbake til hvit.
        for (Koordinat k : koo) {
            ruter[k.r][k.k].setBackground(Color.WHITE);
        }
    }

    class klikk implements ActionListener { // ActionListener klassen til knappene i rutenett-panelet
        int r;
        int k;
        klikk(int r, int k) {
            this.r = r;
            this.k = k;
        }
        @Override
        public void actionPerformed(ActionEvent ae) {
            kontroller.finnUtveiFra(r, k); // Ved 'klikk' utføres det letingen etter den optimale stien til en utvei.
        }
    }

    class venter implements ActionListener { // ActionListener klassen som håndterer beskjeden som vises i tillfellet - valgtSortRute.
        @Override
        public void actionPerformed(ActionEvent ae) {
            status.setText("Velg en start rute");
        }
    }

    class nullUt implements ActionListener { // ActionListener klassen som håndterer nullstillingen av visningen av den optimale stien tilbake til den opprinnelige fargen.
        ArrayList<Koordinat> koo;
        nullUt(ArrayList<Koordinat> koo) {
            this.koo = koo;
        }
        @Override
        public void actionPerformed(ActionEvent ae) {
            nullstill(koo);
            status.setText("Velg en start rute");
        }
    }
}