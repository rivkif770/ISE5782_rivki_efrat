package geometries;

import org.junit.jupiter.api.Test;
import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import primitives.*;
import java.util.List;


class TubeTest {
    /**
     *Test method for {@link geometries.Tube#getNormal(primitives.Point)}.
     */
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Ray ray = new Ray(new Point(0,1,0), new Vector(0,1,0));
        Tube tb = new Tube(2, ray);
        assertEquals(tb.getNormal(new Point(2,0,0)) ,new Vector(1,0,0),
                "Normal abnormality");

        // =============== Boundary Values Tests ==================
        // TC11: test When connecting the point to the horn head of the cylinder axis produces a right angle with the axis
        Tube tube = new Tube(1,new Ray(new Point(0,0,0),new Vector(1,0,0)));
        assertEquals(tube.getNormal(new Point(0,1,0)),new Vector(0,1,0),
                "The point in front of the head of the foundation");
    }

}