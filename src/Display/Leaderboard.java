package Display;

import java.io.Serializable;
import java.util.*;


/**
 *A dicsőséglistáért felelős osztály.
 */
public class Leaderboard implements Serializable{
    private ArrayList<Highscore> leaderboard;

    /**
     * Új dicsőséglista készítése.
     */
    public Leaderboard() {
        leaderboard = new ArrayList<>();
    }

    public ArrayList<Highscore> getLeaderboard() {
        return leaderboard;
    }

    /**
     * A dicsőséglista listázása.
     * @return - a dicsőséglista sorokba tördelve, a helyezésekkel megjelölve
     */
    public String getLeaderboardP() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < leaderboard.size(); i++){
            sb.append((i+1)+". " + leaderboard.get(i).toString() + "\n");
        }
        return sb.toString();
    }

    /**
     * Hozzáad egy új játékost a dicsőséglistához. Amennyiben tele van, tehát 10-nél több akkor csak abban az
     * esetben adja hozzá ha jobb eredményt ért el a 10-től.
     * @param n - A játékos neve
     * @param p - A játkos által elért nyeremény összeg
     */
    public void add(String n, String p) {
        Highscore h = new Highscore(n, p);
        //Csak a top 10-et tároljuk
        if(leaderboard.size() == 10 && leaderboard.get(9).getPrizeInt() < h.getPrizeInt()) {
            leaderboard.remove(9);
        }
        leaderboard.add(h);
        Collections.sort(leaderboard);
    }
}
