package Display;

import Game.Game;
import Game.Helps;
import Questions.Question;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

/**
 * A játék megjelenítéséért, valamint a játékos interakcióiért felelős osztály.
 */
public class GameDisplay extends JFrame implements ActionListener{
    private Helps helps;
    private Game game;
    private JButton aButton;
    private JButton bButton;
    private JButton cButton;
    private JButton dButton;
    private JButton splitButton;
    private JButton crowdButton;
    private JButton callButton;
    private JMenu gMenu;
    private JMenu hMenu;
    private JMenuBar jMenuBar;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JPanel prizeText;
    private JTextArea questionText;
    private JMenuItem SnewGame;
    private JMenuItem stopGame;
    private JMenuItem leaderboard;
    private JMenuItem quitGame;
    private JMenuItem infoHelp;
    private JLabel[] prizes;
    private Leaderboard lb;

    public void enableSplitButton(boolean b) {
         splitButton.setEnabled(b);
    }
    public void enableCrowdButton(boolean b) {
        crowdButton.setEnabled(b);
    }
    public void enableCallButton(boolean b) {
        callButton.setEnabled(b);
    }
    public void enableAButton(boolean b) {
        aButton.setEnabled(b);
    }
    public void enableBButton(boolean b) {
        bButton.setEnabled(b);
    }
    public void enableCButton(boolean b) {
        cButton.setEnabled(b);
    }
    public void enableDButton(boolean b) {
        dButton.setEnabled(b);
    }
    public GameDisplay() {
        initComponents();
    }

    /**
     * Elkészíti a játékhoz szükséges ablakot, a megadott paraméterekkel, menü elemekkel és gombokkal.
     */
    private void initComponents() {
        try {
			game = new Game();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
        helps = new Helps();
        aButton = new JButton();
        splitButton = new JButton();
        crowdButton = new JButton();
        callButton = new JButton();
        bButton = new JButton();
        cButton = new JButton();
        dButton = new JButton();
        jScrollPane1 = new JScrollPane();
        prizeText = new JPanel();
        jScrollPane2 = new JScrollPane();
        questionText = new JTextArea();
        jMenuBar = new JMenuBar();
        gMenu = new JMenu();
        hMenu = new JMenu();
        SnewGame = new JMenuItem();
        stopGame = new JMenuItem();
        leaderboard = new JMenuItem();
        quitGame = new JMenuItem();
        infoHelp = new JMenuItem();
        setTitle("Legyen Ön is Milliomos");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        aButton.setText("A");
        aButton.addActionListener(this);

        splitButton.setText("Felezés");
        splitButton.addActionListener(this);

        crowdButton.setText("Közönség");
        crowdButton.addActionListener(this);

        callButton.setText("Telefon hívás");
        callButton.addActionListener(this);

        bButton.setText("B");
        bButton.addActionListener(this);

        cButton.setText("C");
        cButton.addActionListener(this);

        dButton.setText("D");
        dButton.addActionListener(this);

        prizeText.setOpaque(false);
        prizeText.add(initPrizes());
        jScrollPane1.setViewportView(prizeText);
        jScrollPane1.setOpaque(false);
        jScrollPane1.setBorder(null);

        questionText.setEditable(false);
        questionText.setColumns(20);
        questionText.setRows(5);
        questionText.setFont(questionText.getFont().deriveFont(20f));
        questionText.setOpaque(false);
        questionText.setLineWrap(true);
        questionText.setWrapStyleWord(true);
        jScrollPane2.setViewportView(questionText);
        jScrollPane2.setOpaque(false);
        jScrollPane2.setBorder(null);

        SnewGame.setText("Új játék");
        SnewGame.addActionListener(this);
        stopGame.setText("Játék befejezése");
        stopGame.addActionListener(this);
        leaderboard.setText("Dicsőséglista");
        leaderboard.addActionListener(this);
        quitGame.setText("Kilépés");
        quitGame.addActionListener(this);
        gMenu.setText("Játék");
        gMenu.add(SnewGame);
        gMenu.add(stopGame);
        gMenu.add(leaderboard);
        gMenu.add(quitGame);
        jMenuBar.add(gMenu);

        infoHelp.setText("Névjegy");
        infoHelp.addActionListener(this);
        hMenu.setText("Segítség");
        hMenu.add(infoHelp);
        jMenuBar.add(hMenu);
        setJMenuBar(jMenuBar);

        enableHelpButtons(false);
        enableAnsButtons(false);
        stopGame.setEnabled(false);
        try{
            loadLeaderboard();
        }catch(ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        //Ha esetleg bezárná valaki az ablakot a kilépés gomb helyett
        addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					saveLeaderboard();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(aButton, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(bButton, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(cButton, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(dButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addComponent(jScrollPane2))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1)
                                        .addComponent(splitButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(crowdButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(callButton, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                                        .addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(bButton, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                                        .addComponent(aButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(cButton, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                                        .addComponent(dButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(splitButton, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(crowdButton, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(callButton, GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)))
                                .addGap(18, 18, 18))
        );
        pack();
    }

    /**
     * Az elnyerhető összegek megjelenítéséért felel.
     * @return - az elnyerhető összegek tördelve körönként.
     */
    private JPanel initPrizes() {
        JPanel prizesP = new JPanel(new GridLayout(15, 1, 10, 0));
        prizes = new JLabel[15];
        for (int i = 15; i >= 1; i--) {
            prizes[i - 1] = new JLabel(Integer.toString(i) + ".kör " + game.getPrize(i));
            prizes[i - 1].setHorizontalAlignment(SwingConstants.CENTER);
            prizesP.add(prizes[i - 1]);
        }
        return prizesP;
    }

    /**
     * A képernyőn megjelenő kérdés frissítése.
     */
    private void updateQstn() {
        Question cQuestion = game.getcQuestion();
        questionText.setText(cQuestion.getQuestion());
        aButton.setText("A: " +cQuestion.getA());
        bButton.setText("B: " + cQuestion.getB());
        cButton.setText("C: " + cQuestion.getC());
        dButton.setText("D: " + cQuestion.getD());
        for (int i = 0; i < 15; i++) {
            if (i < game.getRound() - 1) {
                prizes[i].setForeground(Color.green);
            } else if (i == game.getRound() - 1) {
                prizes[i].setForeground(Color.black);
            } else {
                prizes[i].setForeground(Color.gray);
            }
        }
        //System.out.println(cQuestion.getAns());
    }

    /**
     * Új játék indítása.
     */
    private void newGame() {
        game.startGame();
        updateQstn();
        enableAnsButtons(true);
        enableHelpButtons(true);
        SnewGame.setEnabled(false);
        stopGame.setEnabled(true);
    }

    /**
     * A válaszgombok engedélyezése/letiltása
     * @param b - a kívánt érték beállítása a gombok láthatósága kapcsán.
     */
    private void enableAnsButtons(boolean b) {
        enableAButton(b);
        enableBButton(b);
        enableCButton(b);
        enableDButton(b);
    }

    /**
     * A segítség gombok engedélyezése/letiltása
     * @param b - a kívánt érték beállítása a gombok láthatósága kapcsán.
     */
    private void enableHelpButtons(boolean b) {
        enableSplitButton(b);
        enableCrowdButton(b);
        enableCallButton(b);
    }

    /**
     * Ha megfelelően válaszol az adott kérdésre a játékos a következő körbe léphet.
     * Ha elérte a 15. kört akkor nyert, ekkor meghívja a win() függvényt.
     */
    private void goodAns() {
        if(game.getRound() == 15) {
            win();
            return;
        }
        game.nextRound();
        updateQstn();
        enableAnsButtons(true);
    }

    /**
     * A játék megnyerésekor meghívandó függvény.
     * Kiírja egy ablakban, hogy nyert és várja a játékos nevét, hogy hozzáadhassa a dicsőséglistához.
     * Amennyiben nem ad meg nevet "Anonymous"-ként kerül be.
     */
    private void win() {
        prizes[14].setForeground(Color.green);
        String name = JOptionPane.showInputDialog(this,
                "Gratulálok Ön nyert!\nKérlek add meg a neved alább.",
                "Játék vége", JOptionPane.PLAIN_MESSAGE);
        if(name.isEmpty())
            name = "Anonymous";
        lb.add(name, game.getPrize(14));
        SnewGame.setEnabled(true);
        stopGame.setEnabled(false);
    }

    /**
     * A dicsőséglista betöltése fájlból.
     */
    private void loadLeaderboard() throws IOException, ClassNotFoundException {
        lb = new Leaderboard();
        File f = new File("leaderboard.dat");
        FileInputStream fs = new FileInputStream(f);
        ObjectInputStream os = new ObjectInputStream(fs);
        lb = (Leaderboard) os.readObject();
        os.close();
    }

    /**
     * A dicsőséglista mentése fájlba a játék bezárásakor.
     */
    private void saveLeaderboard() throws IOException {
        FileOutputStream fs = new FileOutputStream("leaderboard.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fs);
        oos.writeObject(lb);
        oos.close();
    }

    /**
     * A dicsőséglista megjelenítése.
     */
    private void openLeaderboard() {
        JOptionPane.showMessageDialog(this, lb.getLeaderboardP(), "Dicsőséglista", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Infopanel megjelenítése.
     */
    private void info() {
        JOptionPane.showMessageDialog(this, "A játékot készítette:\nDemény Bálint", "Névjegy", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Játék végeztével megjelenő ablak.
     * Elmenti a játékos nevét a dicsőséglistába, ha legalább az első körön továbbjutott.
     * Amennyiben a játékos nem ad meg nevet "Anonymous"-ként kerül be.
     */
    private void gameOver() {
        char cAns = game.getCorrectAns();
        int cRound = game.getRound();
        enableAnsButtons(false);
        enableHelpButtons(false);
        questionText.setText("");
        if(cRound == 1) {
            JOptionPane.showMessageDialog(this, "Sajnálom, Ön számára a játék véget ért!\nA helyes válasz: " + cAns,
                    "Játék vége", JOptionPane.INFORMATION_MESSAGE);
        }else {
            String name = JOptionPane.showInputDialog(this,
                    "Sajnálom, Ön számára a játék véget ért!\nA helyes válasz: " + cAns +
                            "\nKérlek add meg a neved alább.", "Játék vége", JOptionPane.PLAIN_MESSAGE);
            if (name.isEmpty())
                name = "Anonymous";
            lb.add(name, game.getPrize(cRound - 1));
        }
        prizes[cRound-1].setForeground(Color.red);
        SnewGame.setEnabled(true);
        stopGame.setEnabled(false);
    }

    /**
     * A játékos interakcióit kezeli.
     * @param e - a feldolgozandó esemény
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(SnewGame)) {
            newGame();
        }else if(e.getSource().equals(aButton)) {
            if(game.getCorrectAns() == 'A') {
                goodAns();
            }else {
                gameOver();
            }
        }else if(e.getSource().equals(bButton)) {
            if(game.getCorrectAns() == 'B') {
                goodAns();
            }else {
                gameOver();
            }
        }else if(e.getSource().equals(cButton)) {
            if(game.getCorrectAns() == 'C') {
                goodAns();
            }else {
                gameOver();
            }
        }else if(e.getSource().equals(dButton)) {
            if (game.getCorrectAns() == 'D') {
                goodAns();
            } else {
                gameOver();
            }
        }else if(e.getSource().equals(splitButton)) {
            helps.split(game, this);
        }else if(e.getSource().equals(crowdButton)) {
            helps.crowd(game, this);
        }else if(e.getSource().equals(callButton)) {
            helps.call(game, this);
        }else if(e.getSource().equals(stopGame)) {
            gameOver();
        }else if(e.getSource().equals(leaderboard)){
            openLeaderboard();
        }else if(e.getSource().equals(quitGame)){
            try {
                saveLeaderboard();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            System.exit(0);
        }else if(e.getSource().equals(infoHelp)){
            info();
        }
    }
}
