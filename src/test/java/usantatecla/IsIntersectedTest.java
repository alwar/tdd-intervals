package usantatecla;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IsIntersectedTest {

    @Test
    public void givenAnIntervalIntersectAnotherIntervalReturnTrue() {

        Interval sut = new Interval(new Min(1), new Max(4));

        boolean result = sut.isIntersected(new Interval(new Min(2), new Max(3)));

        assertTrue(result);
    }
}
