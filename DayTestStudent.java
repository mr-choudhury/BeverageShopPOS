import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @version 12/08/2022
 * @author Muhammad Choudhury
 */

class DayTestStudent {
	Day su, m, t, w, th, f, sa;
	
	@BeforeEach
	void setUp() throws Exception {
		su = Day.SUNDAY;
		m = Day.MONDAY;
		t = Day.TUESDAY;
		w = Day.WEDNESDAY;
		th = Day.THURSDAY;;
		f = Day.FRIDAY;
		sa = Day.SATURDAY;
	}

	@AfterEach
	void tearDown() throws Exception {
		su = m = t = w = th = f = sa = null;
	}

	@Test
	void test() {
		assertTrue(su.equals(Day.SUNDAY));
		assertTrue(m.equals(Day.MONDAY));
		assertTrue(t.equals(Day.TUESDAY));
		assertTrue(w.equals(Day.WEDNESDAY));
		assertTrue(th.equals(Day.THURSDAY));
		assertTrue(f.equals(Day.FRIDAY));
		assertTrue(sa.equals(Day.SATURDAY));
		assertFalse(sa.equals(Day.FRIDAY));
		assertFalse(su.equals(Day.WEDNESDAY));
		assertFalse(w.equals(Day.TUESDAY));
	}

}
