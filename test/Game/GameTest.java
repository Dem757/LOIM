package Game;

import java.io.FileNotFoundException;

import org.junit.*;

public class GameTest {
	Game g;
	@Before
	public void createGame() {
		try {
			g = new Game();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void teststartGame() {
		g.startGame();
		Assert.assertTrue(g.isSplitter());
		Assert.assertTrue(g.isCrowd());
		Assert.assertTrue(g.isCall());
		Assert.assertEquals(1, g.getRound());
		Assert.assertEquals(1, g.getcQuestion().getDiff());
	}
	
	@Test
	public void testNextRound() {
		g.startGame();
		g.nextRound();
		Assert.assertEquals(2, g.getRound());
		Assert.assertEquals(2, g.getcQuestion().getDiff());
	}

}
