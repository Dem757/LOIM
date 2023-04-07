package Questions;

/**
 * Kérdést reprezentáló osztály.
 */
public class Question {
    private int diff;
    private String question;
    private String a;
    private String b;
    private String c;
    private String d;
    private char ans;

    public Question(int diff, String q, String a, String b, String c, String d, char ans) {
        this.diff = diff;
        this.question = q;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.ans = ans;
    }

    public int getDiff() {
        return diff;
    }

    public String getQuestion() {
        return question;
    }

    public String getA() {
        return a;
    }

    public String getB() {
        return b;
    }

    public String getC() {
        return c;
    }

    public String getD() {
        return d;
    }

    public char getAns() {
        return ans;
    }
    //Csak teszt
    /*public void MakeQ() {
        System.out.println("Nehézség: " + this.diff);
        System.out.println("Kérdés: " + this.question);
        System.out.println("A: " + this.a);
        System.out.println("B: " + this.b);
        System.out.println("C: " + this.c);
        System.out.println("D: " + this.d);
        System.out.println("Válasz: " + this.ans);
    }*/
}

