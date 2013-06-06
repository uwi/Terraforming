package net.aicomp.entity

import org.specs2.mutable._

class FieldSpecTest extends SpecificationWithJUnit {
  "Field" should {
    "return generated field with initial positions" in {
      val player1 = new Player
      val player2 = new Player
      val player3 = new Player
      val field = Field.generate(7, player1, player2, player3)
      def filterByPlayer(player: Player) = field.indices.filter(
        x => field(x) match {
          case InitialPosition(p) => p == player
          case _ => false
        })
      filterByPlayer(player1).length must_== 1
      filterByPlayer(player2).length must_== 1
      filterByPlayer(player3).length must_== 1
      field.indices.foreach(p => field(p) match {
        case InitialPosition(_) => ()
        case t => t must_== field(p.rotate120)
      })
    }
  }
}
