package net.aicomp.scene

import org.specs2.mutable.SpecificationWithJUnit
import net.aicomp.entity.GameEnvironment
import net.aicomp.entity.Player
import net.aicomp.entity.Field
import net.aicomp.entity.Game

class PlayerSceneSpec extends SpecificationWithJUnit {
  "PlayerScene" should {
    "stores plyaer names ignoring wrong inputs" in new TestSceneInitializer {
      val nextScene = playerScene.acceptAll(env, "abc" :: "def" :: "ghi" :: Nil)
      g.players(0).name must_== "abc"
      g.players(1).name must_== "def"
      g.players(2).name must_== "ghi"
      nextScene must_== mainScene
    }
  }
}