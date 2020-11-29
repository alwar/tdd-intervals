package usantatecla;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IsIntersectedTest {

    @Test
    public void givenAnIntervalIntersectAnotherIntervalReturnTrue() {

        Interval sut = new Interval(new Min(1), new Max(4));

        boolean result = sut.isIntersected(new Interval(new Min(2), new Max(3)));

        assertTrue(result);
    }

    @Test
    public void givenAnIntervalDoesntIntersectAnotherIntervalReturnFalse() {
        Interval sut = new Interval(new Min(1), new Max(4));

        boolean result = sut.isIntersected(new Interval(new Min(5), new Max(7)));

        assertFalse(result);
    }
}
