package usantatecla;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(Parameterized.class)
public class ParametrizedIsIntersectedTest {

    private static final double PRECISION = 0.1;
    private Min sutMin;
    private Max sutMax;
    private Min paramMin;
    private Max paramMax;

    /* salida de allpairs con: (min s = minimo simbolo, sut min s = SUT minimo simbolo...)
              min s     max s   min n    max n    sut min s   sut max s
            cerrado   cerrado   menor    menor      cerrado     cerrado
            abierto   abierto   igual    igual      abierto     abierto
                                mayor    mayor

            TEST CASES
            case     min s     max s  min n  max n  sut min s  sut max s  pairings
              1    cerrado   cerrado  menor  menor    cerrado    cerrado  15
              2    abierto   abierto  menor  igual    abierto    abierto  15
              3    abierto   cerrado  igual  menor    abierto    cerrado  11
              4    cerrado   abierto  igual  igual    cerrado    abierto  11
              5    cerrado   cerrado  mayor  mayor    abierto    abierto  11
              6    abierto   abierto  mayor  mayor    cerrado    cerrado  10
              7   ~cerrado   abierto  menor  menor   ~abierto    abierto  2
              8   ~abierto   cerrado  menor  igual   ~cerrado    cerrado  2
              9   ~cerrado  ~cerrado  igual  mayor   ~cerrado   ~abierto  1
              10  ~abierto  ~abierto  mayor  menor   ~cerrado   ~abierto  1
              11  ~cerrado  ~cerrado  mayor  igual   ~abierto   ~cerrado  1
              12  ~abierto  ~abierto  menor  mayor   ~abierto   ~cerrado  1
             */

    @Parameters()
    public static Collection<Object[]> parameters() {
        return Arrays
                .asList(new Object[][]{
                        {minCerrado(), maxCerrado(), menor(), menor(), minCerrado(), minCerrado(), false },
                        {minCerrado(), maxCerrado(), menor(), menor(), minCerrado(), maxCerrado(), false },
                        {minAbierto(), maxAbierto(), menor(), igual(), minAbierto(), maxAbierto(), false },
                        {minAbierto(), maxCerrado(), igual(), menor(), minAbierto(), maxCerrado(), false },
                        {minCerrado(), maxAbierto(), igual(), igual(), minCerrado(), maxAbierto(), true  },
                        {minCerrado(), maxCerrado(), mayor(), mayor(), minAbierto(), maxAbierto(), false },
                        {minAbierto(), maxAbierto(), mayor(), mayor(), minCerrado(), maxCerrado(), false },
                        {minCerrado(), maxAbierto(), menor(), menor(), minAbierto(), maxAbierto(), false },
                        {minAbierto(), maxCerrado(), menor(), igual(), minCerrado(), maxCerrado(), false },
                        {minCerrado(), maxCerrado(), igual(), mayor(), minCerrado(), maxAbierto(), false },
                        {minAbierto(), maxAbierto(), mayor(), menor(), minCerrado(), maxAbierto(), true  },
                        {minCerrado(), maxCerrado(), mayor(), igual(), minAbierto(), maxCerrado(), true  },
                        {minAbierto(), maxAbierto(), menor(), mayor(), minAbierto(), maxCerrado(), false },
                });
    }

    private boolean isIntersected;

    public ParametrizedIsIntersectedTest(
            Function<Double, Min> paramMinFn,
            Function<Double, Max> paramMaxFn,
            Function<Double, Double> minFn,
            Function<Double, Double> maxFn,
            Function<Double, Min> sutMinFn,
            Function<Double, Max> sutMaxFn,
            boolean isIntersected
    ) {
        double minValue = 1.2;
        double maxValue = 1.8;

        this.sutMin = sutMinFn.apply(minValue);
        this.sutMax = sutMaxFn.apply(maxValue);

        this.paramMin = paramMinFn.apply(minFn.apply(minValue));
        this.paramMax = paramMaxFn.apply(maxFn.apply(maxValue));

        this.isIntersected = isIntersected;
    }

    @Test
    public void testIsIncluded() {
        Interval sut = new Interval(sutMin, sutMax);
        Interval param = new Interval(paramMin, paramMax);
        assertEquals(this.isIntersected, sut.isIntersected(param));
    }

    private static Function<Double, Double> mayor() {
        return val -> val + PRECISION;
    }

    private static Function<Double, Double> menor() {
        return val -> val - PRECISION;
    }

    private static Function<Double, Double> igual() {
        return val -> val;
    }

    private static Function<Double, ClosedMin> minCerrado() {
        return ClosedMin::new;
    }

    private static Function<Double, Min> minAbierto() {
        return Min::new;
    }

    private static Function<Double, ClosedMax> maxCerrado() {
        return ClosedMax::new;
    }

    private static Function<Double, Max> maxAbierto() {
        return Max::new;
    }
}
