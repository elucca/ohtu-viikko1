package ohtuesimerkki;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class StatisticsTest {

    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri", "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    Statistics stats;

    @Before
    public void setUp() {
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }

    @Test
    public void correctPlayerIsFound() {
        Player found = stats.search("Semenko");
        assertEquals("Semenko", found.getName());
    }

    @Test
    public void nullReturnedIfPlayerNotFound() {
        assertNull(stats.search("Luukkainen"));
    }

    @Test
    public void playersOfATeamFound() {
        List<Player> found = stats.team("PIT");
        assertTrue(found.size() == 1);
        assertEquals("Lemieux", found.get(0).getName());
    }
    
    @Test
    public void topScorersFound() {
        List<Player> found = stats.topScorers(2);
        assertEquals(found.get(0).getName(), "Gretzky");
        assertEquals(found.get(1).getName(), "Lemieux");
    }

}
