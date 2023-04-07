package Game;

import Display.GameDisplay;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.Random;

/**
 * A segítségeket tartalmazó osztály.
 */
public class Helps extends Component {
    /**
     * Kérdések felezése.
     * Egy tömb reprezentálja a kérdéseket, ha 0 akkor "törlésre" kerül, ha 1 akkor marad.
     * A jó válasz marad és a maradék opciók közül random választunk egyet.
     * @param game - a jelenlegi játék
     * @param gd - a megjelenített felület
     */
    public void split(Game game, GameDisplay gd) {
        char cAns = game.getCorrectAns();
        game.setCall(false);
        gd.enableSplitButton(false);
        int[] availableAns = {0, 0, 0, 0};
        availableAns[cAns - 'A'] = 1;
        Random r = new Random();
        int rNum = r.nextInt(3);
        rNum++;
        int i = 0;
        for(int j = 0; j < 4; j++) {
            if(availableAns[j] == 0)
                i++;
            if(i == rNum)
                availableAns[j] = 1;
        }
        if(availableAns[0] == 0)
            gd.enableAButton(false);
        if(availableAns[1] == 0)
            gd.enableBButton(false);
        if(availableAns[2] == 0)
            gd.enableCButton(false);
        if(availableAns[3] == 0)
            gd.enableDButton(false);
    }

    /**
     * Közönségszavazás segítség.
     * Egy tömb reprezentálja a kérdések "súlyát". A helyes válaszra több esély van, ezért az nagyobb súlyú.
     * A súly tartományban random számokat generálunk és végül százalékosan leosztjuk és kiírjuk egy ablakba.
     * @param game - a jelenlegi játék
     * @param gd - a megjelenített felület
     */
    public void crowd(Game game, GameDisplay gd) {
        char cAns = game.getCorrectAns();
        game.setCrowd(false);
        gd.enableCrowdButton(false);
        Random rNum = new Random();
        double[] weight = {10, 10, 10, 10};
        weight[cAns - 'A'] = 30;
        double sum = 0;
        for (int i = 0; i < 4; ++i) {
            weight[i] = rNum.nextDouble(weight[i]);
            sum += weight[i];
        }
        double[] percentages = new double[4];
        for (int i = 0; i < 4; ++i) {
            percentages[i] = (weight[i]/sum)*100;
        }
        DecimalFormat df = new DecimalFormat("#.##");
        JOptionPane.showMessageDialog(this,"A közönségszavazás eredménye:\nA: "
                        + df.format(percentages[0]) + "%\nB: " + df.format(percentages[1]) + "%\nC: "
                        + df.format(percentages[2]) + "%\nD: " + df.format(percentages[3]) + "%",
                "Közönségszavazás", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Telefonhívás segítség.
     * A segítség hívásakor egy ablak jelenik meg 30 másodpercig maximum.
     * @param game - a jelenlegi játék
     * @param gd - a megjelenített felület
     */
    public void call(Game game, GameDisplay gd) {
        game.setCall(false);
        gd.enableCallButton(false);
        JOptionPane call = new JOptionPane("30 másodperc áll rendelkezésére", JOptionPane.INFORMATION_MESSAGE);
        JDialog dialog = call.createDialog(null, "Hívás");
        dialog.setModal(false);
        dialog.setVisible(true);
        new Timer(30*1000, e -> dialog.setVisible(false)).start();
    }
}
