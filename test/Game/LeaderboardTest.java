package Game;

import org.junit.*;

import Display.*;

public class LeaderboardTest {

	@Test
	public void testCreateLeaderboard() {
		Leaderboard lb = new Leaderboard();
		Assert.assertEquals(0, lb.getLeaderboard().size());
	}
	
	@Test
	public void testAdd() {
		Leaderboard lb = new Leaderboard();
		lb.add("Példa Név", "Példa összeg");
		Assert.assertEquals("1. Példa Név - Példa összeg\n", lb.getLeaderboardP());
	}
	
	@Test
	public void testHighscoreCompare() {
		Highscore hs1 = new Highscore("Példa1", "10.000 Ft");
		Highscore hs2 = new Highscore("Példa2", "20.000 Ft");
		Assert.assertTrue(hs1.getPrizeInt() < hs2.getPrizeInt());
	}
	
	@Test
	public void testFullLeaderboard() {
		Leaderboard lb = new Leaderboard();
		lb.add("Példa1", "10.000 Ft");
		lb.add("Példa2", "9.000 Ft");
		lb.add("Példa3", "8.000 Ft");
		lb.add("Példa4", "7.000 Ft");
		lb.add("Példa5", "6.000 Ft");
		lb.add("Példa6", "5.000 Ft");
		lb.add("Példa7", "4.000 Ft");
		lb.add("Példa8", "3.000 Ft");
		lb.add("Példa9", "2.000 Ft");
		lb.add("Példa10", "10.000 Ft");
		//Végül hozzáadunk egyet ami nagyobb mint az utolsó
		lb.add("Példa1", "5.500 Ft");
		Assert.assertEquals("1. Példa1 - 10.000 Ft\n"
				+ "2. Példa10 - 10.000 Ft\n"
				+ "3. Példa2 - 9.000 Ft\n"
				+ "4. Példa3 - 8.000 Ft\n"
				+ "5. Példa4 - 7.000 Ft\n"
				+ "6. Példa5 - 6.000 Ft\n"
				+ "7. Példa1 - 5.500 Ft\n"
				+ "8. Példa6 - 5.000 Ft\n"
				+ "9. Példa7 - 4.000 Ft\n"
				+ "10. Példa8 - 3.000 Ft\n", lb.getLeaderboardP());
	}

}
