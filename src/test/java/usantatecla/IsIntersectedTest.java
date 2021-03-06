package usantatecla;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IsIntersectedTest {

    // si interseccion -> afirmativo
    @Test
    public void givenAnIntervalIntersectAnotherIntervalReturnTrue() {

        Interval sut = new Interval(new Min(1), new Max(4));

        boolean result = sut.isIntersected(new Interval(new Min(2), new Max(3)));

        assertTrue(result);
    }

    // si no hay interseccion -> negativo
    @Test
    public void givenAnIntervalDoesntIntersectAnotherIntervalReturnFalse() {
        Interval sut = new Interval(new Min(1), new Max(4));

        boolean result = sut.isIntersected(new Interval(new Min(5), new Max(7)));

        assertFalse(result);
    }

    // min inc. max inc. true
    @Test
    public void givenMinIsIncludedAndMaxIsIncludedThenIsIntersectedReturnTrue() {
        Interval sut = new Interval(new ClosedMin(1), new ClosedMax(4));

        boolean result = sut.isIntersected(new Interval(new ClosedMin(1), new ClosedMax(4)));

        assertTrue(result);
    }

    // min inc. max exc. false
    @Test
    public void givenMinIsIncludedAndMaxIsExcludedThenIsIntersectedReturnFalse() {
        Interval sut = new Interval(new ClosedMin(1), new ClosedMax(4));

        boolean result = sut.isIntersected(new Interval(new ClosedMin(1), new ClosedMax(5)));

        assertFalse(result);
    }

    // min exc. max inc. false
    @Test
    public void givenMinIsExcludedAndMaxIsIncludedThenIsIntersectedReturnFalse() {
        Interval sut = new Interval(new ClosedMin(1), new ClosedMax(4));

        boolean result = sut.isIntersected(new Interval(new ClosedMin(0), new ClosedMax(4)));

        assertFalse(result);
    }

    // min exc. max exc. false
    @Test
    public void givenMinIsExcludedAndMaxIsExcludedThenIsIntersectedReturnFalse() {
        Interval sut = new Interval(new ClosedMin(1), new ClosedMax(4));

        boolean result = sut.isIntersected(new Interval(new ClosedMin(0), new ClosedMax(5)));

        assertFalse(result);
    }
}
