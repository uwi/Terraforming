package net.aicomp.scene

import net.aicomp.entity.Game
import net.aicomp.entity.GameEnvironment
import net.aicomp.entity.GameSetting
import net.aicomp.entity.Player
import net.exkazuu.gameaiarena.gui.Scene

abstract class PlayerScene(nextScene: Scene[GameEnvironment], setting: GameSetting = GameSetting())
  extends AbstractScene {

  override def initialize() {
    describe("Enter player names")
    displayLine("Please enter three player names without spaces.")
  }

  override def runWithCommand(commandString: List[String]) = {
    if (commandString.length <= 0) {
      displayLine("Please enter a name.")
      this
    } else {
      val name :: _ = commandString
      game.currentPlayer.name = name
      if (game.changePlayerIndex() == 0) {
        displayLine(game.players.size + " players have joined the game. ("
          + game.players.map(_.name).mkString(", ") + ")")
        nextScene
      } else this
    }
  }
  
  override def runManipulator = Array(game.currentPlayer.startManipulator.run(game))
}
