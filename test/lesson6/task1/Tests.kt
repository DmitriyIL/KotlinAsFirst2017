package lesson6.task1

import lesson7.task2.fifteenGameMoves
import lesson8.task1.top20Words
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

class Tests {
    @Test
    @Tag("Example")
    fun pointDistance() {
        assertEquals(0.0, Point(0.0, 0.0).distance(Point(0.0, 0.0)), 1e-5)
        assertEquals(5.0, Point(3.0, 0.0).distance(Point(0.0, 4.0)), 1e-5)
        assertEquals(50.0, Point(0.0, -30.0).distance(Point(-40.0, 0.0)), 1e-5)
    }

    @Test
    @Tag("Example")
    fun halfPerimeter() {
        assertEquals(6.0, Triangle(Point(0.0, 0.0), Point(0.0, 3.0), Point(4.0, 0.0)).halfPerimeter(), 1e-5)
        assertEquals(2.0, Triangle(Point(0.0, 0.0), Point(0.0, 1.0), Point(0.0, 2.0)).halfPerimeter(), 1e-5)
    }

    @Test
    @Tag("Example")
    fun triangleArea() {
        assertEquals(6.0, Triangle(Point(0.0, 0.0), Point(0.0, 3.0), Point(4.0, 0.0)).area(), 1e-5)
        assertEquals(0.0, Triangle(Point(0.0, 0.0), Point(0.0, 1.0), Point(0.0, 2.0)).area(), 1e-5)
    }

    @Test
    @Tag("Example")
    fun triangleContains() {
        assertTrue(Triangle(Point(0.0, 0.0), Point(0.0, 3.0), Point(4.0, 0.0)).contains(Point(1.5, 1.5)))
        assertFalse(Triangle(Point(0.0, 0.0), Point(0.0, 3.0), Point(4.0, 0.0)).contains(Point(2.5, 2.5)))
    }

    @Test
    @Tag("Example")
    fun segmentEquals() {
        val first = Segment(Point(1.0, 2.0), Point(3.0, 4.0))
        val second = Segment(Point(1.0, 2.0), Point(3.0, 4.0))
        val third = Segment(Point(3.0, 4.0), Point(1.0, 2.0))
        assertEquals(first, second)
        assertEquals(second, third)
        assertEquals(third, first)
    }

    private fun approxEquals(expected: Line, actual: Line, delta: Double): Boolean = Math.abs(expected.angle - actual.angle) <= delta && Math.abs(expected.b - actual.b) <= delta

    private fun assertApproxEquals(expected: Line, actual: Line, delta: Double = Math.ulp(10.0)) {
        assertTrue(approxEquals(expected, actual, delta))
    }

    private fun assertApproxNotEquals(expected: Line, actual: Line, delta: Double = Math.ulp(10.0)) {
        assertFalse(approxEquals(expected, actual, delta))
    }

    @Test
    @Tag("Example")
    fun lineEquals() {
        run {
            val first = Line(Point(0.0, 0.0), 0.0)
            val second = Line(Point(3.0, 0.0), 0.0)
            val third = Line(Point(-5.0, 0.0), 0.0)
            val fourth = Line(Point(3.0, 1.0), 0.0)
            assertApproxEquals(first, second)
            assertApproxEquals(second, third)
            assertApproxEquals(third, first)
            assertApproxNotEquals(fourth, first)
        }
        run {
            val first = Line(Point(0.0, 0.0), Math.PI / 2)
            val second = Line(Point(0.0, 3.0), Math.PI / 2)
            val third = Line(Point(0.0, -5.0), Math.PI / 2)
            val fourth = Line(Point(1.0, 3.0), Math.PI / 2)
            assertApproxEquals(first, second)
            assertApproxEquals(second, third)
            assertApproxEquals(third, first)
            assertApproxNotEquals(fourth, first)
        }
        run {
            val first = Line(Point(0.0, 0.0), Math.PI / 4)
            val second = Line(Point(3.0, 3.0), Math.PI / 4)
            val third = Line(Point(-5.0, -5.0), Math.PI / 4)
            val fourth = Line(Point(3.00001, 3.0), Math.PI / 4)
            assertApproxEquals(first, second)
            assertApproxEquals(second, third)
            assertApproxEquals(third, first)
            assertApproxNotEquals(fourth, first)
        }
    }

    @Test
    @Tag("Example")
    fun triangleEquals() {
        val first = Triangle(Point(0.0, 0.0), Point(3.0, 0.0), Point(0.0, 4.0))
        val second = Triangle(Point(0.0, 0.0), Point(0.0, 4.0), Point(3.0, 0.0))
        val third = Triangle(Point(0.0, 4.0), Point(0.0, 0.0), Point(3.0, 0.0))
        val fourth = Triangle(Point(0.0, 4.0), Point(0.0, 3.0), Point(3.0, 0.0))
        assertEquals(first, second)
        assertEquals(second, third)
        assertEquals(third, first)
        assertNotEquals(fourth, first)
    }

    @Test
    @Tag("Easy")
    fun circleDistance() {
        assertEquals(0.0, Circle(Point(0.0, 0.0), 1.0).distance(Circle(Point(1.0, 0.0), 1.0)), 1e-5)
        assertEquals(0.0, Circle(Point(0.0, 0.0), 1.0).distance(Circle(Point(0.0, 2.0), 1.0)), 1e-5)
        assertEquals(1.0, Circle(Point(0.0, 0.0), 1.0).distance(Circle(Point(-4.0, 0.0), 2.0)), 1e-5)
        assertEquals(2.0 * Math.sqrt(2.0) - 2.0, Circle(Point(0.0, 0.0), 1.0).distance(Circle(Point(2.0, 2.0), 1.0)), 1e-5)
    }

    @Test
    @Tag("Trivial")
    fun circleContains() {
        val center = Point(1.0, 2.0)
        assertTrue(Circle(center, 1.0).contains(center))
        assertFalse(Circle(center, 2.0).contains(Point(0.0, 0.0)))
        assertTrue(Circle(Point(0.0, 3.0), 5.01).contains(Point(-4.0, 0.0)))
    }

    @Test
    @Tag("Normal")
    fun diameter() {
        val p1 = Point(0.0, 0.0)
        val p2 = Point(1.0, 4.0)
        val p3 = Point(-2.0, 2.0)
        val p4 = Point(3.0, -1.0)
        val p5 = Point(-3.0, -2.0)
        val p6 = Point(0.0, 5.0)
        assertEquals(Segment(p5, p6), diameter(p1, p2, p3, p4, p5, p6))
        assertEquals(Segment(p4, p6), diameter(p1, p2, p3, p4, p6))
        assertEquals(Segment(p3, p4), diameter(p1, p2, p3, p4))
        assertEquals(Segment(p2, p4), diameter(p1, p2, p4))
        assertEquals(Segment(p1, p4), diameter(p1, p4))
    }

    @Test
    @Tag("Easy")
    fun circleByDiameter() {
        assertEquals(Circle(Point(0.0, 1.0), 1.0), circleByDiameter(Segment(Point(0.0, 0.0), Point(0.0, 2.0))))
        assertEquals(Circle(Point(2.0, 1.5), 2.5), circleByDiameter(Segment(Point(4.0, 0.0), Point(0.0, 3.0))))
    }

    @Test
    @Tag("Normal")
    fun crossPoint() {
        assertTrue(Point(2.0, 3.0).distance(Line(Point(2.0, 0.0), Math.PI / 2).crossPoint(Line(Point(0.0, 3.0), 0.0))) < 1e-5)
        assertTrue(Point(2.0, 2.0).distance(Line(Point(0.0, 0.0), Math.PI / 4).crossPoint(Line(Point(0.0, 4.0), 3 * Math.PI / 4))) < 1e-5)
        val p = Point(1.0, 3.0)
        assertTrue(p.distance(Line(p, 1.0).crossPoint(Line(p, 2.0))) < 1e-5)
    }

    @Test
    @Tag("Normal")
    fun lineBySegment() {
        assertApproxEquals(Line(Point(0.0, 0.0), 0.0), lineBySegment(Segment(Point(0.0, 0.0), Point(7.0, 0.0))))
        assertApproxEquals(Line(Point(0.0, 0.0), Math.PI / 2), lineBySegment(Segment(Point(0.0, 0.0), Point(0.0, 8.0))))
        assertApproxEquals(Line(Point(1.0, 1.0), Math.PI / 4), lineBySegment(Segment(Point(1.0, 1.0), Point(3.0, 3.0))))

    }

    @Test
    @Tag("Normal")
    fun lineByPoint() {
        assertApproxEquals(Line(Point(0.0, 0.0), Math.PI / 2), lineByPoints(Point(0.0, 0.0), Point(0.0, 2.0)))
        assertApproxEquals(Line(Point(1.0, 1.0), Math.PI / 4), lineByPoints(Point(1.0, 1.0), Point(3.0, 3.0)))
        assertApproxEquals(Line(Point(0.0, 0.0), Math.PI * 3 / 4), lineByPoints(Point(0.0, 0.0), Point(-4.0, 4.0)))
    }

    @Test
    @Tag("Hard")
    fun bisectorByPoints() {
        assertApproxEquals(Line(Point(2.0, 0.0), Math.PI / 2), bisectorByPoints(Point(0.0, 0.0), Point(4.0, 0.0)))
        assertApproxEquals(Line(Point(1.0, 2.0), 0.0), bisectorByPoints(Point(1.0, 5.0), Point(1.0, -1.0)))
        assertApproxEquals(Line(Point(-2.0, 2.0), Math.PI / 4), bisectorByPoints(Point(0.0, 0.0), Point(-4.0, 4.0)))
    }

    @Test
    @Tag("Normal")
    fun findNearestCirclePair() {
        val c1 = Circle(Point(0.0, 0.0), 1.0)
        val c2 = Circle(Point(3.0, 0.0), 5.0)
        val c3 = Circle(Point(-5.0, 0.0), 2.0)
        val c4 = Circle(Point(0.0, 7.0), 3.0)
        val c5 = Circle(Point(0.0, -6.0), 4.0)
        assertEquals(Pair(c1, c5), findNearestCirclePair(c1, c3, c4, c5))
        assertEquals(Pair(c2, c4), findNearestCirclePair(c2, c4, c5))
        assertEquals(Pair(c1, c2), findNearestCirclePair(c1, c2, c4, c5))
    }

    @Test
    @Tag("Hard")
    fun circleByThreePoints() {
        /*val result2 = circleByThreePoints(Point(-999.6214354713483, -999.6369163117688), Point(-632.0,-632.0), Point(-999.4924891025614, -632.0))
        assertTrue(result2.center.distance(Point(-815.9999999999999, -815.5056063650301)) < 1e-5)
        assertEquals(259.8659415302386, result2.radius, 1e-5)*/
        val result = circleByThreePoints(Point(5.0, 0.0), Point(3.0, 4.0), Point(0.0, -5.0))
        assertTrue(result.center.distance(Point(0.0, 0.0)) < 1e-5)
        assertEquals(5.0, result.radius, 1e-5)
        val result1 = circleByThreePoints(Point(-999.1008583476022, -1000.0), Point(-632.0, -632.0), Point(-999.0954203876826, -632.0))
        assertTrue(result1.center.distance(Point(-815.5477101938412, -816.0027123366227)) < 1e-5)
        assertEquals(259.897595342158, result1.radius, 1e-5)
    }

    @Test
    @Tag("Impossible")
    fun minContainingCircle() {
        val p1 = Point(0.0, 0.0)
        val p2 = Point(1.0, 4.0)
        val p3 = Point(-2.0, 2.0)
        val p4 = Point(3.0, -1.0)
        val p5 = Point(-3.0, -2.0)
        val p6 = Point(0.0, 5.0)
        val result = minContainingCircle(p1, p2, p3, p4, p5, p6)
        assertEquals(4.0, result.radius, 0.02)
        for (p in listOf(p1, p2, p3, p4, p5, p6)) {
            assertTrue(result.contains(p))
        }

        val p1e = Point(-632.0, -999.1974327615777)
        val p2e = Point(-999.2136250294625, -999.3587496316869)
        val p3e = Point(-632.0, -999.2317120032715)
        val p4e = Point(-1000.0, -999.6417959714103)
        val p5e = Point(-999.3904985733098, -999.0089536214418)
        val p6e = Point(-632.0, -999.630038194856)
        val p7e = Point(-999.3869497252677, -999.4027379763162)
        val p8e = Point(-999.8937111910498, -999.5283762256345)
        val p9e = Point(-999.8600368832643, -999.7201277466286)
        val p10e = Point(-999.5957858074545, -1000.0)
        val p11e = Point(-632.0, -1000.0)
        val p12e = Point(-999.2911944366616, -1000.0)
        val p13e = Point(-1000.0, -999.2401791900609)
        val p14e = Point(-999.3238353972721, -1000.0)
        val resulte = minContainingCircle(p1e, p2e, p3e, p4e, p5e, p6e, p7e, p8e, p9e, p10e, p11e, p12e, p13e, p14e)
        assertEquals(184.0003934472205, resulte.radius, 0.02)
        for (p in listOf(p1e, p2e, p3e, p4e, p5e, p6e, p7e, p8e, p9e, p10e, p11e, p12e, p13e, p14e)) {
            assertTrue(resulte.contains(p))
        }
    }
}