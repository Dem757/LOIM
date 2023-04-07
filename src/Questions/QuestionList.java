package Questions;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.*;
import java.util.*;

/**
 * A kérdésadatbázis reprezentáló osztály.
 */
public class QuestionList {
    ArrayList<Question> questionsar = new ArrayList<>();

    /**
     * Létrehozza a kérdések listáját az adott JSON fájlból.
     * @param fn - a kérdéseket tartalmazó fájl neve.
     * @throws FileNotFoundException 
     */
    public QuestionList(String fn) throws FileNotFoundException {
        JSONParser parser = new JSONParser();
        try {
            FileReader reader = new FileReader(fn);
            Object obj = parser.parse(reader);
            JSONArray questionlist = (JSONArray) obj;
            questionlist.forEach( emp -> parseQuestionObject( (JSONObject) emp ) );
        }catch (Exception ex) {
            throw new FileNotFoundException();
        }
    }

    /**
     * A kérdések kinyerése és hozzáadása a listához.
     * @param questions - a kérdés objektum
     */
    public void parseQuestionObject(JSONObject questions) {
        String qstion = (String) questions.get("Question");

        String A = (String) questions.get("A");

        String B = (String) questions.get("B");

        String C = (String) questions.get("C");

        String D = (String) questions.get("D");

        String ans = (String) questions.get("Answer");

        long diff = (long) questions.get("Difficulty");

        questionsar.add(new Question((int) diff, qstion, A, B, C, D, ans.charAt(0)));
    }

    /**
     * Random kérdés választása a megadott nehézség alapján.
     * @param currentdiff - nehézségi szint
     * @return - a talált kérdés
     */
    public Question getQuestion(int currentdiff) {
        List<Question> rightQuestions = new ArrayList<>();
        for (Question q : questionsar)
            if (q.getDiff() == currentdiff)
                rightQuestions.add(q);
        return rightQuestions.get(new Random().nextInt(rightQuestions.size()));
    }
}
