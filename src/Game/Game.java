package Game;

import java.io.FileNotFoundException;

import Questions.Question;
import Questions.QuestionList;

/**
 * A játékot reprezentáló osztály.
 */
public class Game {
    /**
     * Az adott nyeremények körönként.
     */
    private final String[] prizes = {"5.000 Ft", "10.000 Ft", "20.000 Ft", "50.000 Ft", "100.000 Ft", "250.000 Ft",
            "500.000 Ft", "750.000 Ft", "1.000.000 Ft", "1.500.000 Ft", "2.000.000 Ft", "5.000.000 Ft",
            "10.000.000 Ft", "15.000.000 Ft", "25.000.000 Ft", "50.000.000 Ft"};
    private boolean splitter;
    private boolean crowd;
    private boolean call;
    private int round;
    private Question qstn;
    private final QuestionList ql;

    /**
     * Játék kezdetekor beolvassa a kérdéseket.
     * questions.json a kérdéseket tartalmazó fájl neve.
     * @throws FileNotFoundException 
     */
    public Game() throws FileNotFoundException {
        ql = new QuestionList("questions.json");
    }

    /**
     * Játék elindítása.
     * Alapadatok beállítása.
     */
    public void startGame() {
        this.splitter = true;
        this.crowd = true;
        this.call = true;
        this.round = 1;
        this.qstn = ql.getQuestion(round);
    }
    public boolean isSplitter() {
        return splitter;
    }

    public void setSplitter(boolean spl) {
        this.splitter = spl;
    }

    public boolean isCrowd() {
        return crowd;
    }

    public void setCrowd(boolean crowd) {
        this.crowd = crowd;
    }

    public boolean isCall() {
        return call;
    }

    public void setCall(boolean call) {
        this.call = call;
    }

    public int getRound() {
        return round;
    }

    public String getPrize(int cround) {
        return prizes[cround-1];
    }
    public Question getcQuestion() {
        return qstn;
    }

    /**
     * Helyes válasz karaktere az adott kérdéshez.
     * @return - a helyes válasz
     */
    public char getCorrectAns() {
        return qstn.getAns();
    }

    /**
     * Következő kör indítása.
     */
    public void nextRound() {
        round++;
        qstn = ql.getQuestion(round);
    }
}
