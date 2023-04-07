package Game;

import java.io.FileNotFoundException;

import org.junit.*;
import Questions.*;

public class QuestionTest {
	QuestionList ql;
	@Before
	public void createList() throws FileNotFoundException {
		ql = new QuestionList("questions.json");
	}
	@Test
	public void testQuestionCreate() {
		Question q = new Question(3, "Kérdés", "A", "B", "C", "D", 'A');
		Assert.assertEquals(3, q.getDiff());
		Assert.assertEquals("Kérdés", q.getQuestion());
		Assert.assertEquals("A", q.getA());
		Assert.assertEquals("B", q.getB());
		Assert.assertEquals("C", q.getC());
		Assert.assertEquals("D", q.getD());
		Assert.assertEquals('A', q.getAns());
	}
	@Test
	public void testQuestionbydiff() {
		Question q = ql.getQuestion(4);
		Assert.assertEquals(4, q.getDiff());
	}
	@Test
	public void testWrongFilename() throws FileNotFoundException{
		Assert.assertThrows(FileNotFoundException.class, () -> {
			new QuestionList("rossz.json");
		});
	}

}
