package net.aicomp.entity

import jp.ac.waseda.cs.washi.gameaiarena.gui.Renderer

// new Point class for rendering
case class OrthogonalPoint(orthX: Int, orthY: Int) {
  // TODO should be defined in a property
  val defaultX = 500
  val defaultY = 250
  // TODO should be defined in a property
  val pointSize = 32
  val squadSizeX = 10
  val squadSizeY = 9
  val numSizeX = 6
  val numSizeY = 9

  implicit def pointToOrthogonalPoint(p:Point):OrthogonalPoint = {
    transToOrthogonal(p.x, p.y)
  }
  
  // transformation point coordinate to orthogonal coordinate
  def transToOrthogonal(pointX: Int, pointY: Int) = {
    // TODO should be defined in a property
    // TODO should be final
    val centerX = defaultX - pointSize / 2
    val centerY = defaultY - pointSize / 2

    val orthX = centerX + pointSize * pointX + (pointSize / 2) * pointY
    val orthY = centerY + (3 * pointSize / 4) * pointY

    OrthogonalPoint(orthX, orthY)
  }
}

object OrthogonalPoint {
}
