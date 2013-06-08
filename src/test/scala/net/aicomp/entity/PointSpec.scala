package net.aicomp.entity

import org.specs2.mutable._
import org.scalacheck.Properties
import org.scalacheck.Prop.forAll

class PointSpec extends SpecificationWithJUnit {
  "Point" should {
    "calculate additions" in {
      Point(1, 1) + Point(2, -1) must_== Point(3, 0)
    }
    "calculate subtractions" in {
      Point(1, 3) - Point(-1, -1) must_== Point(2, 4)
    }
    "calculate multiplication" in {
      Point(2, 1) * 3 must_== Point(6, 3)
    }
    "calculate rotated point" in {
      Point(1, 0).rotate120 must_== Point(-1, 1)
      Point(-2, 1).rotate120 must_== Point(1, -2)
      Point(-3, -2).rotate120.rotate240 must_== Point(-3, -2)
    }
    "calculate distance between 2 points" in {
      // x
      Point(0, 1).distance(Point(3, -1)) must_== 3
      // y
      Point(1, -2).distance(Point(-1, 2)) must_== 4
      // x + y
      Point(1, 2).distance(Point(0, 0)) must_== 3
      // 0
      Point(-1, 3).distance(Point(-1, 3)) must_== 0
    }
    "determine within distance or not" in {
      Point(0, 1).within(1) must_== true
      Point(3, -2).within(2) must_== false
      Point(3, -2).within(3) must_== true
    }
    "return shortest path to" in {
      val field = new Field(7)
      Point(0, 1).shortestPathTo(Point(0, 1), field) must_==
        Some(List.empty[Direction])
      Point(0, 1).shortestPathTo(Point(0, 3), field) must_==
        Some(List(Direction.dr, Direction.dr))
      Point(2, -1).shortestPathTo(Point(0, 2), field).get.length must_==
        Point(2, -1).distance(Point(0, 2))
      Point(0, -1).shortestPathTo(Point(8, 0), field) must_== None
      Point(90, -1).shortestPathTo(Point(0, 0), field) must_== None

      val fieldWithObstacle = new Field(2)
      fieldWithObstacle.build(Point(0, 0), "b")
      fieldWithObstacle.build(Point(-1, 0), "b")
      Point(0, 1).shortestPathTo(Point(0, -1), fieldWithObstacle) must_==
        Some(List(Direction.ur, Direction.ul, Direction.l))
      Point(0, 0).shortestPathTo(Point(0, -1), fieldWithObstacle) must_== None

      val fieldWithWall = new Field(1)
      fieldWithWall.build(Point(-1, 0), "b")
      fieldWithWall.build(Point(0, 0), "b")
      fieldWithWall.build(Point(1, 0), "b")
      Point(0, 1).shortestPathTo(Point(0, -1), fieldWithWall) must_== None
      Point(-1, 1).shortestPathTo(Point(0, 1), fieldWithWall) must_==
        Some(List(Direction.r))
    }
    "return points within distance" in {
      Point.pointsWithin(1) must_== List(
        Point(-1, 0),
        Point(-1, 1),
        Point(0, -1),
        Point(0, 0),
        Point(0, 1),
        Point(1, -1),
        Point(1, 0))
      Point.pointsWithin(-1) must_== Nil
    }
  }
}