package Display;

import java.io.Serializable;

/**
 * A dicsőséglistán szereplő játékosok adatai reprezentáló osztály.
 */
public class Highscore implements Serializable, Comparable<Highscore>{
    private String name;
    private String prize;

    public Highscore(String n, String p) {
        this.name = n;
        this.prize = p;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public String getPrize() {
        return prize;
    }

    /**
     * Adott játékos nyereménye stringből.
     * @return - a nyeremény összege
     */
    public int getPrizeInt() {
        return Integer.parseInt(prize.replace(" Ft", "").replace(".", ""));
    }

    @Override
    public String toString() {
        return name + " - " + prize;
    }

    /**
     * Két adott játékos nyereményének összehasonlítása.
     * @param o - az összehasonlítandó tárgy
     * @return az összehasonlítás eredménye.
     */
    @Override
    public int compareTo(Highscore o) {
        return o.getPrizeInt()-this.getPrizeInt();
    }


}
